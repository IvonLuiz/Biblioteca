package Biblioteca;

import java.time.LocalDate;

public class Reserva {
	private Usuario usuario;
	private Livro livro;
	LocalDate dataReserva; 
	
	public Reserva(Usuario usuario, Livro livro) {
		this.livro = livro;
		this.usuario = usuario;
		this.dataReserva = LocalDate.now();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public LocalDate dataReserva() {
		return dataReserva;
	}
	
}
