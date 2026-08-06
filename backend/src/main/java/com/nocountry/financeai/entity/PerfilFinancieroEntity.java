package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.RangoAhorro;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "perfil_financiero")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PerfilFinancieroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPerfilFinanciero;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private UserEntity usuario;

    @Column(name = "empleo_formal")
    private Integer empleoFormal;

    @Column(name = "ingreso_mensual", precision = 12, scale = 2)
    private BigDecimal ingresoMensual;

    @Column(name = "linea_credito",  precision = 12, scale = 2)
    private BigDecimal lineaCredito;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
