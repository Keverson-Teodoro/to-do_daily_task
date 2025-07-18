package estudos.kev.to_do.controller;


import estudos.kev.to_do.model.entitie.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user ){
        return ResponseEntity.ok(user);

    }


}
