package br.com.fiap.models;

import br.com.fiap.enums.TipoUsuarioEnum;

//Atribuição
public abstract class Usuario {
    protected String nome;
    protected String email;
    protected String cpf;
    protected String senha;
    protected Agenda agenda;
    protected TipoUsuarioEnum tipoUsuario;

    //Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    //Método
    public void alterarCliente(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }

    public void cadastroCliente(String nome, String email, String cpf, String senha, Agenda agenda) {
        alterarCliente(nome, email, senha);
        this.cpf = cpf;
        this.agenda = agenda;
    }

    public String apresentarDados(){
        return "Nome: "+ nome+"\n"+
                "CPF: "+ cpf+"\n"+
                "Email: "+email+"\n";
    }

    public void apresentarAgenda(){

    }
}


