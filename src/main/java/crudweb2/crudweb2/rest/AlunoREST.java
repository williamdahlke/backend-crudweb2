package crudweb2.crudweb2.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import crudweb2.crudweb2.model.Aluno;
import crudweb2.crudweb2.repository.AlunoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



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
    
    @PostMapping("/alunos")
    public ResponseEntity<Aluno> insertAluno(@RequestBody Aluno aluno) {
        Optional<Aluno> op = alunoRepository.findAlunoByCpf(aluno.getCpf());
        if (op.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        else{
            aluno.setId(-1);
            alunoRepository.save(aluno);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PutMapping("/alunos/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable int id, @RequestBody Aluno aluno) {
        Optional<Aluno> op = alunoRepository.findById(id);
        if (op.isPresent()){
            aluno.setId(id);
            alunoRepository.save(aluno);
            return ResponseEntity.ok(aluno);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(op.get());
        }
    }
    
    @DeleteMapping
    public ResponseEntity<Aluno> deleteAluno(@PathVariable int id){
        Optional<Aluno> op = alunoRepository.findById(id);
        if (op.isPresent()){
            alunoRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
