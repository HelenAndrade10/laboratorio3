package br.com.TeachCoins.TCoins.Modal;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToOne
    private Departamento departamento;

    private String cpf;

    private String user;

    private String password;

    @ManyToOne
    private Instituicao instituicao;

    @Deprecated
    public Professor    (){}

        public Professor(String nome, String cpf, Departamento departamento, Instituicao instituicao) {
        this.nome = nome;
        this.cpf = cpf;
        this.departamento = departamento;
        this.instituicao = instituicao;

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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return id.equals(professor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
