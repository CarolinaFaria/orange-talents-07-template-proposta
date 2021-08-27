package br.com.zup.edu.desafioproposta.aviso;

import br.com.zup.edu.desafioproposta.cartao.infomacoes_cartao.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {

}
