package br.com.zup.edu.desafioproposta.cartao.cadastro_biometria;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import javax.persistence.*;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String biometria;

    @OneToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String biometria, Cartao cartao) {
        this.biometria = biometria;
        this.cartao = cartao;
    }


    public Long getId() {
        return id;
    }

    public String getBiometria() {
        return biometria;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
