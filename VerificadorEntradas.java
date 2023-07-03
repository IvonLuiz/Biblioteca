package Biblioteca;

public class VerificadorEntradas {

    public static boolean verificarUsuarioELivro(Usuario usuario, Livro livro) {
        if (usuario == null || livro == null) {
            System.out.println("Usuário ou livro não encontrado.");
            return false;
        }
        return true;
    }
}