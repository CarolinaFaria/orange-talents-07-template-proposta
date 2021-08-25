package br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime validoAte;
    private String destino;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cartao cartao;

    @Deprecated
    public Aviso() {
    }

    public Aviso(LocalDateTime validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Aviso(Aviso aviso) {
        this.validoAte = aviso.getValidoAte();
        this.destino = aviso.getDestino();
    }

    public LocalDateTime getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
