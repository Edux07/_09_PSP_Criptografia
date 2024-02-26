package com.example;
import java.util.Scanner;
public class main {

    private static Scanner sc = new Scanner(System.in);
    private static String fraseEncriptada = null;

    public static void init() {
        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Encriptar frase");
            System.out.println("2. Mostrar frase encriptada");
            System.out.println("3. Desencriptar frase");
            System.out.println("4. Salir del programa");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce la frase a encriptar: ");
                    String frase = sc.nextLine();
                    fraseEncriptada = EncryptionUtil.encriptar(frase);
                    System.out.println("Frase encriptada y guardada.");
                    break;
                case 2:
                    if (fraseEncriptada != null) {
                        System.out.println("Frase encriptada: " + fraseEncriptada);
                    } else {
                        System.out.println("No hay frase encriptada guardada.");
                    }
                    break;
                case 3:
                    if (fraseEncriptada != null) {
                        String fraseDesencriptada = EncryptionUtil.desencriptar(fraseEncriptada);
                        System.out.println("Frase desencriptada: " + fraseDesencriptada);
                    } else {
                        System.out.println("No hay frase encriptada guardada.");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 4);

        sc.close();
    }
}
