package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "historial_analisis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialAnalisisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_financiero", nullable = false)
    private PerfilFinanciero perfilFinanciero;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal probabilidad;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "resumen_gastos")
    private Map<String, BigDecimal> resumenGastos;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false)
    private List<String> recomendaciones;

    @Column(name = "fecha_analisis", nullable = false, updatable = false)
    private LocalDateTime fechaAnalisis;

    @PrePersist
    protected void onCreate(){
        this.fechaAnalisis = LocalDateTime.now();
    }

}
