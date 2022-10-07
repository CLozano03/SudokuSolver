package sudoku;

import utils.*;
import tads.*;

/**
 * Esta clase es un {@code ArrayList} de numeros candidatos de una Coordenada
 * @author Cesar Lozano
 */
public class NumerosCandidatos extends ArrayList<Integer>{

    public NumerosCandidatos(){
        super();
    }

    public NumerosCandidatos(Sudoku sudoku, Coordenada coordenada) {
        super();
    
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
    
    //Sobreescritura de add para no añadir dos veces el mismo numero
    @Override
    public void add (int index, Integer element){
        if(indexOf(element) != -1){
            super.add(index, element);
        }
    }

}
