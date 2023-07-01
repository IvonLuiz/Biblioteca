package Biblioteca;

import java.util.HashMap;
import java.util.List;


public class Sistema {
	private static Sistema instance;
	private HashMap<String, Comando> comandos;
	private Biblioteca biblioteca;
	
	public Sistema() {
        this.comandos = new HashMap<>();
        this.biblioteca = Biblioteca.getInstancia();
    }
	
	public static Sistema getInstancia() {
        if (instance == null) {
            synchronized (Sistema.class) {
                if (instance == null) {
                    instance = new Sistema();
                }
            }
        }
        return instance;
    }
	
	
	public void adicionarLivro(Livro livro) {
		biblioteca.adicionarLivro(livro);
    }

    public void removerLivro(Livro livro) {
    	biblioteca.removerLivro(livro);
    }

	public void adicionarUsuario(Usuario usuario) {
		biblioteca.adicionarUsuario(usuario);
    }

    public void removerUsuario(Usuario usuario) {
    	biblioteca.removerUsuario(usuario);
    }
    
	public void adicionarObservador(String observador, String livro) {
		biblioteca.adicionarObservador(observador, livro);		
	}
    
    public List<Livro> buscarLivrosDisponiveis() {
        return biblioteca.buscarLivrosDisponiveis();
    }

    public void realizarEmprestimo(String codigoUsuario, String codigoLivro) {
        Comando comando = new RealizarEmprestimo(codigoUsuario, codigoLivro);
        comandos.put("realizarEmprestimo", comando);
        comando.executar();
    }
    
    public void realizarDevolucao(String codigoUsuario, String codigoLivro) {
        Comando comando = new RealizarDevolucao(codigoUsuario, codigoLivro);
        comandos.put("realizarDevolucao", comando);
        comando.executar();
    }
    
    public void realizarReserva(String codigoUsuario, String codigoLivro) {
        Comando comando = new RealizarReserva(codigoUsuario, codigoLivro);
        comandos.put("realizarReserva", comando);
        comando.executar();
    }


    public void desfazerUltimaOperacao() {
        if (!comandos.isEmpty()) {
            String ultimoComando = comandos.keySet().iterator().next();
            Comando comando = comandos.remove(ultimoComando);
            System.out.println("Desfazendo última operação: " + ultimoComando);
            comando.undo();
        } else {
            System.out.println("Não houveram comandos para serems desfeitos.");
        }
    }

	public static void lerComando(String input) {
		String[] entrada = input.split(" ");
		
		switch(entrada[0]) {
		case "emp":
			Sistema.getInstancia().realizarEmprestimo(entrada[1], entrada[2]);
			break;
		case "dev":
			Sistema.getInstancia().realizarDevolucao(entrada[1], entrada[2]);
			break;
		case "res":
			Sistema.getInstancia().realizarReserva(entrada[1], entrada[2]);
			break;
		
		case "obs":
			Sistema.getInstancia().adicionarObservador(entrada[1], entrada[2]);
			break;
		default:
			System.out.println("Comando não identificado.");
		}
	}


}
