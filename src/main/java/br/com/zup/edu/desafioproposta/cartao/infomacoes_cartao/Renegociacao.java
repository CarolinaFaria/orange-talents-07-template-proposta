package br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiId;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Renegociacao() {
    }

    public Renegociacao(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.apiId = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }


    public Renegociacao(Renegociacao renegociacao) {
        this.apiId = renegociacao.getApiId();
        this.quantidade = renegociacao.getQuantidade();
        this.valor = renegociacao.getValor();
        this.dataDeCriacao = renegociacao.getDataDeCriacao();
    }

    public String getApiId() {
        return apiId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
