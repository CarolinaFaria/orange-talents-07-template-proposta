package br.com.zup.edu.desafioproposta.proposta;

import br.com.zup.edu.desafioproposta.proposta.analise.enuns.EstadoProposta;

import java.math.BigDecimal;

public class PropostaResponse {

    private Long id;
    private String nome;
    private String email;
    private String documento;
    private BigDecimal salario;
    private String endereco;
    private EstadoProposta estadoProposta;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.documento = proposta.getDocumento();
        this.salario = proposta.getSalario();
        this.endereco = proposta.getEndereco();
        this.estadoProposta = proposta.getEstadoProposta();
    }

    public Long getId() {
        return id;
    }


}
