package br.com.zup.edu.desafioproposta.proposta;

public class ErroDocumentoPropostaDto {

    private final String campo;
    private final String erro;


    public ErroDocumentoPropostaDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public String getCampo() {
        return campo;
    }
}
