package com.nocountry.financeai.config;

import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Clase para dar un orden a los endpoint en OpenApi-swagger
@Configuration
public class OrdenOpenApi {
    @Bean
    public OpenApiCustomizer ordenarTags() {
        return openApi -> {
            List<String> ordenDeseado = List.of(
                    "Analisis",
                    "Autenticacion",
                    "Perfil Financiero",
                    "Transacciones",
                    "Historial Resultado Analisis",
                    "Test"
            );

            List<Tag> tagsOrdenados = new ArrayList<>(openApi.getTags());
            tagsOrdenados.sort(Comparator.comparingInt(tag -> {
                int idx = ordenDeseado.indexOf(tag.getName());
                return idx == -1 ? Integer.MAX_VALUE : idx;
            }));

            openApi.setTags(tagsOrdenados);
        };
    }
}
