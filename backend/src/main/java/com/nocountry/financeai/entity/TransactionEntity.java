package com.nocountry.financeai.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transacciones")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(length = 50)
    private String categoria;

    @Column(name = "nombre_comercio", nullable = false, length = 255)
    private String nombreComercio;

    @Column(name ="monto_transaccion", nullable = false)
    private BigDecimal montoTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "medio_pago", nullable = false, length = 20)
    private MedioPago medioPago;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;
}

