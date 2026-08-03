package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.entity.enums.RangoAhorro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Schema(description = "Resultado del analisis financiero generado a partir de los datos del usuario")
public record AnalisisResponse(

        @Schema(
                description = "Clasificacion del perfil financiero del usuario segun analisis",
                example = "EN_OBSERVACION",
                allowableValues = {"Saludable", "En observacion", "En riesgo" }
        )
        @JsonProperty("perfil_financiero")
        PerfilFinanciero perfilFinanciero,

        @Schema(
                description = "Probabilidad o nivel de confianza del modelo asociad al perlfil asignado, rango de 0 a 1",
                example = "0.82"
        )
        BigDecimal probabilidad,


        @Schema(
                description = "Porcentaje del nivel de endeudamiento de un usuario",
                example = "0.45"
        )
        @JsonProperty("nivel_endeudamiento")
        BigDecimal nivelEndeudamiento,

        @Schema(
                description = "Clasificacion del nivel de ahorro del usuario (Alta, Media, Baja, Ninguna)",
                example = "ALTA"
        )
        @JsonProperty("porcentaje_ahorro")
        RangoAhorro rangoAhorro,

        @Schema(
                description ="Resumen de gastos agrupados por categoria. Las claves del mapa son las categorias detectadas por el modelo",
                example ="{\"alimentacion\": 650, \"transporte\": 360, \"entretenimiento\":70}"
        )
        @JsonProperty("resumen_gastos")
        Map<String, BigDecimal> resumenGastos,

        @Schema(
                description = "lista de recomendaciones financieras generadas por el modelo, para el usuario",
                example = "[\"Monitorear los gastos recurrentes de entretenimiento\", \"Aumentar la reserva financiera mensual\"]"
        )
        List<String> recomendaciones
) {
}
