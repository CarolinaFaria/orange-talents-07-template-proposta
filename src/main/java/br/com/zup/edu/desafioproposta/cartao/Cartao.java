package br.com.zup.edu.desafioproposta.cartao;

import br.com.zup.edu.desafioproposta.cartao.associa_cartao.AssociaCartaoResponse;
import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime emitidoEm;

    private String titular;

    @OneToMany(mappedBy = "cartao",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Aviso> avisos = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Carteira> carteiras = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Parcela> parcelas = new ArrayList<>();

    private Integer limite;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Renegociacao> renegociacoes = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vencimento vencimento;

    private String idProposta;

    private String numeroCartao;

    @Deprecated
    public Cartao() {
    }

    public Cartao(LocalDateTime emitidoEm, String titular, List<Bloqueio> bloqueios, List<Aviso> avisos,
                  List<Carteira> carteiras, List<Parcela> parcelas, Integer limite, List<Renegociacao> renegociacoes,
                  Vencimento vencimento, String idProposta, String numeroCartao) {
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacoes = renegociacoes;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
        this.numeroCartao = numeroCartao;
    }

    public Cartao(AssociaCartaoResponse cartaoResponse) {
        this.numeroCartao = cartaoResponse.getId();
        this.idProposta = cartaoResponse.getIdProposta();
        this.titular = cartaoResponse.getTitular();
        this.emitidoEm = cartaoResponse.getEmitidoEm();
        this.bloqueios = cartaoResponse.getBloqueios();
        this.avisos = cartaoResponse.getAvisos();
        this.carteiras = cartaoResponse.getCarteiras();
        this.parcelas = cartaoResponse.getParcelas();
        this.limite = cartaoResponse.getLimite();
        this.renegociacoes = cartaoResponse.getRenegociacoes();
        this.vencimento = cartaoResponse.getVencimento();
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

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public List<Renegociacao> getRenegociacoes() {
        return renegociacoes;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
