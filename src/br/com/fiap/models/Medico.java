package br.com.fiap.models;

import br.com.fiap.enums.TipoUsuarioEnum;

public class Medico extends Usuario {
    private String especializacao;

    public String getEspecializacao() {return especializacao;}
    public void setEspecializacao(String especializacao) {this.especializacao = especializacao;}


    //Métodos
    public Medico(){
        tipoUsuario = TipoUsuarioEnum.MEDICO;
    }

    public void alterarMedico(String nome, String email, String senha, String especializacao){
        super.alterarCliente(nome, email, senha);
        this.especializacao = especializacao;
    }

    public void cadastroMedico(String nome, String email, String cpf, String senha, Agenda agenda, String especializacao) {
        cadastroCliente(nome, email, cpf, senha, agenda);
        this.especializacao = especializacao;
    }

    public String apresentarMedico(){
        return super.apresentarDados() +
                "Especialização: "+especializacao+"\n";
    }

    public void cadastrarConsulta(Consulta consulta){
        agenda.agendarConsulta(consulta);
    }
}
