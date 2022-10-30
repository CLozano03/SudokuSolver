package tests;

import sudoku.*;
import utils.*;

public class ResolverTest {

    //Declaracion de coordenadas
    static Coordenada coordenada1 = new Coordenada(1, 1);
    static Coordenada coordenada2 = new Coordenada(2, 5);
    static Coordenada coordenada3 = new Coordenada(3, 2);
    static Coordenada coordenada4 = new Coordenada(3, 4);
    static Coordenada coordenada5 = new Coordenada(3, 5);
    static Coordenada coordenada6 = new Coordenada(4, 4);
    static Coordenada coordenada7 = new Coordenada(6, 2);
    static Coordenada coordenada9 = new Coordenada(7, 8);
    static Coordenada coordenada10 = new Coordenada(9, 4);

    static Sudoku sudoku1 = new Sudoku("files/In1.text");
    static Sudoku sudoku2 = new Sudoku("files/In2.text");

    public static void resolverSudoku(Sudoku sudoku){
        Resolver resolver = new Resolver(sudoku);
        
        resolver.resolver();
        System.out.println("Sudoku: ");
        System.out.println(sudoku.toString());
        resolver.imprimirMatriz();
        System.out.println("Es correcto: " + sudoku.esCorrecto());
        /* resolver.resolver();
        
        System.out.println(sudoku.toString()); */
    }

    public static void main(String[] args){
        //resolver.imprimirMatriz();
        resolverSudoku(sudoku1);

    }
}