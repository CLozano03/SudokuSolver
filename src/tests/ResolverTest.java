package tests;

import sudoku.*;
import utils.*;

public class ResolverTest {


    static Sudoku sudoku1 = new Sudoku("files/In1.text");
    static Sudoku sudoku2 = new Sudoku("files/In2.text");

    public static void resolverSudoku(Sudoku sudoku){
        Resolver resolver = new Resolver(sudoku);
        
        resolver.resolver();
        System.out.println("Sudoku: ");
        sudoku.imprimirSudoku();
        resolver.imprimirMatriz();
        /* resolver.resolver();
        
        sudoku.imprimirSudoku(); */
    }

    public static void main(String[] args){
        //resolver.imprimirMatriz();
        resolverSudoku(sudoku2);
    }
}