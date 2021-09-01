package br.com.zup.edu.desafioproposta.config.utils.cripografia;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

public class JasyptConfig {


    public String criptografaDocumento(String documentoACriptografar){
        return encryptor().encrypt(documentoACriptografar);
    }

    public String descriptografaDocumento(String documentoADescriptografar) {
        return encryptor().decrypt(documentoADescriptografar);
    }

    // retorna um criptografador já configurado
    public StandardPBEStringEncryptor encryptor() {
        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword("pamonha");
        textEncryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        textEncryptor.setIvGenerator(new RandomIvGenerator());

        return textEncryptor;
    }

    // Cria hash do documento
    public String hash(String documento){
        String sha256hex = DigestUtils.sha256Hex(documento);
        return sha256hex;
    }


}
