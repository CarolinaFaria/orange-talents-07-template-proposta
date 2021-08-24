package br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

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

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.apiId = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }
}
