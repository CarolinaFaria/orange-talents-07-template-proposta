package br.com.zup.edu.desafioproposta.cartao.bloquea_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.CartaoRepository;
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

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> bloqueaCartao(@PathVariable("id") Long idCartao, @RequestHeader(value = "User-Agent") String sistemaResponsavel,
                                           @RequestHeader(value = "ip") String ip){
        Optional<Cartao> cartaoExiste = cartaoRepository.findById(idCartao);
        Optional<Bloqueio> bloqueioExiste = bloqueioRepository.findByCartao(cartaoExiste.get());

        if(!cartaoExiste.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(bloqueioExiste.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já está bloqueado");
        }

        Bloqueio bloqueio = new Bloqueio(ip, sistemaResponsavel,cartaoExiste.get());
        bloqueioRepository.save(bloqueio);

        return ResponseEntity.ok().build();
    }
}
