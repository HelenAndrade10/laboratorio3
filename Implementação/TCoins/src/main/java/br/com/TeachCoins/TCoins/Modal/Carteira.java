package br.com.TeachCoins.TCoins.Modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long cliente_id;

    private int saldo;

    @Deprecated
    public Carteira(){}

    public Carteira(long cliente_id) {
        this.cliente_id = cliente_id;
        this.saldo = 0;
    }

    public void receber(int valor) {
        this.saldo += valor;
    }

    public void debitar(int valor){
        this.saldo -= valor;
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

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
