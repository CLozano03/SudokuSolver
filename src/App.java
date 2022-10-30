import sudoku.*;
import java.util.Scanner;

public class App {
    
    public static void resolverSudoku(Sudoku sudoku){
        Resolver resolver = new Resolver(sudoku);
        
        resolver.resolver();
    }

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Introduce el nombre del fichero con formato (files/nombre_fichero): ");
        String sudokuFile = sc.nextLine();
        sc.close();
        
        Sudoku sudoku = new Sudoku(sudokuFile);

        System.out.println("Tu sudoku: \n");
        System.out.print(sudoku.toString());
    
        resolverSudoku(sudoku);
        System.out.println("Tu sudoku resuelto: \n");
        System.out.print(sudoku.toString());     
    }
}