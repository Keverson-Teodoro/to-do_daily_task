package estudos.kev.to_do.controller;


import estudos.kev.to_do.DToS.ToDoDto;
import estudos.kev.to_do.model.entitie.ToDo;
import estudos.kev.to_do.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }


    @PostMapping("/iniciar")
    public ToDo iniciarTarefa(@RequestParam("descricao") String descricao){

        return toDoService.iniciarTarefa(descricao);
    }


    @GetMapping("/finalizar/{idTarefa}")
    public ToDo finalizarTarefa(@PathVariable long idTarefa){
        return toDoService.finalizarTarefa(idTarefa);
    }



    @GetMapping("/buscar/status/{statusTarefa}")
    public List<ToDo> buscarPeloStatus(@PathVariable("statusTarefa") String status){
        return toDoService.buscarPorStatus(status);
    }


    @GetMapping("/buscar/mes/{mes}")
    public List<ToDo> buscarPorData(@PathVariable int mes){

        return toDoService.buscarPorData(mes);

    }



    @PutMapping("/atualizar/{idTarefa}")
    public ToDo atualizarStatus(@PathVariable("idTarefa") long idTarefa, @RequestBody ToDo tarefa){
        tarefa.setId(idTarefa);
        return toDoService.atualizarToDo(tarefa);
    }



    @GetMapping("/buscar/id/{idTarefa}")
    public ToDo buscacr(@PathVariable("idTarefa") long idTarefa){
        return toDoService.buscarPorId(idTarefa);
    }

    @PostMapping
    public ToDo salvarToDo(ToDo novoToDo){
        try{
            return toDoService.salvarToDo(novoToDo);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }


}
