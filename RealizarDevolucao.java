package Biblioteca;

public class RealizarDevolucao implements Comando{
	
	//private String codigoUsuario;
	private String codigoLivro;
	private Biblioteca biblioteca;
	private String codigoUsuario;
	
	RealizarDevolucao(String codigoUsuario, String codigoLivro){
		this.codigoLivro = codigoLivro;
		this.biblioteca = Biblioteca.getInstancia();
		this.codigoUsuario = codigoUsuario;
	}
	
	@Override
	public void executar() {
		biblioteca.realizarDevolucao(codigoUsuario, codigoLivro);
	}

	@Override
	public void undo() {
		// Devolução não precisa ser desfeita
		System.out.println("Não é possivel desfazer uma devolução.");
	}

}
