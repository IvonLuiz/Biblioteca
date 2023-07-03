package Biblioteca;

public class VerificacaoEmprestimoLivro implements Verificacao {

	@Override
	public boolean podeRealizarAcao(Usuario usuario, Livro livro) {

   	 // Verificar se usuario tem empréstimo daquele mesmo livro
        if (livro.buscarEmprestimoPorCodigo(usuario.getCodigo())) {
       	 System.out.println("Não foi possível realizar a ação. Usuário já possui um exemplar.");
   		 return false;
        }
		return true;
	}

}
