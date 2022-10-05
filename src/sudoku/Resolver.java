package sudoku;

import utils.*;

/**
 * Implementación de los metodos de {@code ISudoku}
 * @author Cesar Lozano
 */
public class Resolver implements IResolver{
    public Sudoku sudoku;

    /*
     * Se trata una matriz de 9*9 que representa cada una de 
     * las casillas del sudoku. En cada una de ellas existe una
     * instancia de NumerosCandidatos para esa casilla. 
     * En caso de que la casilla este vacia, el array estara vacio.
     */
    public NumerosCandidatos[][] matrizCandidatos;

    public Resolver(Sudoku sudoku) {

        this.sudoku = sudoku;
        matrizCandidatos = new NumerosCandidatos[Sudoku.TAMANO_SUDOKU][Sudoku.TAMANO_SUDOKU];
        Coordenada coordenada = new Coordenada(0, 0);

        /**
             * ----------------------------------
             * Creacion de la matriz de candidatos
             * ----------------------------------
             */
        for (int i = 0; i < Sudoku.TAMANO_SUDOKU; i++) {
            for (int j = 0; j < Sudoku.TAMANO_SUDOKU; j++) {
                coordenada.setXY(i + 1, j + 1);

                if (sudoku.estaVacia(coordenada)) {
                    matrizCandidatos[i][j] = new NumerosCandidatos(sudoku, coordenada);
                } else {
                    matrizCandidatos[i][j] = new NumerosCandidatos();
                }
            }
        }

    }

    @Override
    public void asignarNumero(int numero, Coordenada coordenada) {

        Integer numeroQ = (Integer) numero;

        sudoku.setN(coordenada, numero);
        /**
         * ----------------------------------
         *  Eliminando candidatos asociados
         * ----------------------------------
         * 
         * Declaro variable auxiliar que me comprueba cada una de las coordenadas de
         * la cruz y del cuadrado que forma la coordenada dada.
         */

        Coordenada coordenadaAux = new Coordenada(1, 1);
        
        //Primero elimino los candidatos de la propia coordenada
        while(matrizCandidatos[coordenada.getX() - 1][coordenada.getY() - 1].size() > 0) {
            matrizCandidatos[coordenada.getX() - 1][coordenada.getY() - 1].remove(0);
        }

        //Cruz que forma la coordenada
        for (int i = 1; i < Sudoku.TAMANO_SUDOKU+1; i++) {

            coordenadaAux.setXY(i, coordenada.getY());

            matrizCandidatos[coordenadaAux.getX()-1][coordenadaAux.getY()-1].remove(numeroQ);

            coordenadaAux.setXY(coordenada.getX(), i);

            matrizCandidatos[coordenadaAux.getX()-1][coordenadaAux.getY()-1].remove(numeroQ);
        }

        //Matriz del cuadrado 3*3

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                coordenadaAux.setX(3 * (int) ((coordenada.getX() - 1) / 3) + 1 + i);
                coordenadaAux.setY(3 * (int) ((coordenada.getY() - 1) / 3) + 1 + j);
                if (sudoku.estaVacia(coordenadaAux)) {
                    matrizCandidatos[coordenadaAux.getX() - 1][coordenadaAux.getY() - 1].remove(numeroQ);
                }
            }
        }
    }
    
    @Override
    public void comprobarFilas(){
        for(int i = 1; i <= Sudoku.TAMANO_SUDOKU; i++){
            comprobarFilaAux(i, true);
            comprobarFilaAux(i, false);
        }
    }

    @Override
    public void comprobarFilaAux(int nFila, boolean filaColumna){
        /**
         * Contador que se resetea cada vez que comprueba un numero. Simboliza el 
         * numero de casillas en las que puede ir un numero, en caso en que solo pueda ir en uno,
         * lo asigna el numero en ese sitio.
         */
        int contador = 0;
        Coordenada coordenadaAux = new Coordenada(0, 0);

        if(filaColumna){
            //Comprobando en una fila
            coordenadaAux.setX(nFila);

            /**
             * Para cada numero del sudoku, en caso de que no este en nFila
             * voy recorriendo esa fila y donde haya un hueco, miro si ese numero esta 
             * en el cuadrado o en la columna. En caso negativo, sumo uno al contador
             */
            for(int numero : Sudoku.NUMEROS_SUDOKU){
                if(!sudoku.hayNFila(numero, nFila, filaColumna)){
                    for(int i = 1; i <= Sudoku.TAMANO_SUDOKU && contador < 2; i++){
                        coordenadaAux.setY(i);
                        if(sudoku.estaVacia(coordenadaAux)){
                            if(!(sudoku.hayN(numero, coordenadaAux))){
                                contador++;
                            }
                        }
                    }
                }

                if(contador == 1){
                    boolean done = false;
                    for(int i = 0; i < Sudoku.TAMANO_SUDOKU && !done; i++){
                        if(!(matrizCandidatos[nFila - 1][i].indexOf(numero) == -1)){
                            asignarNumero(numero, new Coordenada(nFila, i+1));
                            done = true;
                        }
                    }
                }
                contador = 0; //Reseteamos contador para el proximo numero a comprobar
            }

        } else{

        }
    }
    
    @Override
    public void comprobarCandidatos(){
        Coordenada coordenadaAux = new Coordenada(0, 0);
        
        for(int i  = 1; i <= Sudoku.TAMANO_SUDOKU; i++){
            for(int j = 1; j <= Sudoku.TAMANO_SUDOKU; j++){
                coordenadaAux.setXY(i, j);
                if(sudoku.estaVacia(coordenadaAux)){
                    comprobarCandidatosAux(coordenadaAux);
                }
            }
        }
    }

    @Override
    public void comprobarCandidatosAux(Coordenada coordenada) {
        
        NumerosCandidatos casilla = matrizCandidatos[coordenada.getX() - 1][coordenada.getY() - 1];

        if (casilla.size() == 1){
            Integer numeroAQuitar = (Integer) (casilla.get(0));
            asignarNumero(numeroAQuitar, coordenada);
            }
        }

    public void resolver() {

        /*
         * while(!sudoku.estaResuelto()){
         * 
         * }
         */

        for (int i = 0; i < 10; i++) {
            comprobarCandidatos();
            comprobarFilas();
        }
        /* for (int i = 0; i < 3; i++){
            comprobarFilas();
        } */
        

    }


    public void imprimirMatriz() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((j + 1) % 3 == 0 && j != 8) {
                    System.out.print(matrizCandidatos[i][j].size() + "|");
                } else {
                    System.out.print(matrizCandidatos[i][j].size() + " ");
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

}
