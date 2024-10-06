package crudweb2.crudweb2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import crudweb2.crudweb2.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    public Optional<Curso> findCursoByNome(String nome);
    public boolean existsByIdAndMatriculasIsNotEmpty(Integer id);
}
