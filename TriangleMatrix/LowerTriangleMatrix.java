package TriangleMatrix;


public class LowerTriangleMatrix<T> {
    T[] array;
    int rows;
    
    @SuppressWarnings("unchecked")
    public LowerTriangleMatrix(int rows, int cols) {
        this.rows = rows;
        
        this.array = (T[]) new Object[((rows) * (rows + 1)) / 2];
    }

    public void set(int row, int col, T value) {
        if (col > row || row < 0 || col < 0 || row >= rows || col >= rows) {
            throw new IllegalArgumentException("Out of bounds! row = " + row + ",col = " + col);
        }
        int index = row * (row + 1) / 2 + col;
        this.array[index] = value;
    }
    
    public T get(int row, int col) {
        if (col > row || row < 0 || col < 0 || row >= rows || col >= rows) {
            throw new IllegalArgumentException("Out of bounds! row = " + row + ",col = " + col);
        }
        int index = row * (row + 1) / 2 + col;
        return this.array[index];
    }

    public void print() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j <= i; j++) {
                System.out.print(get(i, j) + " ");
            }
            System.out.println();
        }
    }

    int rows()
    {
        return this.rows;
    }
    
    

    public static void main(String[] args) {
        LowerTriangleMatrix<Integer> matrix = new LowerTriangleMatrix<>(3, 3);
        matrix.set(0, 0, 1);
        matrix.set(1, 0, 4);
        matrix.set(1, 1, 5);
        matrix.set(2, 0, 7);
        matrix.set(2, 1, 8);
        matrix.set(2, 2, 9);
        matrix.print();
    }
}
