package br.com.zup.edu.desafioproposta.proposta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.Properties;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder){
        Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(request.getDocumento());

        if(possivelProposta.isPresent()) {
            ErroDocumentoPropostaDto erroDocumentoPropostaDto = new ErroDocumentoPropostaDto("documento", "Já existe uma proposta com esse documento cadastrada!");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erroDocumentoPropostaDto);
        }

        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);
        URI uri = uriBuilder.path("propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
