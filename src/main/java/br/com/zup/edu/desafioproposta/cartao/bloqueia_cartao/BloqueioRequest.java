package br.com.zup.edu.desafioproposta.cartao.bloqueia_cartao;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BloqueioRequest {

    private String sistemaResponsavel;

    @JsonCreator( mode = JsonCreator.Mode.PROPERTIES)
    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
