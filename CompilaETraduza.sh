#!/bin/bash

clear

echo "======================================================"
echo "           COMPILANDO O ASSEMBLER HACK (LINUX)"
echo "======================================================"

if [ ! -d "bin" ]; then
    mkdir bin
fi

# Ajustado para compilar o Assembler.java direto na pasta org/ufma/
javac -d bin src/main/java/org/ufma/parser/*.java src/main/java/org/ufma/symbols/*.java src/main/java/org/ufma/code/*.java src/main/java/org/ufma/Assembler.java

if [ $? -ne 0 ]; then
    echo "[ERRO] Falha na compilacao do codigo Java."
    exit 1
fi

# Ajustado para chamar a classe do pacote correto org.ufma.Assembler
EXEC_CMD="java -cp bin org.ufma.Assembler"

echo ""
echo "======================================================"
echo "           RODANDO MONTAGEM DOS ARQUIVOS .ASM"
echo "======================================================"

$EXEC_CMD "test/projects/6/add/Add.asm"
$EXEC_CMD "test/projects/6/max/Max.asm"
$EXEC_CMD "test/projects/6/rect/Rect.asm"
$EXEC_CMD "test/projects/6/pong/Pong.asm"

echo ""
echo "======================================================"
echo " Processo Concluido! Verifique as pastas de teste."
echo "======================================================"