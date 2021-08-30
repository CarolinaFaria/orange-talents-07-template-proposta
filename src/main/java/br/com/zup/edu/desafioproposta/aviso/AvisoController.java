package br.com.zup.edu.desafioproposta.aviso;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.CartaoRepository;
import br.com.zup.edu.desafioproposta.cartao.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class AvisoController {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    private CartaoService cartaoService;

    @PostMapping("/{id}/avisos")
    @Transactional
    public ResponseEntity<?> cadastraAviso(@PathVariable("id") Long idCartao, @RequestBody @Valid AvisoRequest request,
                                           @RequestHeader(value = "User-Agent") String userAgent,
                                           @RequestHeader(value = "ip") String ip){

        Optional<Cartao> cartaoExiste = cartaoRepository.findById(idCartao);

        if(cartaoExiste.isEmpty()){ return ResponseEntity.notFound().build();}

        Cartao cartao = cartaoExiste.get();

        return cartaoService.verificaAviso(cartao, request, userAgent, ip);

    }

}
