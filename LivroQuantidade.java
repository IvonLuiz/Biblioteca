package Biblioteca;

public class LivroQuantidade {
    private Livro livro;
    private int quantidadeDisponivel;

    public LivroQuantidade(Livro livro, int quantidadeDisponivel) {
        this.livro = livro;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Livro getLivro() {
        return livro;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    @Override
    public String toString() {
        return "LivroQuantidade{" +
                "livro=" + livro +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                '}';
    }
}