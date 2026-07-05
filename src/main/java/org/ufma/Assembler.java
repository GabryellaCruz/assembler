package org.ufma;

import org.ufma.parser.Parser;
import org.ufma.parser.CommandType;
import org.ufma.symbols.SymbolTable;
import org.ufma.code.Code;

import java.io.PrintWriter;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Erro: Forneca o caminho de um arquivo .asm.");
            return;
        }

        String inputFilename = args[0];
        String outputFilename = inputFilename.replace(".asm", ".hack");

        try {
            SymbolTable symbolTable = new SymbolTable();

            Parser firstPassParser = new Parser(inputFilename);
            int romAddress = 0;

            while (firstPassParser.hasMoreInstructions()) {
                firstPassParser.advance();
                CommandType type = firstPassParser.commandType();

                if (type == CommandType.L_COMMAND) {
                    symbolTable.addEntry(firstPassParser.symbol(), romAddress);
                } else {
                    romAddress++;
                }
            }

            Parser secondPassParser = new Parser(inputFilename);
            try (PrintWriter out = new PrintWriter(outputFilename)) {
                while (secondPassParser.hasMoreInstructions()) {
                    secondPassParser.advance();
                    CommandType type = secondPassParser.commandType();

                    if (type == CommandType.A_COMMAND) {
                        String symbol = secondPassParser.symbol();
                        int value;

                        if (symbol.matches("\\d+")) {
                            // Se for constante numerica pura (ex: @100)
                            value = Integer.parseInt(symbol);
                        } else {
                            // Se for uma variavel ou um rotulo mapeado (ex: @sum)
                            if (!symbolTable.contains(symbol)) {
                                symbolTable.addVariable(symbol);
                            }
                            value = symbolTable.getAddress(symbol);
                        }

                        // Formata para string binaria de 15 bits preenchida com 0 a esquerda + o bit '0' indicador de instrucao A
                        String binaryA = "0" + String.format("%15s", Integer.toBinaryString(value)).replace(' ', '0');
                        out.println(binaryA);

                    } else if (type == CommandType.C_COMMAND) {
                        String compBin = Code.comp(secondPassParser.comp());
                        String destBin = Code.dest(secondPassParser.dest());
                        String jumpBin = Code.jump(secondPassParser.jump());

                        // Monta o frame completo da instrucao C: 111 + comp + dest + jump
                        String binaryC = "111" + compBin + destBin + jumpBin;
                        out.println(binaryC);
                    }
                }
            }

            System.out.println(" ARQUIVO BINARIO .HACK GERADO COM SUCESSO!");

        } catch (IOException e) {
            System.out.println("Erro no processamento: " + e.getMessage());
        }
    }
}