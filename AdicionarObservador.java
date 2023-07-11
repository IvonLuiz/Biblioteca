package Biblioteca;

public class AdicionarObservador implements Comando {
	
	private String codigoUsuario;
	private String codigoLivro;
	
	public AdicionarObservador(String codigoUsuario, String codigoLivro){
		this.codigoUsuario = codigoUsuario;
	    this.codigoLivro = codigoLivro;
	}

	@Override
	public void executar() {
		Biblioteca.getInstancia().adicionarObservador(codigoUsuario, codigoLivro);
	}
	@Override
	public void undo() {
		
	}

}
