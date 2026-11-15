public class Biblioteca {
    private final ItemBiblioteca[] acervo;
    private int totalItens;

    public Biblioteca(int capacidade) {
        this.acervo = new ItemBiblioteca[capacidade];
        this.totalItens = 0;
    }

    public boolean adicionarItem(ItemBiblioteca item) {
        if (totalItens >= acervo.length) {
            System.out.println("Acervo cheio: não foi possível adicionar " + item.getTitulo());
            return false;
        }
        acervo[totalItens++] = item;
        return true;
    }

    private ItemBiblioteca buscarPorCodigo(int codigo) {
        for (int i = 0; i < totalItens; i++) {
            if (acervo[i].getCodigo() == codigo) {
                return acervo[i];
            }
        }
        return null;
    }

    public boolean emprestar(Usuario usuario, int codigoItem) {
        ItemBiblioteca item = buscarPorCodigo(codigoItem);

        if (item == null) {
            System.out.println("Recusado: item " + codigoItem + " não existe.");
            return false;
        }
        if (!item.isDisponivel()) {
            System.out.println("Recusado: \"" + item.getTitulo() + "\" já está emprestado.");
            return false;
        }
        if (!usuario.podeEmprestar()) {
            System.out.println("Recusado: " + usuario.getNome()
                    + " atingiu o limite de " + usuario.getLimiteItens() + " itens.");
            return false;
        }

        item.marcarEmprestado();
        usuario.registrarEmprestimo();
        System.out.println("OK: " + usuario.getNome() + " pegou \"" + item.getTitulo()
                + "\" (devolver em " + item.getPrazoDias() + " dias).");
        return true;
    }

    // Devolve o item e retorna a multa a pagar (0.0 se não houver atraso).
    public double devolver(Usuario usuario, int codigoItem, int diasAtraso) {
        ItemBiblioteca item = buscarPorCodigo(codigoItem);

        if (item == null || item.isDisponivel()) {
            System.out.println("Recusado: item " + codigoItem + " não está emprestado.");
            return 0.0;
        }

        item.marcarDisponivel();
        usuario.registrarDevolucao();
        double multa = item.calcularMulta(diasAtraso);
        System.out.println(String.format("OK: %s devolveu \"%s\". Multa: R$ %.2f",
                usuario.getNome(), item.getTitulo(), multa));
        return multa;
    }

    public void listarAcervo() {
        System.out.println("=== Acervo ===");
        for (int i = 0; i < totalItens; i++) {
            System.out.println(acervo[i]);
        }
    }
}