package br.com.alura.screenmatch_spring.principal;

import br.com.alura.screenmatch_spring.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY ="&apikey=31df3067";
    private ConsumoAPI consumo = new ConsumoAPI();
    public void exibeMenu() {
        System.out.println("Digite o nome da série");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
    }
}
