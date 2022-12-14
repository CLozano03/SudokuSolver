package sudoku;

import utils.*;
import stdlib.*;

/**
 * Implementación de {@code ISudoku}
 * 
 * @author Cesar Lozano
 */
public class Sudoku implements ISudoku {

    // Matriz principal del sudoku
    public int[][] matrizSudoku;

    // Declaracion de constantes utiles.
    public static final int[] NUMEROS_SUDOKU = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    public static final int TAMANO_SUDOKU = 9;

    /**
     * Array tridimensional. Cada elemento de la 3x3 contiene
     * un array con los 9 elementos del cuadrado asociado.
     */
    public int[][][] cuadrados = new int[3][3][9];

    /**
     * Pone en {@value matrizSudoku} cada uno de los
     * numeros leidos en el archivo "filename"
     * 
     * Por otro lado, pone cada uno de los numeros del sudoku
     * en la matriz tridimensional de nombre: cuadrados
     * 
     * @param filename
     */
    public Sudoku(String filename) {

        In sc = new In(filename);
        matrizSudoku = new int[TAMANO_SUDOKU][TAMANO_SUDOKU];

        // Matriz del sudoku
        for (int i = 0; i < TAMANO_SUDOKU; i++) {
            for (int j = 0; j < TAMANO_SUDOKU; j++) {
                matrizSudoku[i][j] = sc.readInt();
            }
        }
        sc.close();

        // Creacion de la matriz de cuadrdados
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 9; k++) {
                    cuadrados[i][j][k] = matrizSudoku[i * 3 + (int) k / 3][j * 3 + k % 3]; // Los numeros
                    /*
                     * i*3 y j*3 se refieren al cuadrado 3x3 del sudoku al que apunta.
                     * - con k/3 y k%3 recorremos esa matriz 3x3 con solamente un iterador
                     * - k/3 --> filas
                     * - k%3 --> columnas
                     * EJEMPLO => Si un cuadrado es esto:
                     * 5 0 7
                     * 6 2 0
                     * 1 0 0
                     * Los numeros se meten de esta forma: 5 0 7 6 2 0 1 0 0
                     * 
                     * 
                     * PARA ACCEDER A UN NUMERO A TRAVES DE UNA COORDENADA:
                     * 
                     * FILAS COLUMNAS
                     * 3 *((coordenada.getX() -1) % 3) + (coordenada.getX() -1) % 3
                     * 
                     * 
                     */
                }
            }
        }

    }

    @Override
    public String toString() {

        String toString = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((j + 1) % 3 == 0 && j != 8) {
                    toString += matrizSudoku[i][j] + "|";
                } else {
                    toString += matrizSudoku[i][j] + " ";
                }
            }
            if ((i + 1) % 3 == 0 && i != 8) {
                toString += "\n------------------\n";

            } else {
                toString += "\n";
            }
        }
        toString += "\n";
        return toString;
    }

    @Override
    public boolean estaVacia(Coordenada coordenada) {
        return matrizSudoku[coordenada.getX() - 1][coordenada.getY() - 1] == 0;
    }

    @Override
    public int getN(Coordenada coordenada) {
        return matrizSudoku[coordenada.getX() - 1][coordenada.getY() - 1];
    }

    @Override
    public void setN(Coordenada coordenada, int n) {
        matrizSudoku[coordenada.getX() - 1][coordenada.getY() - 1] = n;
    }

    @Override
    public boolean hayN(int numero, Coordenada coordenada) {
        return hayNFila(numero, coordenada) || hayNCuadrado(numero, coordenada);
    }

    @Override
    public boolean hayNFila(int numero, Coordenada coordenada) {
        boolean hayN = false;
            for (int i = 0; i < 9 && !hayN; i++) {
                hayN = ((i != coordenada.getY() -1) && matrizSudoku[coordenada.getX() - 1][i] == numero) || (i != coordenada.getX() -1
                        && matrizSudoku[i][coordenada.getY() - 1] == numero);
                //Quiero que la i no sea la misma coordenada de lo que compruebo
            }    
            return hayN;
    }

    public boolean hayNCuadrado(int numero, Coordenada coordenada) { // 1 1 | 2 3
        boolean hayN = false;
        int casillaNoComprobar = 3 * ((coordenada.getX() - 1) % 3) + ((coordenada.getY() - 1) % 3); // Consultar constructor
        for (int i = 0; i < 9 && !hayN; i++) {
            if (i != casillaNoComprobar)
                hayN = cuadrados[(int) ((coordenada.getX() - 1) / 3)][(int) ((coordenada.getY() - 1) / 3)][i] == numero;
        }
        return hayN;
    }

    @Override
    public boolean estaCompleto() {
        boolean completo = true;

        Coordenada coordenada = new Coordenada(0, 0);

        for (int i = 1; i <= Sudoku.TAMANO_SUDOKU && completo; i++) {
            for (int j = 1; j <= TAMANO_SUDOKU && completo; j++) {
                coordenada.setXY(i, j);
                completo = !estaVacia(coordenada);
            }
        }
        return completo;
    }
    @Override
    public boolean esCorrecto() {
        boolean esCorrecto = true;
        Coordenada coordenadaAux = new Coordenada(0, 0);

        for (int i = 1; i <= Sudoku.TAMANO_SUDOKU && esCorrecto; i++) {
            for (int j = 1; j <= Sudoku.TAMANO_SUDOKU && esCorrecto; j++) {
                coordenadaAux.setXY(i, j);

                if (!estaVacia(coordenadaAux)) {
                    esCorrecto = !hayN(getN(coordenadaAux), coordenadaAux);
                }
            }
        }
        return esCorrecto;
    }

    @Override
    public boolean sudokuResuelto() {
        return estaCompleto() && esCorrecto();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Sudoku)) {
            return false;
        }
        Sudoku s = (Sudoku) o;
        boolean iguales = true;

        // Recorro las matrices
        for (int i = 0; i < Sudoku.TAMANO_SUDOKU && iguales; i++) {
            for (int j = 0; j < Sudoku.TAMANO_SUDOKU && iguales; j++) {
                iguales = matrizSudoku[i][j] == s.matrizSudoku[i][j];
            }
        }
        return iguales;
    }
}
