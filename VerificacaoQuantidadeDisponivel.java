package Biblioteca;

public class VerificacaoQuantidadeDisponivel implements Verificacao {

	@Override
	public boolean podeRealizarAcao(Usuario usuario, Livro livro) {
	
		// Verificar quantidade disponivel
		if (livro.getQuantidadeDisponivel() == 0) {
			System.out.println("Não foi possível realizar a ação. Livro indisponível: " + livro.getTitulo());
			return false;
		}
		return true;
	}
}
