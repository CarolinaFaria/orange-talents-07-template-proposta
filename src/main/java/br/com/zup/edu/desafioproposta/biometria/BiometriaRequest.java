package br.com.zup.edu.desafioproposta.biometria;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import br.com.zup.edu.desafioproposta.validacao.BiometriaValida;
import com.fasterxml.jackson.annotation.JsonCreator;


public class BiometriaRequest {


    @BiometriaValida
    private String biometria;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaRequest(String biometria) {
        this.biometria = biometria;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(biometria, cartao);

    }

    public String getBiometria() {
        return biometria;
    }
}
