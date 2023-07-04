package Biblioteca;

public class ImprimirLivrosDisponiveis implements Comando{

	private Biblioteca biblioteca;
    
    public ImprimirLivrosDisponiveis() {
		this.biblioteca = Biblioteca.getInstancia();
    }
    
    @Override
    public void executar() {
        biblioteca.printLivrosDisponiveis();
        
    }
    
    @Override
    public void undo() {
        // Não faz nada
    }
}
