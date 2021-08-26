package br.com.zup.edu.desafioproposta.proposta.analisa_proposta.enuns;

public enum StatusDevolvido {

    COM_RESTRICAO{
        @Override
        public EstadoProposta defineEstado(){
            return EstadoProposta.NAO_ELEGIVEL;
    }
    },


    SEM_RESTRICAO{
        @Override
        public EstadoProposta defineEstado() {
            return EstadoProposta.ELEGIVEL;
        }
    };

    public abstract EstadoProposta defineEstado();


}
