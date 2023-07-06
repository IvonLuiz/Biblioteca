package Biblioteca;

public interface Sujeito {
    void adicionarObservador(ObservadorLivro observer);
    void removerObservador(ObservadorLivro observer);
    void notificarObservadores();
}
