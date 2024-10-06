package crudweb2.crudweb2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import crudweb2.crudweb2.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
    public Optional<Aluno> findAlunoByCpf(String cpf);
    public boolean existsByIdAndMatriculasIsNotEmpty(Integer id);
}
