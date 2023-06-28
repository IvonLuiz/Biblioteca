package Biblioteca;

import java.util.ArrayList;
import java.util.List;

public class main {
	public static void main(String[] args) {
        Sistema sistema = Sistema.getInstance();

        // Adicionar livros e usuarios
        Livro livro1 = new Livro("100", "Livro 1", "Editora 1", new ArrayList<>(), 1, 2021, 2);
        Livro livro2 = new Livro("101", "Livro 2", "Editora 2", new ArrayList<>(), 1, 2022, 1);
        AlunoPosGraduacao usu1 = new AlunoPosGraduacao("123", "Ivon");
        AlunoGraduacao usu2 = new AlunoGraduacao("456", "Lucas");
        Professor usu3 = new Professor("123", "Chaves");
        sistema.adicionarLivro(livro1);
        sistema.adicionarLivro(livro2);
        sistema.adicionarUsuario(usu1);
        sistema.adicionarUsuario(usu2);
        sistema.adicionarUsuario(usu3);
        
        
        // Realizar empréstimo
        sistema.realizarEmprestimo("123", "100");

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
        }
    }
}
