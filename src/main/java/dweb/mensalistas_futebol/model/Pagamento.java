package dweb.mensalistas_futebol.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pagamento")

public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codPagamento;

    @Column
    private short ano;

    @Column
    private short mes;

    @Column
    private double valor;

    @ManyToOne()
    @JoinColumn(name = "codJogador")
    @JsonIgnore
    private Jogador jogador;

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Pagamento() {

    }

    public Pagamento(short ano, short mes, double valor, Jogador jogador) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.jogador = jogador;
    }

    public int getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(int codPagamento) {
        this.codPagamento = codPagamento;
    }

    public short getAno() {
        return ano;
    }

    public void setAno(short ano) {
        this.ano = ano;
    }

    public short getMes() {
        return mes;
    }

    public void setMes(short mes) {
        this.mes = mes;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
