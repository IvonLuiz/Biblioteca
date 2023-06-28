package Biblioteca;

public class RealizarEmprestimo implements Comando{
	
	private String codigoUsuario;
	private String codigoLivro;
	private Biblioteca biblioteca;
	
	public RealizarEmprestimo(String codigoUsuario, String codigoLivro) {
	    this.codigoUsuario = codigoUsuario;
	    this.codigoLivro = codigoLivro;
	    this.biblioteca = Biblioteca.getInstancia();
	}
	
	@Override
	public void executar() {
	    biblioteca.realizarEmprestimo(codigoUsuario, codigoLivro);
	}
	
    public void undo() {
        biblioteca.desfazerReserva(codigoLivro);
    }

}
