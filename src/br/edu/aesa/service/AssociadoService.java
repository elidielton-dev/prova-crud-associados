package br.edu.aesa.service; // Pacote da camada de serviço (regras / CRUD)

import br.edu.aesa.model.Associado; // Classe da entidade Associado

import java.util.ArrayList; // Estrutura de dados escolhida: lista dinâmica
import java.util.List;      // Interface List (tipo genérico de lista)

/**
 * Classe AssociadoService (SERVICE / REPOSITÓRIO)
 * -----------------------------------------------
 * Encapsula a estrutura de dados ArrayList e oferece as operações CRUD:
 *   C = Create  → cadastrar
 *   R = Read    → buscarPorId e listarTodos
 *   U = Update  → atualizar
 *   D = Delete  → remover
 *
 * A busca por ID percorre a lista um a um → complexidade O(n).
 */
public class AssociadoService {

    /**
     * associados = estrutura de dados principal do sistema.
     * ArrayList = lista que cresce sozinha conforme cadastramos itens.
     * final = a referência da lista não muda (sempre a mesma lista),
     *         mas o conteúdo (elementos dentro dela) pode mudar.
     */
    private final ArrayList<Associado> associados;

    /**
     * Construtor: cria o service já com um ArrayList vazio,
     * pronto para receber cadastros.
     */
    public AssociadoService() {
        // new ArrayList<>() cria a lista vazia na memória
        this.associados = new ArrayList<>();
    }

    /**
     * CREATE — cadastra um novo associado na lista.
     *
     * Passos:
     * 1) Verifica se o objeto não é nulo
     * 2) Valida se os dados estão corretos
     * 3) Impede ID duplicado
     * 4) Adiciona no final do ArrayList com add()
     *
     * @param associado objeto com os dados a serem salvos
     * @return true se cadastrou com sucesso; false se falhou
     */
    public boolean cadastrar(Associado associado) {
        // Se alguém passou null, não dá para cadastrar
        if (associado == null) {
            return false; // false = operação falhou
        }

        // Se nome/cpf/telefone/status/id estiverem inválidos, cancela
        if (!dadosValidos(associado)) {
            return false;
        }

        // Se já existe alguém com o mesmo ID, cancela (ID deve ser único)
        if (existeId(associado.getId())) {
            return false;
        }

        // add() coloca o associado no final do ArrayList
        associados.add(associado);
        return true; // true = cadastro ok
    }

    /**
     * READ — busca UM associado pelo ID.
     * Percorre o ArrayList do início ao fim (laço for-each).
     *
     * @param id identificador que queremos encontrar
     * @return o Associado encontrado, ou null se não existir
     */
    public Associado buscarPorId(int id) {
        // "associado" é a variável temporária de cada volta do laço
        for (Associado associado : associados) {
            // Compara o ID do item atual com o ID procurado
            if (associado.getId() == id) {
                return associado; // achou → devolve o objeto
            }
        }
        // Se saiu do laço sem achar, o ID não existe na lista
        return null;
    }

    /**
     * READ — lista TODOS os associados cadastrados.
     * Retorna uma CÓPIA da lista para ninguém alterar a lista interna
     * sem passar pelos métodos do service.
     *
     * @return nova ArrayList com todos os associados
     */
    public List<Associado> listarTodos() {
        // new ArrayList<>(associados) = copia todos os elementos
        return new ArrayList<>(associados);
    }

    /**
     * UPDATE — atualiza os dados de um associado já cadastrado.
     * Mantém o mesmo ID; troca nome, cpf, telefone e status.
     *
     * Usa um laço com índice (i) porque o método set(i, objeto)
     * precisa saber a posição na lista.
     *
     * @param id         ID do associado que será alterado
     * @param novosDados objeto com os novos valores dos campos
     * @return true se atualizou; false se não achou ou dados inválidos
     */
    public boolean atualizar(int id, Associado novosDados) {
        // Valida se os novos dados existem e estão corretos
        if (novosDados == null || !dadosValidos(novosDados)) {
            return false;
        }

        // i = índice (posição) do elemento no ArrayList: 0, 1, 2, ...
        for (int i = 0; i < associados.size(); i++) {
            // get(i) pega o associado que está na posição i
            if (associados.get(i).getId() == id) {
                // Cria um novo objeto já com o ID original + dados novos
                Associado atualizado = new Associado(
                        id,                        // ID não muda
                        novosDados.getNome(),     // novo nome
                        novosDados.getCpf(),      // novo cpf
                        novosDados.getTelefone(), // novo telefone
                        novosDados.getStatus()    // novo status
                );
                // set(i, atualizado) troca o item na posição i
                associados.set(i, atualizado);
                return true; // atualização ok
            }
        }
        // Chegou aqui = nenhum associado com esse ID
        return false;
    }

    /**
     * DELETE — remove um associado da lista pelo ID.
     *
     * @param id identificador do associado a remover
     * @return true se removeu; false se o ID não foi encontrado
     */
    public boolean remover(int id) {
        // Percorre a lista com índice para poder usar remove(i)
        for (int i = 0; i < associados.size(); i++) {
            if (associados.get(i).getId() == id) {
                // remove(i) tira o elemento da posição i do ArrayList
                associados.remove(i);
                return true; // remoção ok
            }
        }
        return false; // ID não encontrado
    }

    /**
     * Verifica se a lista está sem nenhum associado.
     *
     * @return true se vazia; false se tem pelo menos 1 item
     */
    public boolean estaVazio() {
        // isEmpty() é método do ArrayList: true quando size() == 0
        return associados.isEmpty();
    }

    /**
     * Informa quantos associados estão cadastrados.
     *
     * @return quantidade de elementos no ArrayList
     */
    public int quantidade() {
        // size() retorna o número de itens da lista
        return associados.size();
    }

    /**
     * Método auxiliar (privado): verifica se já existe um ID na lista.
     * Usado no cadastro para evitar duplicidade.
     *
     * @param id ID a verificar
     * @return true se já existe; false se está livre
     */
    private boolean existeId(int id) {
        // Se buscarPorId achar alguém, o resultado NÃO é null → ID existe
        return buscarPorId(id) != null;
    }

    /**
     * Método auxiliar (privado): valida se os dados do associado estão ok.
     * Regras:
     * - id deve ser maior que zero
     * - nome, cpf e telefone não podem ser nulos nem vazios
     * - status deve ser ATIVO ou INATIVO
     *
     * @param associado objeto a validar
     * @return true se todos os dados estão válidos; false caso contrário
     */
    private boolean dadosValidos(Associado associado) {
        // ID inválido se for 0 ou negativo
        if (associado.getId() <= 0) {
            return false;
        }

        // trim() remove espaços nas pontas; isEmpty() verifica se ficou vazio
        if (associado.getNome() == null || associado.getNome().trim().isEmpty()) {
            return false;
        }
        if (associado.getCpf() == null || associado.getCpf().trim().isEmpty()) {
            return false;
        }
        if (associado.getTelefone() == null || associado.getTelefone().trim().isEmpty()) {
            return false;
        }
        if (associado.getStatus() == null) {
            return false;
        }

        // Normaliza o status: tira espaços e deixa maiúsculo (ex: " ativo " → "ATIVO")
        String status = associado.getStatus().trim().toUpperCase();

        // Só aceita ATIVO ou INATIVO
        return status.equals("ATIVO") || status.equals("INATIVO");
    }
}
