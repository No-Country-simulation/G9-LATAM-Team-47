# FinanceAI
# 🚀 FinanceAI – Asistente Inteligente de Salud Financiera

📋 ## Índice
- [Estado del proyecto](#-estado-del-proyecto)
- [Descripción del proyecto](#-descripción-del-proyecto)
- [Objetivos](#-objetivos)
- [Sector empresarial](#-sector-empresarial)
- [Tecnologías](#%EF%B8%8F-tecnologías)
- [Arquitectura](#-arquitectura)
- [Ejemplo de uso](#-ejemplo-de-uso)
- [Equipo](#-equipo)

---

## 🚧 Estado del proyecto
Actualmente el proyecto se encuentra en fase de planificación y diseño de arquitectura. La implementación se desarrollará durante el Hackathon ONE.

## 📖 Descripción del proyecto
FinanceAI es una solución inteligente orientada a mejorar la salud financiera de los usuarios mediante el análisis automático de sus transacciones y hábitos financieros.
A partir de la información proporcionada por el usuario, el sistema será capaz de analizar su comportamiento financiero y generar información útil que facilite una mejor toma de decisiones.

Entre la información procesada se encuentran:
* Ingreso mensual.
* Nivel de endeudamiento.
* Frecuencia de ahorro.
* Historial de transacciones.
* Descripción y monto de cada gasto.

## 🎯 Objetivos
El proyecto busca desarrollar un MVP capaz de:
* Clasificar automáticamente las transacciones financieras.
* Identificar patrones de consumo.
* Analizar el perfil financiero del usuario.
* Generar recomendaciones personalizadas.
* Exponer los resultados mediante una API REST.
* Integrar al menos un servicio de Oracle Cloud Infrastructure (OCI).

## 🏢 Sector Empresarial
**Fintech · Educación Financiera · Carteras Digitales**  
FinanceAI está dirigido a personas que desean comprender mejor sus hábitos financieros, organizar sus gastos y tomar decisiones más informadas sobre el manejo de su dinero.

## 🛠️ Tecnologías
Actualmente el proyecto contempla el uso de las siguientes tecnologías:

### Backend
* Java 21
* Spring Boot
* Spring Data JPA
* Maven
* Flyway
* Lombok
* Swagger / OpenAPI

### Ciencia de Datos
* Python
* Pandas
* Scikit-Learn
* Jupyter Notebook

### Frontend
* Vue.js

### Infraestructura
La infraestructura del proyecto se encuentra actualmente en definición. Durante el desarrollo del hackathon se seleccionarán los servicios de Oracle Cloud Infrastructure (OCI) que mejor se adapten a las necesidades del proyecto.

## 🏗️ Arquitectura
La solución estará organizada en cuatro módulos principales:
1. **Frontend**, encargado de la interacción con el usuario.
2. **Backend**, responsable de la lógica de negocio y la API REST.
3. **Ciencia de Datos**, donde se desarrollarán y entrenarán los modelos de clasificación y análisis financiero.
4. **Oracle Cloud Infrastructure (OCI)**, utilizado para el almacenamiento, procesamiento o despliegue de la solución.

La arquitectura podrá evolucionar conforme avance el desarrollo del proyecto.

## 💻 Ejemplo de uso

### Endpoint
`POST /api/analisis-financiero`

### Solicitud
```json
{
  "ingreso_mensual": 4500,
  "nivel_endeudamiento": 25,
  "frecuencia_ahorro": "Media",
  "transacciones": [
    {
      "descripcion": "Supermercado",
      "valor": 420
    },
    {
      "descripcion": "Combustible",
      "valor": 300
    },
    {
      "descripcion": "Streaming",
      "valor": 40
    }
  ]
}
```

### Respuesta

```JSON
{
  "perfil_financiero": "En observación",
  "probabilidad": 0.82,
  "resumen_gastos": {
    "alimentacion": 420,
    "transporte": 300,
    "entretenimiento": 40
  },
  "recomendaciones": [
    "Monitorear gastos recurrentes de entretenimiento.",
    "Aumentar la reserva financiera mensual."
  ]
}
```

👥 Equipo
Proyecto desarrollado por el equipo G9-LATAM-Team 47 FinanceAI durante el Hackathon Oracle Next Education (ONE).
