# FinanceAI
## Nota Maestra del Proyecto
*Documentación Técnica, Auditoría de Código y Hoja de Ruta — Documento único de referencia para el equipo y para asistentes de IA*

**Versión 5 — Actualizado: 14 de agosto de 2026**
Stack objetivo: Java 21 + Spring Boot 4.1.0 (backend) · Python 3.12 + Flask (frontend) · Python 3.11 + FastAPI/scikit-learn (motor de IA)
Basado en re-auditoría **verificada línea por línea con herramientas** (`grep -n '<file path='` + `sed -n '/<file path="...">/,/<\/file>/p'`) contra el nuevo snapshot `financeai.md`
*Proyecto Hackathon No Country / ONE (Oracle Next Education – Alura)*
*Este documento reemplaza a la v4 (08 de agosto de 2026) como fuente única de verdad.*

---

## 0. Cómo Usar Este Documento
Fuente única de verdad del proyecto FinanceAI. Convención de identificadores sin cambios: **AUD-XX** para hallazgos técnicos (Sección 6), **TASK-XXX** para tareas de backlog (Sección 10). Se conservan AUD-01 a AUD-26 y TASK-001 a TASK-041 de la v4; los hallazgos y tareas nuevos de esta re-auditoría continúan desde **AUD-27 / TASK-042**.

**Estados:** 🟢/✅ Completado · 🟡 Parcial / con deuda técnica · 🔴 Pendiente · ⚠️ Bloqueado o riesgoso.

### 0.1 ⚠️ Qué cambió realmente en este snapshot (léase antes que el resto del documento)
El snapshot `financeai.md` recibido para esta pasada **ya no contiene el frontend vanilla HTML/Bootstrap/JS** (`index.html`, `dashboard.html`, `js/auth.js`, `js/dashboard.js`, `js/api.js`) sobre el que se auditaron AUD-03, AUD-18, AUD-19 y AUD-23 a AUD-26 en la v4. Verificado con `grep -E 'path="frontend/(index\.html|dashboard\.html|js/)'`: **cero coincidencias**. Esos archivos fueron reemplazados por completo por una aplicación **Flask** (`frontend/app/`) que consume el backend Java desde el servidor, no desde el navegador.

Esto no es una limitación del snapshot — es el estado real del repositorio: el pivote de frontend mencionado como "en curso" ya se completó. Como consecuencia:
* Casi toda la familia de hallazgos de *casing* camelCase/snake_case (AUD-23, AUD-24, AUD-19) **desaparece de raíz**, porque los payloads ahora se escriben a mano en Python con las claves exactas que espera el backend, en vez de construirse en JS con convención propia.
* El almacenamiento del JWT pasó de `localStorage` (legible por JS, vulnerable a robo por XSS) a una cookie de sesión de Flask con `HttpOnly=True` — una mejora de seguridad real, no cosmética.
* `mock-api/` y la copia duplicada de `data-science/` en la raíz del repo **ya no existen** (verificado: `grep -c 'path="mock-api'` y `grep -c 'path="data-science/main.py"'` devuelven `0`).
* A cambio, la reescritura introdujo (o dejó expuestos) **hallazgos nuevos que no existían en el análisis de la v4**, el más importante de los cuales (AUD-27) es hoy el bloqueador único más severo del camino crítico — ver Sección 6.3.

### 0.2 Qué Cambió Desde la v4 (Resumen Ejecutivo)
* **23 de los 26 hallazgos de la v4 quedan resueltos** (algunos por corrección directa, otros porque el archivo que los originaba ya no existe). Balance nuevo: **31 hallazgos totales, 23 resueltos · 1 parcial · 7 pendientes.**
* **AUD-13, AUD-15, AUD-16, AUD-17, AUD-05** — quedaban parciales o abiertos en la v4 — **se confirman resueltos** con evidencia de código: `JwtUtil` sin fallback hardcodeado, `IAClient` apuntando a `/analisis-financiero` (coincide con el endpoint real de `main.py`), contrato de campos del motor de IA alineado con `AnalisisResponse`, y `mock-api`/duplicado de `data-science` eliminados del repositorio.
* **AUD-18, AUD-19, AUD-23, AUD-24, AUD-25** — quedan resueltas por vía arquitectónica: el problema que describían (frontend vanilla JS desconectado o con *casing* roto) ya no aplica porque ese frontend fue reemplazado.
* Un hallazgo del backlog de gaps que el PM traía pendiente — la ausencia de `rango_ahorro` y `nivel_endeudamiento` en `HistorialAnalisisResponse` — **está resuelta**: ambos campos están presentes con `@JsonProperty` correcto.
* **5 hallazgos nuevos** (AUD-27 a AUD-31), producto de esta pasada. El más importante, **AUD-27**, es una anotación JPA (`nullable = false` en `TransactionEntity.tipo`) que nunca se puebla en ningún flujo de creación de transacciones — con alta probabilidad bloquea la creación de transacciones a nivel de persistencia, lo cual bloquea a su vez el análisis financiero (que exige al menos una transacción registrada).
* **AUD-02 y AUD-22 siguen abiertos**, sin cambios respecto a la v4 — y esta pasada aporta evidencia más fuerte de que AUD-02 es un riesgo real, no teórico: el propio código de `main.py` compara `perfil_str == "RIESGOSO"`, lo que sugiere fuertemente que esa es la etiqueta real que emite el modelo para el perfil de riesgo — valor que el enum Java `PerfilFinanciero` no reconoce (solo admite `RIESGO`).

**Conclusión para el PM:** la reescritura a Flask fue, en términos de este backlog, la corrección de mayor apalancamiento que ha tenido el proyecto — eliminó de un solo golpe casi toda la familia de bugs de *casing* que dominaba la v4. El foco ahora debe moverse del frontend al backend: **AUD-27 es un fix de una línea con el mayor impacto posible** (sin él, no hay demo end-to-end posible), y **AUD-02**/AUD-22 dependen de una conversación con Data Science para confirmar el vocabulario exacto del modelo — no de más código exploratorio de este lado.

---

## 1. Visión General del Proyecto
Sin cambios respecto a versiones anteriores (descripción, objetivos del MVP, equipo). El stack de frontend documentado en la Sección 2 se actualiza: ya no es Vue.js/vanilla JS (aspiracional en el README original), sino **Flask** consumiendo el backend Java vía REST server-to-server.

---

## 2. Arquitectura Real del Sistema

### 2.1 Módulos del Monorepo (actualizado v5)
| Módulo | Estado v5 | Nota |
| :--- | :--- | :--- |
| `backend/` | 🟡 Sólido, con un bloqueador puntual nuevo | Casi todo resuelto; `TransactionEntity.tipo` (AUD-27) es el único bloqueador funcional real que queda. |
| `data-science/modeloFinanceAI/` | 🟡 Alcanzable y con contrato de campos correcto, riesgo de valor de enum | Único motor de IA (el duplicado y `mock-api` fueron retirados). Ruta y nombres de campo correctos; el valor exacto de `perfil_financiero` que emite el modelo (AUD-02) sigue sin confirmarse. |
| `frontend/` (Flask) | 🟢 Reescrito y funcional en su mayoría | `app/routes`, `app/services/financeai_api.py`, `app/templates` — arquitectura server-side limpia, con normalización defensiva de payloads y manejo centralizado de errores HTTP. Le falta protección CSRF activa (AUD-28) y no está aún en `docker-compose.yml` (AUD-29). |
| `mock-api/` | — Retirado | Ya no existe en el repositorio. Cierra TASK-029 / AUD-05 / AUD-17. |
| `data-science/` (raíz, copia duplicada) | — Retirado | Ya no existe en el repositorio. |

### 2.2 Flujo de Comunicación (actualizado v5)
El flujo cambia de forma relevante respecto a v3/v4: ya no hay llamadas desde JavaScript en el navegador al backend Java. Ahora:

```
Navegador → Flask (server-side, sesión httponly) → Spring Boot API (Bearer JWT) → PostgreSQL
                                                   → RestClient → FastAPI (motor de IA) → modelos .pkl
```

Flask guarda el JWT del backend en `session["access_token"]` (cookie firmada, `HttpOnly=True`, `SameSite=Lax`) y lo reenvía como header `Authorization: Bearer <token>` en cada llamada saliente (`financeai_api.py::_headers`). Esto elimina la superficie de ataque de robo de token vía XSS que existía con `localStorage` en el frontend vanilla JS, pero introduce una superficie distinta — CSRF sobre las rutas POST de Flask — que no está mitigada (ver AUD-28).

`IAClient.java` ya apunta correctamente a `/analisis-financiero` (AUD-15 resuelto), que coincide exactamente con `@app.post("/analisis-financiero")` en `main.py`.

### 2.3 Estructura de Paquetes del Backend
Sin cambios estructurales respecto a v4.

### 2.4 Estructura de la Nueva App Flask
```
frontend/app/
  routes/        → auth, dashboard, transacciones, analisis, historial (Blueprints)
  services/       → financeai_api.py (cliente HTTP centralizado + normalización + excepciones tipadas)
  templates/      → Jinja2, extienden base.html
  static/         → css/style.css, js/main.js (solo deshabilita doble submit de formularios)
  config.py, decorators.py (login_required), errors.py (manejadores por tipo de excepción)
```
Patrón notable y positivo: `financeai_api.py` normaliza **defensivamente** las respuestas del backend aceptando tanto `snake_case` como `camelCase` (`_pick(data, "nombre_comercio", "nombreComercio", ...)`). Esto significa que, aunque el backend cambie de convención en el futuro, el frontend no se rompe — un patrón de resiliencia que vale la pena preservar conscientemente en vez de "limpiarlo" en una futura refactorización.

---

## 3. Contrato de API: Objetivo vs. Estado Actual (v5, verificado contra Flask real)

| Aspecto | Backend real (Java) | Frontend real (`frontend/app/...`) | Estado |
| :--- | :--- | :--- | :--- |
| Ruta registro | `POST /api/v1/auth/register` | `api.register()` → `POST /auth/register` (`BASE_URL=.../api/v1`) | ✅ Coincide |
| Ruta perfil | `POST /api/v1/perfil` | `api.create_profile()` → `POST /perfil` | ✅ Coincide |
| Ruta transacciones | `GET/POST /api/v1/transacciones/usuario/transacciones` | `api.list_transactions()` / `api.create_transaction()` | ✅ Coincide |
| Ruta análisis | `POST /api/v1/analisis/predict` | `api.request_analysis()` → `POST /analisis/predict` | ✅ Coincide |
| Ruta historial | `GET /api/v1/analisis/usuario/historial` | `api.list_history()` → `GET /analisis/usuario/historial` | ✅ Coincide |
| Payload registro | `{nombre, apellido, documento, email, password, fecha_nacimiento, sexo, estado_civil, numero_hijos}` | Construido en Python con exactamente esas claves snake_case | ✅ Coincide (AUD-23 resuelto) |
| Payload perfil | `{empleo_formal, ingreso_mensual, linea_credito}` | Idéntico en `profile_payload` | ✅ Coincide |
| Payload transacción | `{nombre_comercio, monto_transaccion, medio_pago}` | Idéntico en `transacciones.py::create` | ✅ Coincide |
| Respuesta transacción | `TransaccionResponse` ahora con `@JsonProperty` snake_case | `normalize_transaction()` acepta ambas convenciones | ✅ Coincide doblemente (AUD-24 resuelto en backend y frontend) |
| Respuesta análisis | `{perfil_financiero, probabilidad, nivel_endeudamiento, rango_ahorro, resumen_gastos, recomendaciones}` | `normalize_analysis()` lee las claves correctas y `resultado.html` renderiza `resumen_gastos` y `recomendaciones` por separado | ✅ Coincide (AUD-25 resuelto) |
| Valor de `perfil_financiero` | Enum estricto: `SALUDABLE`, `EN_OBSERVACION`, `RIESGO` | El motor de IA (`main.py`) compara internamente `perfil_str == "RIESGOSO"` | 🔴 **Riesgo real de deserialización — AUD-02, sin cambios** |

**Lectura:** la tabla de contrato que en la v4 tenía 4 filas en rojo ahora tiene una sola — y no es un problema de *casing* sino de vocabulario de negocio con Data Science.

---

## 4. Configuración de Entorno

### 4.1 Backend (sin cambios respecto a v4)
`application.yml` sigue completo, `open-in-view: false` presente, `JWT_SECRET`/`IA_API_URL` externalizados. **Novedad confirmada:** `JwtUtil.getSignInKey()` ya no tiene ningún valor hardcodeado de respaldo — solo `HexFormat.of().parseHex(jwtConfig.getSecret())`. AUD-13 pasa de "parcial" a **✅ Resuelto sin matices**.

### 4.2 Frontend Flask (nuevo)
`frontend/.env.example`:
```
FLASK_SECRET_KEY=replace-with-a-long-random-secret
BACKEND_API_URL=http://localhost:8080/api/v1
REQUEST_TIMEOUT=10
SESSION_COOKIE_SECURE=false
```
`app/__init__.py` falla rápido (`RuntimeError`) si `FLASK_SECRET_KEY` no está definida — buena práctica. `SESSION_COOKIE_SECURE` por defecto es `False`; queda como ítem de checklist de despliegue (AUD-31), no como bug: debe pasarse a `true` cuando el frontend se sirva sobre HTTPS en OCI.

---

## 5. Dependencias
**Backend:** sin cambios en `pom.xml` respecto a v4 (AUD-21 sigue resuelto, verificado).
**Frontend (nuevo):** `Flask==3.1.1`, `requests==2.32.4`, `python-dotenv==1.1.1`, `Flask-WTF==1.2.2`, `pytest==8.4.1`. Nótese que `Flask-WTF` está declarado pero — verificado por `grep -i csrf` en todo el snapshot — **nunca se inicializa** (`CSRFProtect(app)` no aparece en ningún archivo). Ver AUD-28.

---

## 6. Auditoría Técnica v5

Metodología sin cambios respecto a la v4 (Sección 9.6): cada hallazgo se verificó extrayendo el bloque `<file path="...">` exacto y comparándolo contra su contraparte real, nunca contra prosa descriptiva.

### 6.1 Índice de Hallazgos (v5)
| ID | Severidad | Título | Estado v4 | Estado v5 |
| :--- | :--- | :--- | :--- | :--- |
| AUD-01 | Alta | AuthResponse: campos invertidos | ✅ | ✅ Sin cambios |
| AUD-02 | Alta | Enum de perfil financiero inconsistente (`RIESGOSO` vs `RIESGO`) | 🔴 | 🔴 **Sin cambios — evidencia más fuerte esta pasada** |
| AUD-03 | Alta | Contrato de API desalineado | 🟡 | ✅ **Resuelto** (frontend reescrito, contrato verificado en Sección 3) |
| AUD-04 | Media | Campo `transactions` vs `transacciones` | ✅ | ✅ Sin cambios |
| AUD-05 | Alta | `/predict` del mock ignora el body | 🟡 | ✅ **Resuelto** (`mock-api/` eliminado del repo) |
| AUD-06 | Alta | `usuarioId` nunca se asigna en historial | ✅ | ✅ Sin cambios |
| AUD-07 | Alta (seg.) | Transacciones sin autorización/DTO | ✅ | ✅ Sin cambios |
| AUD-08 | Alta (seg.) | IDOR en historial | ✅ | ✅ Sin cambios |
| AUD-09 | Alta | `application.yml` casi vacío | ✅ | ✅ Sin cambios |
| AUD-10 | Baja | `UserEntity.apellido` no se puebla | ✅ | ✅ Sin cambios |
| AUD-11 | Baja | `open-in-view=false` pendiente | ✅ | ✅ Sin cambios |
| AUD-12 | Media | `TransactionRequest` mal aprovechado | ✅ | ✅ Sin cambios |
| AUD-13 | Alta (seg.) | `jwt.secret` hardcodeado | ✅/parcial (ambiguo en v4) | ✅ **Resuelto sin matices** (confirmado: sin fallback) |
| AUD-14 | Alta | Esquema `historial_analisis` no coincide con JPA | ✅ | ✅ Sin cambios |
| AUD-15 | Alta | Motor de IA inalcanzable (ruta) | 🟡 | ✅ **Resuelto** (`IAClient` → `/analisis-financiero`, confirmado) |
| AUD-16 | Alta | Contrato de respuesta del motor no coincide | 🔴 | ✅ **Resuelto** (nombres de campo verificados idénticos) |
| AUD-17 | Media | Motor de IA duplicado | 🔴 | ✅ **Resuelto** (duplicado eliminado del repo) |
| AUD-18 | Media | `dashboard.html` existe pero está huérfano | 🟡 | ✅ **Resuelto/moot** (arquitectura reemplazada por Flask) |
| AUD-19 | Media | Registro→perfil encadenado, bloqueado por casing | 🟡 | ✅ **Resuelto** (Flask usa casing correcto de origen) |
| AUD-20 | Baja | Typo `Sexo.FEMININO` | ✅ | ✅ Sin cambios |
| AUD-21 | Baja | Typo `pom.xml` `<coniguration>` | ✅ | ✅ Sin cambios |
| AUD-22 | Baja | `Sexo` serializado como código de una letra hacia la IA | 🔴 | 🔴 Sin cambios |
| AUD-23 | Alta | Mismatch camelCase/snake_case en payloads del frontend | 🔴 | ✅ **Resuelto** (frontend reescrito con casing correcto) |
| AUD-24 | Media | `TransaccionResponse` camelCase vs. lectura snake_case | 🔴 | ✅ **Resuelto** (backend ahora anota `@JsonProperty`; frontend además normaliza ambos casos) |
| AUD-25 | Media | Lectura del campo equivocado para recomendaciones | 🔴 | ✅ **Resuelto** (`resultado.html` renderiza ambos campos correctamente) |
| AUD-26 | Baja | No existe `GET /api/v1/perfil` | 🔴 | 🟡 **Parcial, impacto reducido** (backend sigue sin el endpoint; el frontend ya no depende de `localStorage` para esto, solo intenta crear y absorbe el 409) |
| **AUD-27** | **Alta — nuevo** | `TransactionEntity.tipo` es `nullable=false` en JPA y nunca se puebla en ningún flujo de creación | — | 🔴 **Nuevo — bloqueador crítico del camino crítico** |
| **AUD-28** | **Alta (seg.) — nuevo** | Sin protección CSRF activa en Flask pese a tener `Flask-WTF` instalado | — | 🔴 **Nuevo** |
| **AUD-29** | **Media — nuevo** | El frontend Flask no está integrado en `docker-compose.yml` | — | 🔴 **Nuevo** |
| **AUD-30** | **Baja — nuevo** | Etiqueta "Monto mostrado" en el dashboard suma *todas* las transacciones, no solo las 5 visibles | — | 🔴 **Nuevo** |
| **AUD-31** | **Baja — nuevo** | `SESSION_COOKIE_SECURE` por defecto en `False`; falta fijarlo para producción/OCI | — | 🔴 **Nuevo (checklist de despliegue)** |

*Balance v5: 31 hallazgos totales. **23 resueltos · 1 parcial · 7 abiertos.***

### 6.2 Hallazgos Reclasificados por la Reescritura del Frontend
AUD-03, AUD-18, AUD-19, AUD-23, AUD-24 y AUD-25 comparten la misma causa de reclasificación y no requieren una entrada individual: el archivo o patrón que los originaba (`dashboard.js`, `auth.js`, `api.js`, `index.html`, `dashboard.html`) ya no existe en el repositorio. Fueron reemplazados por `frontend/app/routes/*.py` y `frontend/app/services/financeai_api.py`, verificados exactos en la Sección 3. No se trata de hallazgos "cerrados por decisión editorial" — se verificó carácter por carácter que el nuevo código no reproduce ninguno de los problemas originales, y en el caso de AUD-24, que el propio backend (`TransaccionResponse.java`) también fue corregido con anotaciones `@JsonProperty`.

### 6.3 Hallazgos Nuevos (AUD-27 a AUD-31)

#### AUD-27 — `TransactionEntity.tipo` es `nullable=false` en JPA y nunca se puebla
**Severidad:** Alta · **Componente:** `TransactionEntity.java`, `TransaccionServiceImpl.java`, `TransactionRequest.java`

Hallazgo, verificado con `grep -n "TransactionEntity.builder\|\.tipo("` sobre todo el snapshot: existen exactamente dos usos de `TransactionEntity.builder()` en el proyecto (`crearTransaccionAutenticado` y `crearTransaccion` en `TransaccionServiceImpl`), y ninguno de los dos invoca `.tipo(...)`. `TransactionRequest` (el DTO que llega desde el cliente) **no tiene un campo `tipo`** — no hay forma de que el cliente lo provea aunque quisiera. Sin embargo, `TransactionEntity.java` declara:
```java
@Column(nullable = false, length = 10)
private String tipo;
```
Esto es además una inconsistencia con el propio esquema de base de datos: la migración `V2__create_transactions_table.sql` define `tipo VARCHAR(10)` **sin** restricción `NOT NULL`. Es decir, la anotación JPA es más estricta que la base de datos real, y ningún flujo de la aplicación la satisface.

**Impacto:** con la validación de nulidad de Hibernate activa por defecto (`hibernate.check_nullability`), la primera vez que se ejecute `POST /api/v1/transacciones/usuario/transacciones` con un valor válido, Hibernate debería lanzar `PropertyValueException` antes de llegar a la base de datos, respondiendo previsiblemente con un 500. Esto bloquea no solo la funcionalidad de transacciones, sino **todo el flujo de análisis financiero**, que en `AnalisisIAServiceImpl` exige explícitamente al menos una transacción registrada (`IllegalStateException` si la lista está vacía). En la práctica, si esto se confirma en ejecución, es el bloqueador único más severo del camino crítico completo — más que cualquier hallazgo heredado de la v4.

**Acción recomendada (TASK-042):** dado que `tipo` no se usa en ninguna lógica de negocio visible ni se expone en ninguna respuesta, la corrección de menor riesgo es quitar `nullable = false` de la anotación (alineando la entidad con la base de datos real) o, si el campo tiene un propósito futuro (p. ej. distinguir "ingreso" vs "gasto"), poblarlo explícitamente con un valor por defecto en `TransaccionServiceImpl` mientras se define su semántica. Antes de aplicar el fix, se recomienda una prueba de humo (`POST` de una transacción real) para confirmar el 500 y documentar el stack trace exacto, en línea con la regla de gobernanza del Protocolo de Colaboración (Sección 9).

#### AUD-28 — Sin protección CSRF activa en Flask
**Severidad:** Alta (seguridad) · **Componente:** `frontend/app/__init__.py`, `frontend/app/config.py`, todas las plantillas con `<form method="post">`

Hallazgo: `Flask-WTF==1.2.2` está en `requirements.txt`, y `WTF_CSRF_TIME_LIMIT` está configurado en `Config`, pero no existe en ningún archivo del snapshot una llamada a `CSRFProtect(app)` ni un `import` de `flask_wtf.csrf`. Ningún formulario (`login.html`, `registro.html`, `formulario.html` de transacciones, el `logout` en `base.html`) incluye un token CSRF oculto. `WTF_CSRF_ENABLED = False` solo aparece en `TestConfig` (para pruebas), no hay un equivalente `True` en la config de producción porque, simplemente, la protección nunca se activó.

**Impacto:** cualquier endpoint POST autenticado por cookie de sesión (`/login`, `/registro`, `/transacciones/nueva`, `/logout`, `/analisis/generar`) es vulnerable a que un sitio malicioso induzca al navegador de un usuario ya logueado a enviar esas peticiones sin su consentimiento, aprovechando que el navegador adjunta la cookie de sesión automáticamente.

**Acción recomendada (TASK-043):** inicializar `CSRFProtect(app)` en `create_app()` y agregar `{{ csrf_token() }}` como campo oculto en cada `<form>`. Es un cambio pequeño y localizado; no requiere tocar el backend Java (que usa JWT Bearer, no cookies, y por eso correctamente tiene CSRF deshabilitado a nivel Spring Security — esa parte no es un hallazgo).

#### AUD-29 — El frontend Flask no está integrado en `docker-compose.yml`
**Severidad:** Media · **Componente:** `docker-compose.yml`, `frontend/Dockerfile`

Hallazgo: `frontend/Dockerfile` existe y es funcional (expone el puerto 5000, usa `flask run`), pero `docker-compose.yml` solo define `postgres-db`, `modelo-financeai` y `backend`. No hay un servicio `frontend`.

**Impacto:** no bloquea el desarrollo local (el frontend puede correrse con `flask run` apuntando a un `BACKEND_API_URL` externo), pero impide levantar el stack completo con un solo `docker compose up`, y es un paso pendiente para el despliegue en OCI.

**Acción recomendada (TASK-044):** agregar un servicio `frontend` a `docker-compose.yml`, con `depends_on: [backend]` y las variables `BACKEND_API_URL`, `FLASK_SECRET_KEY`, `SESSION_COOKIE_SECURE` inyectadas desde `.env`.

#### AUD-30 — Etiqueta "Monto mostrado" no corresponde a lo que se muestra
**Severidad:** Baja · **Componente:** `dashboard.py`, `dashboard/index.html`

Hallazgo: `dashboard.py` calcula `total` sumando **todas** las transacciones del usuario (`sum(... for item in transactions)`), pero solo pasa a la plantilla las primeras 5 (`transactions[:5]`). La tarjeta del dashboard etiqueta ese total como "Monto mostrado", lo cual sugiere (incorrectamente) que es la suma de las filas visibles en la tabla de abajo.

**Impacto:** puramente de percepción/UX — no hay pérdida de datos ni error funcional, pero puede confundir a un usuario con muchas transacciones.

**Acción recomendada (TASK-045):** renombrar la etiqueta a algo como "Monto total registrado" o, alternativamente, calcular el total solo sobre las 5 transacciones mostradas si esa era la intención original.

#### AUD-31 — `SESSION_COOKIE_SECURE` en `False` por defecto
**Severidad:** Baja (checklist de despliegue) · **Componente:** `frontend/app/config.py`, `.env.example`

Hallazgo: `SESSION_COOKIE_SECURE = _as_bool(os.getenv("SESSION_COOKIE_SECURE"), False)`. Es el valor correcto para desarrollo local sobre HTTP, pero debe pasarse explícitamente a `true` vía variable de entorno en cualquier despliegue que sirva sobre HTTPS (OCI), o la cookie de sesión viajará sin el flag `Secure`.

**Acción recomendada (TASK-046):** agregar `SESSION_COOKIE_SECURE=true` a la configuración de entorno de producción/OCI, junto a `TASK-023`/`TASK-024`. No es un bug de código, es un ítem de checklist de despliegue.

### 6.4 Hallazgos Sin Cambios (re-verificados en esta pasada)
* **AUD-02** — confirmado con mayor evidencia: `main.py` compara literalmente `perfil_str == "RIESGOSO"`, lo que indica que ese es probablemente el valor real que emite `modelo_perfil.predict()` para el perfil de riesgo — valor no reconocido por el enum Java (`SALUDABLE`, `EN_OBSERVACION`, `RIESGO`). Sigue bloqueante condicionalmente (solo cuando el modelo predice ese perfil).
* **AUD-22** — re-verificado: `Sexo.getCodigo()` sigue devolviendo `"M"`/`"F"`; `main.py` hace `str(datos.sexo).lower().strip()` → `"m"`/`"f"`. Sin confirmación de que el modelo fue entrenado con ese vocabulario exacto.
* **AUD-04, AUD-06, AUD-07, AUD-08, AUD-09, AUD-10, AUD-11, AUD-12, AUD-14, AUD-20, AUD-21** — re-verificados puntualmente (código de autorización en `TransactionController`/`HistorialAnalisisController`, `Sexo.FEMENINO`, bloques `<configuration>` de `pom.xml`, migraciones V3/V5); sin regresiones detectadas.

---

## 7. Estado Real por Vertical Slice (v5)

### 7.1 Slice 1 — Autenticación
| Capa | Estado v5 |
| :--- | :--- |
| Backend: seguridad base, register/login | ✅ (AUD-13 ya sin matices) |
| Frontend: login | ✅ Funcional end-to-end, JWT en cookie de sesión httponly |
| Frontend: registro → perfil | ✅ Funcional (casing correcto) — pero sin CSRF (AUD-28) |

### 7.2 Slice 2 — Transacciones
| Capa | Estado v5 |
| :--- | :--- |
| Backend: creación | 🔴 **Bloqueador nuevo** — `tipo` `nullable=false` nunca poblado (AUD-27) |
| Backend: lectura/autorización | ✅ Sin cambios, correcto |
| Frontend: alta y listado | ✅ Payload y lectura de respuesta correctos en ambos sentidos |

### 7.3 Slice 3 — Análisis Financiero e IA
| Capa | Estado v5 |
| :--- | :--- |
| Backend: persistencia del historial | ✅ (incluye ahora `rango_ahorro` y `nivel_endeudamiento`, gap de memoria del PM resuelto) |
| Backend: perfil financiero requerido | ✅ Creación funcional; falta `GET` (AUD-26, bajo impacto) |
| Motor de IA: alcanzable | ✅ Ruta correcta (AUD-15 resuelto) |
| Motor de IA: contrato de campos | ✅ Nombres alineados (AUD-16 resuelto) |
| Motor de IA: valor del enum de perfil | 🔴 Riesgo real sin confirmar (AUD-02) |
| Frontend: resultado del análisis | ✅ Renderiza `resumen_gastos` y `recomendaciones` correctamente |
| Frontend: historial | ✅ Implementado y funcional — **TASK-021 cerrada** |

### 7.4 Camino Crítico para una Demo End-to-End (re-evaluado v5)
| # | Bloqueador | Estado v5 | Esfuerzo estimado para cerrar |
| :---: | :--- | :--- | :--- |
| 1 | AUD-01 (login) | ✅ Resuelto | — |
| 2 | AUD-27 (creación de transacciones) | 🔴 **Bloquea todo lo que sigue** | Muy bajo — quitar una anotación o poblar un campo |
| 3 | AUD-02 (valor de enum de perfil de riesgo) | 🔴 Bloquea condicionalmente el análisis | Bajo, pero depende de una respuesta de Data Science |
| 4 | AUD-26 (GET perfil) | 🟡 No bloquea, solo limita robustez | Bajo |
| 5 | AUD-28 (CSRF) | 🔴 No bloquea la demo, sí la seguridad | Bajo |
| 6 | Todo lo demás (rutas, casing, contrato de campos, motor de IA alcanzable) | ✅ Resuelto | — |

**Lectura para el PM:** de los bloqueadores reales que quedan, **uno solo (AUD-27) es la diferencia entre "no se puede registrar ni una transacción" y "el flujo completo funciona"**. Es, además, el fix más barato de todo este documento. La secuencia de menor esfuerzo para una demo end-to-end es: **AUD-27 → (confirmar con Data Science el valor real de `perfil_financiero` para AUD-02, en paralelo) → AUD-28 antes de cualquier exposición pública del frontend.**

---

## 8. Sprint de Estabilización v5

### Grupo A — Bloqueador crítico único (máxima prioridad)
* 🔴 **TASK-042** — Corregir `TransactionEntity.tipo`: quitar `nullable = false` (alineado con el esquema real) o poblarlo explícitamente en `TransaccionServiceImpl`. Cierra AUD-27.

### Grupo B — Vocabulario con Data Science (no es trabajo de código, es una conversación)
* 🔴 **TASK-002** — Confirmar el valor real que emite `modelo_perfil.predict()` para el perfil de riesgo y alinear `PerfilFinanciero` (Java) o el post-procesamiento en `main.py`. Cierra AUD-02.
* 🔴 **TASK-037** — Confirmar con Data Science si `sexo`/`estado_civil` deben viajar como código corto o palabra completa. Cierra AUD-22.

### Grupo C — Seguridad del frontend
* 🔴 **TASK-043** — Activar `CSRFProtect(app)` + tokens en todos los formularios Flask. Cierra AUD-28.

### Grupo D — Infraestructura y despliegue
* 🔴 **TASK-044** — Agregar servicio `frontend` a `docker-compose.yml`. Cierra AUD-29.
* 🔴 **TASK-046** — Fijar `SESSION_COOKIE_SECURE=true` en la configuración de producción/OCI. Cierra AUD-31.
* 🔴 **TASK-023, TASK-024, TASK-025** — `docker-compose.prod.yml`, despliegue OCI, QA final. Sin cambios respecto a v4.

### Grupo E — Robustez y limpieza menor
* 🔴 **TASK-041** — Agregar `GET /api/v1/perfil` (menor urgencia que en v4, pero sigue pendiente). Cierra AUD-26.
* 🔴 **TASK-045** — Corregir etiqueta "Monto mostrado" en el dashboard. Cierra AUD-30.
* 🔴 **TASK-016** — Paginación de transacciones. Sin cambios.
* 🔴 **TASK-013** — Suite de tests de integración de Auth (backend Java). Sin cambios — nótese que el frontend Flask sí ganó cobertura de tests (`test_session.py`, `test_normalizers.py`) en esta reescritura.
* 🔴 **TASK-020** — Tests de resiliencia ante caída del motor de IA. Sin cambios.

**Cerradas desde la v4 (confirmadas por código, no solo por compilación local):** TASK-002 *(no — sigue abierta, ver Grupo B)*, TASK-005 *(no aplica)*, TASK-013 *(no)*, TASK-015 *(no aplica)*, TASK-017 *(no aplica)*, TASK-018 *(cerrada — el mismatch de rutas que describía ya no existe)*, TASK-021 *(cerrada — historial implementado)*, TASK-028 *(cerrada — contrato del motor de IA alineado)*, TASK-029 *(cerrada — mock-api y duplicado retirados)*, TASK-034 *(cerrada — JwtUtil sin fallback)*, TASK-035 *(cerrada/moot — dashboard.html reemplazado)*, TASK-036 *(cerrada — IAClient corregido)*, TASK-038 *(cerrada — casing resuelto por reescritura)*, TASK-039 *(cerrada)*, TASK-040 *(cerrada)*.

---

## 9. Convenciones y Definition of Done
Sin cambios respecto a v4, incluida la regla 9.6 de verificación por extracción. Se agrega:

**9.7 Regla nueva — Auditar el snapshot completo antes de asumir continuidad de arquitectura:** cuando un nuevo snapshot llega tras un período de trabajo activo del equipo, no asumir que los archivos auditados en la versión anterior siguen existiendo. Empezar siempre por `grep -n '<file path=' snapshot.md` y comparar la lista de rutas contra la de la auditoría previa — un módulo entero (en este caso, el frontend) puede haber sido reemplazado sin que el resto del documento lo anticipe.

---

## 10. Backlog Priorizado v5

### P0 — Bloqueador crítico único
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-042 | Corregir `TransactionEntity.tipo` (nullable/poblar) | AUD-27 |

### P1 — Vocabulario con Data Science y seguridad del frontend
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-002 | Confirmar y alinear valor real de `perfil_financiero` de riesgo | AUD-02 |
| TASK-037 | Confirmar vocabulario de `sexo`/`estado_civil` con Data Science | AUD-22 |
| TASK-043 | Activar CSRF en Flask | AUD-28 |

### P2 — Robustez e infraestructura
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-044 | Agregar servicio `frontend` a `docker-compose.yml` | AUD-29 |
| TASK-041 | Agregar `GET /api/v1/perfil` | AUD-26 |
| TASK-016 | Paginación de transacciones | Slice 2 |

### P3 — Verificación, tests y limpieza menor
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-045 | Corregir etiqueta "Monto mostrado" | AUD-30 |
| TASK-013 | Suite de tests de integración de Auth (backend) | Slice 1 |
| TASK-020 | Tests de resiliencia ante caída de IA | Slice 3 |

### P4 — Infraestructura y cierre (Semana 5)
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-046 | `SESSION_COOKIE_SECURE=true` en producción | AUD-31 |
| TASK-023 | `docker-compose.prod.yml` | Semana 5 |
| TASK-024 | Despliegue en OCI | Semana 5 |
| TASK-025 | QA end-to-end + revisión final | Semana 5 |

**Confirmadas completadas en esta pasada (además de las ya cerradas en v3/v4):** TASK-018, TASK-021, TASK-028, TASK-029, TASK-034, TASK-035, TASK-036, TASK-038, TASK-039, TASK-040.

---

## 11. Anexo: Prompts Guía para Sesiones de IA
Sin cambios respecto a v4. Se agrega:

### Prompt de verificación de continuidad de arquitectura entre snapshots
*Antes de reutilizar cualquier hallazgo de una Nota Maestra anterior, ejecuta `grep -n '<file path=' snapshot_nuevo.md` y compara contra las rutas de archivo citadas en los hallazgos previos. Si una ruta ya no aparece, el hallazgo que dependía de ella debe re-verificarse desde cero contra lo que la reemplazó — no heredarse como "resuelto" ni como "sin cambios" por defecto.*
