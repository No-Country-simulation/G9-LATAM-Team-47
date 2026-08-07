# 📌 Protocolo de Colaboración, Verificación y Control de Versionado (Actualizado)

**Proyecto:** FinanceAI - Backend  
**Propósito:** Definir el flujo de interacción estricto para la entrega de código optimizado, validación de compilación local, generación de comandos Git y actualización de la Nota Maestra.

---

### 🎯 Objetivo Principal
Garantizar que ningún commit de Git y ninguna actualización en la Nota Maestra se registren con código no probado. Todo cambio debe estar alineado con la arquitectura real del proyecto (paquete base `com.nocountry.financeai`) y ser compilado localmente antes de pasar a la fase de versionado y documentación.

---

### 🔄 Flujo de Trabajo en 5 Pasos (Paso 0 al Paso 4)

#### **Paso 0: Análisis Estricto de Contexto (Asistente IA)**
* Antes de generar cualquier fragmento de código o sugerencia, la IA **debe revisar obligatoriamente** las fuentes adjuntas en el cuaderno (como `financeai.md`, `pom.xml` o notas previas).
* Tiene prohibido inventar rutas, nombres de paquetes genéricos (*placeholders*) o versiones. Debe extraer el paquete base real (`com.nocountry.financeai`) para entregar una solución 100% *plug and play*.

#### **Paso 1: Entrega de Código (Asistente IA)**
* Se proporciona el código fuente completo en Java 21 / Spring Boot 3 (DTOs, Servicios, Controladores, etc.) con sus anotaciones (Lombok, Jakarta Validation, Spring Security) adaptado a la estructura del proyecto.
* **Restricción:** En este paso **no se generan** comandos Git ni bloques de actualización de la nota.

#### **Paso 2: Verificación Local (Desarrollador)**
* Se copia el código al IDE (IntelliJ / VS Code).
* Se ejecuta la compilación (`mvn clean compile` o build del IDE) y se verifica que no existan errores de sintaxis, dependencias o conflictos de contexto.

#### **Paso 3: Trigger de Confirmación (Desarrollador)**
* El usuario envía un mensaje en el chat confirmando que el módulo/código ha sido integrado y compilado exitosamente (ej. *"Listo, ya compiló correctamente"*).

#### **Paso 4: Artefactos Finales (Asistente IA)**
* Tras recibir el trigger, la IA genera inmediatamente:
  1. **Comando Git:** Formateado bajo el estándar *Conventional Commits* (ej. `feat(auth): ...`, `fix(security): ...`).
  2. **Snippet de Nota Maestra:** Fragmento Markdown listo para copiar y pegar en la documentación general del proyecto.

---

### 🏷️ Convención de Commits (Conventional Commits)

| Tipo | Uso | Ejemplo |
| :--- | :--- | :--- |
| `feat` | Nueva funcionalidad agregada | `feat(auth): implement RegisterRequest and LoginRequest DTOs` |
| `fix` | Corrección de un error o bug | `fix(security): resolve circular dependency in JwtAuthFilter` |
| `refactor` | Reestructuración de código sin alterar comportamiento | `refactor(config): update SecurityConfig to handle specific exceptions` |
| `docs` | Cambios exclusivos en documentación | `docs(readme): update backend technical notes` |
