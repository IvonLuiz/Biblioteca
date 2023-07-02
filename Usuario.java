package Biblioteca;

import java.time.LocalDate;
import java.util.HashMap;

public class Usuario {
    private String codigo;
    private String nome;
    private LocalDate dataDevolucao;
    private HashMap<String, LocalDate>  datasDevolucao;
    private HashMap<String, LocalDate>  datasAlugadas;
    private HashMap<String, LocalDate>  datasReservas;
    private int quantLivrosEmprestados;
    private int quantLivrosReservados;
	private int limiteEmprestimo;
	private int limiteReservas;
    private int diasEmprestimo;
    private boolean passeLivreEmprestimo;
    
    public Usuario(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantLivrosEmprestados = 0;
        this.quantLivrosReservados = 0;
        this.limiteEmprestimo = Integer.MAX_VALUE;
        this.limiteReservas = 3;
        this.diasEmprestimo = 7;
        this.setPasseLivreEmprestimo(false);
        this.datasDevolucao = new HashMap<String, LocalDate>();
        this.datasAlugadas = new HashMap<String, LocalDate>();
        this.datasReservas = new HashMap<String, LocalDate>();
    }

    @Override
    public String toString() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
    	for (String codigoLivro : datasDevolucao.keySet()) {
            LocalDate dataDevolucao = datasDevolucao.get(codigoLivro);
            LocalDate dataAlugada = datasAlugadas.get(codigoLivro);
            
            sb1.append("Título: ").append(codigoLivro)
              .append(", Data de empréstimo: ").append(dataAlugada)
              .append(", Data limite de devolução: ").append(dataDevolucao)
              .append(", Status empréstimo: ").append(verificarStatusString(codigoLivro))
              .append('\n');
    	}
    	
    	for (String codigoLivro : datasReservas.keySet()) {
            LocalDate dataReserva = datasReservas.get(codigoLivro);
            
            sb2.append("Título: ").append(codigoLivro)
              .append(", Data de reserva: ").append(dataReserva);
              }
        
    	return "Usuario \n{\n" +
                "Codigo = " + codigo + '\n' +
                "Nome = " + nome + '\n' +
                "Livro alugados:\n" + 
                sb1 +
                "Livros reservados:\n" +
                sb2 +
                "\n}";
    }
    
    
    public boolean verificarAtrasoDevolucao() {
    	for (String codigoLivro : datasDevolucao.keySet()) {
			verificarAtrasoIndividual(codigoLivro);
			return false;
    	}
    	return false;
    }
    
    private boolean verificarAtrasoIndividual(String codigoLivro) {
    	if (getDataDevolucao(codigoLivro) != null) {
    		LocalDate data = LocalDate.now();
    		return data.isAfter(getDataDevolucao(codigoLivro));
    	}
    	return false;
    }
    
    
    private String verificarStatusString(String codigoLivro) {
    	if (verificarAtrasoIndividual(codigoLivro)) {
    		return "atrasado";
    	}
    	return "em curso";
    }
    
    public boolean atingiuLimiteEmprestimos() {
        return quantLivrosEmprestados >= limiteEmprestimo;
    }
    
    public void incrementarQuantLivrosEmprestados() {
    	quantLivrosEmprestados++;
    }

    public void decrementarQuantLivrosEmprestados() {
    	quantLivrosEmprestados--;
    }


    
    public boolean atingiuLimiteReservas() {
        return quantLivrosReservados >= limiteReservas;
    }
    
    public void incrementarQuantLivrosReservados() {
    	quantLivrosReservados++;
    }

    public void decrementarQuantLivrosReservados() {
    	quantLivrosReservados--;
    }
    
    

	public String getNome() {
		return nome;
	}

	public String getCodigo() {
		return codigo;
	}

	

	public void addReserva(String tituloLivro) {
		datasReservas.put(tituloLivro, LocalDate.now());
		this.incrementarQuantLivrosReservados();
	}
	
	public void removeReserva(String tituloLivro) {
		datasReservas.remove(tituloLivro, LocalDate.now());
		this.decrementarQuantLivrosReservados();
	}
	
	
	
	public LocalDate getReserva(String tituloLivro) {
		return datasReservas.put(tituloLivro, LocalDate.now());
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public LocalDate getDataDevolucao(String codigoLivro) {
	    LocalDate dataDevolucao = datasDevolucao.get(codigoLivro);
	    return dataDevolucao;
	}
	
	public LocalDate getDataAluguel(String codigoLivro) {
		LocalDate dataAlugada = datasAlugadas.get(codigoLivro);
		return dataAlugada;
	}

	
	public void addDatasDevolucao(String codigoLivro, LocalDate dataDevolucao, LocalDate dataAtual) {
		datasDevolucao.put(codigoLivro, dataDevolucao);
		datasAlugadas.put(codigoLivro, dataAtual);
        incrementarQuantLivrosEmprestados();
	}
	
	public void removeDatasDevolucao(String codigoLivro) {
		datasDevolucao.remove(codigoLivro);
	}


	
	
	public void setLimiteEmprestimos(int limiteEmprestimo) {
        this.limiteEmprestimo = limiteEmprestimo;
    }
	
	public int getLimiteEmprestimo() {
		return limiteEmprestimo;
	}

	public int getDiasEmprestimo() {
		return diasEmprestimo;
	}

	public void setDiasEmprestimo(int diasEmprestimo) {
		this.diasEmprestimo = diasEmprestimo;
	}

	
	
	public boolean isPasseLivreEmprestimo() {
		return passeLivreEmprestimo;
	}

	public void setPasseLivreEmprestimo(boolean passeLivreEmprestimo) {
		this.passeLivreEmprestimo = passeLivreEmprestimo;
	}

}