@echo off
cls

echo ======================================================
echo            COMPILANDO O ASSEMBLER HACK (WINDOWS)
echo ======================================================

if not exist "bin" (
    mkdir bin
)

REM Ajustado para compilar o Assembler.java direto na pasta org/ufma/
javac -d bin ^
src\main\java\org\ufma\parser\*.java ^
src\main\java\org\ufma\symbols\*.java ^
src\main\java\org\ufma\code\*.java ^
src\main\java\org\ufma\Assembler.java

if %ERRORLEVEL% neq 0 (
    echo [ERRO] Falha na compilacao do codigo Java.
    exit /b 1
)

REM Ajustado para chamar a classe do pacote correto org.ufma.Assembler
set EXEC_CMD=java -cp bin org.ufma.Assembler

echo.
echo ======================================================
echo            RODANDO MONTAGEM DOS ARQUIVOS .ASM
echo ======================================================

%EXEC_CMD% "test\projects\6\add\Add.asm"
%EXEC_CMD% "test\projects\6\max\Max.asm"
%EXEC_CMD% "test\projects\6\rect\Rect.asm"
%EXEC_CMD% "test\projects\6\pong\Pong.asm"

echo.
echo ======================================================
echo  Processo Concluido! Verifique as pastas de teste.
echo ======================================================

pause