package br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiId;
    private Integer quantidade;
    private BigDecimal valor;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Parcela() {
    }

    public Parcela(String id, Integer quantidade, BigDecimal valor) {
        this.apiId = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Parcela(Parcela parcela) {
        this.apiId = parcela.getApiId();
        this.quantidade = parcela.getQuantidade();
        this.valor = parcela.getValor();
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
}
