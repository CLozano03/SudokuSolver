package tests;

import sudoku.*;
import utils.*;

public abstract class SudokuTest {

    //Creacion sudoku
    static Sudoku sudoku = new Sudoku("files/In2.text");

    //Declaracion de coordenadas
    static Coordenada coordenada1 = new Coordenada(1, 1);
    static Coordenada coordenada2 = new Coordenada(4, 4);
    static Coordenada coordenada3 = new Coordenada(6, 2);
    static Coordenada coordenada4 = new Coordenada(9, 4);
    static Coordenada coordenada5 = new Coordenada(7, 8);

    //FUNCIONES--------------------------------------------

    // estaVacia(Coordenada coordenada)
    static boolean estaVacia_Prueba1 = sudoku.estaVacia(coordenada1);
    static boolean estaVacia_Prueba2 = sudoku.estaVacia(coordenada2);
    static boolean estaVacia_Prueba3 = sudoku.estaVacia(coordenada3);

    static boolean estaVacia = (estaVacia_Prueba1 == true) && 
                               (estaVacia_Prueba2 == false) && 
                               (estaVacia_Prueba3 == true);

    // hayNFila(int numero, int nFilas, boolean FilaColumna)
    static boolean hayNFila_Prueba1 = sudoku.hayNFila(1, 1, true);
    static boolean hayNFila_Prueba2 = sudoku.hayNFila(8, 6, false);
    static boolean hayNFila_Prueba3 = sudoku.hayNFila(8, 3, true);

    static boolean hayNFila = (hayNFila_Prueba1 == true) &&
                              (hayNFila_Prueba2 == true) &&
                              (hayNFila_Prueba3 == false);

    // hayNCuadrado(int numero, Coordenada)
    static boolean hayNCuadrado_Prueba1 = sudoku.hayNCuadrado(6, coordenada1);
    static boolean hayNCuadrado_Prueba2 = sudoku.hayNCuadrado(9, coordenada4); 
    static boolean hayNCuadrado_Prueba3 = sudoku.hayNCuadrado(8, coordenada5);
    
    static boolean hayNCuadrado = (hayNCuadrado_Prueba1 == true) &&
                                  (hayNCuadrado_Prueba2 == false) &&
                                  (hayNCuadrado_Prueba3 == false);

    // hayN(int numero, Coordenada)
    static boolean hayN_Prueba1 = sudoku.hayN(6, coordenada1);
    static boolean hayN_Prueba2 = sudoku.hayN(6, coordenada4); 
    static boolean hayN_Prueba3 = sudoku.hayN(8, coordenada5);
    
    static boolean hayN = (hayN_Prueba1 == true) &&
                          (hayN_Prueba2 == true) &&
                          (hayN_Prueba3 == false);

    
    
    

    public static void main(String[] args) {

        sudoku.imprimirSudoku();

        System.out.println();
        
        System.out.println("Prueba estaVacia: " + estaVacia);
        System.out.println("Prueba hayNFila: " + hayNFila);
        System.out.println("Prueba hayNCuadrado: " + hayNCuadrado);
        System.out.println("Prueba hayN: " + hayN);

        sudoku.setN(coordenada1, 6);
        
        sudoku.imprimirSudoku();


    }
}
