package estudos.kev.to_do.componentes;

import estudos.kev.to_do.model.entitie.ToDo;
import estudos.kev.to_do.repository.ToDoRepository;

public class ToDoValidator {

    private final ToDoRepository toDoRepository;

    public ToDoValidator(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    public void validation(ToDo todo){

        if(haveToDoWithThisDescription(todo.getDescricao())){
            throw new IllegalArgumentException("Já existe um to-do com essa descrição");
        }
    }


    public boolean haveToDoWithThisDescription(String description){
        return toDoRepository.existsByDescricao(description);
    }



}
