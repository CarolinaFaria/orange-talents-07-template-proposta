package br.com.zup.edu.desafioproposta.proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Properties;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder){
        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        URI uri = uriBuilder.path("propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
