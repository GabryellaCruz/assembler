package org.ufma.main;

import org.ufma.parser.Parser;
import org.ufma.parser.CommandType;
import org.ufma.symbols.SymbolTable;
import org.ufma.code.Code;

import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Erro: Forneca o caminho de um arquivo .asm.");
            return;
        }

        String inputFilename = args[0];

        try {
            SymbolTable symbolTable = new SymbolTable();

            // === PRIMEIRA PASSAGEM: Coletar rotulos (Labels) ===
            Parser firstPassParser = new Parser(inputFilename);
            int romAddress = 0;

            while (firstPassParser.hasMoreInstructions()) {
                firstPassParser.advance();
                CommandType type = firstPassParser.commandType();

                if (type == CommandType.L_COMMAND) {
                    symbolTable.addEntry(firstPassParser.symbol(), romAddress);
                } else {
                    romAddress++; // Apenas instrucoes A e C ocupam espaco real na ROM
                }
            }

            System.out.println("Primeira passagem concluida! Rotulos mapeados.");

        } catch (IOException e) {
            System.out.println("Erro no processamento: " + e.getMessage());
        }
    }
}