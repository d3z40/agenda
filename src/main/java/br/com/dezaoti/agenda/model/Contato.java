package br.com.dezaoti.agenda.model;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity(name = "contato")
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The field \"name\" can not empty.")
    private String name;

    @NotEmpty(message = "The field \"email\" can not empty.")
    @Email
    private String email;

    public Contato() {
    }

    public Contato(Long id,
                   @NotEmpty(message = "The field \"name\" can not empty.") String name,
                   @NotEmpty(message = "The field \"email\" can not empty.") @Email String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
