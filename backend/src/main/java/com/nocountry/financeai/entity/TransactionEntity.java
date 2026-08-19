package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.MedioPago;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones") // <-- Corrección del nombre de la tabla
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_comercio")
    private String nombreComercio;

    @Column(name = "monto_transaccion")
    private BigDecimal montoTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "medio_pago")
    private MedioPago medioPago;

    // Corrección TASK-042 (AUD-27): Se remueve 'nullable = false' para alinear con la BD
    @Column(name = "tipo", length = 10)
    private String tipo;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "categoria")
    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;
}