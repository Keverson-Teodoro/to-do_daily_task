package estudos.kev.to_do.model.entitie;

import estudos.kev.to_do.model.enums.ToDoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tarefa")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarefa")
    private Long id;

    @Column(name = "descricao_tarefa")
    private String descricao;

    @Column(name = "data_inicio")
    private OffsetDateTime dataInicio;

    @Column(name = "data_termino")
    private OffsetDateTime dataTermino;

    @Column(name = "status_da_tarefa")
    @Enumerated(EnumType.STRING)
    private ToDoStatus statusDaTarefa;

    public ToDo(){

    }

    public ToDo(String descricao, OffsetDateTime dataInicio, OffsetDateTime dataTermino, ToDoStatus statusDaTarefa) {
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.statusDaTarefa = statusDaTarefa;
    }


}
