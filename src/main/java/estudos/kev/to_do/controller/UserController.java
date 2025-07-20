package estudos.kev.to_do.controller;


import estudos.kev.to_do.DToS.AuthorizationDTo;
import estudos.kev.to_do.DToS.RegisterDTo;
import estudos.kev.to_do.model.entitie.User;
import estudos.kev.to_do.repository.UserRepository;
import estudos.kev.to_do.service.AuthorizationService;
import estudos.kev.to_do.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.net.PasswordAuthentication;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthorizationDTo login ){
        // objeto que verifica o login e senha do usuario.
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.login(), login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTo register){
        if(userRepository.findByLogin(register.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        User user = new User(register.login(), encryptedPassword, register.role());
        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public String deleteUser(@PathVariable long id){
        return this.userService.deleteUser(id);
    }


}
