package estudos.kev.to_do.service;

import estudos.kev.to_do.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {


    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
