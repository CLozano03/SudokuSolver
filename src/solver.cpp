#include "solver.hpp"

Solver::Solver(Sudoku &sudoku) : sudoku(sudoku) {}

bool Solver::solveBacktracking(int row, int col) {
    if (row == 9) return true; // Se ha llegado al final, sudoku resuelto
    if (col == 9) return solveBacktracking(row + 1, 0);  // Siguiente fila

    // Si ya hay un numero, nos saltamos la casilla
    if (!sudoku.emptySquare(row, col)) {
        return solveBacktracking(row, col + 1);
    }

    // Por cada número, en caso que se pueda lo ponemos y pasamos a la siguiente casilla
    for (int num = 1; num <= 9; ++num) {
        if (sudoku.isValidNum(row, col, num)) {
            sudoku.set(row, col, num);
            if (solveBacktracking(row, col + 1)) return true;
            sudoku.set(row, col, 0);
        }
    }
    return false;
}

bool Solver::solve() {
    return solveBacktracking(0, 0);
}

