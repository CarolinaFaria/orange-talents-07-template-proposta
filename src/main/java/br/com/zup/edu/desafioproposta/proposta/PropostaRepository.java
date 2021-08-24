package br.com.zup.edu.desafioproposta.proposta;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.proposta.analise.enuns.EstadoProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta,Long> {

    Optional<Proposta> findByDocumento(Object documento);

    List<Proposta> findAllByEstadoPropostaAndCartao(EstadoProposta elegivel,Long cartaoId);

}
