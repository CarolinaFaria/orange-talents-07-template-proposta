package br.com.zup.edu.desafioproposta.cartao.bloquea_cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioResponse {
    private String resultado;

    public BloqueioResponse(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
