package com.nocountry.financeai.config;

import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.entity.enums.Rol;
import com.nocountry.financeai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class IniciarAdmin {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner crearAdministrador(){
        return args ->{
            String emailAdmin = "admin@financeai.com";
            if ( userRepository.existsByEmail(emailAdmin)){
                return;
            }
            UserEntity admin = UserEntity.builder()
                    .nombre("Administrador")
                    .apellido("FinanceAI")
                    .documento("ADMIN_001")
                    .email(emailAdmin)
                    .password(passwordEncoder.encode("FinanceAdmin2026*"))
                    .fechaNacimiento(LocalDate.of(1990, 1, 1))
                    .rol(Rol.ADMIN)
                    .activo(true)
                    .build();

            userRepository.save(admin);
        };
    }
}
