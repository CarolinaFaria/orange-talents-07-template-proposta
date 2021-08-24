package br.com.zup.edu.desafioproposta.cartao.associa_cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@FeignClient(name = "associaCartao", url="http://localhost:8888/api")
public interface AssociaCartao {

    @PostMapping("/cartoes")
    AssociaCartaoResponse criaCartao(@RequestBody @Valid CriaCartaoRequest criaCartaoRequest);

    @GetMapping("/cartoes")
    AssociaCartaoResponse associaCartao(@RequestParam String idProposta);

}
