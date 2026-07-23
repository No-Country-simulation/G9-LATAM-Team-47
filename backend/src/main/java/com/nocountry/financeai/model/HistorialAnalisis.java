package com.nocountry.financeai.model;

import com.nocountry.financeai.model.enums.PerfilFinanciero.PerfilFinanciero;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_analisis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialAnalisis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_financiero", nullable = false)
    private PerfilFinanciero perfilFinanciero;

    @Column(nullable = false, precision = 5, scale = 4)
    private BigDecimal probabilidad;

    @Column(nullable = false)
    private String recomendaciones;

    @Column(name = "fecha_analisis")
    private LocalDateTime fechaAnalisis;

}
