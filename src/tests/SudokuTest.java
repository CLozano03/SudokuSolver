package tests;

import sudoku.*;
import utils.*;

public abstract class SudokuTest {

    // Creacion sudoku
    static Sudoku sudoku = new Sudoku("files/In1.text");

    // Declaracion de coordenadas
    static Coordenada coordenada1 = new Coordenada(1, 1);
    static Coordenada coordenada2 = new Coordenada(4, 4);
    static Coordenada coordenada3 = new Coordenada(6, 2);
    static Coordenada coordenada4 = new Coordenada(9, 4);
    static Coordenada coordenada5 = new Coordenada(8, 7);

    // FUNCIONES--------------------------------------------

    // estaVacia(Coordenada coordenada)
    static boolean estaVacia_Prueba1 = sudoku.estaVacia(coordenada1); // True
    static boolean estaVacia_Prueba2 = sudoku.estaVacia(coordenada2); // False
    static boolean estaVacia_Prueba3 = sudoku.estaVacia(coordenada3); // True

    static boolean estaVacia = (estaVacia_Prueba1 == true) &&
            (estaVacia_Prueba2 == false) &&
            (estaVacia_Prueba3 == true);

    // estaVacia(Coordenada coordenada)
    static int getN_Prueba1 = sudoku.getN(coordenada1); // 0
    static int getN_Prueba2 = sudoku.getN(coordenada2); // 4
    static int getN_Prueba3 = sudoku.getN(coordenada3); // 0

    static boolean getN = (getN_Prueba1 == 0) &&
            (getN_Prueba2 == 4) &&
            (getN_Prueba3 == 0);

    // hayNFila(int numero, int nFilas, boolean FilaColumna)
    static boolean hayNFila_Prueba1 = sudoku.hayNFila(6, coordenada1); // true
    static boolean hayNFila_Prueba2 = sudoku.hayNFila(8, coordenada3); // false
    static boolean hayNFila_Prueba3 = sudoku.hayNFila(9, coordenada4); // true
    static boolean hayNFila_Prueba4 = sudoku.hayNFila(3, coordenada5); // true
    static boolean hayNFila_Prueba5 = sudoku.hayNFila(9, coordenada5); // false

    static boolean hayNFila = (hayNFila_Prueba1 == true) &&
            (hayNFila_Prueba2 == false) &&
            (hayNFila_Prueba3 == true) &&
            (hayNFila_Prueba4 == true) &&
            (hayNFila_Prueba5 == false);

    // hayNCuadrado(int numero, Coordenada)
    static boolean hayNCuadrado_Prueba1 = sudoku.hayNCuadrado(8, coordenada1); // true
    static boolean hayNCuadrado_Prueba2 = sudoku.hayNCuadrado(1, coordenada4); // false
    static boolean hayNCuadrado_Prueba3 = sudoku.hayNCuadrado(8, coordenada5); // false
    static boolean hayNCuadrado_Prueba4 = sudoku.hayNCuadrado(2, coordenada5); // true

    static boolean hayNCuadrado = (hayNCuadrado_Prueba1 == true) &&
            (hayNCuadrado_Prueba2 == false) &&
            (hayNCuadrado_Prueba3 == false) &&
            (hayNCuadrado_Prueba4 == true);

    // hayN(int numero, Coordenada)
    static boolean hayN_Prueba1 = sudoku.hayN(2, coordenada1); // true
    static boolean hayN_Prueba2 = sudoku.hayN(6, coordenada4); // true
    static boolean hayN_Prueba3 = sudoku.hayN(1, coordenada5); // false

    static boolean hayN = (hayN_Prueba1 == true) &&
            (hayN_Prueba2 == true) &&
            (hayN_Prueba3 == false);

    // esCorrecto()
    static boolean esCorrecto_Prueba1 = sudoku.esCorrecto();

    /*
     * Mapa con las coordenadas para que sean mas accesibles visualmente
     * 
     * * 1 0 0 0 0 0 0 0 0
     * * 0 0 0 0 0 0 0 0 0
     * * 0 0 0 0 0 0 0 0 0
     * 
     * * 0 0 0 2 0 0 0 0 0
     * * 0 0 0 0 0 0 0 0 0
     * * 0 3 0 0 0 0 0 0 0
     * 
     * * 0 0 0 0 0 0 0 0 0
     * * 0 0 0 0 0 0 5 0 0
     * * 0 0 0 4 0 0 0 0 0
     */

    public static void main(String[] args) {

        System.out.println("\n----------------\n");

        System.out.println("Testeando el setter de 6 en coordenada " + coordenada1 + ":\n");
        sudoku.setN(coordenada1, 6);

        // System.out.println("\n" + sudoku.toString());

        boolean esCorrecto_Prueba2 = sudoku.esCorrecto(); //false
        boolean esCorrecto = esCorrecto_Prueba1 && !esCorrecto_Prueba2; 

        System.out.println("Prueba estaVacia: " + estaVacia);
        System.out.println("Prueba getN: " + getN);
        System.out.println("Prueba hayNFila: " + hayNFila);
        System.out.println("Prueba hayNCuadrado: " + hayNCuadrado);
        System.out.println("Prueba hayN: " + hayN);
        System.out.println("Prueba esCorrecto: " + esCorrecto);

        boolean pruebasPasadas = estaVacia && getN &&
                                hayNFila && hayNCuadrado &&
                                hayN && esCorrecto;

        System.out.println("\n----------------\n El test pasa las PRUEBAS: " + pruebasPasadas);

    }
}
