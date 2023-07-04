package Biblioteca;

public class ImprimirDadosUsuario implements Comando{

    private String codigoUsuario;
	private Biblioteca biblioteca;
	
	
	public ImprimirDadosUsuario(String codigoUsuario){
		this.codigoUsuario = codigoUsuario;
		this.biblioteca = Biblioteca.getInstancia();
	}

	
	@Override
	public void executar() {
        biblioteca.getDadosUsuario(codigoUsuario);
	}
	
	@Override
	public void undo() {
		// Não faz nada
	}


}
