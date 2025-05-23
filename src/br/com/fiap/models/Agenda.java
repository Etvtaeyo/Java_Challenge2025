package br.com.fiap.models;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Agenda {
    private List<Consulta> consultas = new ArrayList<>();

    public void agendarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }


    public Agenda() {
        this.consultas = new ArrayList<>();
    }

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void mostrarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
        } else {
            System.out.println("=== SUAS CONSULTAS AGENDADAS ===");
            for (Consulta consulta : consultas) {
                System.out.println("- " + consulta.getNome() + " Às " + consulta.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

            }
        }
    }
}





