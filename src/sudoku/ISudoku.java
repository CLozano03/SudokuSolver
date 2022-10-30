package sudoku;

import utils.*;

/**
 * Esta interfaz define lo que es un sudoku y
 * los metodos que dan informacion sobre este
 * 
 * @author Cesar Lozano
 * 
 */
public interface ISudoku {
    /**
     * Imprime la matriz del sudoku por consola
     */
    public String toString();
    
    /**
     * @param coordenada
     * @return si una casilla esta vacia
     */
    public boolean estaVacia(Coordenada coordenada);

    /**
     * 
     * @param coordenada
     * @return el numero en la coordenada especificada
     */
    public int getN(Coordenada coordenada);
    /**
     * Cambia el numero de una casilla por otro
     *  
     * @param coordenada
     * @param n
     */
    public void setN(Coordenada coordenada, int n);

    /**
     * @param numero a poner
     * @param coordenada 
     * @return si hay un numero determinado en 
     * alguna casilla relacionada con una coordenada
     */
    public boolean hayN(int numero, Coordenada coordenada);
    
    /**
     * 
     * @param numero a comprobar
     * @param coordenada
     * @return si hay un numero en una nFila
     * 
     */
    public boolean hayNFila(int numero, Coordenada coordenada);

    /**
     * 
     * @param numero
     * @param coordenada
     * @return si hay un numero en el cuadrado de 3x3
     * asociado a una coordenada
     */
    public boolean hayNCuadrado(int numero, Coordenada coordenada);

    /**
     * 
     * @return si cada casilla del sudoku esta rellena con algun numero
     */
    public boolean estaCompleto();

    /**
     * 
     * @return si no existen numeros repetidos en casillas asociadas a cada una de las casillas del sudoku
     */
    public boolean esCorrecto();

    /**
     * 
     * @return si el sudoku esta completo y es correcto a su vez 
     */
    public boolean sudokuResuelto();
}
