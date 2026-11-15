public abstract class ItemBiblioteca {
    private final int codigo;
    private final String titulo;
    private boolean disponivel;

    public ItemBiblioteca(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponivel = true;
    }

    public abstract int getPrazoDias();

    public abstract double calcularMulta(int diasAtraso);

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    void marcarEmprestado() {
        this.disponivel = false;
    }

    void marcarDisponivel() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - prazo: %d dias - %s",
                codigo, titulo, getClass().getSimpleName(), getPrazoDias(),
                disponivel ? "disponível" : "emprestado");
    }
}