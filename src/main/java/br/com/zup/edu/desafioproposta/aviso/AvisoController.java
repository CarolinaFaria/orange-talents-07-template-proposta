package br.com.zup.edu.desafioproposta.aviso;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.CartaoRepository;
import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.Aviso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    AvisoRepository avisoRepository;

    @PostMapping("/{id}/avisos")
    @Transactional
    public ResponseEntity<?> cadastraAviso(@PathVariable("id") Long idCartao, @RequestBody @Valid AvisoRequest request,
                                           @RequestHeader(value = "User-Agent") String userAgent,
                                           @RequestHeader(value = "ip") String ip){

        Optional<Cartao> cartaoExiste = cartaoRepository.findById(idCartao);

        if(!cartaoExiste.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Aviso aviso = request.toModel(cartaoExiste.get(), ip, userAgent);
        avisoRepository.save(aviso);

        return ResponseEntity.ok().body("Aviso Cadastrado!");
    }

}
