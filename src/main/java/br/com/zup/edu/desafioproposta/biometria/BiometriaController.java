package br.com.zup.edu.desafioproposta.biometria;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.Properties;

@RestController
@RequestMapping("/cartoes")
public class BiometriaController {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    BiometriaRepository biometriaRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cadastraBiometria(@PathVariable("id") Long idCartao, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder ){
        Optional<Cartao> cartaoExiste = cartaoRepository.findById(idCartao);

        if(!cartaoExiste.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Biometria biometria = request.toModel(cartaoExiste.get());

        biometriaRepository.save(biometria);

        URI uri = uriBuilder.path("cartao/{idCartao}/{id}").buildAndExpand(idCartao,biometria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
