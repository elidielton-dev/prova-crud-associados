package br.edu.aesa.app; // Pacote da aplicação (menu / interação com o usuário)

import br.edu.aesa.model.Associado;         // Entidade (dados do associado)
import br.edu.aesa.service.AssociadoService; // Service (CRUD com ArrayList)

import java.util.List;    // Tipo de retorno da listagem
import java.util.Scanner; // Lê o que o usuário digita no teclado

/**
 * Classe Main (APLICAÇÃO / INTERFACE DE CONSOLE)
 * ----------------------------------------------
 * Ponto de entrada do programa.
 * Mostra o menu, lê as escolhas do usuário e chama o AssociadoService.
 * Aqui NÃO fica a estrutura de dados — ela está encapsulada no service.
 */
public class Main {

    /**
     * scanner = leitor do teclado.
     * static = compartilhado por todos os métodos da classe Main.
     * final = a referência não muda (sempre o mesmo Scanner).
     * System.in = entrada padrão (teclado).
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * service = objeto que gerencia o ArrayList e as operações CRUD.
     * A Main só conversa com o usuário; o service cuida dos dados.
     */
    private static final AssociadoService service = new AssociadoService();

    /**
     * Método principal: o Java começa a execução por aqui.
     *
     * @param args argumentos da linha de comando (não usamos neste projeto)
     */
    public static void main(String[] args) {
        // opcao = número que o usuário escolhe no menu (0 a 5)
        int opcao;

        // do-while: mostra o menu pelo menos uma vez e repete até optar por 0 (sair)
        do {
            exibirMenu(); // imprime as opções na tela
            opcao = lerInteiro("Escolha uma opcao: "); // le a opcao digitada

            // switch decide qual acao executar conforme o numero escolhido
            switch (opcao) {
                case 1: // CREATE
                    cadastrarAssociado();
                    break; // sai do switch (nao cai no proximo case)
                case 2: // READ (listar todos)
                    listarAssociados();
                    break;
                case 3: // READ (buscar um)
                    buscarAssociado();
                    break;
                case 4: // UPDATE
                    atualizarAssociado();
                    break;
                case 5: // DELETE
                    removerAssociado();
                    break;
                case 0: // SAIR
                    System.out.println("\nEncerrando o sistema. Ate logo!");
                    break;
                default: // qualquer numero que nao seja 0-5
                    System.out.println("\nOpcao invalida. Tente novamente.");
            }
        } while (opcao != 0); // continua enquanto NÃO for zero

        // Fecha o Scanner para liberar o recurso do teclado
        scanner.close();
    }

    /**
     * Mostra o menu principal no console.
     * Só imprime textos; não altera dados.
     */
    private static void exibirMenu() {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE ASSOCIADOS (SINC)");
        System.out.println("========================================");
        System.out.println("[1] Cadastrar novo associado");
        System.out.println("[2] Listar todos os associados");
        System.out.println("[3] Buscar associado por ID");
        System.out.println("[4] Atualizar associado");
        System.out.println("[5] Remover associado");
        System.out.println("[0] Sair do programa");
        System.out.println("========================================");
    }

    /**
     * Opção [1] — CREATE
     * Lê os dados digitados, cria um Associado e pede ao service para cadastrar.
     */
    private static void cadastrarAssociado() {
        System.out.println("\n--- Cadastrar Associado ---");

        // Variáveis locais: existem só dentro deste método
        int id = lerInteiro("ID: ");                 // ID numérico único
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();     // trim() remove espaços extras
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();
        String status = lerStatus();                 // só aceita ATIVO ou INATIVO

        // Monta o objeto com os dados lidos do teclado
        Associado associado = new Associado(id, nome, cpf, telefone, status);

        // service.cadastrar devolve true/false → usamos para feedback ao usuário
        if (service.cadastrar(associado)) {
            System.out.println("Associado cadastrado com sucesso!");
        } else {
            System.out.println("Falha no cadastro. Verifique se o ID ja existe "
                    + "ou se os dados estao invalidos (ID > 0, campos preenchidos, status ATIVO/INATIVO).");
        }
    }

    /**
     * Opção [2] — READ (listar)
     * Pede a lista ao service e imprime cada associado no console.
     */
    private static void listarAssociados() {
        System.out.println("\n--- Lista de Associados ---");

        // lista = cópia dos associados retornada pelo service
        List<Associado> lista = service.listarTodos();

        // Caso de borda: lista vazia
        if (lista.isEmpty()) {
            System.out.println("Nenhum associado cadastrado.");
            return; // encerra o método aqui
        }

        // size() = quantidade de itens na lista
        System.out.println("Total: " + lista.size());

        // Percorre cada associado e imprime (usa o toString da classe Associado)
        for (Associado associado : lista) {
            System.out.println(associado);
        }
    }

    /**
     * Opção [3] — READ (buscar por ID)
     * Pergunta o ID, busca no service e mostra o resultado.
     */
    private static void buscarAssociado() {
        System.out.println("\n--- Buscar Associado ---");

        // Caso de borda: não tem sentido buscar se a lista está vazia
        if (service.estaVazio()) {
            System.out.println("A lista esta vazia. Cadastre um associado primeiro.");
            return;
        }

        int id = lerInteiro("Informe o ID: "); // ID digitado pelo usuario
        Associado associado = service.buscarPorId(id); // null se nao achar

        if (associado != null) {
            System.out.println("Registro encontrado:");
            System.out.println(associado); // imprime os dados
        } else {
            // Caso de borda: ID inexistente
            System.out.println("Nenhum item encontrado com o ID informado.");
        }
    }

    /**
     * Opção [4] — UPDATE
     * Localiza o associado pelo ID, pede os novos dados e atualiza no service.
     */
    private static void atualizarAssociado() {
        System.out.println("\n--- Atualizar Associado ---");

        // Caso de borda: lista vazia
        if (service.estaVazio()) {
            System.out.println("A lista esta vazia. Cadastre um associado primeiro.");
            return;
        }

        int id = lerInteiro("ID do associado a atualizar: ");
        Associado existente = service.buscarPorId(id); // registro atual (ou null)

        // Caso de borda: ID nao encontrado
        if (existente == null) {
            System.out.println("Nenhum item encontrado com o ID informado.");
            return;
        }

        // Mostra como esta hoje, para o usuario saber o que vai mudar
        System.out.println("Registro atual: " + existente);

        // Le os novos valores
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Novo CPF: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine().trim();
        String status = lerStatus();

        // Monta objeto com os novos dados (mesmo ID)
        Associado novosDados = new Associado(id, nome, cpf, telefone, status);

        if (service.atualizar(id, novosDados)) {
            System.out.println("Associado atualizado com sucesso!");
        } else {
            System.out.println("Falha na atualizacao. Verifique se os dados estao validos.");
        }
    }

    /**
     * Opção [5] — DELETE
     * Pede o ID e remove o associado da lista pelo service.
     */
    private static void removerAssociado() {
        System.out.println("\n--- Remover Associado ---");

        // Caso de borda: lista vazia
        if (service.estaVazio()) {
            System.out.println("A lista esta vazia. Nao ha o que remover.");
            return;
        }

        int id = lerInteiro("ID do associado a remover: ");

        if (service.remover(id)) {
            System.out.println("Associado removido com sucesso!");
        } else {
            // Caso de borda: ID inexistente
            System.out.println("Nenhum item encontrado com o ID informado.");
        }
    }

    /**
     * Le o status do teclado ate o usuario digitar ATIVO ou INATIVO.
     * Loop infinito (while true) que so para quando a entrada for valida.
     *
     * @return "ATIVO" ou "INATIVO"
     */
    private static String lerStatus() {
        while (true) {
            System.out.print("Status (ATIVO/INATIVO): ");
            // Le, tira espacos e deixa maiusculo (ex: "ativo" -> "ATIVO")
            String status = scanner.nextLine().trim().toUpperCase();

            if (status.equals("ATIVO") || status.equals("INATIVO")) {
                return status; // valor valido -> devolve e sai do metodo
            }
            // Se chegou aqui, o valor e invalido -> pede de novo
            System.out.println("Status invalido. Digite ATIVO ou INATIVO.");
        }
    }

    /**
     * Le um numero inteiro do teclado com seguranca.
     * Se o usuario digitar texto (ex: "abc"), pede novamente.
     *
     * @param mensagem texto mostrado antes da leitura (ex: "ID: ")
     * @return numero inteiro digitado pelo usuario
     */
    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem); // mostra o prompt
            String entrada = scanner.nextLine().trim(); // le como texto

            try {
                // Integer.parseInt transforma texto em int (ex: "10" -> 10)
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                // Se nao for numero valido, cai aqui e pede de novo
                System.out.println("Valor invalido. Digite um numero inteiro.");
            }
        }
    }
}
