package br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String destinoViagem;
    @Future
    private LocalDate dataTerminoViagem;
    private LocalDateTime instanteAviso;
    @NotBlank
    private String ip;
    @NotBlank
    private String userAgent;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cartao cartao;

    @Deprecated
    public Aviso() {
    }

    public Aviso(String destinoViagem, LocalDate dataTerminoViagem, String ip, String userAgent, Cartao cartao) {
        this.destinoViagem = destinoViagem;
        this.dataTerminoViagem = dataTerminoViagem;
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
        this.instanteAviso = LocalDateTime.now();
    }


    public Aviso(LocalDate validoAte, String destino) {
        this.dataTerminoViagem = validoAte;
        this.destinoViagem = destino;
    }

    public Aviso(Aviso aviso) {
        this.dataTerminoViagem = aviso.getValidoAte();
        this.destinoViagem = aviso.getDestino();
    }

    public LocalDate getValidoAte() {
        return dataTerminoViagem;
    }

    public String getDestino() {
        return destinoViagem;
    }
}
