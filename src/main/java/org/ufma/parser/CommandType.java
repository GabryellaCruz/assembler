package org.ufma.parser;

public enum CommandType {
    A_COMMAND,    // Para instruções do tipo @valor ou @simbolo
    C_COMMAND,    // Para instruções do tipo dest=comp;jump
    L_COMMAND     // Para rótulos/labels de desvio ex: (LOOP)
}