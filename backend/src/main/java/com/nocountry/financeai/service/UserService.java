package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.ChangePasswdRequest;
import com.nocountry.financeai.dto.request.UserRequest;
import com.nocountry.financeai.dto.response.UserResponse;

import java.util.List;

public interface UserService  {
    // Lista los todos los usuarios
    List<UserResponse> obtenerUsuarios();

    //  Obtiene usuario por documento
    UserResponse obtenerUsuarioPorDocumento(String documento);

    //obtiene el perlfil del usuario autenticado
    UserResponse obtenerMiPerfil(String email);

    // Actualiza datos del usuario
    UserResponse actualizarMiPerfil(String email, UserRequest userRequest);

    // Actuliza contraseña de usuaria
    void cambiarPasswd(String email, ChangePasswdRequest changePasswdRequest);


}

