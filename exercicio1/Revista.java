public class Revista extends ItemBiblioteca {
    private static final int PRAZO_DIAS = 7;
    private static final double MULTA_POR_DIA = 1.00;

    public Revista(int codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazoDias() {
        return PRAZO_DIAS;
    }

    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso > 0 ? diasAtraso * MULTA_POR_DIA : 0.0;
    }
}