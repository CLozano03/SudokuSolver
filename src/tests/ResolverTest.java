package tests;

import sudoku.*;
import utils.Coordenada;

public class ResolverTest {


    static Sudoku sudoku1 = new Sudoku("files/In2.text");
    static Sudoku sudoku2 = new Sudoku("files/In1.text");

    public static void resolverSudoku(Sudoku sudoku){
        Resolver resolver = new Resolver(sudoku);

        resolver.resolver();

        System.out.println("");

        sudoku .imprimirSudoku();

        /* Coordenada coordenada1 = new Coordenada(4,5);

        System.out.println("\nComprobando coordenada: " + coordenada1.toString()); 

        System.out.println("\n" + new NumerosCandidatos(sudoku1, coordenada1).toString());


        System.out.println("\n" + new NumerosCandidatos(sudoku1, coordenada1).toString());
        
        System.out.println(resolver.matrizCandidatos[coordenada1.getX()-1][coordenada1.getY()-1].size());
 */
        System.out.println("\n");
        resolver.imprimirMatriz();
    }

    public static void main(String[] args){
        //resolver.imprimirMatriz();
        resolverSudoku(sudoku2);
    }
}