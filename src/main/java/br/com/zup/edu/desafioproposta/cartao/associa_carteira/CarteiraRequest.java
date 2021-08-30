package br.com.zup.edu.desafioproposta.cartao.associa_carteira;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private CarteiraDigital carteira;

    @JsonCreator( mode = JsonCreator.Mode.PROPERTIES)
    public CarteiraRequest(String email, CarteiraDigital carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public CarteiraDigital getCarteira() {
        return carteira;
    }
}
