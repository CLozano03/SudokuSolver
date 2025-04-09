#ifndef SUDOKU_HPP
#define SUDOKU_HPP

#include <vector>
#include <string>

class Sudoku {
public:

    static constexpr int SUDOKU_SIZE = 9;

    Sudoku(const std::string &filename);  // Constructor que lee desde un archivo
    bool isValidNum(int row, int col, int num);
    bool emptySquare(int row, int  col);
    void set(int row, int col,  int num);
    int get(int row, int col);
    void print();

private:
    std::vector<std::vector<int>> board;

    bool checkRow(int row, int num);
    bool checkCol(int col, int num);
    bool checkBox(int row, int col, int num);
    void loadFromFile(const std::string &filename);
};

#endif


