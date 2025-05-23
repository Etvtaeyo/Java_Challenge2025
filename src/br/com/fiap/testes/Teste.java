package br.com.fiap.testes;

import br.com.fiap.enums.TipoAvaliacaoEnum;
import br.com.fiap.models.*;

import java.util.regex.*;


import java.time.LocalDateTime;
import java.util.Scanner;

// 1- Obrigatoriamente é feito o cadastro de um usuário ou médico
// 2- Passa para o menu do respectivo usuario (medico ou paciente)
// ** 3- Se o medico quiser marcar consulta mas não tiver usuário cadastrado, ele precisa sair do menu de medico e cadastrar um paciente
// ** 4- quando o paciente é cadastrado, a consulta pode ser marcada (e o login é unico, ou seja, uma vez que o cadastro foi feito, a unica opção é entrar no menu)
// OBS: Fazer uma parte de login (validar senha e cpf)
public class Teste {
    static Scanner scanner = new Scanner(System.in);

    public static void menuPrincipal(){
        System.out.println("=== BEM VINDO AO HOSPITAL DAS CLÍNICAS ===");
        System.out.println("--- DESEJA REALIZAR UM CADASTRO DE ---");
        System.out.println("1. PACIENTE");
        System.out.println("2. MÉDICO");
        System.out.println("3. SAIR");
    }

    public static void MenuPaciente() {
        System.out.println("=== MENU PACIENTE ===");
        System.out.println("1. EDITAR USUÁRIO");
        System.out.println("2. DICA DE TELECONSULTA");
        System.out.println("3. MEUS DADOS");
        System.out.println("4. MINHA AGENDA");
        System.out.println("5. PESQUISA DE SATISFAÇÃO");
        System.out.println("6. PRECISO DE AJUDA");
        System.out.println("7. SAIR");
    }

    public static void MenuMedico() {
        System.out.println("=== MENU MÉDICO ===");
        System.out.println("1. EDITAR USUÁRIO");
        System.out.println("2. AGENDAR CONSULTAS");
        System.out.println("3. MEUS DADOS");
        System.out.println("4. MINHA AGENDA");
        System.out.println("5. SAIR");
    }

    public static String cadCpfCliente(){
        String cpf;
        boolean cpfValido;
        do {
            cpfValido = true;
            System.out.println("Digite o CPF (apenas números): ");
            cpf = scanner.nextLine();
            if (!cpf.matches("\\d{11}")) {
                System.out.println("CPF inválido! Deve conter exatamente 11 números.");
                cpfValido = false;
            }
            if(!cpf.matches("^[^\\s]*$")){
                System.out.println("O cpf não pode conter espaço!");
                cpfValido = false;
            }
        } while (!cpfValido);

        return cpf;
    }

    public static String[] cadEditCliente(){
        String nome, senha, email;

        do {
            System.out.println("Digite o nome completo: ");
            nome = scanner.nextLine();
            if (nome.matches(".*\\d.*")) {
                System.out.println("Não use números no nome.");
            }
        } while (nome.matches(".*\\d.*"));


        boolean senhaValida;
        do {
            senhaValida = true;
            System.out.println("Digite a senha: ");
            senha = scanner.nextLine();
            if (senha.length() < 8) {
                System.out.println("A senha precisa conter no mínimo 8 caracteres.");
                senhaValida = false;
            }
            if(!senha.matches("^[^\\s]*$")){
                System.out.println("A senha não pode conter espaço!");
                senhaValida = false;
            }
        } while (!senhaValida);

        boolean emailValido;
        do {
            emailValido = true;

            System.out.println("Digite o email: ");
            email = scanner.nextLine();
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Email inválido! Deve conter '@' e '.'");
                emailValido = false;
            }
            if(!email.matches("^[^\\s]*$")){
                System.out.println("O email não pode conter espaço!");
                emailValido = false;
            }
        } while (!emailValido);

        return new String[] {nome, senha, email};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Medico medico = new Medico();
        Agenda agendaMedico = new Agenda();

        Paciente paciente = new Paciente();
        Agenda agendaPaciente = new Agenda();

        //----------------------
        // cadastrar como medico ou usuario
        boolean cadastroUnicoMed = false, cadastroUnicoPac = false;
        int opCadastro;

        String nome, senha, cpf, email, especializacao;
        do {
            menuPrincipal();

            opCadastro = scanner.nextInt();


            //CADASTRO PACIENTE
            if (opCadastro == 1 && !cadastroUnicoPac) {
                cadastroUnicoPac = true;

                String[] dados = cadEditCliente();
                nome = dados[0];
                senha = dados[1];
                email = dados[2];

                cpf = cadCpfCliente();

                paciente.cadastroCliente(nome, email, senha, cpf, agendaPaciente);
            } //CADASTRO MÉDICO
            else if (opCadastro == 2 && !cadastroUnicoMed) {
                cadastroUnicoMed = true;

                String[] dados = cadEditCliente();
                nome = dados[0];
                senha = dados[1];
                email = dados[2];


                scanner.nextLine();
                System.out.println("Digite a especialização: ");
                especializacao = scanner.nextLine();

                cpf = cadCpfCliente();

                medico.cadastroMedico(nome, email, senha, cpf, agendaMedico, especializacao);
            }
            //====================================================
            //Cadastro PACIENTE ou MÉDICO
            switch (opCadastro) {
                //Parte do PACIENTe
                case 1:
                    int opPaciente;

                    do {
                        MenuPaciente();
                        opPaciente = scanner.nextInt();

                        switch (opPaciente) {
                            case 1:
                                System.out.println("=== EDITAR USUÁRIO SELECIONADO ===");

                                scanner.nextLine(); //debuffer

                                String[] dados = cadEditCliente();
                                nome = dados[0];
                                senha = dados[1];
                                email = dados[2];

                                paciente.alterarCliente(nome, senha, email);
                                break;

                            case 2:
                                System.out.println("Dica de teleconsulta selecionado:");

                                System.out.println("Esteja em um local confortável e silencioso para a sua teleconsulta");

                                System.out.println("Verifique que sua conexão com a internet esteja estável");

                                System.out.println("Entregue ou envie seus exames com antecedência");

                                System.out.println("Verique se o horário e a data de sua consulta estão corretos");
                                break;

                            case 3:
                                System.out.println("=== MEUS DADOS ===");
                                System.out.println(paciente.apresentarDados());
                                break;

                            case 4: // Minha agenda
                                agendaPaciente.mostrarConsultas();
                                break;

                            case 5:
                                Feedback feedback = new Feedback();
                                TipoAvaliacaoEnum avaliacaoEnum;
                                paciente.mostrarDadosFeedback();

                                System.out.println("=== PESQUISA DE SATISFAÇÃO ===");
                                System.out.println("Por favor, avalie o nosso serviço de 1 a 6");
                                int avaliacao = scanner.nextInt();
                                if (avaliacao == 1) {
                                    System.out.println("Sentimos muito pela sua experiência ruim");
                                    avaliacaoEnum = TipoAvaliacaoEnum.MUITO_RUIM;
                                } else if (avaliacao == 2) {
                                    System.out.println("Sentimos muito pela sua péssima experiência");
                                    avaliacaoEnum = TipoAvaliacaoEnum.RUIM;
                                } else if (avaliacao == 3) {
                                    System.out.println("Obrigado pela avaliação veremos como podemos melhorar nossos serviços");
                                    avaliacaoEnum = TipoAvaliacaoEnum.NORMAL;
                                } else if (avaliacao == 4) {
                                    System.out.println("Obrigado pela avaliação, esperamos contar com você novamente");
                                    avaliacaoEnum = TipoAvaliacaoEnum.BOM;
                                } else if (avaliacao == 5) {
                                    System.out.println("Obrigado pela sua ótima avaliação");
                                    avaliacaoEnum = TipoAvaliacaoEnum.MUITO_BOM;
                                } else {
                                    System.out.println("Obrigado pela sua ótima avaliação, contamos com você para sempre melhorar nosso sistema");
                                    avaliacaoEnum = TipoAvaliacaoEnum.EXCELENTE;
                                }
                                scanner.nextLine();
                                System.out.println("Faça uma descrição descrevendo a sua avaliação");
                                String descricao = scanner.nextLine();
                                feedback.criarFeedbackClasse(avaliacaoEnum, descricao);
                                paciente.cadastrarFeedback(feedback);


                                break;

                            case 6:
                                System.out.println("=== PRECISO DE AJUDA ===");
                                System.out.println("Para qualquer dúvida consulte a ouvidoria do Hospital das Clinicas : ouvidoria.hc@hc.fm.usp.br");
                                break;

                            case 7:
                                System.out.println("=== SAINDO DO MENU PACIENTE ===");
                                break;

                            default:
                                System.out.println("=== OPÇÃO INVÁLIDA! TENTE NOVAMENTE. ===");

                        }
                    } while (opPaciente != 7);
                    break;

                    // Parte Médico
                case 2:
                    int opMedico;

                    do {
                        MenuMedico();

                        opMedico = scanner.nextInt();

                        switch (opMedico) {
                            case 1:
                                System.out.println("=== EDITAR MÉDICO SELECIONADO ===");

                                scanner.nextLine(); //debuffer

                                String[] dados = cadEditCliente();
                                nome = dados[0];
                                senha = dados[1];
                                email = dados[2];

                                System.out.println("Digite a especialização: ");
                                especializacao = scanner.nextLine();

                                medico.alterarMedico(nome, senha, email, especializacao);
                                break;

                            case 2:
                                String StringDia, StringMes, StringHora, StringMin;
                                int dia = 0, mes = 0, hora = 0, min = 0;

                                boolean dataValido;

                                scanner.nextLine();

                                System.out.println("Digite o nome da consulta: ");
                                String nomeConsulta = scanner.nextLine();

                                boolean diaValido, mesValido, horaValido, minValido;

                                do{
                                    diaValido = true;

                                    System.out.println("Qual o dia da consulta? ");
                                    StringDia = scanner.next();

                                    if (StringDia.matches("-?\\d+")) {
                                        dia = Integer.parseInt(StringDia);
                                        if(dia >= 1 && dia <= 31){
                                            diaValido = false;
                                        }else{
                                            System.out.println("Dia inválido!");
                                        }

                                    } else {
                                        System.out.println("Contém caracteres não numéricos.");
                                        diaValido = false;
                                    }
                                }while(diaValido);

                                do{
                                    mesValido = true;

                                    System.out.println("Qual o mês da consulta? ");
                                    StringMes = scanner.next();

                                    if (StringDia.matches("-?\\d+")) {
                                        mes = Integer.parseInt(StringMes);
                                        if(mes >= 1 && mes <= 12){
                                            mesValido = false;
                                        }else{
                                            System.out.println("Mês inválido!");
                                        }
                                    } else {
                                        System.out.println("Contém caracteres não numéricos.");
                                        mesValido = false;
                                    }
                                }while(mesValido);

                                do{
                                    horaValido = true;

                                    System.out.println("Qual o hora da consulta? ");
                                    StringHora = scanner.next();

                                    if (StringDia.matches("-?\\d+")) {
                                        hora = Integer.parseInt(StringHora);
                                        if(hora >= 0 && hora <= 23){
                                            horaValido = false;
                                        }else{
                                            System.out.println("Hora inválido!");
                                        }
                                    } else {
                                        System.out.println("Contém caracteres não numéricos.");
                                        horaValido = false;
                                    }
                                }while(horaValido);

                                do{
                                    minValido = true;

                                    System.out.println("Qual o minuto da consulta? ");
                                    StringMin = scanner.next();

                                    if (StringDia.matches("-?\\d+")) {
                                        min = Integer.parseInt(StringMin);
                                        if(min >= 0 && min <= 59){
                                            minValido = false;
                                        }else{
                                            System.out.println("Dia inválido!");
                                        }

                                    } else {
                                        System.out.println("Contém caracteres não numéricos.");
                                        minValido = false;
                                    }
                                }while(minValido);

                                // Agendar consulta para o paciente cadastrado
                                if (paciente.getNome() == null) {
                                    System.out.println("Nenhum paciente disponível!");
                                    System.out.println("Por favor, cadastre um paciente antes de agendar consultas.");
                                    // Força sair do menu médico e voltar ao principal
                                    break;
                                } else {
                                    Consulta consulta = new Consulta(nomeConsulta, LocalDateTime.of(2025, mes, dia, hora, min), medico, paciente);
                                    System.out.println("Consulta cadastrada");
                                    agendaMedico.agendarConsulta(consulta);
                                }
                                break;

                            case 3:
                                System.out.println("=== MEUS DADOS ===");
                                System.out.println(medico.apresentarDados());
                                break;

                            case 4:
                                System.out.println("=== MINHA AGENDA ===");
                                agendaMedico.mostrarConsultas();
                                break;

                            case 5:
                                System.out.println("=== SAINDO DO MENU MÉDICO ===");
                                break;

                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    } while (opMedico != 5);//Break do menu medico
                    break;

                case 3:
                    System.out.println("=== SAINDO DO MENU PRINCIPAL ===");
                    break;

                default:
                    System.out.println("=== OPÇÃO INVÁLIDA! TENTE NOVAMENTE NO MENU GERAL===");
                    break;
            }
        } while(opCadastro != 3);
    }
}