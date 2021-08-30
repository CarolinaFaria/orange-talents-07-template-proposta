package br.com.zup.edu.desafioproposta.cartao.bloqueia_cartao;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio,Long> {

    Optional<Bloqueio> findByCartao(Cartao cartao);
}
