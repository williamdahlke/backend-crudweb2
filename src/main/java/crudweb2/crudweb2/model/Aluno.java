package crudweb2.crudweb2.model;

import lombok.Getter;
import lombok.Setter;

public class Aluno {
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String cpf;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String dtNascimento;
}
