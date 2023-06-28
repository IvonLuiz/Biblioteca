package Biblioteca;

public class RealizarDevolucao implements Comando{
	
	//private String codigoUsuario;
	private String codigoLivro;
	private Biblioteca biblioteca;
	
	RealizarDevolucao(String codigoLivro){
		this.codigoLivro = codigoLivro;
		this.biblioteca = Biblioteca.getInstancia();
	}
	
	@Override
	public void executar() {
		biblioteca.realizarDevolucao(codigoLivro);
	}

	@Override
	public void undo() {
		// Devolução não precisa ser desfeita
		System.out.println("Não é possivel desfazer uma devolução.");
	}

}
