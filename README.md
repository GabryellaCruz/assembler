# 🏛️ Montador Hack Nativo - Assembler (Nand2Tetris)

Projeto prático final desenvolvido para a disciplina de **Compiladores** da **Universidade Federal do Maranhão (UFMA)**.

O objetivo deste projeto é implementar um montador (*assembler*) completo capaz de traduzir programas escritos em **Assembly Hack** (`.asm`) para código de máquina binário (`.hack`), executável na arquitetura de hardware proposta pelo curso **Nand2Tetris**.

---

# 👥 Integrantes e Matrículas

* **Gabryella Cruz Sousa** — Matrícula: `20250013701`
* **Mateus Dutra Vale** — Matrícula: `20250071302`

---

# 💻 Linguagem e Ambiente

* **Linguagem:** Java (JDK 11 ou superior)
* **Ambiente de Desenvolvimento/Testes:** Linux (Ubuntu/Debian)
* **Arquitetura de Implementação:** Processamento em Duas Passagens (*Two-Pass Architecture*)

---

# 📂 Estrutura do Projeto

O projeto foi modularizado separando a análise sintática, a conversão de mnemônicos para código binário e o gerenciamento da tabela de símbolos.

```plaintext
assembler/
├── bin/                    # Arquivos compilados (.class)
├── src/main/java/          # Código-fonte do projeto
│   └── org/ufma/
│       ├── parser/         # Parser e classificação dos comandos
│       ├── symbols/        # Tabela de símbolos (SymbolTable.java)
│       ├── code/           # Conversão de mnemônicos (Code.java)
│       └── Assembler.java  # Orquestrador principal
├── test/projects/06/       # Suíte oficial de testes do Nand2Tetris
└── CompilaETraduza.sh      # Script Bash para compilação e tradução em lote
```

---

# 🚀 Como Compilar e Executar no Linux

## 1. Execução Automatizada

O projeto acompanha um script em **Bash** responsável por:

* limpar o ambiente de compilação;
* criar a estrutura necessária;
* compilar todas as classes Java;
* traduzir automaticamente os programas oficiais (`Add.asm`, `Max.asm`, `Rect.asm` e `Pong.asm`).

Na raiz do projeto, execute:

```bash
# Concede permissão de execução ao script
chmod +x CompilaETraduza.sh

# Executa toda a automação
./CompilaETraduza.sh
```

---

## 2. Execução Manual

Caso deseje montar apenas um arquivo específico:

```bash
# Cria a pasta de saída
mkdir -p bin

# Compila o projeto
javac -d bin \
src/main/java/org/ufma/parser/*.java \
src/main/java/org/ufma/symbols/*.java \
src/main/java/org/ufma/code/*.java \
src/main/java/org/ufma/Assembler.java

# Executa o montador
java -cp bin org.ufma.Assembler "caminho/do/seu_arquivo.asm"
```

---

# ⚙️ Arquitetura de Duas Passagens (Two-Pass)

O montador utiliza a estratégia clássica de **duas passagens**, permitindo resolver referências futuras de rótulos sem comprometer o endereçamento da memória ROM.

## Primeira Passagem

Nesta etapa o parser:

* percorre todo o arquivo Assembly;
* ignora espaços em branco e comentários;
* identifica comandos do tipo `L_COMMAND`;
* registra cada rótulo encontrado na `SymbolTable`, associando-o ao endereço da próxima instrução real.

Exemplo:

```asm
(LOOP)
```

---

## Segunda Passagem

O arquivo é percorrido novamente.

Durante esta fase:

* comandos `A_COMMAND` têm seus símbolos resolvidos;
* novas variáveis são alocadas automaticamente a partir do endereço **RAM[16]**;
* comandos `C_COMMAND` são decompostos em seus campos `dest`, `comp` e `jump`;
* cada instrução é convertida para sua representação binária de **16 bits**, sendo gravada no arquivo `.hack`.

---

# 🕹️ Execução do Jogo Pong no CPU Emulator

O arquivo `Pong.asm` possui aproximadamente **30.000 linhas de código**, sendo utilizado como principal teste de desempenho do montador.

Para executá-lo:

1. Acesse a pasta de ferramentas do Nand2Tetris:

```bash
cd nand2tetris/tools
./CPUEmulator.sh
```

2. No menu superior, selecione:

```
File → Load Program
```

3. Abra o arquivo `Pong.hack` gerado pelo montador.

4. Na seção **Animate**, selecione:

```
No Animation
```

5. Clique em **Run (Play)**.

6. Clique sobre a tela do emulador e utilize as teclas **A** e **D** do teclado para controlar a raquete durante a execução do jogo.

# 🎥 Video de Apresentação
```
LInk do video
```
