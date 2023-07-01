package Biblioteca;

public interface ObservadorLivro {
	void update(Livro livro, int quantidadeReservas);
	int getQuantidadeNotificacoes();
}
