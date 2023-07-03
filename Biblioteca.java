package Biblioteca;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class Biblioteca {
	
	private static Biblioteca uniqueInstance;
    private List<Livro> livros;
    private Map<String, Usuario> usuarios;
    private Map<String, ArrayList<Livro>> reservas;
    private Verificacao estrategiaVerificacao;

    
    private Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.reservas = new HashMap<String, ArrayList<Livro>>();

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

        reservas.put(codigo, new ArrayList<Livro>());
    }

    public void removerUsuario(Usuario usuario) {
        String codigo = usuario.getCodigo();
    	usuarios.remove(codigo);

        reservas.remove(codigo);
    }
    
    public void getDadosUsuario(String codigoUsuario) {
    	Usuario usu = buscarUsuarioPorCodigo(codigoUsuario);
    	System.out.println(usu.toString());
    }
    
	public void getDadosLivro(String codigoLivro) {
		Livro livro = buscarLivroPorCodigo(codigoLivro);
		
		System.out.println(livro.toString());
	}
	
	public void getDadosObservador(String codigoUsuario) {
		ObservadorLivro observador = (ObservadorLivro) buscarUsuarioPorCodigo(codigoUsuario);
		System.out.println("Quantidade de vezes notificado por livros observados: " + observador.getQuantidadeNotificacoes());	
	}
    
    // Buscas
    public List<Livro> buscarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro : livros) {
        	 if (livro.getQuantidadeDisponivel() > 0) {
                 livrosDisponiveis.add(livro);
             }
         }
         return livrosDisponiveis;
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


    
    private Livro buscarReservaPorCodigo(String codigoUsuario, String codigoLivro) {
        for (Livro livro : reservas.get(codigoUsuario)) {
            if (livro.getCodigo().equals(codigoLivro)) {
                return livro;
            }
        }
        return null;
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
    	
    	reservas.get(codigoUsuario).add(livro);
    	System.out.println("Reserva realizada com sucesso: " + usuario.getNome() + " - " + livro.getTitulo());
        
    }
    


	public void desfazerReserva(String codigoUsuario, String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        
        if(!VerificadorEntradas.verificarUsuarioELivro(usuario, livro)) {
        	return;
        }

        reservas.get(codigoUsuario).remove(livro);
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

         livro.addEmprestimo(usuario);
         
         // Calcular data de devolucao
         LocalDate dataAtual = LocalDate.now();
         LocalDate dataDevolucao = dataAtual.plusDays(usuario.getDiasEmprestimo());
         
         usuario.addDatasDevolucao(codigoLivro, dataDevolucao, dataAtual);

         if(livro.buscarReservaPorCodigo(codigoUsuario)) {
    		 desfazerReserva(codigoUsuario, codigoLivro); // Remover reserva, se existir
    	 }
         
         System.out.println("Empréstimo realizado com sucesso: " + usuario.getNome() + " - " + livro.getTitulo());
         System.out.println("Data de devolução: " + dataDevolucao);
     }


     // Devolucao
     public void realizarDevolucao(String codigoUsuario, String codigoLivro) {
         Livro livro = buscarLivroPorCodigo(codigoLivro);
         Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
         
         if(!VerificadorEntradas.verificarUsuarioELivro(usuario, livro)) {
         	return;
         }
         
         if (!(livro.buscarEmprestimoPorCodigo(codigoUsuario))) {
        	 System.out.println("Usuário não possui empréstimo para determinado livro.");
        	 return;
         }
         
         
         livro.removeEmprestimo(usuario);
         usuario.removeDatasDevolucao(codigoLivro);

         

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
	
 }