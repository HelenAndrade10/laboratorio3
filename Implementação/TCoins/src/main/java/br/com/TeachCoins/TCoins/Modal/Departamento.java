package br.com.TeachCoins.TCoins.Modal;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "instituicao_id")
    private List<Professor> professorres;

    @Deprecated
    public Departamento() {}

    public Departamento(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Professor> getProfessorres() {
        return professorres;
    }

    public void setProfessorres(List<Professor> professorres) {
        this.professorres = professorres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
