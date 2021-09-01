package br.com.zup.edu.desafioproposta.proposta;

import br.com.zup.edu.desafioproposta.cartao.Cartao;
import br.com.zup.edu.desafioproposta.config.utils.cripografia.JasyptConfig;
import br.com.zup.edu.desafioproposta.proposta.analisa_proposta.enuns.EstadoProposta;
import br.com.zup.edu.desafioproposta.config.utils.validacao.CPForCNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String documento;
    private String documentoHash;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;
    @Enumerated
    private EstadoProposta estadoProposta;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = cripografa(documento);
        this.documentoHash = hash(documento);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta(Proposta proposta) {
        this.documento = descripografa(proposta.getDocumento());
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
    }


    private String cripografa(String documento) {
        return new JasyptConfig().criptografaDocumento(documento);
    }

    private String hash(String documento) {
        return new JasyptConfig().hash(documento);
    }

    private String descripografa(String documento) {
        return new JasyptConfig().descriptografaDocumento(documento);
    }



    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return descripografa(documento);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }

    public void setEstadoProposta(EstadoProposta estadoProposta) {
        this.estadoProposta = estadoProposta;
    }

    public void getCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getDocumentoHash() {
        return documentoHash;
    }
}
