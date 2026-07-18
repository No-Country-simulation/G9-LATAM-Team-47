## 💻 Módulo Backend & Infraestructura

El backend de **FinanceAI** está estructurado bajo una arquitectura limpia, desacoplada y orientada a capas utilizando **Java 21** y **Spring Boot 3.x/4.x**. El sistema ha sido diseñado bajo un enfoque "camaleónico", permitiendo un desarrollo local ágil pero completamente preparado para un despliegue seguro y transparente en **Oracle Cloud Infrastructure (OCI)**.

### 🛠️ Stack Tecnológico
* **Lenguaje:** Java 21 (LTS) - Implementación de *Records* inmutables para DTOs y compatibilidad nativa con *Virtual Threads*.
* **Framework:** Spring Boot (Spring Web, Spring Data JPA, Jakarta Validation).
* **Base de Datos:** PostgreSQL - Elegido por su estricta precisión matemática (`NUMERIC`) en transacciones financieras y madurez analítica.
* **Evolución de Datos:** Flyway - Control de versiones y migraciones automatizadas del esquema de base de datos.
* **Virtualización Local:** Docker Compose - Para la réplica exacta y aislada del entorno de base de datos en el equipo.
* **Calidad de Código:** Configurado bajo estándares estrictos de **SonarQube** (Clean Code y prevención de código muerto).

---

### 📂 Estructura de Arquitectura (Capas)
Dentro del directorio `/backend/src/main/java/com/nocountry/financeai/`, el código se organiza bajo el principio de responsabilidad única:

* **`controller/`**: Expone los endpoints REST públicos. Administra las validaciones automáticas de payloads (`@Valid`) y el manejo de políticas CORS para la integración fluida con el frontend.
* **`service/` & `service.impl/`**: Capa pura de lógica de negocio. Utiliza abstracción por interfaces para aislar los procesos internos, dejando el esqueleto preparado para orquestar las llamadas HTTP externas hacia la API de FastAPI del equipo de Data Science.
* **`dto/`**: Objetos de Transferencia de Datos desarrollados mediante *Java 21 Records*, reduciendo el código basura (*boilerplate*) y asegurando la inmutabilidad de los datos transferidos.
* **`model/`**: Aloja las entidades JPA de base de datos y Enums tipados (ej: `CategoriaGasto`, `MedioPago`) mapeados estrictamente en minúsculas mediante Jackson (`@JsonValue`), garantizando una sintonía del 100% con los requerimientos del dataset limpio de Data Science.
* **`repository/`**: Interfaces de persistencia segura que heredan de `JpaRepository`.

---

### 🐳 Réplica de Entorno Local (Docker Compose)
Para eliminar el problema de *"en mi máquina no funciona"*, la infraestructura local de base de datos está completamente automatizada.

**Instrucciones para el equipo de desarrollo:**
1. Asegúrate de tener Docker instalado en tu sistema operativo Linux.
2. Abre una terminal en la raíz del monorepo (donde se ubica el archivo `docker-compose.yml`).
3. Ejecuta el siguiente comando para levantar el entorno en segundo plano:
   ```bash
   docker compose up -d