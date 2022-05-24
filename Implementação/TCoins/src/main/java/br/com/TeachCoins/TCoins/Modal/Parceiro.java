package br.com.TeachCoins.TCoins.Modal;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Parceiro {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nome;

    private int quantidade_ofertas = 0;

    private String cnpj;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "vantagem_id")
    private List<Vantagem> ofertas;

    @Deprecated
    public Parceiro() {}

    public Parceiro(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public List<Vantagem> getOfertas() {
        return ofertas;
    }

    public void adicionarOferta(Vantagem oferta){
        this.ofertas.add(oferta);
    }

    public void setOfertas(List<Vantagem> ofertas) {
        this.ofertas = ofertas;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getQuantidade_ofertas() {
        return quantidade_ofertas;
    }

    public void setQuantidade_ofertas(int quantidade_ofertas) {
        this.quantidade_ofertas = quantidade_ofertas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parceiro parceiro = (Parceiro) o;
        return id == parceiro.id && Objects.equals(nome, parceiro.nome) && Objects.equals(cnpj, parceiro.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cnpj);
    }


}
