package crudweb2.crudweb2.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

@Entity
@Table(name="aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Getter @Setter
    private int id;

    @Column(name="nome")
    @Getter @Setter
    private String nome;

    @Column(name="cpf")
    @Getter @Setter
    private String cpf;

    @Column(name="email")
    @Getter @Setter
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_nascimento")
    @Getter @Setter
    private LocalDate dtNascimento;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    @JsonIgnore
    @Getter @Setter
    private List<Matricula> matriculas;
}
