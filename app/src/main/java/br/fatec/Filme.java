package br.fatec;

public class Filme {
    private String titulo;
    private String genero;
    private Double preco;
    private Integer ano;
    private String descricao;

    public Filme(String titulo, String genero, Double preco, Integer ano, String descricao) {
        this.titulo = titulo;
        this.genero = genero;
        this.preco = preco;
        this.ano = ano;
        this.descricao = descricao;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return titulo + " (" + ano + ") - R$ " + String.format("%.2f", preco);
    }
}
