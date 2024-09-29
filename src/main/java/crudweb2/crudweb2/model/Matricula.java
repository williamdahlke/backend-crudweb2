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

    @Column(name = "data_matricula")
    @Getter @Setter
    private String dtMatricula;

    @Column(name = "nota")
    @Getter @Setter
    private float nota;    
}
