# FinanceAI — Análisis de Datos y Modelado de Riesgo Financiero

Este repositorio documenta el núcleo analítico y experimental del proyecto **FinanceAI**. El trabajo abarca la auditoría de integridad de los datos, la ingeniería de variables financieras, el análisis exploratorio (EDA) y el desarrollo de los modelos de Machine Learning y NLP que alimentan el sistema de inferencia.

---

##  Objetivo del Análisis

El proyecto busca responder a una pregunta central de negocio: **¿Cómo evaluar de manera objetiva la salud financiera y el riesgo de sobreendeudamiento de los usuarios a partir de su comportamiento transaccional?**

Para lograrlo, el flujo metodológico conecta la información socioeconómica de los clientes con su historial de consumo para segmentar perfiles de riesgo y automatizar la clasificación de gastos.

---

## ⚙️ Ingeniería de Variables y Lógica Financiera

Más allá de los registros estáticos, el análisis incorpora métricas diseñadas para capturar la realidad financiera del usuario:

* **Gasto Total:** Agregación consolidada del volumen transaccional por cliente.
* **Nivel de Endeudamiento:** Ratio que evalúa el apalancamiento del usuario frente a sus ingresos y líneas de crédito disponibles.
* **Rango de Ahorro:** Margen residual porcentual no consumido respecto al ingreso mensual.
* **Perfil Financiero (Target):** Segmentación en tres niveles de riesgo (`SALUDABLE`, `MODERADO`, `RIESGOSO`) establecida a partir de umbrales combinados de endeudamiento y capacidad de ahorro.

---

## 📈 Hallazgos Principales del EDA

El análisis exploratorio sobre el volumen transaccional permitió identificar patrones clave:

* **Concentración del Riesgo:** Una proporción significativa de los usuarios concentra niveles de gasto muy cercanos o superiores a sus ingresos mensuales, ubicándose predominantemente en el perfil de riesgo.
* **Distribución del Gasto:** Las categorías de *Alimentación* y *Transporte* acaparan cerca de la mitad del volumen transaccional total del sistema.
* **Correlaciones:** El uso de coeficientes de Spearman permitió evidenciar relaciones no lineales y proteger el análisis frente a la distorsión de transacciones atípicas, confirmando que las líneas de crédito guardan una covarianza directa con los ingresos declarados.

---

## 🤖 Modelos y Componentes del Sistema

El trabajo desarrollado en el notebook se materializa en dos artefactos principales integrados en el flujo de producción:

1. **Clasificador de Transacciones (NLP):** Pipeline basado en vectorización de texto (TF-IDF) para asociar descripciones de establecimientos comerciales con su respectiva categoría de gasto.
2. **Clasificador de Perfil Financiero:** Modelo supervisado (`RandomForest`) alimentado por variables socioeconómicas y los ratios financieros calculados. 
   * *Explicabilidad:* Mediante el análisis de interpretabilidad global (SHAP), se constató que las decisiones del modelo están dominadas por métricas de deuda objetivas (`nivel_endeudamiento` y `rango_ahorro`), reduciendo al mínimo el sesgo de variables demográficas.

---

## ⚠️ Limitaciones Metodológicas

* **Definición Determinista del Target:** La etiqueta de perfil financiero se formuló originalmente mediante reglas condicionales basadas en el endeudamiento y el ahorro. Esto explica el alto rendimiento predictivo alcanzado por el modelo, el cual opera como una validación funcional de la regla de negocio más que sobre un entorno estocástico impredecible.
* **Datos Sintéticos:** Ciertas categorías de gasto (como vivienda y educación) incorporaron registros simulados para completar el alcance del catálogo comercial.

---

## 📁 Estructura del Repositorio

```text
.
├── hackathon_dataset.ipynb                   # Notebook principal: EDA, ingeniería de variables y modelado
├── transactions.csv                          # Historial transaccional base
├── users.csv                                 # Catálogo maestro de clientes
├── inventario_comercios_mexicanos_800_variado.csv  # Catálogo de referencia comercial
└── modeloFinanceAI/                          # Artefactos y servicio de inferencia en producción
    ├── main.py                               # API REST (FastAPI)
    └── *.pkl                                 # Modelos serializados de NLP y clasificación
