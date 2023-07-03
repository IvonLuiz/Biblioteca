package Biblioteca;

public class VerificacaoLimiteEmprestimos implements Verificacao {

	@Override
	public boolean podeRealizarAcao(Usuario usuario, Livro livro) {
        // Verificar se usuario atingiu o limite de emprestimo
        if (usuario.atingiuLimiteEmprestimos() == true) {
            System.out.println("Não foi possível realizar o empréstimo pois limite de empréstimos atingido.");
            return false;
        }
		return true;
	}

}
