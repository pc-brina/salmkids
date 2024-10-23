package view;

import java.util.Arrays;

class Palavra {
    private String palavra;
    
    public Palavra(String palavra) {
        this.palavra = palavra;

    }

    public boolean verificarResposta(String resposta) {
        return palavra.equalsIgnoreCase(resposta);
    }


    public String getPalavra() {
        return palavra;
    }

}