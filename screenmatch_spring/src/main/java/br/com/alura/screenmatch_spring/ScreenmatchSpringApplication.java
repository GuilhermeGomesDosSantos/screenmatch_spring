package br.com.alura.screenmatch_spring;

import br.com.alura.screenmatch_spring.model.DadosEpisodio;
import br.com.alura.screenmatch_spring.model.DadosSerie;
import br.com.alura.screenmatch_spring.model.DadosTemporada;
import br.com.alura.screenmatch_spring.service.ConsumoAPI;
import br.com.alura.screenmatch_spring.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=31df3067");
//		System.out.println(json);
//
//		json = consumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

		System.out.println("Dados do Episodio");
		json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=31df3067");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=31df3067");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		System.out.println("Lista de Temporadas:");
		temporadas.forEach(System.out::println);
	}
}
