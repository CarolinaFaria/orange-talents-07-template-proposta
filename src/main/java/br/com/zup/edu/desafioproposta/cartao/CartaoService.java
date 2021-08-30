package br.com.zup.edu.desafioproposta.cartao;

import br.com.zup.edu.desafioproposta.cartao.associa_carteira.CarteiraApiResponse;
import br.com.zup.edu.desafioproposta.cartao.associa_aviso.AvisoRepository;
import br.com.zup.edu.desafioproposta.cartao.associa_aviso.AvisoRequest;
import br.com.zup.edu.desafioproposta.cartao.associa_aviso.AvisoApiResponse;
import br.com.zup.edu.desafioproposta.cartao.associa_cartao.AssociaCartaoResponse;
import br.com.zup.edu.desafioproposta.cartao.associa_carteira.CarteiraRepository;
import br.com.zup.edu.desafioproposta.cartao.bloqueia_cartao.Bloqueio;
import br.com.zup.edu.desafioproposta.cartao.bloqueia_cartao.BloqueioRepository;
import br.com.zup.edu.desafioproposta.cartao.bloqueia_cartao.BloqueioRequest;
import br.com.zup.edu.desafioproposta.cartao.bloqueia_cartao.BloqueioResponse;
import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.Aviso;
import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.Carteira;
import br.com.zup.edu.desafioproposta.cartao.associa_carteira.CarteiraRequest;
import br.com.zup.edu.desafioproposta.proposta.Proposta;
import br.com.zup.edu.desafioproposta.proposta.PropostaRepository;
import br.com.zup.edu.desafioproposta.proposta.analisa_proposta.enuns.EstadoProposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Service
@Transactional
public class CartaoService {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    ApiCartaoServiceExterno apiCartaoServiceExterno;

    @Autowired
    BloqueioRepository bloqueioRepository;

    @Autowired
    AvisoRepository avisoRepository;
    @Autowired
    CarteiraRepository carteiraRepository;

    @Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
    public void vinculaCartaoProposta(){
        List<Proposta> propostasSemCartao = propostaRepository.findAllByEstadoPropostaAndCartao(EstadoProposta.ELEGIVEL, null);

        propostasSemCartao.forEach(proposta -> {
            try{
                AssociaCartaoResponse cartaoResponse = apiCartaoServiceExterno.associaCartao(proposta.getId().toString());
                proposta.getCartao(new Cartao(cartaoResponse));
                propostaRepository.save(proposta);
            }catch (FeignException e){
                e.getMessage();
            }

        });


    }

    public void bloqueiaCartao(Cartao cartao, String ip, String sistemaResponsavel){

               try {BloqueioResponse bloqueioResponse = apiCartaoServiceExterno.bloqueaCartao(cartao.getNumeroCartao(), new BloqueioRequest("propostas"));
                Bloqueio bloqueio = new Bloqueio(ip, sistemaResponsavel,cartao);
                cartao.setStatusCartao(StatusCartao.BLOQUEADO);
                bloqueioRepository.save(bloqueio);
               } catch (FeignException e){
               }
    }

    public ResponseEntity<?> verificaAviso(Cartao cartao, AvisoRequest avisoRequest, String ip, String userAgent){
        try{
            AvisoApiResponse avisoApiResponse = apiCartaoServiceExterno.avisoCartao(cartao.getNumeroCartao(),
                    new AvisoRequest(avisoRequest.getDestinoViagem(), avisoRequest.getDataTerminoViagem()));
            Aviso aviso = new Aviso(avisoRequest.getDestinoViagem(),avisoRequest.getDataTerminoViagem(),ip,userAgent,cartao);
            avisoRepository.save(aviso);
            return ResponseEntity.ok().body("Aviso Cadastrado!");
        }catch(FeignException e){
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    public ResponseEntity<?>  associaCarteira(Cartao cartao, CarteiraRequest request, UriComponentsBuilder uriBuilder) {
        try{
            CarteiraApiResponse carteiraApiResponse = apiCartaoServiceExterno.associaCarteira(cartao.getNumeroCartao(),
                    new CarteiraRequest(request.getEmail(), request.getCarteira()));
            Carteira carteira = new Carteira(request.getEmail(), request.getCarteira(),cartao);
            carteiraRepository.save(carteira);

            URI uri = uriBuilder.path("cartoes/{idCartao}/carteira/{id}").buildAndExpand(cartao.getId(), carteira.getId()).toUri();
            return ResponseEntity.created(uri).build();

        }catch(FeignException e){
            return ResponseEntity.badRequest().body("Não foi possível cadastrar Carteira");
        }
    }
}
