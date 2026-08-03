package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Rol;
import com.nocountry.financeai.entity.enums.Sexo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", length = 20)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Sexo sexo;

    @Column(name = "numero_hijos")
    private Integer numeroHijos;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Rol rol = Rol.USER;

    @Builder.Default
    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PerfilFinancieroEntity perfilFinanciero;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransactionEntity> transacciones;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistorialAnalisisEntity> historialAnalisis;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
