package br.com.fatec.catalogo.security;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsuarios(UsuarioRepository repository, PasswordEncoder encoder) {
        return args -> {
            if (!repository.existsByUsername("admin")) {
                UsuarioModel admin = new UsuarioModel();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("123456"));
                admin.setRole("ROLE_ADMIN");
                repository.save(admin);
            }
            if (!repository.existsByUsername("aluno")) {
                UsuarioModel aluno = new UsuarioModel();
                aluno.setUsername("aluno");
                aluno.setPassword(encoder.encode("123456"));
                aluno.setRole("ROLE_USER");
                repository.save(aluno);
            }
        };
    }
}