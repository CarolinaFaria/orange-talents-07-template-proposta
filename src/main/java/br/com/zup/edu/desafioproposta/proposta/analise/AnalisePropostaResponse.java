package br.com.zup.edu.desafioproposta.proposta.analise;

import br.com.zup.edu.desafioproposta.proposta.analise.enuns.StatusDevolvido;
import br.com.zup.edu.desafioproposta.validacao.CPForCNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnalisePropostaResponse {

    @NotBlank
    private String nome;
    @NotBlank
    @CPForCNPJ
    private String documento;
    @NotNull
    private String idProposta;

    private StatusDevolvido statusDevolvido;


    public AnalisePropostaResponse(String nome, String documento, String idProposta, StatusDevolvido statusDevolvido) {
        this.nome = nome;
        this.documento = documento;
        this.idProposta = idProposta;
        this.statusDevolvido = statusDevolvido;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public StatusDevolvido getStatusDevolvido() {
        return statusDevolvido;
    }
}
