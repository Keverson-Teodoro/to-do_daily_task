package estudos.kev.to_do.service;

import estudos.kev.to_do.model.entitie.User;
import estudos.kev.to_do.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public User returnUser(long id){
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUser(long id){
        String mensagem = "";

        try{
            userRepository.deleteById(id);
            return mensagem = "user has bean delete";

        }catch (NoSuchElementException exception){
            mensagem = "not found user";
        }
        return mensagem;
    }

}
