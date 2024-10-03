package crudweb2.crudweb2.views;

import java.util.List;

import crudweb2.crudweb2.model.Curso;
import crudweb2.crudweb2.model.Matricula;
import lombok.Getter;
import lombok.Setter;

public class CursoView {
    @Getter @Setter
    public Integer id = 0;
    @Getter @Setter
    public String nome = "";
    @Getter @Setter
    public String link = "";
    @Getter @Setter
    public List<Matricula> matriculas = null;

    public CursoView(Curso curso){
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.link = curso.getLink();
        this.matriculas = curso.getMatriculas();
    }
}
