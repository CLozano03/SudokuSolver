package sudoku;

import stdlib.*;
import utils.*;

/**
 * Implementación de {@code ISudoku}
 * @author Cesar Lozano
 */
public class Sudoku implements ISudoku {

    //Matriz principal del sudoku
    public int[][] matrizSudoku;

    //Declaracion de constantes utiles.
    public static final int[] NUMEROS_SUDOKU = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    public static final int TAMANO_SUDOKU = 9;

    /**
     * Array tridimensional con los numeros de los 9 cuadrados
     * en un array de 9 elementos
     */
    public int[][][] cuadrados = new int[3][3][9];

    /**
     * Pone en la {@value matrizSudoku} cada uno de los 
     * numeros leidos en el archivo "filename"
     * 
     * Por otro lado, pone cada uno de los numeros del sudoku 
     * en la matriz tridimensional de nombre: cuadrados
     * @param filename
     */
    public Sudoku(String filename) {
        In sc = new In(filename);
        matrizSudoku = new int[9][9];

        //Matriz del sudoku
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matrizSudoku[i][j] = sc.readInt();
            }
        }

        //Creacion de la matriz de cuadrdados
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 9; k++) {
                    cuadrados[i][j][k] = matrizSudoku[i * 3 + (int) k / 3][j * 3 + k % 3];
                    /*
                     * 
                     */
                }
            }
        }

    }

    @Override
    public void imprimirSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((j + 1) % 3 == 0 && j != 8) {
                    System.out.print(matrizSudoku[i][j] + "|");
                } else {
                    System.out.print(matrizSudoku[i][j] + " ");
                }
            }
            if ((i + 1) % 3 == 0 && i != 8) {
                System.out.println();
                System.out.println("------------------");

            } else {
                System.out.println();
            }
        }
        System.out.println();
    }

    @Override
    public boolean estaVacia(Coordenada coordenada) {
        return matrizSudoku[coordenada.getX() - 1][coordenada.getY() - 1] == 0;
    }
    
    @Override
    public void setN(Coordenada coordenada, int n) {
        matrizSudoku[coordenada.getX() - 1][coordenada.getY() - 1] = n;
    }

    @Override
    public boolean hayN(int numero, Coordenada coordenada) {
        return hayNFila(numero, coordenada.getX(), true) || hayNFila(numero, coordenada.getY(), false)
                || hayNCuadrado(numero, coordenada);
    }

    @Override
    public boolean hayNFila(int numero, int numeroFila, boolean filaColumna) {
        boolean hayN = false;
        if (filaColumna) {
            for (int i = 0; i < 9 && !hayN; i++) {
                hayN = (matrizSudoku[numeroFila - 1][i] == numero);
            }
            return hayN;
        } else {
            for (int i = 0; i < 9 && !hayN; i++) {
                hayN = (matrizSudoku[i][numeroFila - 1] == numero);
            }
            return hayN;
        }
    }

    public boolean hayNCuadrado(int numero, Coordenada coordenada) {
        boolean hayN = false;
        for (int i = 0; i < 9 && !hayN; i++) {
            hayN = cuadrados[(int) ((coordenada.getX() - 1) / 3)][(int) ((coordenada.getY() - 1) / 3)][i] == numero;
        }
        return hayN;
    }

    @Override
    public boolean estaResuelto() {
        boolean estaResuelto = true;

        Coordenada coordenada = new Coordenada(0, 0);

        for (int i = 0; i < Sudoku.TAMANO_SUDOKU && estaResuelto; i++) {
            for (int j = 0; j < TAMANO_SUDOKU && estaResuelto; j++) {
                coordenada.setXY(i + 1, j + 1);
                estaResuelto = estaVacia(coordenada);
            }
        }

        return estaResuelto;
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
        
        //Recorro las matrices
        for(int i = 0; i < Sudoku.TAMANO_SUDOKU && iguales; i++) {
            for(int j = 0; j < Sudoku.TAMANO_SUDOKU && iguales; j++) {
                iguales = matrizSudoku[i][j] == s.matrizSudoku[i][j];
            }
        }
        return iguales;
    }
}