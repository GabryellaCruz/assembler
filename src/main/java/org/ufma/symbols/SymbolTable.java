package org.ufma.symbols;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> table;
    private int nextVariableAddress;

    public SymbolTable() {
        this.table = new HashMap<>();
        this.nextVariableAddress = 16; // Variáveis de usuário começam em RAM[16]
        initializePredefinedSymbols();
    }

    private void initializePredefinedSymbols() {
        // Inicializa R0 ate R15 (Registradores virtuais)
        for (int i = 0; i <= 15; i++) {
            table.put("R" + i, i);
        }
        // Ponteiros nativos do sistema e da pilha
        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);

        // Mapeamento de Memória I/O do Hardware Hack
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);
    }

    public void addEntry(String symbol, int address) {
        table.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return table.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        return table.get(symbol);
    }

    public void addVariable(String symbol) {
        if (!contains(symbol)) {
            table.put(symbol, nextVariableAddress);
            nextVariableAddress++;
        }
    }
}