package Biblioteca;

import java.util.List;

public class Livro {
	private String codigo;
	private String titulo;
	private String editora;
	private List<String> autores;
	private int edicao;
	private int anoPublicacao;
	private int quantidadeDisponivel;
	private int quantidadeReservas;
	
	public Livro(String codigo, String titulo, String editora, List<String> autores, int edicao, int anoPublicacao, int quantidadeDisponivel) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
	
	public String printDados() {
        return "Livro{" +
                "codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", editora='" + editora + '\'' +
                ", autores=" + autores +
                ", edicao=" + edicao +
                ", anoPublicacao=" + anoPublicacao +
                '}';
    }

	
	public String getCodigo() {
		return codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	
	
	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void incrementarQuantidadeDisponivel() {
        this.setQuantidadeDisponivel(this.getQuantidadeDisponivel() + 1);
	}
	
	public void reduzirQuantidadeDisponivel() {
        this.setQuantidadeDisponivel(this.getQuantidadeDisponivel() - 1);
	}
	
	
	
	public int getQuantidadeReservas() {
		return quantidadeReservas;
	}

	public void setQuantidadeReservas(int quantidadeReservas) {
		this.quantidadeReservas = quantidadeReservas;
	}
	
	public void incrementarQuantidadeReservas() {
        this.setQuantidadeReservas(this.getQuantidadeDisponivel() + 1);
	}
	
	public void reduzirQuantidadeReservas() {
        this.setQuantidadeReservas(this.getQuantidadeDisponivel() - 1);
	}
}

