#ifndef SOLVER_HPP
#define SOLVER_HPP

#include "sudoku.hpp"

class Solver {
public:
    Solver(Sudoku &sudoku);
    bool solve();  // Intenta resolver el Sudoku usando backtracking

private:
    Sudoku &sudoku;

    bool solveBacktracking(int row, int col);  // Función recursiva de backtracking
};

#endif
