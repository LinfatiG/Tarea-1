    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package gestorarchivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author gilio
 */
public class Menu {

    public String leerArchivo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese la ruta completa del archivo.");
        System.out.println("La ruta debe estar escrita de la siguiente forma " + "C:\\Users\\Documetos\\Ejemplo\\Archivo.txt");
        String ruta = teclado.nextLine();
        Path archivo = Paths.get(ruta);
        String texto = "";

        try {
            texto = new String(Files.readAllBytes(archivo));
            System.out.println("El contenido del archivo es:\n" + texto);
            System.out.println();
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leido."); // El archivo no se lee pues bien no existe o la ruta está mal escrita.
        }
        return ruta;

    }

    public String agregarTexto() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese la ruta del fichero donde desea escribir");
        String ruta = teclado.nextLine();
        System.out.println("Ingrese el texto que desea agregar al fichero");
        String texto = teclado.nextLine();

        String Fichero = (ruta);
        File fichero = new File(Fichero);

        try {
            if (!fichero.exists()) {
                fichero.createNewFile();

                // Si el fichero no existe, lo crea automáticamente en la raíz de la carpeta del programa.
                // Se abre un flujo de escritura sobre el fichero con codificación utf-8.
                // En "FileOutputStream(Ffichero,true)", true es por si existe el fichero para añadir texto sin sobreescribir lo ya existente.
            }
            try (BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero, true), "utf-8"))) {
                Fescribe.write('\n' + texto);
                System.out.println("Tus datos han sido grabados en el archivo");

                // Se cierra el fichero y el flujo de escritura.
            }

        } catch (IOException ex) { // Captura cualquier posible error y lo muestra por pantalla.
            System.out.println("Ha ocurrido un error " + ex.getMessage());
        }
        return texto;

    }

    public void salirMenu() {
        System.out.println("Usted ha seleccionado salir del menu, que tenga un buen día.");
        System.exit(0);
    }

    public void menu() throws IOException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Seleccione la opcion a realizar.");
        System.out.println();
        System.out.println("1.- Leer archivo de texto.");
        System.out.println("2.- Agregar texto al archivo.");
        System.out.println("3.- Salir del menu.");

        int opcn = val();
        seleccion(opcn);
    }

    public void seleccion(int opcn) throws IOException {

        switch (opcn) {

            case 1:
                leerArchivo();
                menu();
                break;

            case 2:
                agregarTexto();
                menu();
                break;

            case 3:
                salirMenu();
                break;

            default:
                menu();
                break;
        }

    }

    public int val() { // Valida que solo se ingresen caracteres numericos.
        int num = 0;
        boolean error;

        do {

            try {
                error = false;
                Scanner teclado = new Scanner(System.in);
                num = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("El caracter ingresado no es numerico, intente de nuevo");
                error = true;
            }

        } while (error);

        return num;
    }

}
