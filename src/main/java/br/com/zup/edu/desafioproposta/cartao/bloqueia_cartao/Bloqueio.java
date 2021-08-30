package br.com.zup.edu.desafioproposta.cartao.bloqueia_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiId;
    private LocalDateTime bloqueadoAte;
    private String sistemaResponsavel;
    private boolean ativo;
    private String ip;

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


    public Bloqueio(String ip, String sistemaResponsavel, Cartao cartao) {
        this.ip = ip;
        this.sistemaResponsavel = sistemaResponsavel;
        this.cartao = cartao;
        this.bloqueadoAte = LocalDateTime.now();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloqueio bloqueio = (Bloqueio) o;
        return id.equals(bloqueio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
