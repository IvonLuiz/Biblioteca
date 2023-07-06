package Biblioteca;

public class VerificacaoReservaLivro implements Verificacao {

	@Override
	public boolean podeRealizarAcao(Usuario usuario, Livro livro) {
        // Verifica se o usuario já possui uma reserva
        if (Biblioteca.getInstancia().buscarReservaPorCodigoUsuarioLivro(usuario.getCodigo(), livro.getCodigo()) != null) {
        	System.out.println("Não foi possível realizar a ação. Usuario " + usuario.getNome() + " ja possui uma reserva de " + livro.getTitulo());
            return false;
        }
		return true;
	}

}
