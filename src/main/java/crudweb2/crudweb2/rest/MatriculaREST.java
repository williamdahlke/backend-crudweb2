package crudweb2.crudweb2.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import crudweb2.crudweb2.model.Matricula;
import crudweb2.crudweb2.repository.MatriculaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin
@RestController
@Tag(name="Matricula", description = "Controller para requisições de busca, alteração, atualização e deleção de matrículas")
public class MatriculaREST {
    
    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping("/matriculas/{id}")
    @Operation(description = "Busca todos as matrículas por ID")
    public ResponseEntity<Matricula> getMatriculaById(@PathVariable int id) {
        Optional<Matricula> op = matriculaRepository.findById(id);
        if (op.isPresent()){
            return ResponseEntity.ok(op.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
