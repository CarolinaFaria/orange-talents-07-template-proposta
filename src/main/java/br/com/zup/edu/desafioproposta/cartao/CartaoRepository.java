package br.com.zup.edu.desafioproposta.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    @Override
    Optional<Cartao> findById(Long aLong);

    List<Cartao> findAllByStatusCartao(StatusCartao statusCartao);
}
