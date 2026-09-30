> 🔗 **Link da Apresentação em Vídeo:** [Insira o link aqui]

# Sistema de Associados (SINC) — CRUD com ArrayList em Java

Projeto prático de **Algoritmo e Estrutura de Dados** (ADS).

**Aluno:** Eidielton Rodrigues Silva Bezerra  
**Curso:** ADS  
**Disciplina:** Algoritmo e Estrutura de Dados  
**Estrutura de dados:** `ArrayList`  
**Tema:** Sistema de Associados (inspirado no SINC)

---

## Objetivo

Implementar um CRUD completo (Create, Read, Update, Delete) encapsulando um `ArrayList`, com menu interativo no console.

---

## Estrutura do projeto

```text
Prova/
├── README.md
└── src/
    └── br/edu/aesa/
        ├── model/
        │   └── Associado.java
        ├── service/
        │   └── AssociadoService.java
        └── app/
            └── Main.java
```

---

## Como compilar e executar

No PowerShell, a partir da pasta `Prova`:

```powershell
javac -d out src/br/edu/aesa/model/Associado.java src/br/edu/aesa/service/AssociadoService.java src/br/edu/aesa/app/Main.java
java -cp out br.edu.aesa.app.Main
```

---

## Menu

- `[1]` Cadastrar novo associado
- `[2]` Listar todos os associados
- `[3]` Buscar associado por ID
- `[4]` Atualizar associado
- `[5]` Remover associado
- `[0]` Sair do programa

---

## Por que ArrayList?

- Lista dinâmica: cresce conforme os cadastros.
- Mantém a ordem de inserção (útil na listagem).
- Ideal para volume pequeno/médio de associados e para demonstrar percorrimento linear.
- **Desvantagem:** busca, atualização e remoção por ID são \(O(n)\), pois é necessário percorrer a lista.

---

## Roteiro do vídeo (5 a 10 minutos)

### 1. Abertura e identificação (~1 min)

Fale:

> “Olá, meu nome é Eidielton Rodrigues Silva Bezerra, sou do curso de ADS, disciplina Algoritmo e Estrutura de Dados. Neste trabalho desenvolvi um Sistema de Associados inspirado no SINC, com operações de CRUD em Java, utilizando a estrutura de dados ArrayList.”

### 2. Por que escolhi ArrayList (~1 a 2 min)

Explique:

1. A estrutura usada foi o **ArrayList** do pacote `java.util`.
2. Escolhi porque o sistema precisa cadastrar, listar na ordem e percorrer registros de forma simples.
3. **Vantagens:** tamanho dinâmico, boa para listagem sequencial, fácil de manipular no console.
4. **Desvantagem:** localizar um associado pelo ID exige percorrer a lista — complexidade \(O(n)\). Para poucos registros isso é aceitável; se a busca por ID fosse o foco principal, um HashMap seria mais rápido (\(O(1)\)), mas neste cenário o ArrayList atende bem e deixa clara a lógica do CRUD.

### 3. Explicação do código (~2 a 3 min)

Mostre na ordem:

1. **`Associado.java`** — atributos: `id`, `nome`, `cpf`, `telefone`, `status`.
2. **`AssociadoService.java`** — o `ArrayList` privado e cada método:
   - `cadastrar`: valida dados e impede ID duplicado (`add`).
   - `buscarPorId` / `listarTodos`: percorrem a lista.
   - `atualizar`: encontra o índice e usa `set`.
   - `remover`: encontra o índice e usa `remove`.
3. **`Main.java`** — menu com `Scanner` chamando o service.

### 4. Demonstração prática (~2 a 3 min)

Execute ao vivo e faça nesta ordem:

1. Cadastrar **3 associados** diferentes (IDs 1, 2 e 3).
2. **Listar** todos.
3. **Buscar** o ID 2 (existe) e depois um ID inexistente (ex.: 99).
4. **Atualizar** o associado 1 e listar de novo.
5. **Remover** o associado 3 e listar de novo.
6. Sair com a opção `[0]`.

### Dados sugeridos para a demo

| ID | Nome | CPF | Telefone | Status |
|----|------|-----|----------|--------|
| 1 | Maria Silva | 111.111.111-11 | 83999990001 | ATIVO |
| 2 | João Souza | 222.222.222-22 | 83999990002 | ATIVO |
| 3 | Ana Lima | 333.333.333-33 | 83999990003 | INATIVO |

Na atualização do ID 1, troque o telefone ou o status para ficar visível na listagem.

---

## Entrega

1. Código no repositório Git com commits.
2. Vídeo (YouTube não listado, Drive ou Loom).
3. Colar o link do vídeo no topo deste README.
