# FinanceAI
## Nota Maestra del Proyecto
*Documentación Técnica, Auditoría de Código y Hoja de Ruta — Documento único de referencia para el equipo y para asistentes de IA*

**Versión 2 — Actualizado: 05 de agosto de 2026**
Stack objetivo: Java 21 + Spring Boot 4.1.0
Basado en re-auditoría cruzada entre la Nota Maestra v1 (30 de julio de 2026) y un nuevo snapshot del código real (financeai.md, 05 de agosto de 2026)
*Proyecto Hackathon No Country / ONE (Oracle Next Education – Alura)*
*Este documento reemplaza a la versión del 30 de julio de 2026 como fuente única de verdad del proyecto.*

---

## 0. Cómo Usar Este Documento
Este documento es la fuente única de verdad (single source of truth) del proyecto FinanceAI. Esta es la Versión 2, generada a partir de una nueva re-auditoría cruzada entre la Nota Maestra v1 (30 de julio de 2026) y un snapshot actualizado del código real (financeai.md, capturado el 05 de agosto de 2026 con Repomix). Reemplaza y consolida la versión anterior.

* Úsalo como contexto al iniciar una conversación con un asistente de IA (Claude u otro) para generar historias de usuario, tickets técnicos, revisiones de código o planificación de sprint.
* Cada hallazgo técnico tiene un identificador AUD-XX (Sección 6) y cada tarea propuesta tiene un identificador TASK-XXX (Sección 10), para poder referenciarlos sin ambigüedad en conversaciones futuras. Los identificadores AUD-01 a AUD-13 y TASK-001 a TASK-025 se conservan de la v1 para no romper referencias ya usadas por el equipo; los hallazgos y tareas nuevos de esta re-auditoría continúan la numeración desde AUD-14 / TASK-026.
* Las Secciones 6 (Auditoría Técnica) y 10 (Backlog Priorizado) son las de mayor prioridad de lectura para generar el próximo sprint de trabajo.
* Al pedirle tareas a una IA, cita la sección y el ID exacto (ej.: “Genera la tarea técnica detallada para TASK-026 / AUD-14”) para obtener resultados consistentes con este documento.
* Este documento debe actualizarse cada vez que se cierre un hallazgo o se complete una tarea del backlog, para que siga siendo confiable como fuente de contexto.

### 0.1 Convención de Identificadores
**AUD-XX:** hallazgo técnico detectado en la auditoría de código (Sección 6). Representa un bug, riesgo de seguridad o inconsistencia real, no una opinión de estilo.
**TASK-XXX:** tarea concreta del backlog priorizado (Sección 10), lista para convertirse en ticket. Cada TASK-XXX referencia el AUD-XX o la sección que la origina.
**Estados:** 🟢/✅ Completado · 🟡 Parcial / con deuda técnica · 🔴 Pendiente · ⚠️ Bloqueado o riesgoso.

### 0.2 Qué Cambió Desde la v1 (Resumen Ejecutivo)
El snapshot de código de esta re-auditoría es sustancialmente más reciente que el usado en la v1: aparecieron componentes enteros que no existían antes (gestión de perfil financiero, un motor de IA real con modelos entrenados, y una interfaz de login/registro funcional en el frontend). El balance neto:
* **6 hallazgos de la v1 quedaron resueltos 🟢** (AUD-04, AUD-06, AUD-07, AUD-08, AUD-10, AUD-12), incluyendo los dos hallazgos de seguridad más severos del backlog anterior: la fuga de datos en transacciones (AUD-07) y el IDOR de historial (AUD-08).
* **1 hallazgo es una regresión 🔴** (AUD-09: application.yml quedó peor que antes, no simplemente igual de incompleto).
* **5 hallazgos de la v1 siguen sin cambios 🔴** (AUD-01, AUD-02, AUD-03, AUD-05 en parte, AUD-11, AUD-13).
* **8 hallazgos son nuevos 🟣** (AUD-14 a AUD-21), producto directo de las nuevas funcionalidades incorporadas: el motor de IA real no está conectado al backend (tres desalineaciones independientes), el esquema de la tabla de historial no coincide con la entidad JPA, y el flujo de registro/login del frontend tiene puntos de quiebre nuevos.

Conclusión para el PM: el proyecto avanzó de forma real y significativa en dos frentes (seguridad de transacciones/historial, y existencia de un modelo de IA entrenado), pero el camino crítico para una demo end-to-end sigue bloqueado, ahora por una cadena de 6 dependencias secuenciales en lugar de un solo problema aislado. Ver Sección 7.4 para el detalle de esa cadena.

---

## 1. Visión General del Proyecto
### 1.1 Descripción
FinanceAI es una solución inteligente orientada al sector Fintech / Educación Financiera. Su propósito es transformar transacciones brutas en conocimiento útil y accionable para mejorar la salud financiera de los usuarios, mediante análisis de hábitos, clasificación de perfil financiero y recomendaciones automáticas generadas por un motor de IA.

### 1.2 Objetivos del MVP (Funcionalidades Obligatorias)
* Clasificación automática de transacciones: categorización en Alimentación, Transporte, Salud, Vivienda, Educación, Ocio, Servicios, etc.
* Análisis de perfil financiero: clasificación del usuario en Saludable, En observación o En riesgo.
* Recomendaciones personalizadas: consejos prácticos según patrones de consumo e indicadores financieros.
* Exposición RESTful: interfaz JSON documentada (Swagger/OpenAPI) para consumo de clientes y frontend.
* Despliegue OCI: integración obligatoria con al menos un servicio de Oracle Cloud Infrastructure.

### 1.3 Equipo y Stack Tecnológico
*Sin cambios respecto a la v1: se mantiene Java 21 sobre spring-boot-starter-parent 4.1.0 en todo el documento.*

| Rol / Área | Integrantes | Tecnologías principales |
| :--- | :--- | :--- |
| Backend Developers | 3 personas | Java 21, Spring Boot 4.1.0 (Web, Data JPA, Security), Hibernate/JPA, Flyway, PostgreSQL 16 Alpine, JJWT 0.12.6, springdoc-openapi 3.0.3, Docker |
| Data Science | 4 personas | Python, Pandas, Scikit-Learn (1.6.1 en el servicio productivo).<br>Novedad v2: ya existen dos modelos entrenados y serializados (modelo_perfil_financiero.pkl, modelo_clasificacion_transacciones.pkl) sirviendo desde un microservicio FastAPI propio (data-science/modeloFinanceAI). Ver AUD-15 a AUD-17: el modelo existe y funciona en aislamiento, pero no está correctamente conectado al backend todavía. |
| Frontend | No listado explícitamente en el equipo original | HTML5, Bootstrap 5, JavaScript vanilla (fetch API).<br>Novedad v2: ya existe una pantalla de login/registro funcional (index.html + auth.js) y un dashboard.js con lógica de transacciones y análisis, aunque ambos dependen de contratos de API y de una página (dashboard.html) que hoy no existen (ver AUD-03, AUD-18, AUD-19). |
| Project Management | 1 persona | Metodologías ágiles (Sprints / Kanban) |
| Infraestructura Cloud | Equipo general | Oracle Cloud Infrastructure (OCI) — Compute / Object Storage (pendiente, Semana 5) |

### 1.4 Contrato JSON Objetivo (Especificación Original del Producto)
**⚠️ Este es el contrato OBJETIVO original del producto (documentado en el README). NO coincide con la implementación actual del backend ni con lo que hoy envía/espera el frontend real (auth.js / dashboard.js). Ver Sección 3 y AUD-03 para el detalle completo, que en esta v2 se confirma también en runtime, no sólo en el diseño.**

**Request — POST /api/analisis-financiero**
```json
{ "ingreso_mensual": 4500, "nivel_endeudamiento": 25, "frecuencia_ahorro": "Media", "transacciones": [ { "descripcion": "Supermercado", "valor": 420 }, { "descripcion": "Combustible", "valor": 300 }, { "descripcion": "Streaming", "valor": 40 } ] }
```

**Response**
```json
{ "perfil_financiero": "En observacion", "probabilidad": 0.82, "resumen_gastos": { "alimentacion": 420, "transporte": 300, "entretenimiento": 40 }, "recomendaciones": [ "Monitorear gastos recurrentes de entretenimiento", "Aumentar reserva financiera mensual" ] }
```

---

## 2. Arquitectura Real del Sistema
### 2.1 Módulos del Monorepo
*Novedad v2: el motor de IA real vive en un módulo separado del mock-api original, y ambos coexisten hoy en el repositorio y en docker-compose.yml.*

| Módulo | Tecnología | Estado | Descripción |
| :--- | :--- | :--- | :--- |
| backend/ | Java 21 + Spring Boot 4.1.0 | 🟡 En desarrollo activo | API REST principal: autenticación, transacciones, perfil financiero, análisis y su historial. |
| mock-api/ | Python + FastAPI + Pydantic | ⚠️ Obsoleto, no conectado | Simulaba el motor de IA. Sigue en el repo y sigue siendo el destino teórico de ia.api.url, pero ya no aporta valor real ahora que existe un modelo entrenado (ver AUD-17: decidir su retiro). |
| data-science/modeloFinanceAI/ | Python (FastAPI, pandas, scikit-learn, joblib) | 🟡 Funcional en aislamiento, no conectado | Novedad v2. Sirve dos modelos .pkl reales (perfil financiero y clasificación de transacciones) vía POST /analisis-financiero. Responde bien probado de forma directa, pero el backend no le apunta correctamente (AUD-15/16). |
| data-science/ (raíz) | Python (pandas, scikit-learn) | 🟡 Copia duplicada | Contiene una segunda copia casi idéntica del mismo servicio y los mismos .pkl, con un formato de salida distinto (decimales vs. strings con “%”). No está referenciada en docker-compose.yml. Ver AUD-17. |
| frontend/ | HTML + Bootstrap 5 + JS vanilla | 🟡 Parcialmente construido, no integrado | Novedad v2: ya existe login/registro real (index.html, auth.js) con manejo de JWT en localStorage. dashboard.js también existe pero apunta a endpoints inexistentes y a una página dashboard.html que no está en el repo (AUD-03, AUD-18). |

### 2.2 Flujo de Comunicación (Previsto vs. Real)
Frontend  →  Backend (Spring Boot, puerto 8080)  →  Motor de IA (hoy con dos candidatos: mock-api en :8001, o modelo-financeai en :8000)  →  Backend persiste el historial en PostgreSQL. El backend también persiste usuarios, transacciones y perfil financiero directamente en PostgreSQL vía Spring Data JPA / Flyway.

**Nota de infraestructura (actualizada):** docker-compose.yml en la raíz del repo ya orquesta cuatro servicios: postgres-db, mock-api (puerto host 8001), modelo-financeai (puerto host 8000) y backend. Sin embargo, la variable IA_API_URL del backend sigue apuntando a http://mock-api:8000 — un host correcto pero con el puerto equivocado (mock-api escucha internamente en 8001, no 8000), y el servicio que sí escucha en el puerto 8000 es modelo-financeai, con otro nombre de host. Ver AUD-15.

### 2.3 Estructura de Paquetes del Backend (actualizada)
*com.nocountry.financeai*
```text
client/            IAClient (RestClient hacia el motor de IA)
config/            CorsConfig, OpenApiConfig, OrdenOpenApi, RestClientConfig
controller/        AnalisisController, AuthController, HistorialAnalisisController,
                   PerfilFinancieroController (nuevo), TestSecurityController (nuevo),
                   TransactionController
dto/request/       AnalisisRequest, LoginRequest, PerfilFinancieroRequest (nuevo),
                   RegisterRequest, TransactionRequest
dto/response/      AnalisisResponse, AuthResponse, ErrorResponse,
                   HistorialAnalisisResponse, PerfilFinancieroResponse (nuevo),
                   TransaccionResponse (nuevo)
entity/enums/      EstadoCivil, MedioPago, PerfilFinanciero, RangoAhorro, Rol, Sexo (todos nuevos salvo PerfilFinanciero)
entity/            HistorialAnalisisEntity, PerfilFinancieroEntity (nuevo), TransactionEntity, UserEntity
exception/         ApiExceptionHandler, ResourceNotFoundException (nuevo), UserAlreadyExistsException
repository/        HistorialAnalisisRepository, PerfilFinancieroRepository (nuevo),
                   TransactionRepository, UserRepository
security/          CustomUserDetailsService, JwtAuthFilter, JwtUtil, SecurityConfig
service/impl/      AnalisisIAServiceImpl, AuthServiceImpl, HistorialAnalisisServiceImpl,
                   PerfilFinancieroServiceImpl (nuevo), TransaccionServiceImpl (nuevo)
FinanceaiApplication
```
La incorporación más relevante desde la v1 es el módulo completo de Perfil Financiero (entidad, repositorio, service, controller y DTOs), que ahora es un prerrequisito obligatorio para poder generar un análisis (AnalisisIAServiceImpl.analizarPorUsuarioId lanza ResourceNotFoundException si el usuario no tiene perfil). El frontend actual no llama a este endpoint nuevo (ver AUD-19).

---

## 3. Contrato de API: Objetivo vs. Estado Actual
La v1 ya documentaba que el contrato README/frontend no coincidía con el backend real. En esta v2 se confirma que el problema no es sólo teórico: el frontend ya tiene código ejecutable (dashboard.js) que llama activamente a endpoints que no existen en el backend.

| Aspecto | Contrato objetivo (README) | Backend real (Java) | Frontend real (dashboard.js) |
| :--- | :--- | :--- | :--- |
| Endpoint transacciones | POST /api/analisis-financiero (todo en un solo request) | POST /api/v1/transacciones/usuario/transacciones | POST /transactions (con BASE_URL http://localhost:8080/api/v1) → URL final inexistente |
| Endpoint análisis | incluido en el mismo request | POST /api/v1/analisis/predict | POST /analisis → URL final inexistente |
| Payload transacción | { descripcion, valor } | { nombre_comercio, monto_transaccion, medio_pago } | { descripcion, valor } — coincide con el README, no con el backend |
| Autenticación | No especificada | Requiere JWT (Bearer) en casi todas las rutas | Sí envía Bearer token, pero contra las URLs incorrectas de arriba |
| Valores de perfil_financiero | Saludable / En observación / En riesgo (texto libre) | Enum Java: SALUDABLE, EN_OBSERVACION, RIESGO | dashboard.js ya contempla un cuarto valor: 'EN_RIESGO', que tampoco coincide con el enum Java (ver AUD-02) |

### 3.1 Recomendación del PM (sin cambios de fondo)
Se mantiene la recomendación de la v1: fijar un único contrato antes de continuar con nuevas features (TASK-003), conservando el versionado /api/v1/... del backend y evaluando snake_case global vía Jackson (spring.jackson.property-naming-strategy: SNAKE_CASE) para hablar el mismo idioma que el README y el frontend. Esta vez con un agravante: dashboard.js ya fue escrito asumiendo nombres de endpoint y de payload que no son ni el contrato README ni el contrato backend real (usa /transactions en inglés, que no aparece en ninguno de los dos documentos previos) — es un tercer dialecto a reconciliar, no sólo dos.

---

## 4. Configuración de Entorno
### 4.1 docker-compose.yml (raíz del repo, vigente)
*Novedad v2: ahora orquesta 4 servicios (antes sólo PostgreSQL). Ver AUD-15 sobre el desalineamiento de host/puerto que esto introdujo.*
*postgres-db (5432) · mock-api (host 8001 → contenedor 8001) · modelo-financeai (host 8000 → contenedor 8000) · backend (8080), con IA_API_URL=http://mock-api:8000 — combinación de host y puerto que no corresponde a ningún servicio real (ver AUD-15).*

### 4.2 application.yml del backend — ⚠️ Regresión detectada (AUD-09)
**Hallazgo nuevo relevante: 🔴** backend/src/main/resources/application.yml ya no contiene siquiera spring.application.name (que sí tenía en la v1). Su contenido actual es, literalmente, una copia del docker-compose.yml de la raíz (bloques services:, postgres-db:, volumes:, etc.), probablemente pegada por error o resultado de un merge mal resuelto. Un application.yml con sintaxis de docker-compose no es YAML de Spring Boot válido para configuración de la app; en el mejor caso Spring lo ignora silenciosamente, en el peor caso puede fallar el arranque según el classpath. Se prioriza como P0 por su bajo costo de arreglo y su alto impacto en el arranque local de cualquier integrante nuevo.

### 4.3 Variables de Entorno Requeridas
| Variable | Descripción | Dónde se usa | Estado |
| :--- | :--- | :--- | :--- |
| SPRING_DATASOURCE_URL / _USERNAME / _PASSWORD | Credenciales y cadena JDBC hacia PostgreSQL | application.properties (local, gitignored) / docker-compose | 🟡 Sólo local o vía compose, no versionada como plantilla (AUD-09 sigue relacionado) |
| ia.api.url (IA_API_URL) | URL base del motor de IA consumido por IAClient | RestClientConfig | 🔴 Configurada, pero apunta a un host:puerto que no sirve el modelo real (AUD-15) |
| jwt.secret | Clave HMAC para firmar los tokens | JwtUtil | ⚠️ Sigue con un valor por defecto embebido en el código fuente (AUD-13, sin cambios desde v1) |
| jwt.expiration | Tiempo de expiración del token en ms | JwtUtil | 🟢 Sin cambios, aceptable para desarrollo |
| server.port | Puerto del backend | Por defecto 8080 | 🟢 OK |

### 4.4 Historial de Diagnóstico y Resoluciones (Bitácora del Equipo)
Se conserva la bitácora completa de la v1 (ver documento anterior); se agrega la siguiente entrada correspondiente a esta re-auditoría:
* 2026-08-05 — Re-auditoría cruzada de PM: se contrastó la Nota Maestra v1 (30-jul) contra un nuevo snapshot Repomix del código real. Se confirmaron 6 hallazgos resueltos, 1 regresión (AUD-09) y se detectaron 8 hallazgos nuevos (AUD-14 a AUD-21), en su mayoría producto de la incorporación del motor de IA real y del módulo de perfil financiero. Esta nota maestra v2 reemplaza a la v1 como fuente de verdad.

---

## 5. Dependencias del Backend (pom.xml)
Parent: org.springframework.boot:spring-boot-starter-parent:4.1.0 · `<java.version>21</java.version>`.

| Dependencia | Versión | Propósito |
| :--- | :--- | :--- |
| spring-boot-starter-data-jpa | gestionada por el parent | Persistencia JPA/Hibernate sobre PostgreSQL. |
| spring-boot-starter-validation | gestionada por el parent | Bean Validation (Jakarta) para DTOs. |
| spring-boot-starter-web | gestionada por el parent | MVC / API REST. |
| spring-boot-starter-security | gestionada por el parent | Autenticación y autorización. |
| spring-boot-starter-flyway + flyway-core + flyway-database-postgresql | gestionada por el parent | Migraciones versionadas del esquema (V1–V4, con V5 propuesta en TASK-026). |
| postgresql (runtime) | gestionada por el parent | Driver JDBC. |
| lombok (optional) | gestionada por el parent | Reducción de boilerplate. |
| jjwt-api / jjwt-impl / jjwt-jackson | 0.12.6 | Emisión y validación de JWT. |
| springdoc-openapi-starter-webmvc-ui | 3.0.3 — corrección v2 | Swagger UI / documentación OpenAPI. |
| spring-boot-starter-test | gestionada por el parent | JUnit 5 + Mockito para testing. |

**Corrección aplicada en v2:** la Nota Maestra v1 documentaba springdoc-openapi 2.6.0; el pom.xml real hoy fija la versión 3.0.3. Además, el bloque `<configuration>` del maven-compiler-plugin en el pom.xml real está mal escrito como `<coniguration>` (falta la primera “f”), lo que probablemente hace que Maven ignore ese bloque completo y procese Lombok por su mecanismo por defecto en vez del explícito (ver AUD-21).

---

## 6. Auditoría Técnica — Re-auditoría sobre Snapshot Actualizado
Metodología: se contrastó línea por línea el nuevo snapshot financeai.md (05-ago-2026) contra la Nota Maestra v1 (30-jul-2026) y contra sí mismo entre módulos (backend, mock-api, motor de IA real, frontend). Los 13 hallazgos originales (AUD-01 a AUD-13) se re-verifican uno por uno; se agregan los hallazgos AUD-14 a AUD-21, detectados por primera vez en esta ronda.

### 6.1 Índice de Hallazgos (actualizado)
| ID | Severidad | Componente | Título | Estado en v2 |
| :--- | :--- | :--- | :--- | :--- |
| AUD-01 | Alta | Backend / Auth | AuthResponse: campos message/email invertidos | 🔴 Sin cambios |
| AUD-02 | Alta | Backend + Motor IA | Enum de perfil financiero inconsistente (RIESGO / EN_RIESGO / RIESGOSO) | 🔴 Sin cambios, ahora 3 variantes |
| AUD-03 | Alta | Backend + Frontend | Contrato de API desalineado (path y payload) | 🔴 Sin cambios, confirmado en runtime |
| AUD-04 | Media | Backend + Mock API | Campo transactions vs. transacciones | ✅ Resuelto |
| AUD-05 | Alta | Mock API | El endpoint /predict ignora el body recibido | 🟡 Superado en sustancia por AUD-15/16/17 |
| AUD-06 | Alta | Backend / Análisis | usuarioId nunca se asigna en HistorialAnalisisEntity | ✅ Resuelto |
| AUD-07 | Alta (seguridad) | Backend / Transactions | Sin autorización ni DTO propio: fuga de datos | ✅ Resuelto |
| AUD-08 | Alta (seguridad) | Backend / Historial | IDOR en historial de análisis | ✅ Resuelto |
| AUD-09 | Media | Backend / Config | application.yml casi vacío, sin plantilla | 🔴 Regresión: peor que en v1 |
| AUD-10 | Baja | Backend / Entity | UserEntity.apellido nunca se puebla | ✅ Resuelto |
| AUD-11 | Baja | Backend / Config | spring.jpa.open-in-view=false pendiente | 🔴 Sin cambios |
| AUD-12 | Media | Backend / DTO | TransactionRequest mal aprovechado | ✅ Resuelto |
| AUD-13 | Alta (seguridad) | Backend / Security | jwt.secret hardcodeado | 🔴 Sin cambios |
| AUD-14 | Alta — nuevo | Backend / Persistencia | Esquema de historial_analisis no coincide con la entidad JPA | 🔴 Nuevo |
| AUD-15 | Alta — nuevo | Infra / IAClient | Motor de IA real inalcanzable: host, puerto y ruta desalineados | 🔴 Nuevo |
| AUD-16 | Alta — nuevo | Backend + Data Science | Contrato de respuesta del motor real no coincide con AnalisisResponse | 🔴 Nuevo |
| AUD-17 | Media — nuevo | Data Science | Motor de IA duplicado, con salidas incompatibles entre sí | 🔴 Nuevo |
| AUD-18 | Media — nuevo | Frontend | dashboard.html no existe; scripts cargados en la página equivocada | 🔴 Nuevo |
| AUD-19 | Media — nuevo | Frontend + Backend | El registro no crea el perfil financiero, bloqueando el análisis | 🔴 Nuevo |
| AUD-20 | Baja — nuevo | Backend / Entity | Typo en enum Sexo (FEMININO) | 🔴 Nuevo |
| AUD-21 | Baja — nuevo | Backend / Config | Typo en pom.xml (<coniguration>) y versión de springdoc desactualizada en docs | 🔴 Nuevo |

*Balance de la re-auditoría: 6 hallazgos resueltos · 1 regresión · 5 sin cambios · 1 superado en sustancia · 8 nuevos. Total de hallazgos activos hoy: 15 de 21.*

### 6.2 Hallazgos Re-verificados de la v1 (AUD-01 a AUD-13)
*Se conserva el detalle original de cada hallazgo (ver Nota Maestra v1, Sección 6.2, para el texto completo de Hallazgo / Impacto / Acción recomendada); a continuación sólo se documenta el resultado de la re-verificación de cada uno contra el snapshot actualizado.*

#### AUD-01 — AuthResponse: campos invertidos — 🔴 Sin cambios
El record AuthResponse(String message, String email) sigue instanciándose como new AuthResponse(token, "mensaje...") en AuthServiceImpl. Con agravante nuevo: frontend/js/auth.js ya existe y hace localStorage.setItem('jwtToken', data.token) — un campo token que no existe en la respuesta real. El login/registro desde el navegador falla en silencio (localStorage guarda undefined) aunque el backend responda 200 OK.

#### AUD-02 — Enum de perfil financiero inconsistente — 🔴 Sin cambios, ahora con una tercera variante
Persisten los dos valores documentados en v1 (Java: RIESGO; mock-api antiguo: EN_RIESGO). Se detecta un tercer candidato: data-science/modeloFinanceAI/main.py compara perfil_str == "RIESGOSO", lo que sugiere que la clase real que produce el modelo entrenado es "RIESGOSO" — un tercer literal, distinto de los otros dos. dashboard.js, por su parte, ya contempla un cuarto: 'EN_RIESGO' para el color del badge. Antes de fijar el valor unificado hace falta inspeccionar model.classes_ del .pkl para saber cuál es la verdad de base.

#### AUD-03 — Contrato de API desalineado — 🔴 Sin cambios, confirmado en runtime
Ver detalle completo actualizado en la Sección 3. La novedad es que ya no es un desalineamiento teórico entre documentos: dashboard.js llama activamente a /transactions y /analisis, ninguno de los cuales existe en el backend real.

#### AUD-04 — Nombre del campo de transacciones — ✅ Resuelto
El componente del record AnalisisRequest ya se llama transacciones (antes transactions), coincidiendo con el campo requerido por el modelo Pydantic del mock. Sin acción adicional necesaria salvo si se retira el mock-api (ver AUD-17).

#### AUD-05 — El mock API ignora el body — 🟡 Superado en sustancia, pendiente de decisión formal
mock-api/app/routers/analisis.py no cambió: sigue sin declarar parámetro de request. Pero ya existe un motor de IA real (modelo-financeai) que sí procesa el body con un modelo entrenado. El hallazgo original queda parcialmente obsoleto: el problema ya no es “no hay lógica real” sino “hay que decidir si el mock se retira o se documenta como stub de desarrollo” (ver AUD-17, TASK-029).

#### AUD-06 — usuarioId nunca se asigna — ✅ Resuelto
AnalisisIAServiceImpl.guardarHistorial() ahora invoca .usuario(usuario) en el builder antes de guardar. El historial ya queda correctamente asociado al usuario autenticado. Nota: este arreglo expone un problema distinto y nuevo — AUD-14 — sobre el esquema de la tabla destino.

#### AUD-07 — TransactionController sin autorización ni DTO propio — ✅ Resuelto
Reescrito por completo: usa TransactionRequest (DTO, no la entidad JPA), separa las rutas administrativas (/usuario/{usuarioId}, protegidas con @PreAuthorize("hasRole('ADMIN')")) de las rutas del propio usuario (/usuario/transacciones, que derivan el usuario del @AuthenticationPrincipal). Ya no hay mass assignment ni fuga de datos entre cuentas. Es el arreglo de seguridad más significativo entre ambas versiones.

#### AUD-08 — IDOR en HistorialAnalisisController — ✅ Resuelto
Se agregó el endpoint /api/v1/analisis/usuario/historial, que deriva el usuario del token JWT vía @AuthenticationPrincipal. La ruta original por userId libre (/usuario/{userId}) se restringió con @PreAuthorize("hasRole('ADMIN')"). Ya no es posible leer el historial de otro usuario con un token válido propio.

#### AUD-09 — application.yml casi vacío — 🔴 Regresión: el archivo empeoró
Ver detalle completo en la Sección 4.2. En v1 el archivo al menos definía spring.application.name; hoy contiene contenido de docker-compose.yml pegado por error, sin ninguna propiedad válida de Spring Boot. Se reclasifica de Media a Alta por su impacto potencial en el arranque.

#### AUD-10 — UserEntity.apellido nunca se puebla — ✅ Resuelto
RegisterRequest ya incluye el campo apellido con validación @NotBlank, y AuthServiceImpl.register() lo asigna correctamente al construir la entidad.

#### AUD-11 — spring.jpa.open-in-view=false pendiente — 🔴 Sin cambios
Sigue sin aplicarse. No se puede verificar su efecto hasta resolver AUD-09, dado que hoy no hay un application.yml funcional donde colocarlo.

#### AUD-12 — TransactionRequest mal aprovechado — ✅ Resuelto
Como consecuencia directa del arreglo de AUD-07, TransactionRequest ya es el DTO real usado tanto en el alta de transacciones del usuario autenticado como en la ruta administrativa. Ya no hay ambigüedad sobre su propósito.

#### AUD-13 — jwt.secret con valor por defecto hardcodeado — 🔴 Sin cambios
JwtUtil conserva el mismo @Value("${jwt.secret:404E...}") con el fallback embebido y versionado en Git. Sigue siendo el hallazgo de seguridad abierto más severo del proyecto.

### 6.3 Hallazgos Nuevos (AUD-14 a AUD-21)

#### AUD-14 — Esquema de historial_analisis no coincide con la entidad JPA
**Severidad:** Alta   ·   **Componente:** V3__create_analysis_table.sql vs. HistorialAnalisisEntity
Hallazgo: la migración Flyway V3 define las columnas frecuencia_ahorro (VARCHAR) y nivel_endeudamiento (INTEGER). La entidad HistorialAnalisisEntity, en cambio, mapea @Column(name = "rango_ahorro") — una columna que la migración nunca creó — y define nivelEndeudamiento como BigDecimal(4,2), un tipo incompatible con INTEGER.
Impacto: cualquier intento de persistir un HistorialAnalisisEntity falla con un error SQL (columna inexistente / tipo incompatible), incluso después de que AUD-06 ya propaga correctamente el usuarioId. Este hallazgo bloquea en la práctica el mismo flujo que AUD-06 acababa de destrabar.
**Acción recomendada (TASK-026):** crear una migración Flyway V5 que renombre/ajuste frecuencia_ahorro → rango_ahorro (VARCHAR) y corrija el tipo de nivel_endeudamiento a NUMERIC(4,2), alineando el esquema real con lo que la entidad ya espera. No editar V3, que ya pudo haberse aplicado en ambientes existentes.

#### AUD-15 — Motor de IA real inalcanzable: host, puerto y ruta desalineados
**Severidad:** Alta   ·   **Componente:** docker-compose.yml, RestClientConfig, IAClient
Hallazgo: se detectan tres desalineaciones independientes, cualquiera de las cuales por sí sola ya rompe la integración: (a) IA_API_URL=http://mock-api:8000, pero el contenedor mock-api expone el puerto 8001, no 8000; (b) el servicio que sí escucha en el puerto 8000 es modelo-financeai, con otro nombre de host dentro de la red de Docker; (c) IAClient.analizar() llama siempre a .uri("/predict"), una ruta que existe en el mock-api antiguo pero no en el motor real, cuyo endpoint es POST /analisis-financiero.
Impacto: hoy, con la configuración actual, la llamada del backend al motor de IA fallará sin importar cuál de los dos servicios se pretenda usar, por al menos dos de las tres razones simultáneamente.
**Acción recomendada (TASK-027):** decidir explícitamente cuál motor es el canónico (recomendación: modelo-financeai, por ser el que tiene modelos entrenados reales), corregir IA_API_URL al host:puerto correcto de ese servicio, y actualizar la ruta en IAClient a /analisis-financiero.

#### AUD-16 — Contrato de respuesta del motor real no coincide con AnalisisResponse
**Severidad:** Alta   ·   **Componente:** AnalisisResponse (Java) vs. data-science/modeloFinanceAI/main.py
Hallazgo: incluso resolviendo AUD-15, el body de respuesta no es deserializable tal como está. El motor real devuelve perfilFinanciero y resumenGastos (camelCase, sin guion bajo), mientras que AnalisisResponse espera perfil_financiero y resumen_gastos vía @JsonProperty. Más grave aún: probabilidad y nivel_endeudamiento llegan como strings con formato porcentual (ej. "87.5%"), donde el DTO Java los tipa como BigDecimal — esto no es un desalineamiento de nombre, es un error de deserialización que Jackson no puede resolver solo.
Impacto: la llamada HTTP tendría éxito (200 OK), pero restClient.retrieve().body(AnalisisResponse.class) lanzará una excepción de conversión, resultando en un 500 genérico para el usuario final incluso con AUD-15 ya resuelto.
**Acción recomendada (TASK-028):** en el servicio de Data Science, emitir los campos con los nombres que el backend ya espera (o agregar @JsonAlias en el lado Java) y devolver probabilidad y nivel_endeudamiento como valores numéricos puros (sin el sufijo “%”), dejando el formato de presentación como responsabilidad del frontend.

#### AUD-17 — Motor de IA duplicado, con salidas incompatibles entre sí
**Severidad:** Media   ·   **Componente:** data-science/ (raíz) vs. data-science/modeloFinanceAI/
Hallazgo: existen dos copias casi idénticas del servicio de inferencia, cada una con su propio par de archivos .pkl. Ambas cargan los mismos modelos y calculan las mismas métricas, pero difieren en el formato de salida: la copia en la raíz devuelve nivel_endeudamiento y probabilidad como valores decimales puros; la copia en modeloFinanceAI/ (la que está conectada en docker-compose.yml) los devuelve como strings con “%”. Sólo esta última está referenciada en la infraestructura.
Impacto: riesgo de que un integrante del equipo edite la copia equivocada, o de que una futura re-auditoría compare AnalisisResponse contra el archivo que no está en producción. Genera confusión sobre cuál es la fuente de verdad del modelo.
**Acción recomendada (TASK-029):** eliminar la copia no referenciada (data-science/, raíz) o documentar explícitamente por qué se conserva (ej. como notebook de entrenamiento vs. servicio de inferencia), dejando una sola carpeta como canónica para servir el modelo.

#### AUD-18 — dashboard.html no existe; scripts cargados en la página equivocada
**Severidad:** Media   ·   **Componente:** frontend/index.html, auth.js, dashboard.js
Hallazgo: tras un login o registro exitoso, auth.js hace window.location.href = 'dashboard.html', un archivo que no existe en el repositorio (frontend/ sólo contiene index.html). Además, dashboard.js está cargado como <script> dentro de index.html —la propia pantalla de login— por lo que su lógica de “verificar sesión activa y cargar transacciones” se ejecuta sobre la pantalla de login antes de que exista una sesión, no sobre un dashboard real.
Impacto: incluso si AUD-01 se resolviera y el login funcionara correctamente, el usuario llegaría a una página en blanco (error 404 del navegador) en vez de a un dashboard.
**Acción recomendada (TASK-030):** crear frontend/dashboard.html como una página separada de index.html, mover ahí la carga de dashboard.js y api.js, y dejar en index.html únicamente auth.js.

#### AUD-19 — El registro no crea el perfil financiero, bloqueando el análisis
**Severidad:** Media   ·   **Componente:** frontend/js/auth.js, RegisterRequest, PerfilFinancieroController
Hallazgo: el formulario de registro en index.html sigue capturando y enviando ingresoMensual, lineaCredito y empleoFormal dentro del payload de POST /api/v1/auth/register. Pero esos campos ya no forman parte de RegisterRequest (se movieron a un endpoint independiente, POST /api/v1/perfil, agregado en esta misma iteración del backend). Jackson ignora en silencio los campos desconocidos: el registro se completa “con éxito” pero el perfil financiero nunca se crea, y el frontend nunca llama al endpoint nuevo.
Impacto: AnalisisIAServiceImpl.analizarPorUsuarioId() exige que exista un perfil financiero y lanza ResourceNotFoundException si no lo encuentra. Todo usuario registrado desde el frontend actual queda, sin saberlo, incapacitado para generar un análisis.
**Acción recomendada (TASK-031):** tras un registro exitoso, encadenar automáticamente una llamada a POST /api/v1/perfil con los datos ya capturados en el mismo formulario, antes de redirigir al dashboard.

#### AUD-20 — Typo en el enum Sexo (FEMININO)
**Severidad:** Baja   ·   **Componente:** entity/enums/Sexo.java
Hallazgo: el enum define FEMININO en lugar de FEMENINO. Bajo impacto funcional directo (el valor se usa de forma consistente en todo el backend), pero si el modelo de Data Science fue entrenado con la categoría “femenino” escrita correctamente, este valor cae fuera de vocabulario para cualquier encoder categórico que dependa del texto exacto.
**Acción recomendada (TASK-032):** corregir el nombre del enum a FEMENINO, coordinando con el equipo de Data Science para confirmar que no rompe el encoding usado al entrenar los modelos .pkl.

#### AUD-21 — Typo en pom.xml y versión de springdoc desactualizada en la documentación
**Severidad:** Baja   ·   **Componente:** backend/pom.xml
Hallazgo: el bloque de configuración del maven-compiler-plugin está escrito como <coniguration> (falta la primera “f”) en ambas aperturas y cierres. Es probable que Maven ignore silenciosamente ese bloque completo, dependiendo en cambio del procesamiento por defecto de anotaciones para Lombok. Adicionalmente, la Nota Maestra v1 documentaba springdoc-openapi 2.6.0, mientras que el pom.xml real ya fija 3.0.3 (corregido en la Sección 5 de esta v2).
**Acción recomendada (TASK-033):** corregir el typo a <configuration> y verificar con mvn clean package que Lombok se siga procesando correctamente; no se requiere acción adicional sobre la versión de springdoc, ya documentada.

---

## 7. Estado Real por Vertical Slice (actualizado)
Los estados siguientes corrigen el estatus reportado en la v1, cruzándolo contra el código real y los hallazgos actualizados de la Sección 6.

### 7.1 Slice 1 — Autenticación (Auth)
| Capa | Estado real | Nota |
| :--- | :--- | :--- |
| Backend: Seguridad base (SecurityFilterChain, JwtUtil, JwtAuthFilter) | ✅ Completo | Sin cambios respecto a v1. |
| Backend: Endpoints register / login | 🟡 Funcional con deuda | Bloqueado semánticamente por AUD-01 (sin cambios) y con riesgo de seguridad por AUD-13 (sin cambios). |
| Backend: Validación de funcionalidad (tests) | 🔴 Pendiente | Sin cambios (TASK-013). |
| Frontend: Login / Registro | 🟡 Existe, pero no funcional end-to-end | Novedad v2: index.html + auth.js ya implementan el flujo completo de UI, pero rompen en tres puntos: AUD-01 (token mal nombrado), AUD-18 (dashboard.html inexistente) y AUD-19 (perfil financiero nunca se crea). |

### 7.2 Slice 2 — Gestión de Transacciones
| Capa | Estado real | Nota |
| :--- | :--- | :--- |
| Backend: Persistencia (entidad, repositorio, migración) | ✅ Completo | Sin cambios. |
| Backend: Controlador y lógica de negocio | ✅ Reescrito y seguro | AUD-07 resuelto: ya no es una vulnerabilidad activa. Separación correcta entre rutas admin y rutas del usuario autenticado, con DTO propio. |
| Frontend: Dashboard de transacciones | 🔴 No funcional end-to-end | dashboard.js llama a /transactions con payload { descripcion, valor }; el backend real espera /api/v1/transacciones/usuario/transacciones con { nombre_comercio, monto_transaccion, medio_pago } (AUD-03). Además depende de una página que no existe (AUD-18). |

### 7.3 Slice 3 — Análisis Financiero e IA
| Capa | Estado real | Nota |
| :--- | :--- | :--- |
| Backend: DTOs, cliente REST, entidad de historial | 🟡 Completo estructuralmente, con un bug nuevo | AUD-02 (enum, sin cambios) y AUD-06 (resuelto) pero AUD-14 (nuevo) bloquea la persistencia del historial. |
| Backend: Integración de negocio real (perfil financiero requerido) | 🟡 Implementado, pero sin insumos del frontend | Novedad v2: el módulo de Perfil Financiero (entidad/servicio/controller) ya existe y funciona, pero el frontend nunca lo alimenta (AUD-19). |
| Motor de IA | 🟡 De 0% a modelo entrenado real, pero desconectado | Salto más grande del proyecto desde la v1: existen dos modelos .pkl funcionales en aislamiento (AUD-17), pero el backend no puede alcanzarlos por errores de configuración (AUD-15) y de contrato (AUD-16). |
| Frontend: Vista de diagnóstico e historial | 🔴 No existe en el repositorio | Sin cambios respecto a v1. |

### 7.4 Camino Crítico para una Demo End-to-End (nuevo análisis)
Para que un usuario pueda completar el flujo registrarse → iniciar sesión → ver su dashboard → cargar una transacción → pedir un análisis → ver una recomendación en pantalla, existen hoy seis bloqueos secuenciales. Cada uno oculta al siguiente hasta que se resuelve, por lo que no alcanza con arreglar uno o dos ítems aislados para tener una demo funcional:
* **1. AUD-01** — sin esto no hay token utilizable en el cliente; no se puede ni completar un login.
* **2. AUD-18** — sin dashboard.html, no hay a dónde navegar después del login.
* **3. AUD-19** — sin perfil financiero cargado, el análisis no puede ejecutarse más adelante.
* **4. AUD-03** — el dashboard llama URLs y payloads que no existen en el backend real.
* **5. AUD-15 + AUD-16** — aunque todo lo anterior se resuelva, la llamada al motor de IA falla por host/puerto/ruta y por incompatibilidad de formato de respuesta.
* **6. AUD-14** — incluso si el motor de IA respondiera correctamente, guardar el resultado en historial_analisis falla por el desalineamiento de esquema.

Recomendación de secuencia: los ítems 1, 2 y 3 son requisitos de UI/flujo y no dependen de infraestructura — pueden resolverse en paralelo por el equipo de frontend. Los ítems 5 y 6 requieren coordinación entre Backend y Data Science para acordar el contrato de datos. El ítem 4 es el más costoso en tiempo (reescritura de dashboard.js) y conviene abordarlo después de fijar el contrato definitivo en la Sección 3.1, para no reescribirlo dos veces.

---

## 8. Hoja de Ruta / Cronograma Ágil (Actualizado v2)
Estatus general (05 de agosto de 2026): el equipo avanzó sustancialmente en seguridad (Slice 2) y en el motor de IA (Slice 3, antes 0%), pero el Sprint de Estabilización propuesto en la v1 quedó parcialmente ejecutado: 4 de los 8 hallazgos de severidad Alta originales siguen abiertos, y se sumaron 3 hallazgos Alta nuevos. Se recomienda un segundo Sprint de Estabilización antes de continuar con features nuevas.

### Sprint de Estabilización v2 (bloqueante, antes de nuevas features)
Objetivo: cerrar los hallazgos de severidad Alta que siguen abiertos o son nuevos (AUD-01, 02, 03, 09, 13, 14, 15, 16) antes de intentar una demo end-to-end. Corresponde al grupo P0/P1 del backlog (Sección 10).
* ✅ Corregir AuthResponse (AUD-01 / TASK-001) — arrastrado de la v1, sigue sin resolver.
* ✅ Restaurar application.yml real (AUD-09 / TASK-010) — regresión, prioridad alta por su bajo costo.
* ✅ Eliminar jwt.secret hardcodeado (AUD-13 / TASK-009) — arrastrado de la v1.
* ✅ Crear dashboard.html y reordenar scripts (AUD-18 / TASK-030) — nuevo, bloquea toda navegación post-login.
* ✅ Nueva migración V5 para alinear historial_analisis (AUD-14 / TASK-026) — nuevo, bloquea la persistencia de análisis.
* 🔴 Alinear host/puerto/ruta del motor de IA (AUD-15 / TASK-027) — nuevo.
* 🔴 Alinear contrato de respuesta del motor de IA (AUD-16 / TASK-028) — nuevo.
* 🔴 Unificar el valor del enum de perfil de riesgo (AUD-02 / TASK-002) — arrastrado, requiere inspeccionar el modelo .pkl.
* 🔴 Fijar contrato único de API y conectar el flujo de perfil financiero (AUD-03 + AUD-19 / TASK-003 + TASK-031).

### ✅ Progreso desde la v1 (ya no requiere trabajo adicional)
* Rediseño seguro de TransactionController con DTO y autorización (AUD-07).
* Corrección del IDOR en HistorialAnalisisController (AUD-08).
* Propagación de usuarioId al guardar historial (AUD-06).
* Unificación del nombre de campo transacciones (AUD-04).
* Población de UserEntity.apellido (AUD-10).
* Aprovechamiento correcto de TransactionRequest como DTO (AUD-12).
* Existencia de un motor de IA real con modelos entrenados (antes 0% de integración con OCI/Data Science).
* Existencia de una pantalla de login/registro funcional en el frontend (antes “no existe en el repositorio”).

### Semanas 2 a 5 (sin cambios de fondo respecto a la v1)
El resto del cronograma original (Semana 2: Core Bancario, Semana 3: Motor de IA, Semana 4: Refinamiento, Semana 5: Despliegue OCI) se mantiene vigente en su estructura. Ver Sección 10 para el detalle de tareas actualizado con los nuevos identificadores TASK-026 a TASK-033.

---

## 9. Convenciones y Definition of Done
### 9.1 Convención de Contratos y Nombres (decisión pendiente del equipo)
Sin cambios respecto a la v1: conviven records de Java 21 y clases Lombok @Data sin una regla explícita. Se agrega a esta versión que los DTOs nuevos del módulo de Perfil Financiero (PerfilFinancieroRequest, PerfilFinancieroResponse) ya siguen el patrón recomendado (records inmutables), lo cual es una buena señal de consistencia hacia adelante. Se mantiene la recomendación: usar records para DTOs inmutables sin lógica adicional, reservando @Data sólo si se necesita mutabilidad real.

### 9.2 Checklist — Definition of Done
* El endpoint/feature respeta el contrato de API vigente (Sección 3) y no introduce un nuevo casing o path ad-hoc.
* Toda entrada de usuario pasa por un DTO validado con Jakarta Validation — nunca se expone una @Entity directamente en un @RequestBody o @ResponseBody.
* Toda consulta o mutación de datos sensibles filtra explícitamente por el usuario autenticado extraído del SecurityContext.
* No se introducen nuevos valores por defecto de secretos/credenciales en el código fuente (ver AUD-13, todavía abierto).
* Se agregó o actualizó al menos un test (JUnit/Mockito) que cubra el camino feliz y un camino de error relevante.
* Los cambios en el esquema de base de datos se realizan mediante una nueva migración Flyway (nunca editando una migración ya aplicada) — ver AUD-14 como ejemplo concreto de por qué esta regla importa.
* Swagger/OpenAPI (springdoc) refleja el endpoint nuevo o modificado con sus @Schema y ejemplos.
* Si la tarea cierra un hallazgo AUD-XX, se marca como resuelto en la Sección 6 al actualizar este documento.

### 9.3 Convención de Ramas y Commits
Sin cambios respecto a la v1: feature/slice-{n}-{descripcion-corta} · fix/AUD-{nn}-{descripcion-corta} · Conventional Commits (feat:, fix:, chore:, docs:, test:, refactor:) con referencia al TASK-XXX o AUD-XX en el cuerpo del commit.

### 9.4 Testing Mínimo Esperado
Sin cambios respecto a la v1. Se resalta que, a pesar del progreso funcional entre versiones, no se detectaron tests nuevos en el snapshot actualizado — TASK-013 sigue plenamente vigente y su ausencia es la razón por la cual varios de los hallazgos de esta sección se hubieran detectado antes con una suite mínima (por ejemplo, AUD-14 se habría detectado con un solo test de integración que persista un historial de análisis).

---

## 10. Backlog Priorizado — Próximas Tareas (actualizado v2)
Se conservan los identificadores TASK-001 a TASK-025 de la v1 (con su estado actualizado); las tareas nuevas de esta re-auditoría continúan la numeración desde TASK-026.

### P0 — Sprint de Estabilización v2 (bloqueante, antes de continuar)
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-001 | ✅ Corregir campos de AuthResponse (token / message) | AUD-01 | El JSON de login/registro expone un campo token explícito y uno message descriptivo, sin datos cruzados. |
| TASK-009 | ✅ Externalizar jwt.secret y eliminar el fallback hardcodeado | AUD-13 | La aplicación falla rápido si jwt.secret no está definido en el entorno. |
| TASK-010 | ✅ Restaurar application.yml del backend (revertir el contenido de docker-compose pegado por error) | AUD-09 | El backend arranca localmente usando application.yml + variables de entorno, sin depender de una plantilla accidental. |
| TASK-026 | ✅ Migración V5: alinear esquema de historial_analisis con la entidad JPA | AUD-14 | Un análisis se persiste sin error SQL; rango_ahorro y nivel_endeudamiento tienen el tipo y nombre correctos en BD. |
| TASK-030 | ✅ Crear dashboard.html real y reordenar la carga de scripts | AUD-18 | Tras un login exitoso, el usuario llega a una página real que carga dashboard.js y api.js correctamente. |

### P1 — Conectar el Motor de IA Real
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-002 | 🔴 Unificar valores del enum de perfil de riesgo (inspeccionar model.classes_) | AUD-02 | AnalisisIAServiceImpl no lanza excepción ante ninguna respuesta válida del motor real; cubierto por test unitario. |
| TASK-027 | 🔴 Alinear host, puerto y ruta del motor de IA en docker-compose e IAClient | AUD-15 | El backend puede invocar exitosamente al motor de IA elegido como canónico, en local y en docker-compose. |
| TASK-028 | 🔴 Alinear contrato de respuesta del motor de IA con AnalisisResponse | AUD-16 | AnalisisResponse se deserializa sin error a partir de la respuesta real del motor (nombres de campo y tipos numéricos correctos). |
| TASK-031 | 🔴 Conectar la creación de perfil financiero al flujo de registro del frontend | AUD-19 | Tras registrarse desde el navegador, el usuario tiene un perfil financiero persistido y puede solicitar un análisis sin error 404. |

### P2 — Integración Frontend ↔ Backend Restante
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-003 | 🔴 Definir y documentar el contrato único de API (path + casing + payload de transacciones) | AUD-03 | dashboard.js puede llamar exitosamente al backend real sin transformar el payload a mano. |
| TASK-018 | 🔴 Dashboard de transacciones funcional conectado al backend real | Slice 2 (depende de TASK-003) | El formulario crea transacciones visibles de inmediato en la tabla, usando el token de sesión y el payload correcto. |
| TASK-029 | 🔴 Retirar o documentar formalmente el motor de IA duplicado / mock-api obsoleto | AUD-17 + AUD-05 | Sólo queda un servicio de inferencia canónico referenciado en docker-compose y documentado como tal. |
| TASK-016 | 🟡 Completar paginación en el listado de transacciones (el filtrado por usuario ya está resuelto) | Slice 2 | GET soporta parámetros de página/tamaño además del filtrado por usuario autenticado ya existente. |
| TASK-021 | 🔴 Vista de historial de diagnósticos en el frontend | Slice 3 | El usuario puede ver sus análisis previos ordenados por fecha, una vez AUD-14 esté resuelto. |

### P3 — Deuda Técnica y Limpieza
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-011 | 🔴 Aplicar spring.jpa.open-in-view=false | AUD-11 | Aplicable recién después de TASK-010 (application.yml restaurado); los tests de integración siguen pasando. |
| TASK-013 | 🔴 Suite de pruebas de integración de Auth (colección versionada en el repo) | Slice 1 | Registro exitoso, registro duplicado (400), login exitoso y login inválido (401) cubiertos y documentados. |
| TASK-020 | 🟡 Completar resiliencia ante caída del motor de IA (ya existe el manejo de ResourceAccessException → 503) | Slice 3 | Se agregan tests que cubran explícitamente el escenario de caída del servicio de IA. |
| TASK-032 | ✅ Corregir typo Sexo.FEMININO → FEMENINO | AUD-20 | El enum usa la ortografía correcta; se confirma con Data Science que no rompe el encoding del modelo entrenado. |
| TASK-033 | ✅ Corregir <coniguration> en pom.xml | AUD-21 | mvn clean package procesa Lombok correctamente con el bloque de configuración corregido. |

### P4 — Infraestructura y Cierre (Semana 5, sin cambios de fondo)
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-023 | 🔴 docker-compose.prod.yml con backend, motor de IA real y PostgreSQL contenerizados | Semana 5 | docker compose -f docker-compose.prod.yml up levanta el stack completo end-to-end. |
| TASK-024 | 🔴 Despliegue en OCI (Compute u Object Storage) | Semana 5 | La API es accesible públicamente vía HTTPS y documentada con la URL final. |
| TASK-025 | 🔴 QA end-to-end + revisión final de esta nota maestra | Semana 5 | Los hallazgos AUD-01 a AUD-21 están cerrados o explícitamente diferidos con justificación. |
**Tareas ya completadas (sin acción pendiente)**
*TASK-004 (AUD-04), TASK-006 (AUD-06), TASK-007 (AUD-07), TASK-008 (AUD-08), TASK-012 (AUD-10), TASK-015 (alta de transacciones con userId desde JWT), TASK-017 (validaciones Jakarta sobre TransactionRequest) y TASK-019 (endpoint disparador reutilizando transacciones persistidas) ya están resueltas en el snapshot actual y no requieren trabajo adicional salvo verificación en QA final (TASK-025).*

---

## 11. Anexo: Prompts Guía para Sesiones de IA
Bloques de prompt listos para copiar y pegar al iniciar una conversación con un asistente de IA, adjuntando o pegando esta nota maestra v2 como contexto.

### Prompt base (inicio de cualquier sesión)
*Actúa como Tech Lead senior de Java 21 / Spring Boot 4.1.0 del proyecto FinanceAI. Te comparto la Nota Maestra v2 del proyecto (documento adjunto). Antes de proponer código, confirma en qué archivo(s) reales del repositorio (Sección 2) impacta el cambio, y respeta las convenciones de la Sección 9 (Definition of Done).*

### Prompt para una tarea puntual del backlog
*Con base en TASK-0XX de la Sección 10 de la Nota Maestra v2 de FinanceAI, redacta la historia de usuario en formato Gherkin (Given/When/Then) y la lista de archivos a modificar o crear, siguiendo la arquitectura descrita en la Sección 2.3.*

### Prompt para planificación de sprint
*Tomando el Backlog Priorizado (Sección 10) y el Camino Crítico para Demo (Sección 7.4) de la Nota Maestra v2 de FinanceAI, arma el plan del próximo sprint de 1 semana. Respeta que el grupo P0 debe cerrarse antes de continuar con features nuevas de los grupos P1 en adelante.*

### Prompt para la próxima re-auditoría
*Actúa como auditor técnico senior. Te comparto la Nota Maestra v2 de FinanceAI y un nuevo snapshot Repomix del código real. Contrasta línea por línea el estado de los hallazgos AUD-01 a AUD-21 contra el nuevo snapshot, marca cuáles se resolvieron, cuáles siguen abiertos y cuáles son regresiones, y detecta hallazgos nuevos continuando la numeración desde AUD-22.*
