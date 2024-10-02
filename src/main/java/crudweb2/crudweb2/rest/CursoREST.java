package crudweb2.crudweb2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import java.util.Optional;
import crudweb2.crudweb2.interfaces.IView;
import crudweb2.crudweb2.model.Curso;
import crudweb2.crudweb2.repository.CursoRepository;
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
@Tag(name="Curso", description = "Controller para requisições de busca, alteração, atualização e deleção de cursos")
public class CursoREST {
    @Autowired
    private CursoRepository cursoRepository;

    @JsonView(IView.IViewOptional.class)
    @GetMapping("/cursos")
    @Operation(description = "Busca todos os cursos registrados")
    @ApiResponses(value = {
         @ApiResponse(responseCode = "200"),
         @ApiResponse(responseCode = "204", content = @Content()),
         @ApiResponse(responseCode = "500", content = @Content())        
     })        
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> list = cursoRepository.findAll();
        if (list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }        
        return ResponseEntity.ok(list);
    }

    @JsonView(IView.IViewOptional.class)
    @GetMapping("/cursos/{id}")
    @Operation(description = "Busca um curso por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())        
    })       
    public ResponseEntity<Curso> getCursoById(@PathVariable int id) {
        Optional<Curso> op = cursoRepository.findById(id);
        if (op.isPresent()){
            return ResponseEntity.ok(op.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PostMapping("/cursos")
    @Operation(description = "Registra um novo curso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201"),
        @ApiResponse(responseCode = "400", content = @Content()),
        @ApiResponse(responseCode = "409", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())                        
    })       
    public ResponseEntity<Curso> insertCurso(@Valid @RequestBody Curso curso) {
        Optional<Curso> op = cursoRepository.findCursoByLink(curso.getLink());        
        if (op.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        else{
            cursoRepository.save(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(curso);
        }
    }
    
    @PutMapping("/cursos/{id}")
    @Operation(description = "Atualiza um curso existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", content = @Content()),
        @ApiResponse(responseCode = "404", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())                        
    })        
    public ResponseEntity<Curso> updateCurso(@PathVariable int id, @Valid @RequestBody Curso curso) {
        Optional<Curso> op = cursoRepository.findById(id);
        if (op.isPresent()){
            curso.setId(id);
            cursoRepository.save(curso);
            return ResponseEntity.ok(curso);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/cursos/{id}")
    @Operation(description = "Deleta um curso existente")  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404", content = @Content()),
        @ApiResponse(responseCode = "500", content = @Content())
    })            
    public ResponseEntity<Curso> deleteCurso(@PathVariable int id){
        Optional<Curso> op = cursoRepository.findById(id);
        if (op.isPresent()){
            cursoRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }    
}
