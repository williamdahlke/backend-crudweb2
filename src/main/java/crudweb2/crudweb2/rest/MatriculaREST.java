package crudweb2.crudweb2.rest;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import crudweb2.crudweb2.model.Matricula;
import crudweb2.crudweb2.repository.MatriculaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
@Tag(name="Matricula", description = "Controller para requisições de busca, alteração, atualização e deleção de matrículas")
public class MatriculaREST {
    
    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping("/matriculas/{id}")
    @Operation(description = "Busca todos as matrículas por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())        
    })     
    public ResponseEntity<Matricula> getMatriculaById(@PathVariable int id) {
        Optional<Matricula> op = matriculaRepository.findById(id);
        if (op.isPresent()){
            return ResponseEntity.ok(op.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/matriculas")
    @Operation(description = "Registra uma nova matrícula")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201"),
        @ApiResponse(responseCode = "400", content = @Content()),
        @ApiResponse(responseCode = "409", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())                        
    })    
    public ResponseEntity<Matricula> insertMatricula(@Valid @RequestBody Matricula matricula) {
        Optional<Matricula> op = matriculaRepository.findByCursoAndAluno(matricula.getCurso(), matricula.getAluno());
        if (op.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            matriculaRepository.save(matricula);
            return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
        }
    }
    
    @PutMapping("/matriculas/{id}")
    @Operation(description = "Atualiza uma matrícula existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", content = @Content()),
        @ApiResponse(responseCode = "404", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())                        
    })    
    public ResponseEntity<Matricula> updateMatricula(@PathVariable int id, @Valid @RequestBody Matricula matricula) {
        Optional<Matricula> op = matriculaRepository.findById(id);
        if (op.isPresent()){
            matricula.setId(id);
            matriculaRepository.save(matricula);
            return ResponseEntity.ok(matricula);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/matriculas/{id}")
    @Operation(description = "Deleta uma matrícula existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())
    })    
    public ResponseEntity<Matricula> deleteMatricula(@PathVariable int id){
        Optional<Matricula> op = matriculaRepository.findById(id);
        if (op.isPresent()){
            matriculaRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
