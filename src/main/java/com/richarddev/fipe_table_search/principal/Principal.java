package com.richarddev.fipe_table_search.principal;

import com.richarddev.fipe_table_search.model.Dados;
import com.richarddev.fipe_table_search.model.Modelo;
import com.richarddev.fipe_table_search.model.Veiculo;
import com.richarddev.fipe_table_search.service.ConsumoApi;
import com.richarddev.fipe_table_search.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    ConsumoApi consumoApi = new ConsumoApi();
    ConverteDados converteDados = new ConverteDados();
    private Scanner sc = new Scanner(System.in);

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        System.out.println("=== VEÍCULOS PARA CONSULTAR ===\n[1] - Carro\n[2] - Moto\n[3] - Caminhões");
        System.out.print("ESCOLHA UMA OPCÃO: ");
        int opcaoVeiculo = sc.nextInt();
        sc.nextLine();

        String endereco = "";

        if (opcaoVeiculo == 1) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcaoVeiculo == 2) {
            endereco = URL_BASE + "motos/marcas";
        } else if (opcaoVeiculo == 3) {
            endereco = URL_BASE + "caminhoes/marcas";
        } else {
            System.out.println("Opcão inválida!");
        }

        var json = consumoApi.obterDados(endereco);
        System.out.println(json);
        var marcas = converteDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.print("Escolha a marca que você deseja digitando o código: ");
        var codigoMarca = sc.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumoApi.obterDados(endereco);
        var modeloLista = converteDados.obterDados(json, Modelo.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.print("\nDigite um trecho do nome do carro a ser buscado: ");
        var nomeVeiculo = sc.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nomeMarca().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.print("\nDigite o nome código do modelo para buscar os valores de avaliação: ");
        var codigoModelo = sc.nextLine();
        sc.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";

        json = consumoApi.obterDados(endereco);

        List<Dados> anos = converteDados.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size() ; i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumoApi.obterDados(enderecoAnos);
            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("Todos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);



    }

}
