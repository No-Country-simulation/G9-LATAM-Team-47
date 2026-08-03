from fastapi import FastAPI, HTTPException, status
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field
from typing import List, Dict
from contextlib import asynccontextmanager
import pandas as pd
import numpy as np
import joblib
import sklearn
import sklearn.compose._column_transformer

# ==============================================================================
# 1. PARCHE DE COMPATIBILIDAD SKLEARN
# ==============================================================================
if not hasattr(sklearn.compose._column_transformer, '_RemainderColsList'):
    class _RemainderColsList(list):
        pass
    sklearn.compose._column_transformer._RemainderColsList = _RemainderColsList

# ==============================================================================
# 2. CARGA SEGURA DE MODELOS (LIFESPAN)
# ==============================================================================
modelos = {}

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Proceso de arranque (Startup)
    try:
        modelos['transacciones'] = joblib.load('modelo_clasificacion_transacciones.pkl')
        modelos['perfil'] = joblib.load('modelo_perfil_financiero.pkl')
        print("✅ [PROD] Modelos ML cargados exitosamente.")
    except Exception as e:
        print(f"❌ [ERROR CRÍTICO] Fallo al cargar modelos .pkl: {e}")
        raise RuntimeError(f"No se pudieron cargar los modelos en producción: {e}")
    yield
    # Proceso de apagado (Shutdown)
    modelos.clear()

# ==============================================================================
# 3. CREAR LA APLICACIÓN FASTAPI
# ==============================================================================
app = FastAPI(
    title="API Analítica Financiera",
    version="1.0.0",
    lifespan=lifespan
)

# Configuración de CORS para producción / Oracle Cloud
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # En prod estricto, reemplaza "*" por la IP/Dominio de tu Frontend
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ==============================================================================
# 4. ESTRUCTURA DE DATOS DE ENTRADA (Pydantic Models)
# ==============================================================================
class TransaccionInput(BaseModel):
    nombre_comercio: str = Field(
        ..., 
        example="Uber", 
        description="Nombre del establecimiento o comercio"
    )
    monto_transaccion: float = Field(
        ..., 
        gt=0, 
        example=250.0, 
        description="Monto de la transacción (debe ser mayor a 0)"
    )
    medio_pago: str = Field(
        ..., 
        example="credito", 
        description="Medios aceptados: credito, debito, transaccion, efectivo"
    )

class EntradaUsuario(BaseModel):
    edad: int = Field(..., ge=18, le=120)
    sexo: str
    estado_civil: str
    numero_hijos: int = Field(..., ge=0)
    empleo_formal: int = Field(..., ge=0, le=1)
    ingreso_mensual: float = Field(..., ge=0)
    linea_credito: float = Field(..., ge=0)
    transacciones: List[TransaccionInput] = []

# ==============================================================================
# 5. ENDPOINTS DE PRODUCCIÓN
# ==============================================================================

@app.get("/health", status_code=status.HTTP_200_OK)
def health_check():
    """Endpoint para que Oracle Cloud / Docker verifique si la API está viva"""
    if 'transacciones' not in modelos or 'perfil' not in modelos:
        raise HTTPException(status_code=500, detail="Modelos no inicializados")
    return {"status": "ok", "models_loaded": True}

@app.post("/analisis-financiero")
def analizar_usuario(datos: EntradaUsuario):
    try:
        modelo_perfil = modelos.get('perfil')
        modelo_transacciones = modelos.get('transacciones')

        # ----------------------------------------------------------------------
        # A) CÁLCULO DE GASTOS Y MÉTRICAS FINANCIERAS
        # ----------------------------------------------------------------------
        gasto_total = 0.0
        if datos.transacciones:
            gasto_total = sum([float(tx.monto_transaccion) for tx in datos.transacciones])

# 1. Nivel de Endeudamiento
        denom_endeudamiento = datos.ingreso_mensual + datos.linea_credito
        if denom_endeudamiento > 0:
            nivel_endeudamiento_val = round(float((gasto_total / denom_endeudamiento) * 100), 2)
            nivel_endeudamiento = f"{nivel_endeudamiento_val}%"
        else:
            nivel_endeudamiento = "0.0%"

        # 2. Porcentaje y Frecuencia de Ahorro
        if datos.ingreso_mensual > 0:
            ahorro_bruto = max(datos.ingreso_mensual - gasto_total, 0.0)
            pct_ahorro = ahorro_bruto / datos.ingreso_mensual
        else:
            pct_ahorro = 0.0

        if pct_ahorro >= 0.40:
            porcentaje_ahorro_str = "Alta"
        elif pct_ahorro >= 0.20:
            porcentaje_ahorro_str = "Media"
        elif pct_ahorro > 0:
            porcentaje_ahorro_str = "Baja"
        else:
            porcentaje_ahorro_str = "Ninguna"

# ----------------------------------------------------------------------
        # B) PREDICCIÓN CON MODELO DE PERFIL (.pkl)
        # ----------------------------------------------------------------------
        # 1. Escala decimal para endeudamiento (0.0 a 1.0) para hacer match con Colab
        nivel_endeudamiento_decimal = (gasto_total / denom_endeudamiento) if denom_endeudamiento > 0 else 0.0

        # 2. DataFrame con las 9 variables exactas del modelo
        df_cliente = pd.DataFrame([{
            'edad': int(datos.edad),
            'sexo': str(datos.sexo).lower().strip(),
            'estado_civil': str(datos.estado_civil).lower().strip(),
            'numero_hijos': int(datos.numero_hijos),
            'empleo_formal': int(datos.empleo_formal),
            'ingreso_mensual': float(datos.ingreso_mensual),
            'linea_credito': float(datos.linea_credito),
            'nivel_endeudamiento': float(nivel_endeudamiento_decimal),
            'porcentaje_ahorro': float(pct_ahorro)
        }])

        # 3. Predicción de perfil
        perfil_pred = modelo_perfil.predict(df_cliente)[0]
        perfil_str = str(perfil_pred).upper().replace(" ", "_")

        # 4. Cálculo seguro de la probabilidad en porcentaje
        probabilidad = "85.0%"  # Valor por defecto de respaldo
        if hasattr(modelo_perfil, "predict_proba"):
            probs = modelo_perfil.predict_proba(df_cliente)[0]
            prob_val = round(float(np.max(probs)) * 100, 2)
            probabilidad = f"{prob_val}%"
        # ----------------------------------------------------------------------
        # C) CLASIFICACIÓN NLP DE TRANSACCIONES (Protección lista vacía)
        # ----------------------------------------------------------------------
        resumen_gastos: Dict[str, float] = {}
        
        if datos.transacciones and len(datos.transacciones) > 0:
            df_tx = pd.DataFrame([
                {
                    'nombre_comercio': str(t.nombre_comercio).lower().strip(),
                    'monto_transaccion': float(t.monto_transaccion)
                }
                for t in datos.transacciones
            ])
            
            if hasattr(modelo_transacciones, "predict_proba"):
                probs_matriz = modelo_transacciones.predict_proba(df_tx)
                clases = modelo_transacciones.classes_
                categorias_finales = []

                for probs in probs_matriz:
                    prob_max = float(np.max(probs))
                    idx_max = int(np.argmax(probs))
                    
                    # Umbral del 60%
                    if prob_max <= 0.60:
                        categorias_finales.append("otros servicios")
                    else:
                        categorias_finales.append(str(clases[idx_max]))
                
                df_tx['categoria'] = categorias_finales
            else:
                preds = modelo_transacciones.predict(df_tx)
                df_tx['categoria'] = [str(p) for p in preds]
            
            # Agrupar y formatear
            agrupado = df_tx.groupby('categoria')['monto_transaccion'].sum().to_dict()
            resumen_gastos = {str(k).lower(): round(float(v), 2) for k, v in agrupado.items()}

# ----------------------------------------------------------------------
        # D) GENERACIÓN DE RECOMENDACIONES
        # ----------------------------------------------------------------------
        recomendaciones = []

        # 1. Recomendación por sobreapalancamiento en perfil RIESGOSO
        if perfil_str == "RIESGOSO" and datos.linea_credito > datos.ingreso_mensual:
            recomendaciones.append(
                "Para aumentar el score del perfil financiero, se recomienda incrementar el ingreso mensual de modo que supere la línea de crédito asignada"
            )

        # 2. Recomendaciones por comportamiento de gasto y ahorro
        if "entretenimiento" in resumen_gastos and resumen_gastos["entretenimiento"] > (datos.ingreso_mensual * 0.15):
            recomendaciones.append("Monitorear los gastos recurrentes de entretenimiento.")
            
        if nivel_endeudamiento_val > 50:
            recomendaciones.append("Reducir el uso de tarjetas de crédito para bajar el nivel de endeudamiento.")

        # Recomendación por defecto si no aplica ninguna de las anteriores
        if not recomendaciones:
            recomendaciones.append("Mantener los hábitos de gasto actuales y continuar monitoreando el presupuesto.")
        # ----------------------------------------------------------------------
        # E) SALIDA EN FORMATO ESTRICTO DEL BACK-END
        # ----------------------------------------------------------------------
        return {
            "perfilFinanciero": perfil_str,
            "probabilidad": probabilidad,
            "nivel_endeudamiento": nivel_endeudamiento,
            "porcentaje_ahorro": porcentaje_ahorro_str,
            "resumenGastos": resumen_gastos,
            "recomendaciones": recomendaciones
        }

    except Exception as e:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=f"Error interno en la inferencia del modelo: {str(e)}"
        )





####http://localhost:8000/docs####  
