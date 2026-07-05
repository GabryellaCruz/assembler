package org.ufma.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<String> instructions;
    private int currentIndex;
    private String currentInstruction;

    public Parser(String filename) throws IOException {
        this.instructions = new ArrayList<>();
        this.currentIndex = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Remove todos os espacos em branco da instrucao Hack
                line = line.replaceAll("\\s+", "");

                // Ignora linhas vazias ou comentarios puros
                if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }

                // Remove comentarios que ficam no fim da linha de instrucao
                if (line.contains("//")) {
                    line = line.split("//")[0];
                }

                instructions.add(line);
            }
        }
    }

    public boolean hasMoreInstructions() {
        return currentIndex < instructions.size();
    }

    public void advance() {
        currentInstruction = instructions.get(currentIndex);
        currentIndex++;
    }
}