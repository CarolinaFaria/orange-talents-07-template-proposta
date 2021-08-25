package br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiId;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @OneToOne
    private Cartao cartao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.apiId = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento(Vencimento vencimento) {
        this.apiId = vencimento.getApiId();
        this.dia = vencimento.getDia();
        this.dataDeCriacao = vencimento.getDataDeCriacao();

    }

    public String getApiId() {
        return apiId;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
