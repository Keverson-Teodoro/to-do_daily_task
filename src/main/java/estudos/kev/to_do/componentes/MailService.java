package estudos.kev.to_do.componentes;

import estudos.kev.to_do.repository.ToDoRepository;
import org.springframework.stereotype.Component;

@Component
public class MailService {
    private final ToDoRepository toDoRepository;

    public MailService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    public void enviar(String mensagem){
        System.out.println("Email enviado: " + mensagem);
    }



}
