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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "O nome do aluno não pode ser nulo ou em branco")
    @Column(name="nome")
    @Getter @Setter
    private String nome;

    @Size(min=11, max=11, message = "O CPF precisa ter 11 caracteres")
    @Column(name="cpf")
    @Getter @Setter
    private String cpf;

    @Email(message = "O e-mail informado está inválido")
    @Column(name="email")
    @Getter @Setter
    private String email;

    @NotNull(message = "A data de nascimento não pode estar nula")
    @Past(message = "A data de nascimento é inválida")
    @Temporal(TemporalType.DATE)
    @Column(name="dt_nascimento")
    @Getter @Setter
    private LocalDate dtNascimento;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    @JsonIgnore
    @Getter @Setter
    private List<Matricula> matriculas;
}
