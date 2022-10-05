package sudoku;

import utils.*;
import tads.*;

/**
 * Esta clase es un {@code ArrayList} de numeros candidatos de una Coordenada
 * @author Cesar Lozano
 */
public class NumerosCandidatos extends ArrayList<Integer>{
    private ArrayList<Integer> numeros;

    public NumerosCandidatos(){
        numeros = new ArrayList<Integer>();
    }

    public NumerosCandidatos(Sudoku sudoku, Coordenada coordenada) {
        numeros = new ArrayList<Integer>();
    
        for (int numero : Sudoku.NUMEROS_SUDOKU) {
            if(!(sudoku.hayN(numero, coordenada) || sudoku.hayNCuadrado(numero, coordenada))){
               add(size(), numero);
            }   
        }
        /**
         *  (2,4) 
         * 
         * 
         * (int)(x /3) == 0 --> primera
         * (int)(x /3) == 1 --> segunda
         * (int)(x /3) == 2 --> tercera
         */
    }
    
    
    ///ERROOR: cuando ya hay un numero candidato en una casilla, hay veces que lo añade otra vez

    /* @Override
    public void add (int index, Integer element){
        if(indexOf(element) != -1){
            super.add(index, element);
        }
    } */

}
