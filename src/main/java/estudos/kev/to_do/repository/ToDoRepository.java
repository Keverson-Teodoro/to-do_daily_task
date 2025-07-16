package estudos.kev.to_do.repository;

import estudos.kev.to_do.model.entitie.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;


@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query(value = "select * from tarefa where tarefa.data_inicio between '2025-mes-01 00:00:00.000000-00' and '2025-mes-30 23:59:00.000000-00'", nativeQuery = true)
    List<ToDo> buscarPorData(@Param("mes") int mes);

    boolean existsByDescricao(String descricao);
}
