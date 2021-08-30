package br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.associa_carteira.CarteiraDigital;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiId;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @Enumerated(EnumType.STRING)
    private CarteiraDigital carteiraDigital;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String email, CarteiraDigital carteiraDigital, Cartao cartao) {
        this.email = email;
        this.carteiraDigital = carteiraDigital;
        this.cartao = cartao;
    }

    public Carteira(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.apiId = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteira(Carteira carteira) {
        this.apiId = carteira.getApiId();
        this.email = carteira.getEmail();
        this.associadaEm = carteira.getAssociadaEm();
        this.emissor = carteira.getEmissor();
    }

    public Long getId() {
        return id;
    }

    public String getApiId() {
        return apiId;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }

}
