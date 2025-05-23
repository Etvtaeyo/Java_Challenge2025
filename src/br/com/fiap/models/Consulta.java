package br.com.fiap.models;

import java.time.LocalDateTime;

public class Consulta {
    private LocalDateTime dataHora;
    private Medico medico;
    private Paciente paciente;
    private String nome;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Consulta(String nome,LocalDateTime data, Medico medico, Paciente paciente) {
        this.nome = nome;
        this.dataHora = data;
        this.medico = medico;
        this.paciente = paciente;
    }
public String getDescricao() {
    return "Consulta com Dr. " + medico.getNome() + "em " + dataHora.toString();
}
}