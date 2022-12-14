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

        Coordenada coordenadaAux = new Coordenada(0,0);
        for(int i = 1; i <= Sudoku.TAMANO_SUDOKU; i++){
            coordenadaAux.setX(i);
            comprobarFilaAux(coordenadaAux);
        }
    }

    @Override
    public void comprobarFilaAux(Coordenada coordenada){
        /**
         * Contador que se resetea cada vez que comprueba un numero. Simboliza el 
         * numero de casillas en las que puede ir un numero, en caso en que solo pueda ir en uno,
         * lo asigna el numero en ese sitio.
         */
        int contador = 0;
        Coordenada coordenadaAux = new Coordenada(0, 0);

            //Comprobando en una fila
            coordenadaAux.setX(coordenada.getX());

            /**
             * Para cada numero del sudoku, en caso de que no este en nFila
             * voy recorriendo esa fila y donde haya un hueco, miro si ese numero esta 
             * en el cuadrado o en la columna. En caso negativo, sumo uno al contador
             */
            for(int numero : Sudoku.NUMEROS_SUDOKU){
                if(!sudoku.hayNFila(numero, coordenada)){
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
                        if(!(matrizCandidatos[coordenada.getX() - 1][i].indexOf(numero) == -1)){
                            asignarNumero(numero, new Coordenada(coordenada.getX(), i+1));
                            done = true;
                        }
                    }
                }
                contador = 0; //Reseteamos contador para el proximo numero a comprobar
            }
    }
    
    public void comprobarCuadrados(){
        Coordenada aux = new Coordenada(0, 0);
        Coordenada posibleAsignacion = new Coordenada(0, 0);
        int contador = 0;


        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                //Aqui estoy recorriendo cada uno de los cuadrados 3*3 del sudoku
                contador = 0;

                aux.setXY(3*i +1, 3*j+1);
                for(int numeroComprobando: Sudoku.NUMEROS_SUDOKU){
                    if(!sudoku.hayNCuadrado(numeroComprobando, aux)){
                        contador = 0;
                        //En caso de que ese numero no este en el cuadrado, comprobamos si puede ir en algun sitio
                        for(int k = 0; k < 3; k++){
                            aux.setX(3*i +1 + k);
                            for(int l = 0; l < 3; l++){
                                aux.setY(3*j + 1 + l);

                                /*
                                 * Si pasa estas dos ultimas condiciones, sabemos que:
                                 * 
                                 *  -La casilla esta vacia
                                 *  -El numeroComprobando puede ir en la casilla
                                 * 
                                 * Si al terminar el recorrido del cuadrado
                                 * el numero solo puede ir en una casilla,
                                 * se le asigna en esa coordenada
                                 */
                                if(!sudoku.hayN(numeroComprobando, aux) && sudoku.estaVacia(aux)){
                                    posibleAsignacion = new Coordenada(aux.getX(), aux.getY());
                                    contador++;
                                }
                            }
                        }     
                        if(contador == 1){
                            asignarNumero(numeroComprobando, posibleAsignacion);
                        }
                    }
                }
                //Paso a comprobar siguiente cuadrado
                
            }
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
        /* while(!sudoku.sudokuResuelto()){
            comprobarCandidatos();
            comprobarFilas();
            comprobarCuadrados();  
        } */

        for(int i = 0; i < 20; i++){
            //comprobarFilas();
            comprobarCandidatos();
            comprobarCuadrados(); 
        }
    }

    //Este metodo imprime una matriz con la longitud de la lista numeros candidatos, util para testear esta clase
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