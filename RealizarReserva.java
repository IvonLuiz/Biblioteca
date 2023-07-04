package Biblioteca;

public class RealizarReserva implements Comando{

    private String codigoUsuario;
    private String codigoLivro;
	private Biblioteca biblioteca;
	
	public RealizarReserva(String codigoUsuario, String codigoLivro){
		this.codigoUsuario = codigoUsuario;
		this.codigoLivro = codigoLivro;
		this.biblioteca = Biblioteca.getInstancia();
	}

	@Override
	public void executar() {
        biblioteca.realizarReserva(codigoUsuario, codigoLivro);
	}
	
	@Override
	public void undo() {
		biblioteca.desfazerReserva(codigoUsuario, codigoLivro);
	}

}
