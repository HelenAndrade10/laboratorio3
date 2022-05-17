package br.com.TeachCoins.TCoins.Modal;

import javax.persistence.*;
import java.util.List;

@Entity
public class Extrato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long cliente_id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transacao_id")
    private List<Transacao> transacoes;

    @Deprecated
    public Extrato(){}

    public Extrato(long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}
