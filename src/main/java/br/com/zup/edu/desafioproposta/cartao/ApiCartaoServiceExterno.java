package br.com.zup.edu.desafioproposta.cartao;

import br.com.zup.edu.desafioproposta.cartao.associa_aviso.AvisoRequest;
import br.com.zup.edu.desafioproposta.cartao.associa_aviso.AvisoApiResponse;
import br.com.zup.edu.desafioproposta.cartao.associa_cartao.AssociaCartaoResponse;
import br.com.zup.edu.desafioproposta.cartao.associa_cartao.CriaCartaoRequest;
import br.com.zup.edu.desafioproposta.cartao.associa_carteira.CarteiraApiResponse;
import br.com.zup.edu.desafioproposta.cartao.associa_carteira.CarteiraRequest;
import br.com.zup.edu.desafioproposta.cartao.bloqueia_cartao.BloqueioResponse;
import br.com.zup.edu.desafioproposta.cartao.bloqueia_cartao.BloqueioRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "associaCartao", url="http://localhost:8888/api")
public interface ApiCartaoServiceExterno {

    @PostMapping("/cartoes")
    AssociaCartaoResponse criaCartao(@RequestBody @Valid CriaCartaoRequest criaCartaoRequest);

    @PostMapping("/cartoes/{id}/bloqueios")
    BloqueioResponse bloqueaCartao(@PathVariable String id, @RequestBody BloqueioRequest bloqueioRequest);

    @GetMapping("/cartoes")
    AssociaCartaoResponse associaCartao(@RequestParam String idProposta);

    @PostMapping("/cartoes/{id}/avisos")
    AvisoApiResponse avisoCartao(@PathVariable String id, @RequestBody AvisoRequest avisoRequest);

    @PostMapping("/cartoes/{id}/carteiras")
    CarteiraApiResponse associaCarteira(@PathVariable String id, @RequestBody CarteiraRequest carteiraRequest);

}
