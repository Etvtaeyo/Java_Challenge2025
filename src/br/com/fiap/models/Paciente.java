package br.com.fiap.models;

import br.com.fiap.enums.TipoUsuarioEnum;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario {
    private Agenda agenda;
    private List<Feedback> feedbacks = new ArrayList<>();

    public Paciente() {
        super();
        this.tipoUsuario = TipoUsuarioEnum.PACIENTE;
        this.agenda = new Agenda();
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void cadastrarFeedback(Feedback feedback) {
        this.feedbacks.add(feedback);
    }
    public void mostrarDadosFeedback() {
        if (feedbacks.isEmpty()) {
            System.out.println("Nenhum feedback realizado.");
        } else {
            System.out.println("=== SEUS FEEDBACKS REALIZADOS ===");
            for (Feedback feedback : feedbacks) {
                System.out.println("- Nota: " + feedback.getTipoAvaliacao() +" Descrição: " + feedback.getComentario() );

            }
        }
    }
}

