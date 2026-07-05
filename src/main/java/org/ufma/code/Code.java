package org.ufma.code;

import java.util.HashMap;
import java.util.Map;

public class Code {
    private static final Map<String, String> compTable = new HashMap<>();
    private static final Map<String, String> destTable = new HashMap<>();
    private static final Map<String, String> jumpTable = new HashMap<>();

    static {
        // Tabela COMP (7 bits) - Quando bit a=0
        compTable.put("0",   "0101010"); compTable.put("1",   "0111111"); compTable.put("-1",  "0111010");
        compTable.put("D",   "0001100"); compTable.put("A",   "0110000"); compTable.put("!D",  "0001101");
        compTable.put("!A",  "0110001"); compTable.put("-D",  "0001111"); compTable.put("-A",  "0110011");
        compTable.put("D+1", "0011111"); compTable.put("A+1", "0110111"); compTable.put("D-1", "0001110");
        compTable.put("A-1", "0110010"); compTable.put("D+A", "0000010"); compTable.put("D-A", "0010011");
        compTable.put("A-D", "0000111"); compTable.put("D&A", "0000000"); compTable.put("D|A", "0000010");

        // Tabela COMP (7 bits) - Quando bit a=1 (Troca-se A por M)
        compTable.put("M",   "1110000"); compTable.put("!M",  "1110001"); compTable.put("-M",  "1110011");
        compTable.put("M+1", "1110111"); compTable.put("M-1", "1110010"); compTable.put("D+M", "1000010");
        compTable.put("D-M", "1001011"); compTable.put("M-D", "1000111"); compTable.put("D&M", "1000000");
        compTable.put("D|M", "1000010");

        // Tabela DEST (3 bits)
        destTable.put("",    "000"); destTable.put("M",   "001"); destTable.put("D",   "010");
        destTable.put("MD",  "011"); destTable.put("A",   "100"); destTable.put("AM",  "101");
        destTable.put("AD",  "110"); destTable.put("AMD", "111");

        // Tabela JUMP (3 bits)
        jumpTable.put("",    "000"); jumpTable.put("JGT", "001"); jumpTable.put("JEQ", "010");
        jumpTable.put("JGE", "011"); jumpTable.put("JLT", "100"); jumpTable.put("JNE", "101");
        jumpTable.put("JLE", "110"); jumpTable.put("JMP", "111");
    }

    public static String comp(String mnemonic) {
        return compTable.getOrDefault(mnemonic, "0000000");
    }

    public static String dest(String mnemonic) {
        return destTable.getOrDefault(mnemonic, "000");
    }

    public static String jump(String mnemonic) {
        return jumpTable.getOrDefault(mnemonic, "000");
    }
}