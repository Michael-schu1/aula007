package br.fatec;

public class Compra {
    private Filme filme;
    private int quantidade;
    private double valorTotal;
    private String usuario;

    public Compra(Filme filme, int quantidade, double valorTotal, String usuario) {
        this.filme = filme;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.usuario = usuario;
    }

    public Filme getFilme() { return filme; }
    public int getQuantidade() { return quantidade; }
    public double getValorTotal() { return valorTotal; }
    public String getUsuario() { return usuario; }

    @Override
    public String toString() {
        return filme.getTitulo() + " x" + quantidade + " = R$ " + String.format("%.2f", valorTotal) + " (" + usuario + ")";
    }
}
