package crudweb2.crudweb2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crudweb2.crudweb2.model.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{
    
}
