package br.com.zup.edu.desafioproposta.cartao;

import br.com.zup.edu.desafioproposta.cartao.associa_cartao.AssociaCartao;
import br.com.zup.edu.desafioproposta.cartao.associa_cartao.AssociaCartaoResponse;
import br.com.zup.edu.desafioproposta.proposta.Proposta;
import br.com.zup.edu.desafioproposta.proposta.PropostaRepository;
import br.com.zup.edu.desafioproposta.proposta.analisa_proposta.enuns.EstadoProposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CartaoService {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    AssociaCartao associaCartao;

    @Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
    public void vinculaCartaoProposta(){
        List<Proposta> propostasSemCartao = propostaRepository.findAllByEstadoPropostaAndCartao(EstadoProposta.ELEGIVEL, null);

        propostasSemCartao.forEach(proposta -> {
            try{
                AssociaCartaoResponse cartaoResponse = associaCartao.associaCartao(proposta.getId().toString());
                proposta.getCartao(new Cartao(cartaoResponse));
                propostaRepository.save(proposta);
            }catch (FeignException e){
                e.getMessage();
            }

        });


    }





}
