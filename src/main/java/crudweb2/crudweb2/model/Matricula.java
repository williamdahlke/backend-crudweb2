package crudweb2.crudweb2.model;

import lombok.Getter;
import lombok.Setter;

public class Matricula {
    @Getter @Setter
    private int id;

    @Getter @Setter
    private Aluno aluno;

    @Getter @Setter
    private Curso curso;

    @Getter @Setter
    private String dtMatricula;

    @Getter @Setter
    private float nota;
}
