package Biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
        Sistema sistema = Sistema.getInstancia();
        Scanner s = new Scanner(System.in);

        // Adicionar livros e usuarios
        Livro livro1 = new Livro("100", "Engenharia de Software", "AddisonWesley", new ArrayList<>(Arrays.asList("Ian Sommervile")), 6, 2000, 2);
        Livro livro2 = new Livro("101", "UML – Guia do Usuário", "Campus", new ArrayList<>(Arrays.asList("Grady Booch", "James Rumbaugh", "Ivar Jacobson")), 7, 2000, 1);
        Livro livro3 = new Livro("200", "Code Complete", "Microsoft Press", new ArrayList<>(Arrays.asList("Steve McConnell")), 2, 2014, 1);
        Livro livro4 = new Livro("201", "Agile Software Development, Principles, Patterns, and Practices", "Prentice Hall", new ArrayList<>(Arrays.asList("Robert Martin")), 1, 2002, 1);
        Livro livro5 = new Livro("300", "Refactoring: Improving the Design of Existing Code", "Addison Wesley Professional", new ArrayList<>(Arrays.asList("Martin Fowler")), 1, 1999, 2);
        
        AlunoPosGraduacao usu1 = new AlunoPosGraduacao("123", "João da Silva");
        AlunoGraduacao usu2 = new AlunoGraduacao("456", "Luiz Fernando Rodrigues");
        AlunoGraduacao usu3 = new AlunoGraduacao("789", "Pedro Paulo");
        Professor usu4 = new Professor("100", "Carlos Lucena");
        Professor usu5 = new Professor("101", "Ivon Monstro");
        
        sistema.adicionarLivro(livro1);
        sistema.adicionarLivro(livro2);
        sistema.adicionarLivro(livro3);
        sistema.adicionarLivro(livro4);
        sistema.adicionarLivro(livro5);
        sistema.adicionarUsuario(usu1);
        sistema.adicionarUsuario(usu2);
        sistema.adicionarUsuario(usu3);
        sistema.adicionarUsuario(usu4);
        sistema.adicionarUsuario(usu5);
        
        while(true) {
        	String input = s.nextLine();
        	Sistema.lerComando(input);
    	}
            
            
            
        
        /*
        sistema.realizarReserva("456", "100");
        sistema.realizarReserva("456", "100");
        
        sistema.realizarReserva("456", "300");
        sistema.realizarReserva("456", "201");
        sistema.realizarReserva("456", "101");
        
        sistema.realizarEmprestimo("456", "100");
        sistema.realizarEmprestimo("789", "100");
        sistema.realizarReserva("100", "100");
        sistema.printDadosUsuario("456");
        sistema.printDadosLivro("100");
        
        sistema.adicionarObservador("100", "300");
        sistema.realizarReserva("457", "300");

        sistema.realizarReserva("789", "300");
        sistema.realizarReserva("101", "300");
        sistema.realizarReserva("456", "300");
        sistema.printDadosObservador("100");
        
        // Realizar empréstimo
        //sistema.realizarReserva("456", "100");
        //sistema.realizarReserva("123", "100");

        sistema.realizarEmprestimo("100", "111");
        //sistema.realizarEmprestimo("123", "100");
 
        
        sistema.realizarReserva("123", "100");
        sistema.realizarReserva("123", "100");
        sistema.realizarEmprestimo("123", "100");
        sistema.realizarEmprestimo("123", "300");
        sistema.realizarEmprestimo("123", "201");
        sistema.realizarDevolucao("123", "100");
        sistema.realizarReserva("123", "100");
        sistema.realizarEmprestimo("123", "100");
        sistema.realizarEmprestimo("123", "100");
        sistema.realizarDevolucao("123", "300");
        sistema.printDadosLivro("100");
        
        sistema.realizarReserva("456", "100");
        sistema.realizarEmprestimo("100", "300");
        sistema.realizarEmprestimo("100", "300");
        sistema.realizarReserva("789", "100");
        sistema.realizarEmprestimo("100", "100");
        sistema.realizarDevolucao("100", "100");
        sistema.realizarReserva("789", "100");
        sistema.realizarEmprestimo("100", "100");
        //sistema.realizarEmprestimo("123", "300");
        //sistema.realizarEmprestimo("123", "100");
        //sistema.realizarEmprestimo("123", "300");
        
        
        // Realizar reserva
        sistema.realizarReserva("456", "101");

        // Realizar devolução
        sistema.realizarDevolucao("100");

        // Desfazer última operação
        sistema.desfazerUltimaOperacao();

        // Buscar livros disponíveis
        List<Livro> livrosDisponiveis = sistema.buscarLivrosDisponiveis();
        System.out.println("Livros disponíveis:");
        for (Livro livro : livrosDisponiveis) {
            System.out.println(livro.getTitulo());
        }*/
    }
}
