# Sistema de Associados (SINC) — CRUD com ArrayList em Java

**Link da apresentação em vídeo:**  
https://drive.google.com/file/d/1Uwl4U6uehmXtsj49cMSDOT4aHlIsiMgJ/view

**Repositório:**  
https://github.com/elidielton-dev/prova-crud-associados

---

## Identificação

| Campo | Informação |
|-------|------------|
| **Aluno** | Eidielton Rodrigues Silva Bezerra |
| **Curso** | Análise e Desenvolvimento de Sistemas (ADS) |
| **Disciplina** | Algoritmo e Estrutura de Dados |
| **Estrutura de dados** | `ArrayList` (`java.util`) |
| **Tema** | Sistema de Associados (inspirado no SINC) |

---

## Objetivo

Implementar um CRUD completo (Create, Read, Update, Delete) encapsulando um `ArrayList`, com menu interativo em console Java.

---

## Estrutura do projeto

```text
Prova/
├── README.md
└── src/
    └── br/edu/aesa/
        ├── model/
        │   └── Associado.java          # Entidade (id, nome, cpf, telefone, status)
        ├── service/
        │   └── AssociadoService.java   # Regras de negócio + ArrayList
        └── app/
            └── Main.java               # Menu e entrada pelo console
```

---

## Como compilar e executar

No PowerShell, a partir da pasta do projeto:

```powershell
javac -d out src/br/edu/aesa/model/Associado.java src/br/edu/aesa/service/AssociadoService.java src/br/edu/aesa/app/Main.java
java -cp out br.edu.aesa.app.Main
```

---

## Funcionalidades (menu)

| Opção | Operação |
|-------|----------|
| `[1]` | Cadastrar novo associado |
| `[2]` | Listar todos os associados |
| `[3]` | Buscar associado por ID |
| `[4]` | Atualizar associado |
| `[5]` | Remover associado |
| `[0]` | Sair do programa |

### Operações sobre o ArrayList

- **Cadastrar** — valida os dados, impede ID duplicado e usa `add`
- **Listar** — percorre a lista na ordem de inserção
- **Buscar** — percorre a lista até encontrar o ID (`O(n)`)
- **Atualizar** — localiza o índice e usa `set`
- **Remover** — localiza o índice e usa `remove`

---

## Por que ArrayList?

- Lista dinâmica: cresce conforme os cadastros.
- Mantém a ordem de inserção, o que facilita a listagem.
- Adequada para volume pequeno/médio de associados e para demonstrar percorrimento linear.
- **Limitação:** busca, atualização e remoção por ID são \(O(n)\), pois é necessário percorrer a lista.
