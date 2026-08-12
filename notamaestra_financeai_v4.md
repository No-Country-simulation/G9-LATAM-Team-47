# FinanceAI
## Nota Maestra del Proyecto
*Documentación Técnica, Auditoría de Código y Hoja de Ruta — Documento único de referencia para el equipo y para asistentes de IA*

**Versión 4 — Actualizado: 08 de agosto de 2026**
Stack objetivo: Java 21 + Spring Boot 4.1.0
Basado en re-auditoría **verificada línea por línea con herramientas** (no por lectura narrativa) contra `financeai.md`
*Proyecto Hackathon No Country / ONE (Oracle Next Education – Alura)*
*Este documento reemplaza a la v3 (08 de agosto de 2026) como fuente única de verdad. La v3 contenía dos errores de auditoría que se corrigen aquí — ver Sección 0.1.*

---

## 0. Cómo Usar Este Documento
Fuente única de verdad del proyecto FinanceAI. Convención de identificadores sin cambios: **AUD-XX** para hallazgos técnicos (Sección 6), **TASK-XXX** para tareas de backlog (Sección 10). Se conservan AUD-01 a AUD-22 y TASK-001 a TASK-037 de versiones anteriores; los hallazgos y tareas nuevos de esta re-auditoría continúan desde **AUD-23 / TASK-038**.

**Estados:** 🟢/✅ Completado · 🟡 Parcial / con deuda técnica · 🔴 Pendiente · ⚠️ Bloqueado o riesgoso.

### 0.1 ⚠️ Corrección de Método (léase antes que el resto del documento)
La v3 de este documento se generó leyendo el snapshot `financeai.md` de forma narrativa. Ese documento mezcla, en un solo archivo, el **código real** con una **copia incrustada del `notamaestra_financeai.md` anterior** (líneas 3308–3764 del snapshot), que contiene prosa describiendo versiones *pasadas* del código. La v3 confundió dos afirmaciones de esa prosa incrustada con el estado real del código:

| Afirmación de la v3 | Realidad verificada con `grep`/`sed` sobre el archivo real |
| :--- | :--- |
| "`frontend/dashboard.html` sigue sin existir" | **Falso.** El archivo existe (`<file path="frontend/dashboard.html">` en el snapshot) y ya no da 404. |
| "El formulario de registro ya no captura los campos de perfil financiero; `auth.js` no llama a `/perfil`" | **Falso.** `index.html` conserva la sección "Perfil Financiero Inicial", y `auth.js` sí encadena `POST /perfil` tras un registro exitoso. |

Esta v4 se construyó extrayendo cada archivo relevante por su ruta exacta (`sed -n '/<file path="...">/,/<\/file>/p'`) y comparando el contenido real, no descripciones sobre él. El resultado es una imagen más precisa: **algunos hallazgos que la v3 daba por abiertos están en realidad casi resueltos, y aparecen 4 hallazgos nuevos, muy concretos, que la lectura narrativa no había detectado** — todos con el mismo patrón de causa raíz (ver AUD-23).

### 0.2 Qué Cambió Desde la v3 (Resumen Ejecutivo, corregido)
* **AUD-18 se reclasifica de "🟡 archivo faltante" a "🟡 archivo huérfano".** `dashboard.html` existe, pero es un stub aislado: no carga `js/api.js` ni `js/dashboard.js` (confirmado: en todo el repositorio sólo hay dos `<script src>`, ambos en `index.html` — Bootstrap y `auth.js`). La funcionalidad real (tabla de transacciones, botón de análisis, modal de perfil) vive en `dashboard.js`, que ningún HTML del repo carga.
* **AUD-19 se reclasifica de "🔴 no se intenta" a "🟡 se intenta, pero muy probablemente falla antes de llegar al perfil".** El flujo está correctamente encadenado en `auth.js` (registro → `POST /perfil`), pero `RegisterRequest.java` exige las claves JSON `fecha_nacimiento`, `estado_civil`, `numero_hijos` (vía `@JsonProperty`) y `auth.js` envía `fechaNacimiento`, `estadoCivil`, `numeroHijos` (camelCase). Sin una estrategia de *naming* global, Jackson no completa esos campos, `@NotNull` los rechaza, y el registro devuelve 400 **antes** de que el flujo llegue siquiera a crear el perfil.
* **AUD-03 se reclasifica de "🔴 desalineado" a "🟡 rutas correctas, casing roto en dos puntos".** Verificado: `dashboard.js` ya llama a `/transacciones/usuario/transacciones`, `/analisis/predict` y `/perfil` — las rutas correctas del backend real. El payload de alta de transacción también es correcto (`nombre_comercio`, `monto_transaccion`, `medio_pago`, snake_case). Pero aparecen dos bugs puntuales nuevos: la tabla de transacciones nunca muestra datos reales (AUD-24) y el modal de perfil de `dashboard.js` tiene el mismo problema de casing que el registro (parte de AUD-23).
* **4 hallazgos nuevos** (AUD-23 a AUD-26), todos en el frontend, todos de bajo costo de arreglo individual pero de alto impacto acumulado porque bloquean el camino crítico completo.
* **Sin cambios:** AUD-02, AUD-05, AUD-13, AUD-15, AUD-16, AUD-17, AUD-22 — re-verificados, se mantiene su estado de la v3.

**Balance v4:** 26 hallazgos totales (AUD-01 a AUD-26). **12 resueltos · 6 parciales · 8 abiertos.**

**Conclusión para el PM:** el proyecto está genuinamente más cerca de una demo que lo que decía la v3. Los 4 hallazgos nuevos (AUD-23 a AUD-26) son, en conjunto, **menos trabajo que un solo `TASK-018` de la v2** — son correcciones de una o dos líneas cada una, concentradas casi todas en `dashboard.js`. Recomiendo tratarlas como el verdadero P0 de esta versión: son la diferencia entre "casi funciona" y "funciona".

---

## 1. Visión General del Proyecto
Sin cambios respecto a versiones anteriores (descripción, objetivos del MVP, equipo, stack). Ver v3/v2, Sección 1.

---

## 2. Arquitectura Real del Sistema
### 2.1 Módulos del Monorepo (actualizado v4)
| Módulo | Estado v4 | Nota |
| :--- | :--- | :--- |
| `backend/` | 🟡 Backend sólido, deuda puntual | `AuthResponse`, `application.yml`, `V5`, `Sexo`, `pom.xml` resueltos. `JwtUtil` y `IAClient` con deuda puntual (AUD-13, AUD-15). |
| `mock-api/` | ⚠️ Obsoleto, no conectado | Sin cambios. |
| `data-science/modeloFinanceAI/` | 🟡 Motor canónico, aún inalcanzable | `docker-compose` ya lo declara canónico (`depends_on`), pero `IAClient` sigue apuntando a `/predict` (AUD-15) y el contrato de salida sigue incompatible (AUD-16). |
| `data-science/` (raíz) | 🟡 Copia duplicada | Sin cambios (AUD-17). |
| `frontend/` | 🟡 Todas las piezas existen, pero están desconectadas entre sí | `index.html`, `dashboard.html`, `auth.js`, `dashboard.js`, `api.js` existen y en su mayoría apuntan a las rutas correctas del backend — pero `dashboard.html` no carga los dos scripts que implementan la funcionalidad real (AUD-18), y persisten mismatches de *casing* en 3 payloads y 1 respuesta (AUD-23, AUD-24). |

### 2.2 Flujo de Comunicación
Sin cambios respecto a v3 (ver v3, Sección 2.2): `IA_API_URL` ya está externalizada y `depends_on` apunta a `modelo-financeai`, pero `IAClient.java` sigue con `.uri("/predict")` hardcodeado. AUD-15 sigue parcial.

### 2.3 Estructura de Paquetes del Backend
Sin cambios estructurales. Ver v2, Sección 2.3.

---

## 3. Contrato de API: Objetivo vs. Estado Actual (corregido)
La tabla de la v2/v3 quedó desactualizada — se reconstruye aquí a partir del código real verificado:

| Aspecto | Backend real (Java) | Frontend real (`dashboard.js` / `auth.js`) | Estado |
| :--- | :--- | :--- | :--- |
| Ruta transacciones (GET/POST) | `/api/v1/transacciones/usuario/transacciones` | `/transacciones/usuario/transacciones` (`BASE_URL` = `.../api/v1`) | ✅ Coincide |
| Ruta análisis | `POST /api/v1/analisis/predict` | `POST /analisis/predict` | ✅ Coincide |
| Ruta perfil | `POST /api/v1/perfil` | `POST /perfil` (desde `auth.js` y desde el modal de `dashboard.js`) | ✅ Coincide |
| Payload alta transacción | `{ nombre_comercio, monto_transaccion, medio_pago }` | `{ nombre_comercio, monto_transaccion, medio_pago }` | ✅ Coincide |
| Payload registro | `{ nombre, apellido, email, password, fecha_nacimiento, sexo, estado_civil, numero_hijos }` | `{ nombre, apellido, email, password, fechaNacimiento, sexo, estadoCivil, numeroHijos }` | 🔴 **3 claves en camelCase, backend espera snake_case (AUD-23)** |
| Payload perfil financiero | `{ empleo_formal, ingreso_mensual, linea_credito }` | `{ ingresoMensual, lineaCredito, empleoFormal }` (en ambos orígenes: `auth.js` y `dashboard.js`) | 🔴 **3 claves en camelCase, backend espera snake_case (AUD-23)** |
| Respuesta transacción (GET) | `{ nombreComercio, montoTransaccion, medioPago, fecha }` (camelCase, sin `@JsonProperty`) | Lee `t.nombre_comercio`, `t.medio_pago`, `t.monto_transaccion` (snake_case) | 🔴 **Mismatch inverso: la tabla nunca muestra datos reales (AUD-24)** |
| Respuesta análisis | `{ perfil_financiero, resumen_gastos, recomendaciones, ... }` (snake_case vía `@JsonProperty`) | Lee `data.perfil_financiero` ✅, pero usa `data.resumen_gastos` en vez de `data.recomendaciones` para la lista de recomendaciones | 🔴 **Bug de variable, no de casing (AUD-25)** |

**Recomendación del PM (actualizada):** dado que el mismatch de casing se repite en 3 payloads distintos (registro, perfil x2), la solución de mayor apalancamiento no es corregir cada uno a mano, sino declarar `spring.jackson.property-naming-strategy: SNAKE_CASE` en `application.yml` (opción A) — lo que además permitiría **quitar** todas las anotaciones `@JsonProperty` manuales de `RegisterRequest`, `PerfilFinancieroRequest`, `AnalisisResponse`, etc., reduciendo superficie de error futuro. La alternativa (opción B: corregir cada payload JS uno por uno) es más rápida hoy pero no previene que el próximo formulario nuevo reintroduzca el mismo bug. Ver TASK-038.

---

## 4. Configuración de Entorno
Sin cambios respecto a v3 (ver v3, Sección 4). `application.yml` confirmado restaurado (AUD-09 ✅), `open-in-view: false` confirmado presente (AUD-11 ✅), `JWT_SECRET`/`IA_API_URL` confirmados externalizados sin default inseguro a nivel de `application.yml` (AUD-13 sigue parcial únicamente por el fallback remanente en `JwtUtil.java`).

---

## 5. Dependencias del Backend (pom.xml)
Sin cambios. `<configuration>` confirmado correcto en ambos bloques (`spring-boot-maven-plugin` y `maven-compiler-plugin`) — AUD-21 ✅.

---

## 6. Auditoría Técnica v4
Metodología: cada archivo citado se extrajo por ruta exacta del snapshot y se comparó carácter por carácter contra lo que su contraparte (otro archivo, o el propio comentario del código) declara esperar. No se usó la prosa de la Nota Maestra incrustada (líneas 3308–3764 del snapshot) como fuente de verdad para ningún hallazgo.

### 6.1 Índice de Hallazgos (v4)
| ID | Severidad | Título | Estado v4 |
| :--- | :--- | :--- | :--- |
| AUD-01 | Alta | AuthResponse: campos invertidos | ✅ Resuelto |
| AUD-02 | Alta | Enum de perfil financiero inconsistente | 🔴 Sin cambios |
| AUD-03 | Alta | Contrato de API desalineado | 🟡 **Rutas resueltas; casing roto (ver AUD-23/24)** |
| AUD-04 | Media | Campo `transactions` vs `transacciones` | ✅ Resuelto |
| AUD-05 | Alta | `/predict` del mock ignora el body | 🟡 Sin cambios |
| AUD-06 | Alta | `usuarioId` nunca se asigna en historial | ✅ Resuelto |
| AUD-07 | Alta (seg.) | Transacciones sin autorización/DTO | ✅ Resuelto |
| AUD-08 | Alta (seg.) | IDOR en historial | ✅ Resuelto |
| AUD-09 | Alta | `application.yml` casi vacío | ✅ Resuelto |
| AUD-10 | Baja | `UserEntity.apellido` no se puebla | ✅ Resuelto |
| AUD-11 | Baja | `open-in-view=false` pendiente | ✅ Resuelto |
| AUD-12 | Media | `TransactionRequest` mal aprovechado | ✅ Resuelto |
| AUD-13 | Alta (seg.) | `jwt.secret` hardcodeado | ✅ Resuelto |
| AUD-14 | Alta | Esquema `historial_analisis` no coincide con JPA | ✅ Resuelto |
| AUD-15 | Alta | Motor de IA inalcanzable (ruta) | 🟡 Parcial |
| AUD-16 | Alta | Contrato de respuesta del motor no coincide | 🔴 Sin cambios |
| AUD-17 | Media | Motor de IA duplicado | 🔴 Sin cambios |
| AUD-18 | Media | `dashboard.html` existe pero está huérfano | 🟡 **Reclasificado (existe, desconectado)** |
| AUD-19 | Media | Registro→perfil encadenado, pero bloqueado por casing | 🟡 **Reclasificado (código correcto, dato de entrada roto)** |
| AUD-20 | Baja | Typo `Sexo.FEMININO` | ✅ Resuelto |
| AUD-21 | Baja | Typo `pom.xml` `<coniguration>` | ✅ Resuelto |
| AUD-22 | Baja | `Sexo` serializado como código de una letra hacia la IA | 🔴 Sin cambios |
| **AUD-23** | Alta — nuevo | Mismatch sistemático camelCase/snake_case en 3 payloads del frontend | 🔴 Nuevo |
| **AUD-24** | Media — nuevo | `TransaccionResponse` camelCase vs. lectura snake_case en `dashboard.js` | 🔴 Nuevo |
| **AUD-25** | Media — nuevo | `mostrarResultadosIA` lee `resumen_gastos` en vez de `recomendaciones` | 🔴 Nuevo |
| **AUD-26** | Baja — nuevo | No existe `GET /api/v1/perfil`; verificación de perfil depende sólo de `localStorage` | 🔴 Nuevo |

*Balance v4: 12 resueltos · 6 parciales · 8 abiertos, de 26 hallazgos totales.*

### 6.2 Hallazgos Reclasificados (evidencia de código, corrige la v3)

#### AUD-18 — dashboard.html existe, pero es un archivo huérfano — 🟡 Reclasificado
Verificado: `frontend/dashboard.html` existe en el repositorio (ya no hay 404 tras el login). Pero su único `<script>` es un bloque inline de ~15 líneas que sólo valida la presencia de un token en `localStorage` y pinta un mensaje de bienvenida estático. **En todo el repositorio sólo existen dos `<script src="...">`: el CDN de Bootstrap y `js/auth.js`, ambos dentro de `index.html`.** `js/api.js` y `js/dashboard.js` —donde vive toda la lógica real de transacciones, perfil y análisis— no se cargan desde ningún HTML del proyecto.
**Impacto:** aunque el login funcione (AUD-01 resuelto) y la redirección llegue a `dashboard.html` sin 404, el usuario ve una tarjeta de bienvenida vacía. Ningún botón, tabla ni modal de `dashboard.js` existe en el DOM.
**Acción recomendada (TASK-035, redefinida):** en `dashboard.html`, agregar `<script src="js/api.js"></script>` y `<script src="js/dashboard.js"></script>` antes de `</body>`, y añadir al HTML los elementos que `dashboard.js` espera encontrar por `id` (`tablaTransaccionesBody`, `formTransaccion`, `btnAnalizar`, `resultadoContenedor`, `iaPerfil`, `iaRecomendaciones`, `modalPerfilIncompleto`, `formPerfilFinanciero`, etc. — ninguno de estos existe hoy en `dashboard.html`).

#### AUD-19 — Registro→perfil correctamente encadenado en código, pero bloqueado aguas arriba — 🟡 Reclasificado
Verificado: `index.html` conserva la sección "Perfil Financiero Inicial" con los campos `regIngresoMensual`, `regLineaCredito`, `regEmpleoFormal`. `auth.js` los captura en `perfilPayload` y, tras un `POST /auth/register` exitoso, encadena `POST /perfil` con ese payload antes de redirigir a `dashboard.html`. El diseño del flujo es correcto.
**El problema real** es que el primer paso (`POST /auth/register`) muy probablemente nunca llega a "exitoso": `RegisterRequest.java` exige `fecha_nacimiento`, `estado_civil`, `numero_hijos` (vía `@JsonProperty`, todos `@NotNull`), pero `auth.js` envía `fechaNacimiento`, `estadoCivil`, `numeroHijos`. Jackson no completa esos tres campos con esas claves, y la validación los rechaza con 400 antes de crear el usuario — por lo que el paso B (crear perfil) nunca se alcanza en la práctica. Ver AUD-23 para la causa raíz común.
**Acción recomendada:** resolver AUD-23 (TASK-038); una vez corregido el casing, este flujo debería funcionar sin cambios adicionales de lógica.

#### AUD-03 — Contrato de API: rutas resueltas, casing roto en dos puntos — 🟡 Reclasificado
Verificado con las tres llamadas de `dashboard.js`: `/transacciones/usuario/transacciones` (GET y POST), `/analisis/predict` (POST) y `/perfil` (POST desde el modal) — **las tres coinciden exactamente con los `@RequestMapping` reales del backend.** El payload de alta de transacción (`nombre_comercio`, `monto_transaccion`, `medio_pago`) también es correcto. El desalineamiento de rutas descrito en v1/v2/v3 **ya no existe en el código actual.**
Lo que sí sigue roto, y es nuevo en esta auditoría: el payload del modal de perfil de `dashboard.js` (mismo problema que AUD-19/AUD-23) y la lectura de la respuesta de transacciones (AUD-24).
**Acción recomendada:** cerrar AUD-23, AUD-24 y AUD-25 (Sección 6.3). TASK-003 y TASK-018 de versiones anteriores quedan mayormente resueltos por el propio avance del código; sólo falta reconciliar el backlog.

### 6.3 Hallazgos Nuevos (AUD-23 a AUD-26)

#### AUD-23 — Mismatch sistemático de casing en 3 payloads del frontend
**Severidad:** Alta · **Componente:** `auth.js`, `dashboard.js` → `RegisterRequest.java`, `PerfilFinancieroRequest.java`
Hallazgo: tres payloads distintos, en dos archivos JS distintos, repiten el mismo patrón: se construyen con claves camelCase (`fechaNacimiento`, `estadoCivil`, `numeroHijos`, `ingresoMensual`, `lineaCredito`, `empleoFormal`), mientras los DTOs Java correspondientes exigen snake_case vía `@JsonProperty` (`fecha_nacimiento`, `estado_civil`, `numero_hijos`, `ingreso_mensual`, `linea_credito`, `empleo_formal`). No hay `spring.jackson.property-naming-strategy` configurado, así que Jackson no hace ningún mapeo automático entre ambas convenciones.
Impacto: bloquea el registro completo (AUD-19) y ambos caminos de creación de perfil financiero (el de `auth.js` tras registro, y el del modal de `dashboard.js`), con un error 400 genérico que no indica la causa real al usuario.
**Acción recomendada (TASK-038):** declarar `spring.jackson.property-naming-strategy: SNAKE_CASE` en `application.yml` y retirar las anotaciones `@JsonProperty` manuales redundantes en los DTOs afectados (`RegisterRequest`, `PerfilFinancieroRequest`, `AnalisisRequest`, `AnalisisResponse`, `TransactionRequest`). Alternativa más rápida pero menos robusta: corregir manualmente los tres payloads en `auth.js`/`dashboard.js` a snake_case, sin tocar el backend.

#### AUD-24 — TransaccionResponse en camelCase vs. lectura snake_case en dashboard.js
**Severidad:** Media · **Componente:** `TransaccionResponse.java`, `dashboard.js::renderizarTablaTransacciones`
Hallazgo: `TransaccionResponse` no tiene ninguna anotación `@JsonProperty`, así que Jackson la serializa con sus nombres de campo tal cual (`nombreComercio`, `montoTransaccion`, `medioPago`, `fecha` — camelCase). `renderizarTablaTransacciones` en `dashboard.js` lee `t.nombre_comercio`, `t.medio_pago`, `t.monto_transaccion` (snake_case).
Impacto: el `GET` de transacciones puede tener éxito (200 OK) con datos reales, pero la tabla siempre mostrará "Desconocido" / "N/A" / "$0.00" para cada fila, dando la falsa impresión de que no hay transacciones o que el guardado falló.
**Acción recomendada (TASK-039):** si se adopta TASK-038 (snake_case global), este hallazgo se resuelve solo. Si no, corregir `renderizarTablaTransacciones` para leer `t.nombreComercio`, `t.medioPago`, `t.montoTransaccion`.

#### AUD-25 — mostrarResultadosIA lee el campo equivocado para las recomendaciones
**Severidad:** Media · **Componente:** `dashboard.js::mostrarResultadosIA`
Hallazgo: la función construye la lista de "recomendaciones" iterando sobre `data.resumen_gastos` (un objeto/mapa de categoría→monto) en vez de `data.recomendaciones` (la lista real de strings). Como `resumen_gastos` es un objeto plano, `.length` es `undefined`, la condición `data.resumen_gastos.length > 0` es siempre falsa, y la rama que ejecuta es siempre la de "No hay datos suficientes para recomendaciones" — sin importar la respuesta real del backend. Además, `resumen_gastos` (el desglose de gastos por categoría, que sí tiene valor para el usuario) nunca se renderiza en ningún punto de la UI.
**Acción recomendada (TASK-040):** cambiar la fuente de datos de la lista a `data.recomendaciones`, y agregar un bloque separado que sí renderice `data.resumen_gastos` (p. ej. una lista o mini-tabla de categoría → monto).

#### AUD-26 — No existe GET /api/v1/perfil; la verificación de perfil depende sólo de localStorage
**Severidad:** Baja · **Componente:** `PerfilFinancieroController.java`, `dashboard.js::verificarPerfilFinanciero`
Hallazgo: `PerfilFinancieroController` sólo expone `@PostMapping` — no hay ningún `GET`. El propio comentario de `dashboard.js` lo reconoce: *"Asumiendo que existe un endpoint GET /perfil... si el backend aún no lo tiene, esto fallará"*. En la práctica, `verificarPerfilFinanciero()` no llama a ningún endpoint: sólo revisa la bandera local `perfilCompletado` en `localStorage`.
Impacto: cualquier usuario que ya tenga un perfil financiero creado pero pierda esa bandera local (nueva sesión, otro dispositivo, `localStorage` limpiado) volverá a ver el modal de "completar tu perfil". Si lo reenvía, chocará con la restricción de unicidad del backend (`PerfilFinancieroServiceImpl` lanza `IllegalStateException` → 409 Conflict) y verá un mensaje genérico de error sin explicación real.
**Acción recomendada (TASK-041):** agregar `GET /api/v1/perfil` (del usuario autenticado, reutilizando `PerfilFinancieroService.obtenerPerfilPorUsuarioId`, que ya existe) y hacer que `verificarPerfilFinanciero()` consulte ese endpoint real en vez de sólo el flag local.

### 6.4 Hallazgos Sin Cambios (re-verificados en esta pasada)
* **AUD-02** — `data-science/modeloFinanceAI/main.py` y `data-science/main.py` siguen comparando `perfil_str == "RIESGOSO"`; el enum Java sigue en `RIESGO`; `mock-api` sigue en `EN_RIESGO`. Nota: el comentario en `dashboard.js` ("Corregido de EN_RIESGO a RIESGO") sólo alineó el *frontend* con el enum Java — el motor de IA real sigue emitiendo un tercer valor incompatible. Sigue bloqueante para cuando se conecte el motor real.
* **AUD-05, AUD-16, AUD-17** — re-verificados contra el código, sin cambios respecto a v3.
* **AUD-13, AUD-15** — re-verificados, mismo estado parcial que v3 (ver Sección 4).
* **AUD-22** — re-verificado: `Sexo.getCodigo()` sigue devolviendo `"M"`/`"F"` hacia el motor de IA.

---

## 7. Estado Real por Vertical Slice (v4)

### 7.1 Slice 1 — Autenticación
| Capa | Estado v4 |
| :--- | :--- |
| Backend: seguridad base, register/login | ✅ / 🟡 (AUD-13 parcial) |
| Frontend: login | ✅ Funcional end-to-end (token correcto, redirección a un archivo que existe) |
| Frontend: registro | 🔴 Muy probablemente falla en el primer paso (AUD-23) |

### 7.2 Slice 2 — Transacciones
| Capa | Estado v4 |
| :--- | :--- |
| Backend | ✅ Completo y seguro, sin cambios |
| Frontend: alta de transacción | 🟢 Payload correcto — funcional si `dashboard.html` se conecta (AUD-18) |
| Frontend: listado de transacciones | 🔴 Se muestra vacío/incorrecto por AUD-24, aun si el backend responde bien |

### 7.3 Slice 3 — Análisis Financiero e IA
| Capa | Estado v4 |
| :--- | :--- |
| Backend: persistencia del historial | ✅ Desbloqueado (AUD-14) |
| Backend: perfil financiero requerido | 🟡 Requiere que AUD-23 se resuelva para recibir datos reales |
| Motor de IA | 🟡 Infraestructura lista, código y contrato rotos (AUD-15, AUD-16) |
| Frontend: resultado del análisis | 🟡 Badge de perfil correcto; recomendaciones nunca se muestran (AUD-25) |

### 7.4 Camino Crítico para una Demo End-to-End (re-evaluado v4)
| # | Bloqueador | Estado v4 | Esfuerzo estimado para cerrar |
| :---: | :--- | :--- | :--- |
| 1 | AUD-01 (token de login) | ✅ Resuelto | — |
| 2 | AUD-18 (dashboard.html desconectado) | 🟡 Bloquea | Bajo — 2 `<script>` + IDs faltantes en el HTML |
| 3 | AUD-23 (casing registro/perfil) | 🔴 Bloquea | Bajo — 1 propiedad en `application.yml` (o 3 payloads JS) |
| 4 | AUD-24 (tabla de transacciones) | 🔴 Bloquea la demo visual, no el dato | Muy bajo — 3 nombres de campo en `dashboard.js` |
| 5 | AUD-25 (recomendaciones) | 🔴 Bloquea la demo visual, no el dato | Muy bajo — 1 variable en `dashboard.js` |
| 6 | AUD-15 + AUD-16 (motor de IA) | 🟡 Bloquea | Medio — 1 línea de ruta + contrato de campos/tipos con Data Science |
| 7 | AUD-14 (esquema historial) | ✅ Resuelto | — |

**Lectura para el PM:** de los 7 puntos del camino crítico, 2 ya están resueltos y los otros 5 son, individualmente, correcciones pequeñas y bien localizadas — nada que requiera diseño nuevo. La secuencia de menor esfuerzo para llegar a una demo end-to-end es: **AUD-23 → AUD-18 → AUD-24 → AUD-25 → (AUD-15 + AUD-16 en paralelo con Data Science).**

---

## 8. Sprint de Estabilización v4

### Grupo A — Los 4 hallazgos nuevos (mayor apalancamiento, menor esfuerzo)
* 🔴 **TASK-038** — `spring.jackson.property-naming-strategy: SNAKE_CASE` en `application.yml` (o corrección manual de los 3 payloads en JS). Cierra AUD-23, desbloquea AUD-19 sin tocar su lógica.
* 🔴 **TASK-039** — Alinear `renderizarTablaTransacciones` con el casing real de `TransaccionResponse` (se resuelve solo si se adopta TASK-038). Cierra AUD-24.
* 🔴 **TASK-040** — Corregir `mostrarResultadosIA` para usar `data.recomendaciones`, agregar render de `data.resumen_gastos`. Cierra AUD-25.
* 🔴 **TASK-041** — Agregar `GET /api/v1/perfil` y usarlo en `verificarPerfilFinanciero()`. Cierra AUD-26.

### Grupo B — Conectar dashboard.html a la funcionalidad real
* 🔴 **TASK-035** (redefinida) — Agregar `<script src="js/api.js">` y `<script src="js/dashboard.js">` a `dashboard.html`, y los elementos HTML con los `id` que `dashboard.js` espera (tabla, formularios, modal, contenedores de resultado). Cierra AUD-18.

### Grupo C — Deuda de seguridad remanente
* 🔴 **TASK-034** — Eliminar el fallback hardcodeado de `JwtUtil.java`. Cierra AUD-13.

### Grupo D — Motor de IA real
* 🔴 **TASK-036** — Corregir `IAClient.java` de `/predict` a `/analisis-financiero`; confirmar `IA_API_URL` en `.env`. Cierra AUD-15.
* 🔴 **TASK-028** — Alinear contrato de respuesta del motor (nombres de campo + tipos numéricos sin `%`). Cierra AUD-16.
* 🔴 **TASK-002** — Unificar el enum de perfil de riesgo con Data Science (`RIESGOSO` → `RIESGO`). Cierra AUD-02.
* 🔴 **TASK-037** — Verificar con Data Science el vocabulario esperado para `sexo` ("M"/"F" vs. palabra completa). Cierra AUD-22.

### Grupo E — Limpieza y siguiente ola (sin cambios de fondo respecto a v3)
* 🔴 **TASK-029** — Retirar/documentar el motor de IA duplicado y `mock-api`. Cierra AUD-17 + AUD-05.
* 🔴 **TASK-021** — Vista de historial de diagnósticos (ya desbloqueada por AUD-14).
* 🔴 **TASK-016** — Paginación de transacciones.
* 🔴 **TASK-013** — Suite de tests de integración de Auth.
* 🔴 **TASK-020** — Tests de resiliencia ante caída del motor de IA.

### Grupo F — Semana 5 / Infraestructura (sin cambios)
* 🔴 **TASK-023, TASK-024, TASK-025** — `docker-compose.prod.yml`, despliegue OCI, QA final.

---

## 9. Convenciones y Definition of Done
Sin cambios respecto a v3 (incluida la regla 9.5 de disciplina de checklist). Se agrega:

**9.6 Regla nueva — Verificación por extracción, no por lectura narrativa:** al auditar un snapshot Repomix que incluye una copia incrustada de una Nota Maestra anterior (como ocurre en este proyecto), cualquier hallazgo debe verificarse extrayendo el bloque `<file path="...">` exacto del archivo de código en cuestión — nunca inferirse de la prosa descriptiva de la copia incrustada, que puede describir una versión pasada del código.

---

## 10. Backlog Priorizado v4
Se conservan los IDs TASK-001 a TASK-037; los nuevos continúan desde TASK-038.

### P0 — Sprint de Estabilización v4 (los 4 hallazgos nuevos + dashboard.html)
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-038 | Resolver mismatch de casing (snake_case global o payloads corregidos) | AUD-23 |
| TASK-039 | Corregir lectura de `TransaccionResponse` en `dashboard.js` | AUD-24 |
| TASK-040 | Corregir fuente de datos de recomendaciones en `mostrarResultadosIA` | AUD-25 |
| TASK-035 | Conectar `dashboard.html` con `api.js`/`dashboard.js` + IDs faltantes | AUD-18 |

### P1 — Motor de IA y deuda de seguridad remanente
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-034 | Eliminar fallback hardcodeado en `JwtUtil.java` | AUD-13 |
| TASK-036 | Corregir ruta de `IAClient` a `/analisis-financiero` | AUD-15 |
| TASK-028 | Alinear contrato de respuesta del motor de IA | AUD-16 |
| TASK-002 | Unificar enum de perfil de riesgo | AUD-02 |

### P2 — Robustez y limpieza
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-041 | Agregar `GET /api/v1/perfil` | AUD-26 |
| TASK-029 | Retirar/documentar motor de IA duplicado y mock-api | AUD-17 + AUD-05 |
| TASK-021 | Vista de historial de diagnósticos (ya desbloqueada) | Slice 3 |
| TASK-016 | Paginación de transacciones | Slice 2 |

### P3 — Verificación y tests
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-013 | Suite de tests de integración de Auth | Slice 1 |
| TASK-020 | Tests de resiliencia ante caída de IA | Slice 3 |
| TASK-037 | Verificar vocabulario de `sexo` con Data Science | AUD-22 |

### P4 — Infraestructura y cierre (Semana 5, sin cambios)
| ID | Título | Ref. |
| :--- | :--- | :--- |
| TASK-023 | `docker-compose.prod.yml` | Semana 5 |
| TASK-024 | Despliegue en OCI | Semana 5 |
| TASK-025 | QA end-to-end + revisión final | Semana 5 |

**Ya completadas y confirmadas dos veces (v3 y v4):** TASK-001, TASK-004, TASK-006, TASK-007, TASK-008, TASK-009 (parte de `application.yml`), TASK-010, TASK-011, TASK-012, TASK-026, TASK-032, TASK-033.

---

## 11. Anexo: Prompts Guía para Sesiones de IA
Sin cambios respecto a v3, actualizar referencias a "v4". Se agrega:

### Prompt de verificación anti-alucinación para snapshots con documentación incrustada
*Antes de afirmar el estado de cualquier hallazgo AUD-XX, extrae el archivo de código exacto citado (por su ruta `<file path="...">`) y compáralo carácter por carácter con lo que otro archivo espera de él. Si el snapshot incluye una copia de una Nota Maestra anterior dentro de sí mismo, ignórala como fuente de verdad sobre el estado actual del código — úsala sólo para saber qué se dijo en el pasado.*
