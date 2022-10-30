package utils;

public class Coordenada {
    
    private int x;
    private int y;
    /**
     * OJO: Las coordenadas del sudoku iran del 1 al 9
     * y no del 0 al 8, como seria en una matriz por defecto
     * 
     * @param x : filas
     * @param y : columnas
     */

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
      return this.x;
    }
  
    public void setX(int x) {
      this.x = x;
    }
  
    public int getY() {
      return this.y;
    }
  
    public void setY(int y) {
      this.y = y;
    }

    public void setXY(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public String toString() {
      return "(" + x + ", " + y + ")";
    }

    public boolean equals(Object o){
      if(!(o instanceof Coordenada)){
        return false;
      } else {
        Coordenada other = (Coordenada) o;
        return this.x == other.getX() && this.y == other.getY();
      }
    }

}
