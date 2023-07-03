package Biblioteca;

public class VerificacaoReserva implements Verificacao {

	@Override
	public boolean podeRealizarAcao(Usuario usuario, Livro livro) {

		// Verifica se usuario tem reserva e tem exemplares disponíveis
	   	if (livro.getQuantidadeDisponivel() <= livro.getQuantidadeReservas()) {
	   		if (usuario.isPasseLivreEmprestimo()) {
	          	 System.out.println("Usuario com passe livre detectado.");
	           	 return true;
	   		} else if (livro.buscarReservaPorCodigo(usuario.getCodigo()) == false) {  
	   		
	   		System.out.println("Não foi possível realizar o empréstimo. Livro indisponível: " + livro.getTitulo());
	   	    return false;
	   		}
	   	}
		return true;
	}

}
