package Biblioteca;

public class ImprimirDadosObservador implements Comando{

    private String codigoObservador;
	private Biblioteca biblioteca;
	
	
	public ImprimirDadosObservador(String codigoObservador){
		this.codigoObservador = codigoObservador;
		this.biblioteca = Biblioteca.getInstancia();
	}

	
	@Override
	public void executar() {
        biblioteca.getDadosObservador(codigoObservador);
	}
	
	@Override
	public void undo() {
		// Não faz nada
	}


}