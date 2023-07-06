package Biblioteca;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.temporal.ChronoUnit;

public class Biblioteca {
	
	private static Biblioteca uniqueInstance;
    private List<Livro> livros;
    private Map<String, Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    private Verificacao estrategiaVerificacao;

    
    private Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.emprestimos = new ArrayList<>();
    }

    public static Biblioteca getInstancia() {
    	if (uniqueInstance == null)
			uniqueInstance = new Biblioteca();
    	
    	return uniqueInstance;
    }
    
    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }
    
    public void adicionarUsuario(Usuario usuario) {
    	String codigo = usuario.getCodigo();
    	usuarios.put(codigo, usuario);
    }

    public void removerUsuario(Usuario usuario) {
        String codigo = usuario.getCodigo();
    	usuarios.remove(codigo);
    }
    
    public void getDadosUsuario(String codigoUsuario) {
    	Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        if(!VerificadorEntradas.verificarUsuario(usuario)) {
        	return;
        }
        String ret = "Usuario \n{\n";
        ret += usuario.toString();
		ret += "\nLivros alugados: \n" ;
        for (Emprestimo emprestimo : buscarEmprestimosPorCodigoUsuario(codigoUsuario)) {

  			ret += "Titulo: " + emprestimo.getLivro().getTitulo() + 
  					" - Alugado em: " + emprestimo.getDataAlugado() + 
  					" - Data limite retorno: " + emprestimo.getDataDevolucao() + 
  					" Status empréstimo: " + emprestimo.verificarAtrasoString();
  			ret += "\n";
          }
            	
        ret += "}";
    	System.out.println(ret);
    }
    
    
    
    
	public void getDadosLivro(String codigoLivro) {
		Livro livro = buscarLivroPorCodigo(codigoLivro);
        if(!VerificadorEntradas.verificarLivro(livro)) {
        	return;
        }
        String ret = "Livro: \n{";
        ret += livro.toString();
		ret += "Usuarios que estão alugando: \n" ;
        
        for (Emprestimo emprestimo : buscarEmprestimosPorCodigoLivro(codigoLivro)) {
			ret += emprestimo.getUsuario().getNome() + 
					" - Alugado em: " + emprestimo.getDataAlugado() + 
					" - Data limite retorno: " + emprestimo.getDataDevolucao();
			ret += "\n";
        }
        ret += "}";
        System.out.println(ret);
	}
	
	public void getDadosObservador(String codigoUsuario) {
		ObservadorLivro observador = (ObservadorLivro) buscarUsuarioPorCodigo(codigoUsuario);
		System.out.println(observador.toStringObs());	
	}
    
    // Buscas
    private List<Livro> buscarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro : livros) {
        	 if (livro.getQuantidadeDisponivel() > 0) {
                 livrosDisponiveis.add(livro);
             }
         }
         return livrosDisponiveis;
     }

    public void printLivrosDisponiveis(){
        List<Livro> livrosDisponiveis = buscarLivrosDisponiveis();
        System.out.println("Livros disponíveis:");
        for (Livro livro : livrosDisponiveis) {
            System.out.println(livro.getTitulo());
        }
    }

    private Livro buscarLivroPorCodigo(String codigoLivro) {
        for (Livro livro : livros) {
            if (livro.getCodigo().equals(codigoLivro)) {
                return livro;
            }
        }
        return null;
    }
    
    private Usuario buscarUsuarioPorCodigo(String codigoUsuario) {
    	return usuarios.get(codigoUsuario);
    }

    public List<Emprestimo> buscarEmprestimosPorCodigoUsuario(String codigoUsuario) {
    	List<Emprestimo> emprestimosDoUsuario = new ArrayList<>();
    	
    	// Loop retorna todos os emprestimos do Usuario
    	for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getCodigo() == codigoUsuario) {
            	emprestimosDoUsuario.add(emprestimo);
            }
        }
        return emprestimosDoUsuario;
    }
    
    
    public Emprestimo buscarEmprestimoPorCodigoUsuarioLivro(String codigoUsuario, String codigoLivro) {
    	
    	// Loop retorna empréstimo específico de um Usuario de um livro
    	for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getCodigo() == codigoUsuario && emprestimo.getLivro().getCodigo() == codigoLivro) {
            	return emprestimo;
            }
        }
        return null;
    }
    
    public List<Emprestimo> buscarEmprestimosPorCodigoLivro(String codigoLivro) {
    	List<Emprestimo> emprestimosDoLivro = new ArrayList<>();
    	
    	// Loop retorna todos os emprestimos do Livro
    	for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getCodigo() == codigoLivro) {
            	emprestimosDoLivro.add(emprestimo);
            }
        }
        return emprestimosDoLivro;
    }
    
    // Reserva
    public void realizarReserva(String codigoUsuario, String codigoLivro) {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        
        // Verifica se os parametros sao nulos
        if(!VerificadorEntradas.verificarUsuarioELivro(usuario, livro)) {
        	return;
        }

        
        setEstrategiaVerificacao(new VerificacaoReservaComposta());
        if (getEstrategiaVerificacao().podeRealizarAcao(usuario, livro) == false){
       	 return;
        }
        
    	
    	livro.addReserva(usuario);
    	usuario.addReserva(livro.getTitulo());
    	System.out.println("Reserva realizada com sucesso: " + usuario.getNome() + " - " + livro.getTitulo());
        
    }
    


	public void desfazerReserva(String codigoUsuario, String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        
        if(!VerificadorEntradas.verificarUsuarioELivro(usuario, livro)) {
        	return;
        }
        livro.removeReserva(usuario);
        usuario.removeReserva(livro.getTitulo());
        
        System.out.println("Reserva desfeita: " + codigoUsuario + " -> " + livro.getTitulo());        
    }
    
    
    
    
    // Empréstimo
     public void realizarEmprestimo(String codigoUsuario, String codigoLivro) {
         Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
         Livro livro = buscarLivroPorCodigo(codigoLivro);

         
         if(!VerificadorEntradas.verificarUsuarioELivro(usuario, livro)) {
         	return;
         }
         
         setEstrategiaVerificacao(new VerificacaoEmprestimoComposta());
         if (getEstrategiaVerificacao().podeRealizarAcao(usuario, livro) == false){
        	 return;
         }
         
    	 // Realizar Emprestimo
    	 emprestar(codigoUsuario, codigoLivro, livro, usuario);
    	 

     }
         
         
     private void emprestar(String codigoUsuario, String codigoLivro, Livro livro, Usuario usuario) {

    	 // Adicionar um usuario que realizou empréstimo ao livro
    	 Emprestimo emprestimo = new Emprestimo(usuario, livro); 
    	 emprestimos.add(emprestimo);
    	 usuario.incrementarQuantLivrosEmprestados();
    	 livro.decrementarQuantidadeDisponivel();

         if(livro.buscarReservaPorCodigo(codigoUsuario)) {
    		 desfazerReserva(codigoUsuario, codigoLivro); // Remover reserva, se existir
    	 }
         
         System.out.println("Empréstimo realizado com sucesso: " + usuario.getNome() + " - " + livro.getTitulo());
         System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());
     }


     // Devolucao
     public void realizarDevolucao(String codigoUsuario, String codigoLivro) {
         Livro livro = buscarLivroPorCodigo(codigoLivro);
         Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
         
         if(!VerificadorEntradas.verificarUsuarioELivro(usuario, livro)) {
         	return;
         }
         
         Emprestimo emprestimo = buscarEmprestimoPorCodigoUsuarioLivro(codigoUsuario, codigoLivro);
         if (emprestimo == null) {
        	 System.out.println("Usuário não possui empréstimo para determinado livro.");
        	 return;
         }
         
         emprestimos.remove(emprestimo);
         usuario.decrementarQuantLivrosEmprestados();
         livro.incrementarQuantidadeDisponivel();

         System.out.println("Devolução de " + usuario.getNome() + " realizada com sucesso: " + livro.getTitulo());
     }

    


	public void adicionarObservador(String codigoUsuario, String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        ObservadorLivro observador = (ObservadorLivro) buscarUsuarioPorCodigo(codigoUsuario);
        
        livro.adicionarObservador(observador);
        System.out.println("Observador " + ((Usuario) observador).getNome() + " de " + livro.getTitulo() + " criado.");
        
	}



	public Verificacao getEstrategiaVerificacao() {
		return estrategiaVerificacao;
	}

	public void setEstrategiaVerificacao(Verificacao estrategiaVerificacao) {
		this.estrategiaVerificacao = estrategiaVerificacao;
	}
	
	
	// Verifica se o usuario esta com algum emprestimo atrasado, retorta true se estiver pelo menos um atrasado, false caso contrario
	public boolean verificarAtrasoDevolucaoUsuario(String codigoUsuario) {
		List<Emprestimo> emprestimos = buscarEmprestimosPorCodigoUsuario(codigoUsuario);
		for (Emprestimo emp : emprestimos) {
			if (emp.verificarAtraso()) {
				return true;
			}
		}
		return false;
	}
	
 }