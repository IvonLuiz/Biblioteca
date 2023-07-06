package Biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Livro implements Sujeito{
	private String codigo;
	private String titulo;
	private String editora;
	private List<String> autores;
	private int edicao;
	private int anoPublicacao;
	private int quantidadeDisponivel;
	private int quantidadeReservas;
	private List<ObservadorLivro> observadores;
	
	
	public Livro(String codigo, String titulo, String editora, List<String> autores, int edicao, int anoPublicacao, int quantidadeDisponivel) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.quantidadeReservas = 0;
        this.observadores = new ArrayList<>();
	}
	
	public String toString() {
        return  "Titulo = " + titulo + '\n' +	
        		"Codigo = " + codigo + '\n' +
                "Status = " + getStatus() + '\n' +
                "Quantidade de Reservas: " + quantidadeReservas + '\n';
    }

	
	public String getCodigo() {
		return codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	
	// Quantidade Disponivel
	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void incrementarQuantidadeDisponivel() {
        this.setQuantidadeDisponivel(this.getQuantidadeDisponivel() + 1);
	}
	
	public void decrementarQuantidadeDisponivel() {
        this.setQuantidadeDisponivel(this.getQuantidadeDisponivel() - 1);
	}
	
	public String getStatus() {
		if(this.getQuantidadeDisponivel() > 0) {
			return "Disponível";
		}
		return "Indisponível";
	}
	
	
	// Reservas
	public int getQuantidadeReservas() {
		return quantidadeReservas;
	}

	public void setQuantidadeReservas(int quantidadeReservas) {
		this.quantidadeReservas = quantidadeReservas;
	}
	
	public void incrementarQuantidadeReservas() {
        this.setQuantidadeReservas(this.getQuantidadeReservas() + 1);
        if (this.getQuantidadeReservas() > 2) {
            notificarObservadores();
        }
	}
	
	public void reduzirQuantidadeReservas() {
        this.setQuantidadeReservas(this.getQuantidadeReservas() - 1);
	}
	
	

	// Observador
	@Override
	public void adicionarObservador(ObservadorLivro observador) {
        observadores.add(observador);
    }

	@Override
    public void removerObservador(ObservadorLivro observador) {
        observadores.remove(observador);
    }
    
	@Override
    public void notificarObservadores() {
        for (ObservadorLivro observador : observadores) {
            observador.update(this, this.getQuantidadeReservas());
        }
    }

   
	public String getEditora() {
		return editora;
	}

	public List<String> getAutores() {
		return autores;
	}

	public int getEdicao() {
		return edicao;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}
    
    
}

