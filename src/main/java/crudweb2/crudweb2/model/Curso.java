package crudweb2.crudweb2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Column(name="nome")
    @Getter @Setter
    private String nome;

    @Column(name="link")
    @Getter @Setter
    private String link;

    @OneToMany(mappedBy="curso", fetch = FetchType.EAGER)
    @JsonManagedReference
    @Getter @Setter
    private List<Matricula> matriculas;
}
