package Biblioteca;

import java.util.HashMap;
import java.util.List;


public class Sistema {
	private static Sistema instance;
	private HashMap<String, Comando> comandos;
	private Biblioteca biblioteca;
	
	private Sistema() {
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
		Comando comando = new AdicionarObservador(observador, livro);
		comandos.put("adicionarObservador", comando);
		comando.executar();
	}
    
    public void buscarLivrosDisponiveis() {
        Comando comando = new ImprimirLivrosDisponiveis();
        comandos.put("imprimirLivrosDisponiveis", comando);
        comando.executar();
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

	public void printDadosUsuario(String codigoUsuario) {
		Comando comando = new ImprimirDadosUsuario(codigoUsuario);
        comandos.put("imprimirDadosUsuario", comando);
        comando.executar();
	}
	public void printDadosLivro(String codigoLivro) {
		Comando comando = new ImprimirDadosLivro(codigoLivro);
        comandos.put("imprimirDadosLivro", comando);
        comando.executar();
	}

	public void printDadosObservador(String codigoObservador) {
		Comando comando = new ImprimirDadosObservador(codigoObservador);
        comandos.put("imprimirDadosObservador", comando);
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
	
		case "usu":
			Sistema.getInstancia().printDadosUsuario(entrada[1]);
			break;
		case "liv":
			Sistema.getInstancia().printDadosLivro(entrada[1]);
			break;
		case "ntf":
			Sistema.getInstancia().printDadosObservador(entrada[1]);
			break;
			
		case "dis":
			Sistema.getInstancia().buscarLivrosDisponiveis();
			break;
		case "sai":
			System.exit(0); // Finaliza o programa
            break;
		
		case "undo":
			Sistema.getInstancia().desfazerUltimaOperacao();
			break;
		default:
			System.out.println("Comando não identificado.");
		}
	}
	
}
