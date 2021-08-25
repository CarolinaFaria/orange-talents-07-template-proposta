package br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiId;
    private LocalDateTime bloqueadoAte;
    private String sistemaResponsavel;
    private boolean ativo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String id, LocalDateTime bloqueadoAte, String sistemaResponsavel, boolean ativo) {
        this.apiId = id;
        this.bloqueadoAte = bloqueadoAte;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio(Bloqueio bloqueio) {
        this.apiId = bloqueio.getApiId();
        this.bloqueadoAte = bloqueio.getBloqueadoAte();
        this.sistemaResponsavel = bloqueio.getSistemaResponsavel();
        this.ativo = bloqueio.isAtivo();
    }


    public String getApiId() {
        return apiId;
    }

    public LocalDateTime getBloqueadoAte() {
        return bloqueadoAte;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
