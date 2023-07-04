package Biblioteca;

public class ImprimirDadosLivro implements Comando{

    private String codigoLivro;
	private Biblioteca biblioteca;
	
	
	public ImprimirDadosLivro(String codigoLivro){
		this.codigoLivro = codigoLivro;
		this.biblioteca = Biblioteca.getInstancia();
	}

	
	@Override
	public void executar() {
        biblioteca.getDadosLivro(codigoLivro);
	}
	
	@Override
	public void undo() {
		// Não faz nada
	}


}