package estudos.kev.to_do.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// a cada requisição eu faço essa verificação
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    // pega no token e recupera as informações nele
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);

    }

    private String recoverToken (HttpServletRequest request){
        var authheader = request.getHeader("Authorization");
        if(authheader != null) return null;
        return authheader.replace("Bearer ", "");
    }
}
