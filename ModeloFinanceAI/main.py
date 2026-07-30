from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List
import pandas as pd
import joblib
import sklearn
import sklearn.compose._column_transformer

# 1. PARCHE DE COMPATIBILIDAD
if not hasattr(sklearn.compose._column_transformer, '_RemainderColsList'):
    class _RemainderColsList(list):
        pass
    sklearn.compose._column_transformer._RemainderColsList = _RemainderColsList

# 2. CREAR LA APLICACIÓN FASTAPI
app = FastAPI(title="API Analítica - Hackathon")

# 3. CARGAR MODELOS ML
try:
    modelo_transacciones = joblib.load('modelo_clasificacion_transacciones.pkl')
    modelo_perfil = joblib.load('modelo_perfil_financiero.pkl')
    print("¡Modelos cargados con éxito!")
except Exception as e:
    print(f"Error al cargar archivos .pkl: {e}")

# 4. ESTRUCTURA DE DATOS (PYDANTIC)
class TransaccionInput(BaseModel):
    descripcion_transaccion: str
    monto_transaccion: float

class EntradaUsuario(BaseModel):
    edad: int
    sexo: str
    estado_civil: str
    numero_hijos: int
    empleo_formal: int
    ingreso_mensual: float
    linea_credito: float
    credito_utilizado: float
    frecuencia_ahorro: str
    monto_promedio_ahorro: float
    transacciones: List[TransaccionInput]

# 5. ENDPOINT DE LA API
@app.post("/analisis-financiero")
def analizar_usuario(datos: EntradaUsuario):
    try:
        # Normalizar datos de entrada
        df_cliente = pd.DataFrame([{
            'edad': datos.edad,
            'sexo': str(datos.sexo).lower(),
            'estado_civil': str(datos.estado_civil).lower(),
            'numero_hijos': datos.numero_hijos,
            'empleo_formal': datos.empleo_formal,
            'ingreso_mensual': datos.ingreso_mensual,
            'linea_credito': datos.linea_credito,
            'credito_utilizado': datos.credito_utilizado,
            'frecuencia_ahorro': str(datos.frecuencia_ahorro).lower(),
            'monto_promedio_ahorro': datos.monto_promedio_ahorro
        }])
        
        # Predicción Perfil Financiero
        perfil = modelo_perfil.predict(df_cliente)[0]

        # Extraer probabilidad desde el .pkl
        if hasattr(modelo_perfil, "predict_proba"):
            probs = modelo_perfil.predict_proba(df_cliente)[0]
            probabilidad = round(float(max(probs)), 2)
        else:
            probabilidad = 0.85

        # Calcular Nivel de Endeudamiento (%)
        if datos.linea_credito > 0:
            nivel_endeudamiento = round((datos.credito_utilizado / datos.linea_credito) * 100, 2)
        else:
            nivel_endeudamiento = 0.0

        # Clasificación de Transacciones
        if datos.transacciones:
            df_tx = pd.DataFrame([
                {
                    'descripcion_transaccion': str(t.descripcion_transaccion).lower(),
                    'monto_transaccion': t.monto_transaccion
                }
                for t in datos.transacciones
            ])
            df_tx['categoria'] = modelo_transacciones.predict(df_tx)
            resumen_gastos = df_tx.groupby('categoria')['monto_transaccion'].sum().to_dict()
            resumen_gastos = {str(k).lower(): round(float(v), 2) for k, v in resumen_gastos.items()}
        else:
            resumen_gastos = {}

        # Recomendaciones Dinámicas
        recomendaciones = []
        if nivel_endeudamiento > 50:
            recomendaciones.append("Reducir el uso de tarjetas de crédito para bajar el nivel de endeudamiento.")
        if datos.frecuencia_ahorro.lower() in ['baja', 'ninguna']:
            recomendaciones.append("Incrementar la reserva financiera mensual y automatizar el ahorro.")
        if "entretenimiento" in resumen_gastos and resumen_gastos["entretenimiento"] > (datos.ingreso_mensual * 0.15):
            recomendaciones.append("Monitorear y ajustar los gastos recurrentes en entretenimiento.")
        
        if not recomendaciones:
            recomendaciones.append("Mantener los hábitos de gasto actuales y continuar monitoreando el presupuesto.")

        # Respuesta final
        return {
            "perfil_financiero": str(perfil).upper(),
            "probabilidad": probabilidad,
            "nivel_endeudamiento": nivel_endeudamiento,
            "frecuencia_ahorro": datos.frecuencia_ahorro.capitalize(),
            "resumen_gastos": resumen_gastos,
            "recomendaciones": recomendaciones
        }

    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Error en procesamiento del modelo: {str(e)}")