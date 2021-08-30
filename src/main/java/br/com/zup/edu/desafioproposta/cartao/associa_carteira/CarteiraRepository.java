package br.com.zup.edu.desafioproposta.cartao.associa_carteira;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByCartaoAndCarteiraDigital(Cartao cartao, CarteiraDigital carteira);
}
