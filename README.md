# SudokuSolver

Este proyecto implementa la manera que se me ha ocurrrido en la que se puede computar la resolución de un sudoku. Por el momento, el programa implementa únicamente los métodos básicos de resolución. 
Toma un sudoku de un fichero de texto y lo imprime por consola tras resolverlo.

***
### Clases principales
- **Sudoku.java** representa un sudoku e incluye los métodos que aportan información sobre este.
- **NumerosCandidatos.java** hereda de ArrayList e incluye los números que pueden ir en una casilla.
- **Resolver.java** donde se implementan los métodos de resolución.

### Posibles errores
- *Bugs en comprobarFilas():* puede introducir algún número en casillas incorrectas. 
- *No resuelve sudokus dificiles*

### Futuras mejoras
- Resolver los errores mencionados
- Agregar métodos de resolución avanzados.
- Agregar una GUI al proyecto
