package br.com.zup.edu.desafioproposta.cartao.associa_carteira;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarteiraApiResponse {

    private String resultado;
    private String id;

    @JsonCreator( mode = JsonCreator.Mode.PROPERTIES)
    public CarteiraApiResponse(@JsonProperty("resultado") String resultado,@JsonProperty("id") String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
