package br.com.zup.edu.desafioproposta.cartao.associa_aviso;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.associa_carteira.CarteiraDigital;
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
    public AvisoRequest(String destinoViagem, @JsonFormat(pattern = "yyyy-MM-dd",
            shape = JsonFormat.Shape.STRING) LocalDate dataTerminoViagem) {

        this.destinoViagem = destinoViagem;
        this.dataTerminoViagem = dataTerminoViagem;
    }


    public Aviso toModel(Cartao cartao,String ip, String userAgent ) {
        return new Aviso(destinoViagem,dataTerminoViagem,ip,userAgent,cartao);
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getDataTerminoViagem() {
        return dataTerminoViagem;
    }
}
