package br.com.TeachCoins.TCoins.Modal;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long remetente_id;

    private long destinatario_id;

    private String destinatario_nome;

    private String rementente_nome;

    private int valor;

    private String horaFormatada;

    @ManyToOne
    private Extrato extrato;

    @Deprecated
        public Transacao(){}

    public Transacao(long rementente, long destinatario, int valor, String nomeDest,String nomeRem, Extrato extrato) {
        this.remetente_id = rementente;
        this.destinatario_id = destinatario;
        this.valor = valor;
        this.destinatario_nome = nomeDest;
        this.rementente_nome = nomeRem;
        this.extrato = extrato;

        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatterData.format(agora);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(agora);

        this.horaFormatada = ( dataFormatada + " - " + horaFormatada);
    }

    public Long getId() {
        return id;
    }

    public String getDestinatario_nome() {
        return destinatario_nome;
    }

    public String getRementente_nome() {
        return rementente_nome;
    }

    public void setRementente_nome(String rementente_nome) {
        this.rementente_nome = rementente_nome;
    }

    public void setDestinatario_nome(String destinatario_nome) {
        this.destinatario_nome = destinatario_nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getRemetente_id() {
        return remetente_id;
    }

    public void setRemetente_id(long remetente_id) {
        this.remetente_id = remetente_id;
    }

    public long getDestinatario_id() {
        return destinatario_id;
    }

    public void setDestinatario_id(long destinatario_id) {
        this.destinatario_id = destinatario_id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getHoraFormatada() {
        return horaFormatada;
    }

    public void setHoraFormatada(String horaFormatada) {
        this.horaFormatada = horaFormatada;
    }

    public Extrato getExtrato() {
        return extrato;
    }

    public void setExtrato(Extrato extrato) {
        this.extrato = extrato;
    }
}
