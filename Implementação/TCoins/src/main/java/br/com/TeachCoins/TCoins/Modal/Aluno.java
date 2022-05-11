package br.com.TeachCoins.TCoins.Modal;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String endereco;

    @ManyToOne
    private Instituicao instituicao;

    @Deprecated
    public Aluno(){}

    public Aluno(String nome, String email, String cpf, String endereco, Instituicao instituicao) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.endereco = endereco;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id) && Objects.equals(nome, aluno.nome) && Objects.equals(email, aluno.email) && Objects.equals(cpf, aluno.cpf) && Objects.equals(endereco, aluno.endereco) && Objects.equals(instituicao, aluno.instituicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, cpf, endereco, instituicao);
    }
}
