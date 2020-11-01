import java.awt.desktop.SystemEventListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class Principal {
    public static ArrayList<Float> Notas;
    static int linhasInvalidas;

    public static boolean validadorDoArquivo;

    public static void main(String[] args) throws FileNotFoundException {
        Notas = new ArrayList<>();
        linhasInvalidas = 0;

        validadorDoArquivo = false;
        while(!validadorDoArquivo) {
            try {
                System.out.println("Digite o caminho do arquivo: ");
                Scanner scanner = new Scanner(System.in);
                String nomeDoArquivo = scanner.nextLine();

                System.out.println(String.format("Média: %f", calcularMedia(nomeDoArquivo)));
            } catch (FileNotFoundException e) {
                
            } catch (ArquivoCorrompidoException e) {
                System.out.println(ArquivoCorrompidoException.getLinhasInvalidas());
            }

        }

    }

    public static void armazenarLinhasFloat(String linha){
        String[] palavras = linha.split("[\\s ]");

        for(String cadaPalavra : palavras) {
            String formatada = cadaPalavra.replaceAll(",", ".");
            try{
                float f = Float.parseFloat(formatada);
                if (f <= 10 && f >= 0) {
                    Notas.add(f);
                }
                else {
                    linhasInvalidas++;
                }
            }
            catch(NumberFormatException e){
                linhasInvalidas++;
            }
        }
    }

    public static float calcularMedia(String nomeDoArquivo)throws FileNotFoundException, ArquivoCorrompidoException{
        float media= 0;

        try {
            File arquivo = new File(nomeDoArquivo);
            Scanner scan = new Scanner(arquivo);

            while (scan.hasNextLine()) {
                armazenarLinhasFloat(String.format(scan.nextLine()));
            }

        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
            throw new FileNotFoundException();
        }

        validadorDoArquivo = true;

        if(linhasInvalidas <= Notas.size()) {
            for (float notaAtual : Notas) {
                media += notaAtual;
            }
            media = media / Notas.size();
            return media;
        }
        else{
            throw new ArquivoCorrompidoException(linhasInvalidas);
        }
    }
}
