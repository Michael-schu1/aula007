package br.fatec;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    public static List<User> usuarios = new ArrayList<>();
    public static List<Filme> filmes = new ArrayList<>();
    public static List<Compra> compras = new ArrayList<>();
    public static String currentUser = "";

    static {
        // 5 Fixed Users
        if (usuarios.isEmpty()) {
            usuarios.add(new User("admin", "123456", "Administrador"));
            usuarios.add(new User("joao", "123456", "João Silva"));
            usuarios.add(new User("maria", "123456", "Maria Santos"));
            usuarios.add(new User("carlos", "123456", "Carlos Souza"));
            usuarios.add(new User("ana", "123456", "Ana Paula"));
        }

        // Initial Movies
        if (filmes.isEmpty()) {
            filmes.add(new Filme("Matrix", "Ficção Científica", 29.90, 1999, "Um hacker descobre a realidade simulada."));
            filmes.add(new Filme("Interestelar", "Ficção Científica", 34.90, 2014, "Exploradores viajam através de um buraco de minhoca."));
            filmes.add(new Filme("O Senhor dos Anéis", "Fantasia", 39.90, 2001, "Uma jornada épica para destruir o Um Anel."));
            filmes.add(new Filme("Vingadores: Ultimato", "Ação", 44.90, 2019, "A batalha final contra Thanos."));
        }
    }
}
