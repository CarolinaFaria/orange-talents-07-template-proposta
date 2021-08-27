package br.com.zup.edu.desafioproposta.cartao.bloquea_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.CartaoRepository;
import br.com.zup.edu.desafioproposta.cartao.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class BloqueioController {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    private BloqueioRepository bloqueioRepository;
    @Autowired
    private CartaoService cartaoService;

    @PostMapping("/{id}/bloqueio")
    @Transactional
    public ResponseEntity<?> bloqueaCartao(@PathVariable("id") Long idCartao, @RequestHeader(value = "User-Agent") String sistemaResponsavel,
                                           @RequestHeader(value = "ip") String ip){
        Optional<Cartao> cartaoExiste = cartaoRepository.findById(idCartao);
        Cartao cartao = cartaoExiste.get();
        Optional<Bloqueio> bloqueioExiste = bloqueioRepository.findByCartao(cartao);

        if(!cartaoExiste.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(bloqueioExiste.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já está bloqueado");
        }

        cartaoService.bloqueiaCartao(cartao, sistemaResponsavel, ip);

        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }
}
