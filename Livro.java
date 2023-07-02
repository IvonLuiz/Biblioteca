package Biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
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
	private List<ObservadorLivro> observadores;
    private List<Usuario> usuariosReserva;
    private List<Usuario> usuariosEmprestimos;
    
	
	
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
        this.usuariosReserva = new ArrayList<Usuario>();
        this.usuariosEmprestimos = new ArrayList<Usuario>();
    }
	
	public String toString() {
        return "Livro \n{\n" +
                "Codigo = " + codigo + '\n' +
                "Titulo = " + titulo + '\n' +
                "Status = " + getStatus() + '\n' +
                "Quantidade de Reservas: " + quantidadeReservas + '\n' +
                "Usuarios que reservaram: \n" + getReservas() + '\n' +
                "Usuarios que estão alugando: \n" + getEmprestimos() +
                '}';
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
	
	public void reduzirQuantidadeDisponivel() {
        this.setQuantidadeDisponivel(this.getQuantidadeDisponivel() - 1);
	}
	
	public String getStatus() {
		if(this.getQuantidadeDisponivel() > 0) {
			return "Disponível";
		}
		return "Indisponível";
	}
	
	
	// Reservas
	public void addReserva(Usuario usu) {
		usuariosReserva.add(usu);
		this.incrementarQuantidadeReservas();
	}
	
	public void removeReserva(Usuario usu) {
		usuariosReserva.remove(usu);
		this.reduzirQuantidadeReservas();
	}
	
	public String getReservas() {
		String ret = "";
		for (Usuario usu : usuariosReserva) {
			ret += usu.getNome();
			ret += " / ";
		}
		return ret;
	}	
	
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
	
	
	
	// Emprestimos
	public void addEmprestimo(Usuario usu) {
		usuariosEmprestimos.add(usu);
		this.reduzirQuantidadeDisponivel();;
	}
	
	public void removeEmprestimo(Usuario usu) {
		usuariosEmprestimos.remove(usu);
		this.incrementarQuantidadeDisponivel();
	}
	
	public String getEmprestimos() {
		String ret = "";
		for (Usuario usu : usuariosEmprestimos) {
			ret += usu.getNome();
			ret += " alugado em: " + usu.getDataAluguel(this.getCodigo());
			ret += " data limite retorno: " + usu.getDataDevolucao(this.getCodigo());
			ret += "\n";
			
		}
		return ret;
	}
	

	// Observador
	public void adicionarObservador(ObservadorLivro observador) {
        observadores.add(observador);
    }

    public void removerObservador(ObservadorLivro observador) {
        observadores.remove(observador);
    }
    
    private void notificarObservadores() {
        for (ObservadorLivro observador : observadores) {
            observador.update(this, this.getQuantidadeReservas());
        }
    }

    
    // Buscar nas ArrayLists
    public boolean buscarEmprestimoPorCodigo(String codigoUsuario) {
        for (Usuario usu : usuariosEmprestimos) {
            if (usu.getCodigo().equals(codigoUsuario)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean buscarReservaPorCodigo(String codigoUsuario) {
        for (Usuario usu : usuariosReserva) {
            if (usu.getCodigo().equals(codigoUsuario)) {
                return true;
            }
        }
        return false;
    }
    
    
}

