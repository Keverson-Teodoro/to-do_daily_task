package estudos.kev.to_do.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import estudos.kev.to_do.model.entitie.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    //token passa pelo cliente e o servidor(transita entre eles)
    // o método vai ver quem ém o usuario e ver se ele tem a permissão necessária para fazer a requisição
    // o token vai conter as informações do cliente


    // os metodos criados na geração de token podem lançar uma exceção

    // declaramos qual algoritimo de geração de token vamos usar
    // o método de criptografia vai receber como parametro uma secret que é uma chave para deixar os hash exclusivos da applicação
    //para evitar invasões brutas nas senha


    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("To-Do check-list") // diz por onde vai ser gerado o token
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token ", exception);

        }

    }


    public String validationtoken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("To-Do check-list")
                    .build()
                    .verify(token)
                    .getSubject();


        }catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
