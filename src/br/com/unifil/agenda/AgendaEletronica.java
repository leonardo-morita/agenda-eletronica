package br.com.unifil.agenda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AgendaEletronica {

    public static void main(String[] args) throws IOException {
        List<List<String>> listaPessoaParaEditar;
        List<Integer> indices;
        Scanner in;
        Scanner teclado = new Scanner(System.in);
        boolean rodando = true;

        try {
            while (rodando) {
                List<List<String>> informacaoPessoas = new ArrayList<>();
                FileWriter out = new FileWriter("src/br/com/unifil/agenda/saida/agenda.txt", true);
                in = new Scanner(new BufferedReader(new FileReader("src/br/com/unifil/agenda/saida/agenda.txt")));

                exibirMenuDeOpcoes();

                System.out.println();
                System.out.print("Opção: ");
                int opcao = teclado.nextInt();
                teclado.nextLine();

                switch (opcao) {
                    case 1: {
                        if (!cadastrarNovaPessoa(teclado, out)) break;

                        adicionarInformacoesDasPessoasNaLista(in, informacaoPessoas);
                        ordenarAgenda(informacaoPessoas);
                        atualizarAgendaOrdenada(informacaoPessoas);
                        break;
                    }

                    case 2: {
                        listarAgenda(in);
                        break;
                    }

                    case 3: {
                        buscarPessoaNaAgenda(in, teclado, informacaoPessoas);
                        break;
                    }

                    case 4: {
                        System.out.println();
                        System.out.println("Editar informações de uma pessoa na agenda");
                        listaPessoaParaEditar = new ArrayList<>();
                        indices = new ArrayList<>();

                        boolean identificar =
                                buscarPessoaNaAgenda(listaPessoaParaEditar, indices, in, teclado, informacaoPessoas);

                        if (!identificar) {
                            System.out.println("Pessoa não encontrada!");
                            System.out.println();
                            break;
                        }

                        String nome;

                        exibirListaDePessoasParaModificar(listaPessoaParaEditar, indices);

                        String linha2 = selecionarPessoaEspecificaParaFazerAlteracao(teclado, "editar");
                        int linha = Integer.valueOf(linha2);
                        if (!indices.contains(linha)) {
                            System.out.println("Opção inválida!");
                            System.out.println();
                            break;
                        }

                        System.out.println("Pessoa selecionada: " + informacaoPessoas.get(linha));

                        // Atualizar informações da pessoa selecionada
                        System.out.println();
                        System.out.println("1: Atualizar nome");
                        System.out.println("2: Atualizar telefone");
                        System.out.println("3: Voltar");
                        System.out.println();

                        System.out.print("Opção: ");
                        int atualizar = teclado.nextInt();
                        teclado.nextLine();

                        switch (atualizar) {
                            case 1: {
                                System.out.println();
                                System.out.print("Nome: ");
                                nome = teclado.nextLine();
                                boolean nomeInvalido = validarNomeDaPessoa(nome);

                                if (nomeInvalido) break;

                                informacaoPessoas.get(linha).set(0, nome);

                                System.out.println(informacaoPessoas);
                                ordenarAgenda(informacaoPessoas);
                                System.out.println(informacaoPessoas);

                                atualizarAgendaOrdenada(informacaoPessoas);

                                System.out.println("O nome foi atualizado com sucesso!");
                                System.out.println();
                                break;
                            }

                            case 2: {
                                System.out.println();
                                System.out.print("Telefone: ");
                                String telefone = teclado.nextLine();

                                boolean verificador = verificarTelefone(telefone);
                                if (!verificador) {
                                    System.out.println("O telefone está inválido!");
                                    break;
                                }

                                informacaoPessoas.get(linha).set(1, telefone);

                                System.out.println(informacaoPessoas);
                                ordenarAgenda(informacaoPessoas);
                                System.out.println(informacaoPessoas);

                                atualizarAgendaOrdenada(informacaoPessoas);

                                System.out.println("O telefone foi atualizado com sucesso!");
                                System.out.println();
                                break;
                            }

                            case 3: {
                                System.out.println();
                                break;
                            }

                            default: {
                                System.out.println();
                                System.out.println("Opção inválida!");
                                System.out.println();
                            }
                        }

                        System.out.println();
                        break;
                    }

                    case 5: {
                        listaPessoaParaEditar = new ArrayList<>();
                        indices = new ArrayList<>();

                        System.out.println();
                        System.out.println("Excluir uma pessoa da agenda");
                        boolean pessoaEncontrada =
                                buscarPessoaNaAgenda(listaPessoaParaEditar, indices, in, teclado, informacaoPessoas);

                        if (!pessoaEncontrada) {
                            System.out.println("Pessoa não encontrada!");
                            System.out.println();
                            break;
                        }


                        exibirListaDePessoasParaModificar(listaPessoaParaEditar, indices);
                        System.out.println("00: Deletar todos");

                        String linha2 = selecionarPessoaEspecificaParaFazerAlteracao(teclado, "excluir");
                        int linha = Integer.valueOf(linha2);

                        if (!indices.contains(linha) && !linha2.equals("00")) {
                            System.out.println("Opção inválida!");
                            System.out.println();
                            break;
                        }

                        if (linha2.equals("00")) {
                            System.out.println(listaPessoaParaEditar);
                            System.out.println(informacaoPessoas);

                            if (confirmarExclusaoDaPessoa(teclado)) break;

                            listaPessoaParaEditar.forEach((elemento) -> informacaoPessoas.remove(elemento));

                            atualizarAgendaOrdenada(informacaoPessoas);

                            System.out.println(informacaoPessoas);

                            System.out.println("Exclusão efetuada com sucesso!");
                            System.out.println();
                            break;
                        }

                        System.out.println("Pessoa selecionada: " + informacaoPessoas.get(linha));

                        if (confirmarExclusaoDaPessoa(teclado)) break;

                        System.out.println(informacaoPessoas);
                        informacaoPessoas.remove(linha);
                        System.out.println(informacaoPessoas);
                        System.out.println();

                        atualizarAgendaOrdenada(informacaoPessoas);

                        System.out.println("Esta pessoa foi removida com sucesso!");
                        System.out.println();
                        break;
                    }

                    case 9: {
                        System.out.println();
                        System.out.print("Fechando o programa!");
                        rodando = false;

                        break;
                    }

                    default:
                        System.out.println();
                        System.out.println("Opção inválida!");
                        System.out.println();
                }
            }
        } finally {
            if (teclado != null) {
                teclado.close();
            }
        }

    }

    private static boolean confirmarExclusaoDaPessoa(Scanner teclado) {
        System.out.println();
        System.out.print("Excluir (s/n): ");
        String excluir = teclado.nextLine();
        System.out.println();

        if (excluir.equals("n")) {
            System.out.println("Exclusão cancelada com sucesso!");
            System.out.println();
            return true;
        }
        return false;
    }

    private static void buscarPessoaNaAgenda(Scanner in, Scanner teclado, List<List<String>> informacaoPessoas) {
        System.out.println();
        System.out.println("Buscar pessoa na agenda");
        System.out.print("Nome da pessoa: ");
        String nome = teclado.nextLine();

        adicionarInformacoesDasPessoasNaLista(in, informacaoPessoas);
        identificarPessoasPorCaracteres(informacaoPessoas, nome);

        System.out.println();
    }

    private static String selecionarPessoaEspecificaParaFazerAlteracao(Scanner teclado, String acao) {
        System.out.println();
        if (acao.toLowerCase().equals("editar")) System.out.print("Selecione o número da linha que queira " +
                "fazer a alteração: ");
        if (acao.toLowerCase().equals("excluir")) System.out.print("Selecione o número da linha que queira " +
                "fazer a exclusão: ");
        String linha = teclado.next();
        teclado.nextLine();
        return linha;
    }

    private static void listarAgenda(Scanner in) {
        System.out.println();
        System.out.println("Agenda:");

        while (in.hasNextLine()) {
            String pessoa = in.nextLine();
            String[] informacoes = pessoa.split(",");
            String nome = informacoes[0];
            String telefone = informacoes[1];

            Pessoa p1 = new Pessoa(nome, telefone.trim());

            System.out.println(p1);
        }

        System.out.println();
    }

    private static boolean cadastrarNovaPessoa(Scanner teclado, FileWriter out) throws IOException {
        Pessoa pessoa;
        System.out.println();
        System.out.println("Cadastro da nova pessoa");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        if (validarNomeDaPessoa(nome)) return false;

        System.out.print("Telefone: ");
        String telefone = teclado.nextLine();

        boolean verificador = verificarTelefone(telefone);
        if (!verificador) {
            System.out.println("O telefone está inválido!");
            System.out.println();
            return false;
        }

        boolean verificarSegundoTelefone = verificarSegundoTelefone(out, teclado, nome);

        if (!verificarSegundoTelefone) return false;

        pessoa = new Pessoa(nome.trim(), telefone);

        out.write(pessoa.getNome() + ", ");
        out.write(pessoa.getTelefone() + "\n");

        out.close();
        return true;
    }

    private static boolean validarNomeDaPessoa(String nome) {
        if (nome.trim().equals("")) {
            System.out.println("Nome inválido!");
            System.out.println();
            return true;
        }
        return false;
    }

    private static boolean verificarTelefone(String telefone) {
        return telefone.matches(".([0-9][0-9]).[9][0-9]{4}-[0-9]{4}") ||
                telefone.matches(".([0-9][0-9]).[0-9]{4}-[0-9]{4}");
    }

    private static boolean buscarPessoaNaAgenda(List<List<String>> listaPessoaParaEditar, List<Integer> indices,
                                             Scanner in, Scanner teclado, List<List<String>> informacaoPessoas) {
        System.out.print("Nome da pessoa: ");
        String nome = teclado.nextLine();

        adicionarInformacoesDasPessoasNaLista(in, informacaoPessoas);

        boolean identificar = identificarPessoasPorCaracteres(informacaoPessoas, listaPessoaParaEditar, indices, nome);

        return identificar;
    }

    private static List<String> exibirListaDePessoasParaModificar(List<List<String>> listaPessoaParaEditar,
                                                                  List<Integer> indices) {
        List<String> opcaoParaAlterar = new ArrayList<>();
        for (int i = 0; i < listaPessoaParaEditar.size(); i++) {
            opcaoParaAlterar.add(indices.get(i) + ": " + listaPessoaParaEditar.get(i).get(0) + ", " +
                    listaPessoaParaEditar.get(i).get(1));
            System.out.println(indices.get(i) + ": " + listaPessoaParaEditar.get(i).get(0) + ", " +
                    listaPessoaParaEditar.get(i).get(1));
        }

        return opcaoParaAlterar;

    }

    private static void exibirMenuDeOpcoes() {
        System.out.println("Agenda Eletrônica");
        System.out.println("1: Adicionar nova pessoa na agenda");
        System.out.println("2: Listar as pessoas da agenda");
        System.out.println("3: Buscar pessoa na agenda");
        System.out.println("4: Editar pessoa cadastrada");
        System.out.println("5: Excluir pessoa da agenda");
        System.out.println("9: Fechar programa");
    }

    private static void atualizarAgendaOrdenada(List<List<String>> informacaoPessoas) throws IOException {
        FileWriter outOrdenada;
        outOrdenada = new FileWriter("src/br/com/unifil/agenda/saida/agenda.txt");

        for (int i = 0; i < informacaoPessoas.size(); i++) {
            String pessoa1 = informacaoPessoas.get(i).get(0) + ", " +
                    informacaoPessoas.get(i).get(1);
            outOrdenada.write(pessoa1 + "\n");
        }

        outOrdenada.close();
    }

    private static void adicionarInformacoesDasPessoasNaLista(Scanner in, List<List<String>> informacaoPessoas) {
        while (in.hasNextLine()) {
            String pessoa = in.nextLine();
            String[] informacoes = pessoa.split(",");
            String nome1 = informacoes[0];
            String telefone1 = informacoes[1];

            informacaoPessoas.add(Arrays.asList(nome1, telefone1.trim()));
        }
    }

    private static void identificarPessoasPorCaracteres(List<List<String>> informacaoPessoas, String nome) {
        List<String> pessoaSelecionada = new ArrayList<>();
        for (int i = 0; i < informacaoPessoas.size(); i++) {
            if (informacaoPessoas.get(i).get(0).toUpperCase().indexOf(nome.toUpperCase()) >= 0) {
                pessoaSelecionada = informacaoPessoas.get(i);

                System.out.println(pessoaSelecionada.get(0) + ", " + pessoaSelecionada.get(1));
            }
        }

        if (pessoaSelecionada.isEmpty()) System.out.println("Pessoa não encontrada!");
    }



    private static boolean identificarPessoasPorCaracteres(List<List<String>> informacaoPessoas,
                                                        List<List<String>> listaPessoaParaEditar,
                                                        List<Integer> indices, String nome) {
        List<String> pessoaSelecionada;
        boolean identificar = false;
        for (int i = 0; i < informacaoPessoas.size(); i++) {
            if (informacaoPessoas.get(i).get(0).toUpperCase().indexOf(nome.toUpperCase()) >= 0) {
                pessoaSelecionada = informacaoPessoas.get(i);
                indices.add(i);
                listaPessoaParaEditar.add(pessoaSelecionada);
                identificar = true;
            }
        }

        return identificar;
    }

    private static boolean verificarSegundoTelefone(FileWriter out, Scanner teclado, String nome) throws IOException {
        Pessoa pessoa;
        boolean rodando = true;
        while (rodando) {
            System.out.print("Outro telefone? (s/n): ");
            String segundoTelefone = teclado.next().trim().toLowerCase();

            if (segundoTelefone.equals("s")) {
                System.out.print("Telefone: ");
                String telefone2 = teclado.next();

                boolean verificador = verificarTelefone(telefone2);
                if (!verificador) {
                    System.out.println("O telefone está inválido!");
                    System.out.println();
                    return false;
                }

                pessoa = new Pessoa(nome, telefone2);

                out.write(pessoa.getNome() + ", ");
                out.write(pessoa.getTelefone() + "\n");
            } else if (segundoTelefone.equals("n")) {
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
        System.out.println();

        return true;
    }

    private static void ordenarAgenda(List<List<String>> informacaoPessoas) {
        List<String> aux;
        for (int i = 0; i < informacaoPessoas.size(); i++) {
            for (int j = 0; j < informacaoPessoas.size()-1; j++) {
                if (informacaoPessoas.get(j).get(0)
                        .compareTo(informacaoPessoas.get(j+1).get(0)) > 0) {
                    aux = informacaoPessoas.get(j);
                    informacaoPessoas.set(j, informacaoPessoas.get(j+1));
                    informacaoPessoas.set(j+1, aux);
                }
            }
        }
    }

}
