import sudoku.*;
import java.util.Scanner;

public class App {
    
    public static void resolverSudoku(Sudoku sudoku){
        Resolver resolver = new Resolver(sudoku);
        
        resolver.resolver();
        
        sudoku.imprimirSudoku();
        
        System.out.println("\n");
    }

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Introduce el nombre del fichero (files/nombre_fichero): ");
        String sudokuFile = sc.nextLine();
        sc.close();
        
        Sudoku sudoku = new Sudoku(sudokuFile);

        resolverSudoku(sudoku);
    }
}