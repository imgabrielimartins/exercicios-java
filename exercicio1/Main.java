public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(10);

        biblioteca.adicionarItem(new Livro(1, "Java: Como Programar"));
        biblioteca.adicionarItem(new Livro(2, "Clean Code"));
        biblioteca.adicionarItem(new Livro(3, "Estruturas de Dados"));
        biblioteca.adicionarItem(new Revista(4, "Revista Programar"));
        biblioteca.adicionarItem(new Revista(5, "Superinteressante"));

        Aluno ana = new Aluno("Ana");
        Professor carlos = new Professor("Carlos");

        biblioteca.listarAcervo();

        System.out.println("\n=== Empréstimos ===");
        biblioteca.emprestar(ana, 1);   
        biblioteca.emprestar(ana, 2);
        biblioteca.emprestar(ana, 4);
        biblioteca.emprestar(ana, 5);   
        biblioteca.emprestar(carlos, 1); 

        System.out.println("\n=== Devolução com atraso ===");
        biblioteca.devolver(ana, 4, 3); 

        System.out.println();
        biblioteca.listarAcervo();
        System.out.println("\n" + ana);
        System.out.println(carlos);
    }
}