package crudweb2.crudweb2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import crudweb2.crudweb2.model.Aluno;
import crudweb2.crudweb2.model.Curso;
import crudweb2.crudweb2.model.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{
    public Optional<Matricula> findByCursoAndAluno(Curso curso, Aluno aluno);
}
