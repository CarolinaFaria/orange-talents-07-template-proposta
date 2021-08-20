package br.com.zup.edu.desafioproposta.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "analiseProposta", url="http://localhost:9999/api")
public interface AnaliseProposta {

    @PostMapping("/solicitacao")
    AnalisePropostaResponse analisar(@RequestBody @Valid AnalisePropostaRequest analisePropostaRequest);

}
