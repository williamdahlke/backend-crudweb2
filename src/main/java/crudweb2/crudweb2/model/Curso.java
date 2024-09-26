package crudweb2.crudweb2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="curso")
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
}
