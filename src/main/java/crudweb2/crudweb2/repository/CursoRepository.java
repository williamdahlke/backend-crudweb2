package crudweb2.crudweb2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import crudweb2.crudweb2.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    public Optional<Curso> findCursoByLink(String link);
}
