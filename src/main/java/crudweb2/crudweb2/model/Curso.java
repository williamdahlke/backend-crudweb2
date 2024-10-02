package crudweb2.crudweb2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import crudweb2.crudweb2.interfaces.IView;

@Entity
@Table(name="curso")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "matriculas" }, allowGetters = true)
 
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name="id")
    @Getter @Setter
    private int id;

    @NotEmpty(message = "O nome do curso não pode ser nulo ou em branco")
    @Size(min=3, message = "O nome do curso precisa ter no mínimo 3 caracteres")
    @Column(name="nome")
    @Getter @Setter
    private String nome;
    
    @NotEmpty(message = "O link do curso não pode ser nulo ou em branco")
    @Column(name="link")
    @Getter @Setter
    private String link;

    @JsonView(IView.IViewOptional.class)
    @OneToMany(mappedBy="curso", fetch = FetchType.EAGER)
    @JsonManagedReference
    @Getter @Setter
    private List<Matricula> matriculas;
}
