package Biblioteca;

public class VerificacaoLimiteReservas implements Verificacao {

	@Override
	public boolean podeRealizarAcao(Usuario usuario, Livro livro) {
        // Verifica se usuario já possui limite de reservas
        if (usuario.atingiuLimiteReservas()) {
        	System.out.println("Não foi possível realizar a ação. Usuario atingiu limite de reservas.");
            return false;
        }
        return true;
	}

}
