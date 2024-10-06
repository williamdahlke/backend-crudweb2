package crudweb2.crudweb2.model;

import java.time.LocalDate;

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
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

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
    @PastOrPresent(message = "A data de matrícula é inválida")
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_matricula")
    @Getter @Setter
    private LocalDate dtMatricula;

    @Min(value = 0, message="A nota deve ser informada entre 0 e 10")
    @Max(value = 10, message="A nota deve ser informada entre 0 e 10")
    @Column(name = "nota")
    @Getter @Setter
    private float nota;    
}
