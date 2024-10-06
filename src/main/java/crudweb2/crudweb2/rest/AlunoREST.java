package crudweb2.crudweb2.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import crudweb2.crudweb2.model.Aluno;
import crudweb2.crudweb2.repository.AlunoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(exposedHeaders = "error-message")
@RestController
@Tag(name="Aluno", description = "Controller para requisições de busca, alteração, atualização e deleção de alunos")
public class AlunoREST {
    @Autowired
    private AlunoRepository alunoRepository;
    
    @GetMapping("/alunos")
    @Operation(description = "Busca todos os alunos registrados")    
    @ApiResponses(value = {
         @ApiResponse(responseCode = "200"),
         @ApiResponse(responseCode = "204", content = @Content()),
         @ApiResponse(responseCode = "500", content = @Content())        
    })    
    public ResponseEntity<List<Aluno>> getAllAlunos(){
        List<Aluno> list = alunoRepository.findAll();
        if (list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/alunos/{id}")
    @Operation(description = "Busca um aluno por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())        
    })           
    public ResponseEntity<Aluno> getAlunoById(@PathVariable int id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()){
            return ResponseEntity.ok(aluno.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } 
    
    @PostMapping("/alunos")
    @Operation(description = "Registra um novo aluno")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201"),
        @ApiResponse(responseCode = "400", content = @Content()),
        @ApiResponse(responseCode = "409", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())                        
    })        
    public ResponseEntity<Aluno> insertAluno(@Valid @RequestBody Aluno aluno) {        
        Optional<Aluno> op = alunoRepository.findAlunoByCpf(aluno.getCpf());
        if (op.isPresent()){            
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        else{
            alunoRepository.save(aluno);            
            return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
        }
    }

    @PutMapping("/alunos/{id}")
    @Operation(description = "Atualiza um aluno existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", content = @Content()),
        @ApiResponse(responseCode = "404", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())                        
    })           
    public ResponseEntity<Aluno> updateAluno(@PathVariable int id, @Valid @RequestBody Aluno aluno) {
        Optional<Aluno> op = alunoRepository.findById(id);
        if (op.isPresent()){
            aluno.setId(id);
            alunoRepository.save(aluno);
            return ResponseEntity.ok(aluno);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/alunos/{id}")
    @Operation(description = "Deleta um aluno existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content()),
        @ApiResponse(responseCode = "409", content = @Content(), description = "Conflito: O aluno possui matrículas associadas"),        
        @ApiResponse(responseCode = "500", content = @Content())
    })        
    public ResponseEntity<Aluno> deleteAluno(@PathVariable int id){
        Optional<Aluno> op = alunoRepository.findById(id);
        if (op.isPresent()){
            boolean possuiMatriculas = alunoRepository.existsByIdAndMatriculasIsNotEmpty(id);
            if (possuiMatriculas){
                return ResponseEntity.status(HttpStatus.CONFLICT).header("error-message", "Conflito: O aluno possui matrículas associadas").build();
            }
            alunoRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
