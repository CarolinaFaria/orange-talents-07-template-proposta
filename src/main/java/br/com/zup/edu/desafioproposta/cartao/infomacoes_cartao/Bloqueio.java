package br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiId;
    private LocalDateTime bloqueadoAte;
    private String sistemaResponsavel;
    private boolean ativo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String id, LocalDateTime bloqueadoAte, String sistemaResponsavel, boolean ativo) {
        this.apiId = id;
        this.bloqueadoAte = bloqueadoAte;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }
}
