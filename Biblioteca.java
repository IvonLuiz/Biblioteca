package Biblioteca;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Biblioteca {
	
	private static Biblioteca uniqueInstance;
    private List<Livro> livros;
    private Map<String, Usuario> usuarios;
    private Map<String, ArrayList<Livro>> reservas;
    Map<String, ArrayList<Livro>> emprestimos; 

    
    private Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.reservas = new HashMap<String, ArrayList<Livro>>();
        this.emprestimos = new HashMap<String, ArrayList<Livro>>();
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
        emprestimos.put(codigo, new ArrayList<Livro>());
        reservas.put(codigo, new ArrayList<Livro>());
    }

    public void removerUsuario(Usuario usuario) {
        String codigo = usuario.getCodigo();
    	usuarios.remove(codigo);
        emprestimos.remove(codigo);
        reservas.remove(codigo);
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

    private Livro buscarEmprestimoPorCodigo(String codigoUsuario, String codigoLivro) {
        for (Livro livro : emprestimos.get(codigoUsuario)) {
            if (livro.getCodigo().equals(codigoLivro)) {
                return livro;
            }
        }
        return null;
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
        if (usuario == null || livro == null) {
            System.out.println("Usuário ou livro não encontrado.");
            return;
        }
        
        // Verifica se usuario já possui limite de reservas
        if (usuario.atingiuLimiteReservas()) {
        	System.out.println("Não foi possível realizar a reserva. Usuario atingiu limite de reservas.");
            return;
        }
        
        // Verifica se os parametros sao nulos
        if (buscarEmprestimoPorCodigo(codigoUsuario, codigoLivro) == livro) {
            System.out.println("Não foi possível realizar a reserva. Usuario " + usuario.getNome() + "ja possui exemplar de " + livro.getTitulo());
            return;
        }
        
        if (buscarReservaPorCodigo(codigoUsuario, codigoLivro) == livro) {
        	System.out.println("Não foi possível realizar a reserva. Usuario " + usuario.getNome() + "ja possui uma reserva de " + livro.getTitulo());
            return;
        }
        
        // Verifica disponibilidade do livro
        if (livro.getQuantidadeDisponivel() > 0) {
        	
        	livro.incrementarQuantidadeReservas();
        	usuario.incrementarQuantLivrosReservados();
        	System.out.println(livro.getQuantidadeReservas());
        	reservas.get(codigoUsuario).add(livro);
            
            System.out.println("Reserva realizada com sucesso: " + usuario.getNome() + " - " + livro.getTitulo());
        } else {
            System.out.println("Não foi possível realizar a reserva. Livro indisponível: " + livro.getTitulo());
        }
    }
    


	public void desfazerReserva(String codigoUsuario, String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        reservas.get(codigoUsuario).remove(livro);
        livro.reduzirQuantidadeReservas();;
        System.out.println(livro.getQuantidadeReservas());
        System.out.println("Reserva desfeita: " + codigoUsuario + " -> " + livro.getTitulo());        
    }
    
    
    
    
    // Empréstimo
     public void realizarEmprestimo(String codigoUsuario, String codigoLivro) {
         Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
         Livro livro = buscarLivroPorCodigo(codigoLivro);
         //System.out.println("Quantiadde do livro disponivel:" +  livro.getQuantidadeDisponivel());
         
         
         if (usuario == null || livro == null) {
             System.out.println("Usuário ou livro não encontrado.");
             return;
         }
         
         // Verificar se usuario atingiu o limite de emprestimo
         if (usuario.atingiuLimiteEmprestimos() == true) {
             System.out.println("Não foi possível realizar o empréstimo pois limite de empréstimos atingido.");
             return;
         }
         
         
         // Verificar se o usuario tem atraso para devolucao
         if (usuario.verificarAtrasoDevolucao()) {
        	 System.out.println("Não foi possível realizar o empréstimo. Usuário " + codigoUsuario + " está atraso em uma devolução.");
        	 return;
         }
         
    	 // Verificar se usuario tem empréstimo daquele mesmo livro
    	 for (int i = 0; i < emprestimos.get(codigoUsuario).size(); i++) {
        	 if(emprestimos.get(codigoUsuario).get(i) == livro) {
        		 System.out.println("Não foi possível realizar o empréstimo. Usuário já possui um exemplar.");
        		 return;
        	 }
    	 }

         
         // Verificar quantidade disponivel
    	 if (livro.getQuantidadeDisponivel() == 0) {
    		 System.out.println("Não foi possível realizar o empréstimo. Livro indisponível: " + livro.getTitulo());
    	     return;
    	 }
    	 
    	 
         boolean tinhaReserva = false;
    	 // Verificar se o usuário tem uma reserva para o livro
         for (int i = 0; i < reservas.get(codigoUsuario).size(); i++) {
        	 if(reservas.get(codigoUsuario).get(i) == livro) {
        		 desfazerReserva(codigoUsuario, codigoLivro);// Remover reserva, se existir
        		 tinhaReserva = true;
        	 }
    	 }
         
         
         // Verifica se o usuario tem passe livre (no momento: somente professor)
         if (livro.getQuantidadeDisponivel() <= livro.getQuantidadeReservas() && usuario.isPasseLivreEmprestimo()) {
        	 System.out.println("Usuario com passe livre detectado.");
        	 emprestar(codigoUsuario, codigoLivro, livro, usuario);
        	 return;
         }
    	 
         

         // Verifica se usuario tem reserva
    	 if (livro.getQuantidadeDisponivel() <= livro.getQuantidadeReservas() && tinhaReserva==false) {  
    		 System.out.println("Não foi possível realizar o empréstimo. Livro indisponível: " + livro.getTitulo());
    	     return;
    	 }
    	 
    	 
    	 
    	 
    	 // Verificar se for um professor e tiver quantidade disponivel mas reservada

    	 // Realizar Emprestimo
    	 emprestar(codigoUsuario, codigoLivro, livro, usuario);
     }
         
         
     private void emprestar(String codigoUsuario, String codigoLivro, Livro livro, Usuario usuario) {
    	 emprestimos.get(codigoUsuario).add(livro);
         livro.reduzirQuantidadeDisponivel();
         
         // Calcular data de devolucao
         LocalDate dataDevolucao = LocalDate.now().plusDays(usuario.getDiasEmprestimo());
         usuario.setDataDevolucao(dataDevolucao);
         usuario.incrementarQuantLivrosEmprestados();
         
         System.out.println("Empréstimo realizado com sucesso: " + usuario.getNome() + " - " + livro.getTitulo());
         System.out.println("Data de devolução: " + dataDevolucao);
     }
    /*
     public void desfazerEmprestimo(String codigoUsuario, String codigoLivro) {
         Livro livro = buscarLivroPorCodigo(codigoLivro);
         Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);

         if (usuario == null || livro == null) {
             System.out.println("Usuário ou livro não encontrado.");
             return;
         }

         livro.incrementarQuantidadeDisponivel();
         usuario.decrementarQuantLivrosEmprestados();
         System.out.println("Empréstimo desfeito: " + usuario.getNome() + " -> " + livro.getTitulo());
     }

     */

     // Devolucao
     public void realizarDevolucao(String codigoUsuario, String codigoLivro) {
         Livro livro = buscarLivroPorCodigo(codigoLivro);
         Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
         
         if (usuario == null || livro == null) {
             System.out.println("Usuário ou livro não encontrado.");
             return;
         }
         
         if (!(buscarEmprestimoPorCodigo(codigoUsuario, codigoLivro) == livro)) {
        	 System.out.println("Usuário não possui empréstimo para determinado livro.");
        	 return;
         }
         
         
         livro.incrementarQuantidadeDisponivel();
         usuario.decrementarQuantLivrosEmprestados();
         
         emprestimos.get(codigoUsuario).remove(livro);

         System.out.println("Devolução de " + usuario.getNome() + " realizada com sucesso: " + livro.getTitulo());
     }

    
     private boolean verificarEntradas(Livro livro, Usuario usuario) {
         if (usuario == null || livro == null) {
             System.out.println("Usuário ou livro não encontrado.");
             return false;
         }
         return true;
     }

 }