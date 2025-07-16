package estudos.kev.to_do.DToS;

import estudos.kev.to_do.model.enums.ToDoStatus;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ToDoDto {

    public String descricao;

    public OffsetDateTime dataInicio;

    public OffsetDateTime dataTermino;

    public ToDoStatus statusDaTarefa;
}
