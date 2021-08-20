package br.com.zup.edu.desafioproposta.proposta.analise;

import br.com.zup.edu.desafioproposta.proposta.Proposta;
import br.com.zup.edu.desafioproposta.validacao.CPForCNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnalisePropostaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    @CPForCNPJ
    private String documento;
    @NotNull
    private String idProposta;

    public AnalisePropostaRequest(String nome, String documento, String idProposta) {
        this.nome = nome;
        this.documento = documento;
        this.idProposta = idProposta;
    }

    public AnalisePropostaRequest(Proposta proposta) {
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.idProposta = proposta.getId().toString();

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
}
