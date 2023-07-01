package Biblioteca;

//Professor não tem restrição quantidade de emprestimos e os dias de devolução é o valor de Usuario, 7

public class Professor extends Usuario implements ObservadorLivro{
    
	private int quantidadeNotificacoes;
	
    public Professor(String codigo, String nome) {
        super(codigo, nome);
        setPasseLivreEmprestimo(true);
        this.quantidadeNotificacoes = 0;
    }

	@Override
	public void update(Livro livro, int quantidadeReservas) {
		quantidadeNotificacoes++;
		System.out.println("Notificando observador " + this.getNome() +  " " + "-> Livro: " + livro.getTitulo() + " com " + quantidadeReservas + " reservas." );
		System.out.println("Observador já foi notificado " + getQuantidadeNotificacoes() + " vezes.");
	}

	@Override
	public int getQuantidadeNotificacoes() {
        return quantidadeNotificacoes;
	}
}