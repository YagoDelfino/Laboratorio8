public class ArquivoCorrompidoException extends Exception{

    private static int linhasInvalidas;

    public ArquivoCorrompidoException(int linhasInvalidas) {
        ArquivoCorrompidoException.linhasInvalidas = linhasInvalidas;
    }
    public static int getLinhasInvalidas(){
        return ArquivoCorrompidoException.linhasInvalidas;
    }
}
