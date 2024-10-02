package crudweb2.crudweb2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    @Getter @Setter
    @Column(name="id")    
    private int id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="aluno_id")
    @JsonIgnoreProperties("matriculas")
    @Getter @Setter
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="curso_id")
    @JsonIgnoreProperties("matriculas")
    @Getter @Setter        
    private Curso curso;

    @NotNull(message = "A data de matrícula não pode ser nula")
    @Past(message = "A data de matrícula é inválida")
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_matricula")
    @Getter @Setter
    private LocalDate dtMatricula;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "nota")
    @Getter @Setter
    private float nota;    
}
