package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.ChangePasswdRequest;
import com.nocountry.financeai.dto.request.UserRequest;
import com.nocountry.financeai.dto.response.UserResponse;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // metodos definidos en la interfaz

    @Override
    public List<UserResponse> obtenerUsuarios() {
        return userRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public UserResponse obtenerUsuarioPorDocumento(String documento) {
        UserEntity usuario = buscarUsuarioPorDocumento(documento);
        return convertirRespuesta(usuario);
    }

    @Override
    public UserResponse obtenerMiPerfil(String email) {
        UserEntity usuario = buscarUsuarioPorEmail(email);
        return convertirRespuesta(usuario);

    }

    @Override
    public void cambiarPasswd(String email, ChangePasswdRequest request) {
        UserEntity usuario = buscarUsuarioPorEmail(email);

        if(!passwordEncoder.matches(
                request.currentPasswd(),
                usuario.getPassword()
        )){
            throw new IllegalArgumentException("La contraseña actual es incorrecta");
        }
        if(!request.newPasswd().equals(request.confirmPasswd())){
            throw new IllegalArgumentException("La nueva contraseña no coincide");
        }

        usuario.setPassword(passwordEncoder.encode(request.newPasswd()));
        userRepository.save(usuario);
    }

    @Override
    public UserResponse actualizarMiPerfil(String email, UserRequest request) {
        UserEntity usuario = buscarUsuarioPorEmail(email);

        System.out.println(request.nombre());

        if(request.nombre() != null){
            usuario.setNombre(request.nombre());
        }
        if(request.apellido() != null){
            usuario.setApellido(request.apellido());
        }
        if(request.email() != null){
            usuario.setEmail(request.email());
        }
        if(request.estadoCivil() != null){
            usuario.setEstadoCivil(request.estadoCivil());
        }
        if(request.sexo() != null){
            usuario.setSexo(request.sexo());
        }
        if(request.numeroHijos() != null){
            usuario.setNumeroHijos(request.numeroHijos());
        }

        UserEntity usuarioActualizado = userRepository.save(usuario);

        return convertirRespuesta(usuarioActualizado);
    }

    // metodos privados de la clase

    private UserEntity buscarUsuarioPorEmail(String email) {
        return  userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Usuario no encontrado"
                ));
    }

    private UserEntity buscarUsuarioPorDocumento(String documento){
        UserEntity usuario = userRepository.findByDocumento(documento)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado")
                );
        return usuario;
    }

    private UserResponse convertirRespuesta(UserEntity usuario) {
        return new UserResponse(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDocumento(),
                usuario.getEmail(),
                usuario.getFechaNacimiento(),
                usuario.getEstadoCivil(),
                usuario.getSexo(),
                usuario.getNumeroHijos()
        );
    }
}
