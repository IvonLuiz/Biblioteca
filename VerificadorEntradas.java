package Biblioteca;

public class VerificadorEntradas {

    public static boolean verificarUsuarioELivro(Usuario usuario, Livro livro) {
    	return verificarUsuario(usuario) && verificarLivro(livro);
    }
    
    public static boolean verificarUsuario(Usuario usuario) {
    	
	    if (usuario == null) {
	        System.out.println("Usuário não encontrado.");
	        return false;
	    }
	    return true;
    }
    
    public static boolean verificarLivro(Livro livro) {
    	
	    if (livro == null) {
	        System.out.println("Livro não encontrado.");
	        return false;
	    }
	    return true;
    }
}