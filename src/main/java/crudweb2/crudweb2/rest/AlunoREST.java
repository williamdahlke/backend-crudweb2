package crudweb2.crudweb2.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import crudweb2.crudweb2.model.Aluno;
import crudweb2.crudweb2.repository.AlunoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
public class AlunoREST {
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/alunos")    
    public ResponseEntity<List<Aluno>> getAllAlunos(){
        List<Aluno> list = alunoRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable int id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()){
            return ResponseEntity.ok(aluno.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }    
    
}
