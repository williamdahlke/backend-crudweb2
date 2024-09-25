package crudweb2.crudweb2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crudweb2.crudweb2.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
    
}
