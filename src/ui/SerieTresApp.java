package ui;

import service.SerieTresService;
import java.util.Scanner;

public class SerieTresApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SerieTresService service = new SerieTresService();

        System.out.println("ingrese el numero: ");
        int limite = scanner.nextInt();

        try {
            int suma = service.calcularSuma(limite);
            service.procesarSerie(limite);

            System.out.println("serie generada de 3 en 3 hasta " + limite);
            System.out.println("resultado de la suma: " + suma);
            System.out.println("resultado guardado en oracle 19c");
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}   
