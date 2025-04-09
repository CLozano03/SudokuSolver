#include <iostream>
#include "../include/sudoku.hpp"
#include "../include/solver.hpp"

int main(int argc, char* argv[]) {
    if (argc < 2) {
        std::cerr << "Error: se debe de pasar el fichero con el sudoku como  argumento" << std::endl;
        return 1;
    }

    std::string filename = argv[1];
    Sudoku sudoku(filename);

    std::cout << std::endl;
    std::cout << "Sudoku a resolver:" << std::endl;
    sudoku.print();

    std::cout << "---------------------------" << std::endl;
    Solver solver(sudoku);
    if (solver.solve()) {
        std::cout << "Sudoku resuelto:" << std::endl;
        sudoku.print();
    } else {
        std::cout << "No se pudo resolver el Sudoku." << std::endl;
    }

    return 0;
}

