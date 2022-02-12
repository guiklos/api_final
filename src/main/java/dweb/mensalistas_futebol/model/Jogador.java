package dweb.mensalistas_futebol.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "jogador")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codJogador;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private Date dataNasc;

    @OneToMany(targetEntity = Pagamento.class, mappedBy = "jogador", cascade = CascadeType.ALL)
    private Set<Pagamento> pagamento;

    public Jogador() {
    }

    public Jogador(String nome, String email, Date dataNasc) {
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
    }

    public Set<Pagamento> getListPagamento() {
        return pagamento;
    }

    public void setPagamento(Set<Pagamento> pagamento) {
        this.pagamento = pagamento;
    }

    public int getCodJogador() {
        return codJogador;
    }

    public void setCodJogador(int codJogador) {
        this.codJogador = codJogador;
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

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

}
