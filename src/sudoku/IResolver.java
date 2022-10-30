package sudoku;

import utils.*; 

/**
 * Clase con metodos de resolucion
 * necesarios para resolver el sudoku
 */
public interface IResolver {

    /**
     * Asigna un numero a la coordenada dada del sudoku, 
     * eliminando los candidatos de las casillas asociadas:
     * -Las que estan en la cruz que forma la coordenada
     * -La que estan en la submatriz de 3*3 asociada la coordenada
     * 
     * @param coordenada
     */
    public void asignarNumero(int numero, Coordenada coordenada);

    /**
     * Metodo que llama, para cada una de las filas y columnas, a la funcion
     * comprobarFilaAux(int nFila, boolean filaColumna)
     * 
     * @param nFila numerada del 1 al 9
     * @param FilaColumna si es fila o columna
     */
    public void comprobarFilas();

    /**
     * Para cada numero en Sudoku.NUMEROS_SUDOKU que no este en la fila/columna
     * cuenta las veces que ese numero. Acabada la iteracion de la fila/columna, en caso de que el
     * contador sea 1, asigna ese numero a la casilla vacia que tenga ese numero como candidato
     * @param coordenada
     */
    public void comprobarFilaAux(Coordenada coodenada);

    /**
     * Funcion que llama a comprobarCandidatosAux(Coordenada coordenada)
     * para cada una de las casillas vacias del sudoku.
     * 
     * @param coordenada
     */
    public void comprobarCandidatos();

    /**
     * Para la coordenada dada, en caso de que tamanno del numerosCandidatos de
     * la coordenada de matrizCandidatos sea 1, aplica 
     * la funcion asignarNumero(int numero, Coordenada coordenada) 
     * 
     * @param coordenada
     */
    public void comprobarCandidatosAux(Coordenada coordenada);
}
