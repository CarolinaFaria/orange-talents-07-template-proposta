package br.com.zup.edu.desafioproposta.aviso;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.Aviso;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequest {

    @NotBlank
    private String destinoViagem;
    @Future
    @NotNull
    private LocalDate dataTerminoViagem;

    @JsonCreator( mode = JsonCreator.Mode.PROPERTIES)
    public AvisoRequest(String destinoViagem, @JsonFormat(pattern = "dd/MM/yyyy",
            shape = JsonFormat.Shape.STRING) LocalDate dataTerminoViagem) {

        this.destinoViagem = destinoViagem;
        this.dataTerminoViagem = dataTerminoViagem;
    }

    public Aviso toModel(Cartao cartao,String ip, String userAgent ) {
        return new Aviso(destinoViagem,dataTerminoViagem,ip,userAgent,cartao);
    }

}
