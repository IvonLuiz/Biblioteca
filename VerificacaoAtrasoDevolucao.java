package Biblioteca;

public class VerificacaoAtrasoDevolucao implements Verificacao {

	@Override
	public boolean podeRealizarAcao(Usuario usuario, Livro livro) {

		
		
        // Verificar se o usuario tem atraso para devolucao
        if (Biblioteca.getInstancia().verificarAtrasoDevolucaoUsuario(usuario.getCodigo())) {
       	 System.out.println("Não foi possível realizar o empréstimo. Usuário " + usuario.getNome() + " está atrasado em uma devolução.");
       	 return false;
        }
		return true;
	}
}
