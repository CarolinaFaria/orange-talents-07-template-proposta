package br.com.zup.edu.desafioproposta.cartao;

import br.com.zup.edu.desafioproposta.cartao.associa_cartao.AssociaCartaoResponse;
import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime emitidoEm;

    private String titular;

    @OneToMany(mappedBy = "cartao",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Bloqueio> bloqueios;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Aviso> avisos;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Carteira> carteiras;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Parcela> parcelas;

    private Integer limite;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Renegociacao renegociacao;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vencimento vencimento;

    private String idProposta;

    private String numeroCartao;

    @Deprecated
    public Cartao() {
    }

    public Cartao(LocalDateTime emitidoEm, String titular, Set<Bloqueio> bloqueios, Set<Aviso> avisos,
                  Set<Carteira> carteiras, Set<Parcela> parcelas, Integer limite, Renegociacao renegociacao,
                  Vencimento vencimento, String idProposta, String numeroCartao) {
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
        this.numeroCartao = numeroCartao;
    }

    public Cartao(AssociaCartaoResponse cartaoResponse) {
        this.numeroCartao = cartaoResponse.getId();
        this.idProposta = cartaoResponse.getIdProposta();
        this.titular = cartaoResponse.getTitular();
        this.emitidoEm = cartaoResponse.getEmitidoEm();

        if (!cartaoResponse.getBloqueios().isEmpty()) {
            this.bloqueios = cartaoResponse.getBloqueios().stream().map(Bloqueio::new).collect(Collectors.toSet());
        }
        if (!cartaoResponse.getAvisos().isEmpty()) {
            this.avisos = cartaoResponse.getAvisos().stream().map(Aviso::new).collect(Collectors.toSet());
        }
        if (!cartaoResponse.getCarteiras().isEmpty()) {
            this.carteiras = cartaoResponse.getCarteiras().stream().map(Carteira::new).collect(Collectors.toSet());
        }
        if (!cartaoResponse.getParcelas().isEmpty()) {
            this.parcelas = cartaoResponse.getParcelas().stream().map(Parcela::new).collect(Collectors.toSet());
        }
        this.limite = cartaoResponse.getLimite();

        if (!Objects.isNull(cartaoResponse.getRenegociacoes())) {
            this.renegociacao = new Renegociacao(cartaoResponse.getRenegociacoes());
        }
        if(!Objects.isNull(cartaoResponse.getVencimento())) {
            this.vencimento =  new Vencimento(cartaoResponse.getVencimento());
        }
    }
    public String getNumeroCartao() {
        return this.numeroCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Set<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public Set<Aviso> getAvisos() {
        return avisos;
    }

    public Set<Carteira> getCarteiras() {
        return carteiras;
    }

    public Set<Parcela> getParcelas() {
        return parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }
}
