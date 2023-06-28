package Biblioteca;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {
	
	private static Biblioteca uniqueInstance;
    private List<Livro> livros;
    private Map<String, Usuario> usuarios;
    private Map<String, Livro> reservas;

    private Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.reservas = new HashMap<>();
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
        usuarios.put(usuario.getCodigo(), usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario.getCodigo());
    }
    
    
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

    
    // Reserva
    public void realizarReserva(String codigoUsuario, String codigoLivro) {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        
        // Verifica se os parametros sao nulos
        if (usuario == null || livro == null) {
            System.out.println("Usuário ou livro não encontrado.");
            return;
        }
        
        // Verifica disponibilidade do livro
        if (livro.getQuantidadeDisponivel() > 0) {
        	livro.reduzirQuantidadeDisponivel();
            reservas.put(usuario.getCodigo(), livro);
            System.out.println("Reserva realizada com sucesso: " + usuario.getNome() + " - " + livro.getTitulo());
        } else {
            System.out.println("Não foi possível realizar a reserva. Livro indisponível: " + livro.getTitulo());
        }
    }
    
    
    public void desfazerReserva(String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        reservas.remove(codigoLivro);
        System.out.println("Reserva desfeita: " + livro.getTitulo());
    }
    
    
    // Empréstimo
     public boolean realizarEmprestimo(String codigoUsuario, String codigoLivro) {
         Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
         Livro livro = buscarLivroPorCodigo(codigoLivro);

         if (usuario == null || livro == null) {
             System.out.println("Usuário ou livro não encontrado.");
             return false;
         }

         if (livro.getQuantidadeDisponivel() > 0) {
             livro.reduzirQuantidadeDisponivel();
             reservas.remove(codigoLivro); // Remover reserva, se existir
             System.out.println("Empréstimo realizado com sucesso: " + usuario.getNome() + " - " + livro.getTitulo());
             return true;
         } else {
             System.out.println("Não foi possível realizar o empréstimo. Livro indisponível: " + livro.getTitulo());
             return false;
         }
     }
     
    
     public void desfazerEmprestimo(String codigoUsuario, String codigoLivro) {
         Livro livro = buscarLivroPorCodigo(codigoLivro);
         Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);

         if (usuario == null || livro == null) {
             System.out.println("Usuário ou livro não encontrado.");
             return;
         }

         livro.incrementarQuantidadeDisponivel();
         System.out.println("Empréstimo desfeito: " + usuario.getNome() + " -> " + livro.getTitulo());
     }

     

     
     public void realizarDevolucao(String codigoLivro) {
         Livro livro = buscarLivroPorCodigo(codigoLivro);

         if (livro == null) {
             System.out.println("Livro não encontrado.");
             return;
         }

         livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
         System.out.println("Devolução realizada com sucesso: " + livro.getTitulo());
     }

    


 }