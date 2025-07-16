package estudos.kev.to_do.service;

import estudos.kev.to_do.DToS.ToDoDto;
import estudos.kev.to_do.componentes.MailService;
import estudos.kev.to_do.componentes.ToDoValidator;
import estudos.kev.to_do.model.entitie.ToDo;
import estudos.kev.to_do.model.enums.ToDoStatus;
import estudos.kev.to_do.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ToDoService {

    private final ToDoValidator toDoValidator;
    private final MailService mailService;
    private final ToDoRepository toDoRepository;


    public ToDoService(ToDoValidator toDoValidator, MailService mailService, ToDoRepository toDoRepository) {
        this.toDoValidator = toDoValidator;
        this.mailService = mailService;
        this.toDoRepository = toDoRepository;
    }

    SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    // inicia um novo to-do passaando a descrição
    public ToDo iniciarTarefa(String descricao){
        ToDo todo = new ToDo();
        todo.setDescricao(descricao);
        todo.setStatusDaTarefa(ToDoStatus.PENDENTE);
        todo.setDataInicio(OffsetDateTime.now());
        toDoValidator.validation(todo);


        toDoRepository.save(todo);
        return todo;
    }



    // finaliza os sto-dos pendentes
    public ToDo finalizarTarefa(long idTarefa){
        ToDo todo = toDoRepository.findById(idTarefa)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        ToDoDto toDoDto = new ToDoDto();


        todo.setDataTermino(OffsetDateTime.now());

        todo.setStatusDaTarefa(ToDoStatus.FINALIZADA);
        toDoRepository.save(todo);
        return todo;
    }


    // busca o to-do por status
    public List<ToDo> buscarPorStatus (String statusDaTarefa){
        List<ToDo> listaToDo = toDoRepository.findAll();
        List<ToDo> toDosEncontrados = new ArrayList<>();


        for (ToDo tarefa : listaToDo){
            if(tarefa.getStatusDaTarefa().toString().equals(statusDaTarefa)){
                toDosEncontrados.add(tarefa);
            }
        }

        return toDosEncontrados;
    }


    public List<ToDo> buscarPorData (int mes){
        return toDoRepository.buscarPorData(mes);
    }


    // atualiza os dados do to-do
    public ToDo atualizarToDo(ToDo todo){
        toDoRepository.save(todo);
        return todo;
    }


    // busca o to-do pelo id
    public ToDo buscarPorId(long id){
        Optional<ToDo> todo = toDoRepository.findById(id);
        return todo.orElse(null);
    }

    public ToDo salvarToDo(ToDo todo){
        return toDoRepository.save(todo);
    }





}
