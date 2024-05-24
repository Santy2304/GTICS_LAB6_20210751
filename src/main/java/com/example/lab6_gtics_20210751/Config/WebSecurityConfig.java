package com.example.lab6_gtics_20210751.Config;

import com.example.lab6_gtics_20210751.Repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

public class WebSecurityConfig {
    final DataSource dataSource;
    final UsuarioRepository usuarioRepository;

    public WebSecurityConfig(DataSource dataSource, UsuarioRepository usuarioRepository) {
        this.dataSource = dataSource;
        this.usuarioRepository = usuarioRepository;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.formLogin()

                .successHandler((request, response, authentication) -> {

                    HttpSession session = request.getSession();
                    session.setAttribute("usuario",usuarioRepository.findByCorreo(authentication.getName()));

                    String rol = "";
                    for(GrantedAuthority role : authentication.getAuthorities()){
                        rol = role.getAuthority();
                        break;
                    }

                    if(rol.equals("admin")){
                        response.sendRedirect("/admin/listaMesas");
                    }else{
                        if (rol.equals("gerente")){
                            response.sendRedirect("/gerente/listaMesas");

                        }else{
                            if(rol.equals("cliente")){
                                response.sendRedirect("/cliente/listaMesas");

                            }
                        }
                    }

                });

        http.logout()
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);


        http.authorizeHttpRequests()
                .requestMatchers("/admin", "/admin/**").hasAnyAuthority("admin")
                .requestMatchers("/gerente", "/gerente/**").hasAnyAuthority("gerente")
                .requestMatchers("/cliente", "/cliente/**").hasAnyAuthority("cliente")
                .anyRequest().permitAll();

        return http.build();
    }
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);

        String sql1 = "SELECT correo, password, activo FROM usuario where correo = ?";
        String sql2 = "SELECT u.correo, r.autoridad FROM usuarios u INNER JOIN rol r ON (u.idrol = r.idrol) " +
                "WHERE u.correo = ?";

        jdbc.setUsersByUsernameQuery(sql1);
        jdbc.setAuthoritiesByUsernameQuery(sql2);
        return jdbc;
    }
}
