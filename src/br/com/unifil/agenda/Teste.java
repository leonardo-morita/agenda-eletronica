package br.com.unifil.agenda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teste {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("teste.txt")));
        List<String> pessoas = new ArrayList<>();

//        while (in.hasNext()) {
//            pessoas.add(in.next());
//        }

        while (in.hasNext()) {
            String pessoa = in.nextLine();
            pessoas.add(pessoa);
            System.out.println(pessoa);
        }


        String pessoa1 = pessoas.get(0);
        String[] informacoes1 = pessoa1.split(",");
        String nome1 = informacoes1[0];
        String telefone1 = informacoes1[1];

        System.out.println(pessoa1);
        System.out.println(nome1.trim());
        System.out.println(telefone1.trim());

//        String pessoa = "Leo H Morita, (43)1111-1111";
//        String[] separacao = pessoa.split(",");
//        String nome = separacao[0].trim();
//        String telefone = separacao[1].trim();
//
//        System.out.println("Pessoa: " + pessoa);
//        System.out.println("Nome: " + nome);
//        System.out.println("Telefone: " + telefone);
    }

    private static boolean verificarTelefone(String telefone) {
        return telefone.matches(".([0-9][0-9]).[0-9]{4}-[0-9]{4}");
    }

    /*
    @startuml
title "Diagrama de Sequência da Agenda Eletrônica"


Alice -> Bob: test
@enduml
     */

}
