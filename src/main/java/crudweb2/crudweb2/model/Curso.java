package crudweb2.crudweb2.model;

import lombok.Getter;
import lombok.Setter;

public class Curso {
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String link;
}
