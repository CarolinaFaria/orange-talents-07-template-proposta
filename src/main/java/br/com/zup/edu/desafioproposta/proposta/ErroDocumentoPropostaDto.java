package br.com.zup.edu.desafioproposta.proposta;

public class ErroDocumentoPropostaDto {

    private final String documento;
    private final String s;


    public ErroDocumentoPropostaDto(String documento, String s) {
        this.documento = documento;
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public String getDocumento() {
        return documento;
    }
}
