package com.nocountry.financeai.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Permisos que tiene un usuario en el sistema",
        example = "USER"
)
public enum Rol {
    USER,
    ADMIN,
}
