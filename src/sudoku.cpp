#include "sudoku.hpp"
#include <fstream>
#include <iostream>


Sudoku::Sudoku(const std::string &filename) {
    loadFromFile(filename);
}

void Sudoku::loadFromFile(const std::string &filename) {
    std::ifstream file(filename);
    if (!file.is_open()) {
        std::cerr << "No se pudo abrir el archivo." << std::endl;
        exit(1);
    }

    board.resize(9, std::vector<int>(9, 0));
    for (int i = 0; i < 9; ++i) {
        for (int j = 0; j < 9; ++j) {
            file >> board[i][j];
        }
    }
    file.close();
}

bool Sudoku::emptySquare(int row, int col){
    return board[row][col] == 0;
}

void Sudoku::set(int row, int col, int num){
    board[row][col] = num;
}

int Sudoku::get(int row, int col){
    return board[row][col];
}

bool Sudoku::checkRow(int row, int num) {
    for (int col = 0; col < 9; ++col) {
        if (board[row][col] == num) return false;
    }
    return true;
}

bool Sudoku::checkCol(int col, int num) {
    for (int row = 0; row < 9; ++row) {
        if (board[row][col] == num) return false;
    }
    return true;
}

bool Sudoku::checkBox(int row, int col, int num) {
    int startRow = row - row % 3;
    int startCol = col - col % 3;
    for (int i = startRow; i < startRow + 3; ++i) {
        for (int j = startCol; j < startCol + 3; ++j) {
            if (board[i][j] == num) return false;
        }
    }
    return true;
}

bool Sudoku::isValidNum(int row, int col, int num) {
    return checkRow(row, num) && checkCol(col, num) && checkBox(row, col, num);
}

void Sudoku::print() {
    const std::string horizontal = "+-------+-------+-------+";

    for (int i = 0; i < SUDOKU_SIZE; ++i) {
        if (i % 3 == 0) {
            std::cout << horizontal << std::endl;
        }

        for (int j = 0; j < SUDOKU_SIZE; ++j) {
            if (j % 3 == 0) std::cout << "| ";
            if (board[i][j] == 0)
                std::cout << ". "; // Para mostrar casillas vacías como puntos
            else
                std::cout << board[i][j] << " ";
        }
        std::cout << "|" << std::endl;
    }

    std::cout << horizontal << std::endl;
}
