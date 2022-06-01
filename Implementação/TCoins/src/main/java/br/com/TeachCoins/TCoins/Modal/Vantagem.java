package br.com.TeachCoins.TCoins.Modal;

import javax.persistence.*;

@Entity
public class Vantagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = true, length = 64)
    private String photos;

    private String nome;

    private String path;

    private String descricao;

    private int custo;

    @ManyToOne
    private Parceiro fornecedor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/vantagem-photos/" + id + "/" + photos;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public Parceiro getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Parceiro fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Deprecated
    public Vantagem(){}

    public Vantagem(String photos, String nome, String descricao, int custo, Parceiro fornecedor) {
        this.photos = photos;
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.fornecedor = fornecedor;
    }

}
