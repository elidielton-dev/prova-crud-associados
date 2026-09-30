package br.edu.aesa.model; // Pacote da camada de modelo (entidades / dados)

/**
 * Classe Associado (MODEL / ENTIDADE)
 * -----------------------------------
 * Representa UM associado do sistema (inspirado no SINC).
 * Guarda apenas os dados da pessoa. A lógica de CRUD fica no AssociadoService.
 */
public class Associado {

    // ========== ATRIBUTOS (variáveis de instância) ==========
    // Cada objeto Associado tem sua própria cópia desses valores.

    /** Identificador único do associado (chave para buscar/atualizar/remover). */
    private int id;

    /** Nome completo do associado. */
    private String nome;

    /** CPF do associado (armazenado como texto para aceitar máscara). */
    private String cpf;

    /** Telefone de contato do associado. */
    private String telefone;

    /** Situação do associado no sistema: deve ser "ATIVO" ou "INATIVO". */
    private String status;

    /**
     * Construtor: cria um novo objeto Associado já com todos os dados.
     *
     * @param id       número único do associado
     * @param nome     nome completo
     * @param cpf      documento CPF
     * @param telefone telefone de contato
     * @param status   ATIVO ou INATIVO
     */
    public Associado(int id, String nome, String cpf, String telefone, String status) {
        // this.campo = parâmetro → guarda o valor recebido no atributo do objeto
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.status = status;
    }

    // ========== GETTERS (ler o valor do atributo) ==========

    /**
     * Retorna o ID do associado.
     *
     * @return valor do atributo id
     */
    public int getId() {
        return id; // devolve o id guardado neste objeto
    }

    /**
     * Altera o ID do associado.
     *
     * @param id novo identificador
     */
    public void setId(int id) {
        this.id = id; // substitui o id antigo pelo novo
    }

    /**
     * Retorna o nome do associado.
     *
     * @return valor do atributo nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do associado.
     *
     * @param nome novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o CPF do associado.
     *
     * @return valor do atributo cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Altera o CPF do associado.
     *
     * @param cpf novo CPF
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o telefone do associado.
     *
     * @return valor do atributo telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Altera o telefone do associado.
     *
     * @param telefone novo telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o status do associado.
     *
     * @return valor do atributo status (ATIVO ou INATIVO)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Altera o status do associado.
     *
     * @param status novo status (ATIVO ou INATIVO)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Converte o objeto Associado em texto legível para imprimir no console.
     * Exemplo: "ID: 1 | Nome: Maria | CPF: 111... | Telefone: 83... | Status: ATIVO"
     *
     * @return texto formatado com todos os campos
     */
    @Override // indica que estamos reescrevendo o toString() da classe Object
    public String toString() {
        // %d = número inteiro | %s = texto (String)
        return String.format(
                "ID: %d | Nome: %s | CPF: %s | Telefone: %s | Status: %s",
                id, nome, cpf, telefone, status // valores que entram nos %d e %s
        );
    }
}
