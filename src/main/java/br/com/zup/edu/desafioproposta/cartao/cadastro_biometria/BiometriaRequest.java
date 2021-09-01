package br.com.zup.edu.desafioproposta.cartao.cadastro_biometria;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import br.com.zup.edu.desafioproposta.config.utils.validacao.BiometriaValida;
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
