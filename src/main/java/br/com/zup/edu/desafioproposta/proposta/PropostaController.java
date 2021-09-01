package br.com.zup.edu.desafioproposta.proposta;

import br.com.zup.edu.desafioproposta.config.utils.cripografia.JasyptConfig;
import br.com.zup.edu.desafioproposta.proposta.analisa_proposta.AnaliseProposta;
import br.com.zup.edu.desafioproposta.proposta.analisa_proposta.AnalisePropostaRequest;
import br.com.zup.edu.desafioproposta.proposta.analisa_proposta.AnalisePropostaResponse;
import br.com.zup.edu.desafioproposta.proposta.analisa_proposta.enuns.StatusDevolvido;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseProposta analiseProposta;

    private final Tracer tracer;

    public PropostaController(Tracer tracer) {
        this.tracer = tracer;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder){
        String requestHash = hash(request.getDocumento());
        Optional<Proposta> possivelProposta = propostaRepository.findByDocumentoHash(requestHash);

        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", "carolina@gmail.com");
        activeSpan.log("Testando log");

        if(possivelProposta.isPresent()) {
            ErroDocumentoPropostaDto erroDocumentoPropostaDto = new ErroDocumentoPropostaDto("documento",
                    "Já existe uma proposta com esse documento cadastrada!");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erroDocumentoPropostaDto);
        }

        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);
        resultadoAnalise(proposta);

        URI uri = uriBuilder.path("propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> acompanhaProposta(@PathVariable Long id) {
        Optional<Proposta> possivelProposta = propostaRepository.findById(id);

        tracer.activeSpan().setBaggageItem("cartao.id", id.toString());

        if (possivelProposta.isPresent()) {
            Proposta proposta = possivelProposta.get();
            return ResponseEntity.ok(new PropostaResponse(proposta));
        }
        return ResponseEntity.notFound().build();
    }

    private void resultadoAnalise(Proposta proposta) {
        AnalisePropostaRequest request = new AnalisePropostaRequest(proposta);
        StatusDevolvido statusDevolvido;
        try{
            AnalisePropostaResponse response = analiseProposta.analisar(request);
            statusDevolvido = StatusDevolvido.SEM_RESTRICAO;
        } catch (FeignException e){
            statusDevolvido = StatusDevolvido.COM_RESTRICAO;
        }
        proposta.setEstadoProposta(statusDevolvido.defineEstado());
    }

    private String hash(String documento) {
        return new JasyptConfig().hash(documento);
    }
}
