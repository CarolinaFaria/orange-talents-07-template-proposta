package br.com.zup.edu.desafioproposta.cartao.associa_cartao;

import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class AssociaCartaoResponse {


    @NotBlank
    private String id;
    @NotBlank
    private String idProposta;
    private String titular;
    private LocalDateTime emitidoEm;
    private List<Bloqueio> bloqueios;
    private List<Aviso> avisos;
    private List<Carteira> carteiras;
    private List<Parcela> parcelas;
    private Integer limite;
    private Renegociacao renegociacoes;
    private Vencimento vencimento;


    public AssociaCartaoResponse(String id, String idProposta, String titular, LocalDateTime emitidoEm,
                                 List<Bloqueio> bloqueios, List<Aviso> avisos, List<Carteira> carteiras,
                                 List<Parcela> parcelas, Integer limite, Renegociacao renegociacoes,
                                 Vencimento vencimento) {
        this.id = id;
        this.idProposta = idProposta;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacoes = renegociacoes;
        this.vencimento = vencimento;
    }

    public String getId() {
        return id;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
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

    public Renegociacao getRenegociacoes() {
        return renegociacoes;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }
}
