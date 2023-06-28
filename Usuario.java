package Biblioteca;

public class Usuario {
    private String codigo;
    private String nome;

    public Usuario(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
    
    // getters e setters
	public String getNome() {
		return nome;
	}

	public String getCodigo() {
		return codigo;
	}
}