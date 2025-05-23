package br.com.fiap.models;

import br.com.fiap.enums.TipoAvaliacaoEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Feedback {
    private TipoAvaliacaoEnum tipoAvaliacao;
    private String comentario;

    public String getComentario() {return comentario;}
    public void setComentario(String comentario) {this.comentario = comentario;}

    public TipoAvaliacaoEnum getTipoAvaliacao() {return tipoAvaliacao;}
    public void setTipoAvaliacao(TipoAvaliacaoEnum tipoAvaliacao) {this.tipoAvaliacao = tipoAvaliacao;}

    public void criarFeedbackClasse(TipoAvaliacaoEnum tipoAvaliacao, String comentario){
        this.tipoAvaliacao = tipoAvaliacao;
        this.comentario = comentario;
    }
}




