package estudos.kev.to_do.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// habilitar corrente de filtro de sec=gurança( valida se o usuario esta apto para fazer a requisição)
// autenticação statless, é feita atraves de tokens que são liberados para o usuario depois de fazer login na applicação, com o token o usuario consegue fazer as
// requisições, o servidor armazena os dados

@Configuration
@EnableWebSecurity // diz ao spring para habilitar o web security q que vamos configurar ele por aq

public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // define as politicas da nossa applicação
                .authorizeHttpRequests(autorize -> autorize  // autoriza as requisições, // declarando qual das requisições http serão autorizada
                        .requestMatchers(HttpMethod.POST, "/tarefa").hasRole("Admin") // definindo que qualquer request do tipo do post passada na url declarada, vai ter um tipo de permissão
                        .anyRequest().authenticated() // todas as requisições que nao forem as que eu definit acima,  vai estar apenas autenticado

                )
                .build();
    }



}



