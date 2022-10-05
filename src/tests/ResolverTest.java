package tests;

import sudoku.*;
import utils.*;

public class ResolverTest {


    static Sudoku sudoku1 = new Sudoku("files/In1.text");
    static Sudoku sudoku2 = new Sudoku("files/In2.text");

    public static void resolverSudoku(Sudoku sudoku){
        Resolver resolver = new Resolver(sudoku);
        
        resolver.imprimirMatriz();
        resolver.asignarNumero(1, new Coordenada(1, 1));
        resolver.imprimirMatriz();
        resolver.asignarNumero(5, new Coordenada(1, 2));
        resolver.imprimirMatriz();
        /* resolver.resolver();
        
        sudoku.imprimirSudoku(); */
    }

    public static void main(String[] args){
        //resolver.imprimirMatriz();
        resolverSudoku(sudoku2);
    }
}