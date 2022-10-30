package tests;

import sudoku.*;
import utils.*;

public class NumerosCandidatosTest {

    public static void main(String[] args){

        Sudoku sudoku1 = new Sudoku("files/In2.text");

        //sudoku1.toString();
        NumerosCandidatos num = new NumerosCandidatos(sudoku1, new Coordenada(5,6));
        
        //System.out.println(num.get(1));
        //System.out.println(num.size());
        System.out.println(num.toString());

        System.out.println();

        num.add(num.size(), 5);
        num.add(num.size(), 3);

        System.out.println(num.toString());

        num.remove((Integer) 5);

        System.out.println(num.toString());

        System.out.println(num.size());
    }
    
}
