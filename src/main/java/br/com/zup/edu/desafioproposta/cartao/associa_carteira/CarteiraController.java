package br.com.zup.edu.desafioproposta.cartao.associa_carteira;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.CartaoRepository;
import br.com.zup.edu.desafioproposta.cartao.CartaoService;
import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.Carteira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CarteiraController {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    CarteiraRepository carteiraRepository;
    @Autowired
    private CartaoService cartaoService;

    @PostMapping("/{id}/carteiras")
    @Transactional
    public ResponseEntity<?> cadastraCarteira(@PathVariable("id") Long idCartao, @RequestBody @Valid CarteiraRequest request,
                                              UriComponentsBuilder uriBuilder){
        Optional<Cartao> cartaoExiste = cartaoRepository.findById(idCartao);

        if(cartaoExiste.isEmpty()){ return ResponseEntity.notFound().build();}

        Cartao cartao = cartaoExiste.get();
        Optional<Carteira> carteiraExiste = carteiraRepository.findByCartaoAndCarteiraDigital(cartao, request.getCarteira());

        if(carteiraExiste.isPresent()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Cartão já associada com uma carteira do tipo "+ request.getCarteira());
        }

        return cartaoService.associaCarteira(cartao, request,uriBuilder);
    }
}
