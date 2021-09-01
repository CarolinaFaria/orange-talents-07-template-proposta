package br.com.zup.edu.desafioproposta.config.utils.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BiometriaValidator implements ConstraintValidator <BiometriaValida, Object>{

    @Override
    public void initialize(BiometriaValida constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
        try{
            decoder.decode(String.valueOf(value));
            return true;
        }catch(IllegalArgumentException e) {
            return false;
        }
    }
}
