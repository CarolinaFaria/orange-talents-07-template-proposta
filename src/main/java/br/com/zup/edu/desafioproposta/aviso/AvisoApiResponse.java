package br.com.zup.edu.desafioproposta.aviso;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AvisoApiResponse {

    private String resultado;

    @JsonCreator( mode = JsonCreator.Mode.PROPERTIES)
    public AvisoApiResponse(@JsonProperty("resultado") String resultado) {

        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
