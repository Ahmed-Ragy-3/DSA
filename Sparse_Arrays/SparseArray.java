package Sparse_Arrays;

import java.util.ArrayList;
import java.util.Arrays;

class Number {
    static private int numOfElements = 0;
    private int xIndex;
    private int yIndex;
    private int value;

    public Number(int xIndex, int yIndex, int value) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.value = value;

        numOfElements++;
    }
    
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setXIndex(int xIndex) {
        this.xIndex = xIndex;
    }

    public void setYIndex(int y) {
        this.yIndex = y;
    }

    public int getXIndex() {
        return this.xIndex;
    }

    public int getYIndex() {
        return this.yIndex;
    }

    public void setCoordinates(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public static int getNumberOfElements() {
        return numOfElements;
    }
}

public class SparseArray {
    private int numberOfCols;
    private int numberOfRows;
    private int[] rowIndex;
    private ArrayList<Number> values;

    //constructor
    public SparseArray(int[][] matrix) { // true
        values = new ArrayList<Number>();
        rowIndex = new int[matrix.length];
        this.numberOfCols = matrix[0].length;
        this.numberOfRows = matrix.length;

        boolean isFirst = true;
        boolean isEmpty;
        for(int i = 0; i < matrix.length; i++)
        {
            isFirst = true;
            isEmpty = true;
            for(int j = 0; j < matrix[0].length; j++)
            {
                if(matrix[i][j] != 0)
                {
                    isEmpty = false;
                    if(isFirst)
                    {
                        rowIndex[i] = Number.getNumberOfElements();
                        isFirst = false;
                    }
                    values.add(new Number(i, j, matrix[i][j]));
                }
            }
            if(isEmpty)
            {
                rowIndex[i] = -1;
            }
        }
    }
    //end constructor

    //checkers
    public boolean isRowEmpty(int xIndex)
    {
        if(rowIndex[xIndex] == -1)
        {
            return true;
        } 
        return false;
    }
    //end checkers

    //getters
    public int getNumOfRows()
    {
        return numberOfRows;
    }
    
    public int getNumOfCols()
    {
        return numberOfCols;
    }

    public ArrayList<Number> getValuesArray() {

        return values;
    }
    public int getNumOfEmptyRows(){
        int numOfEmptyRows = 0;
        for(int i = 0; i < rowIndex.length; i++)
        {
            if(rowIndex[i] == -1)
            {
                numOfEmptyRows++;
            }
        }
        return numOfEmptyRows;
    }
    public int[] getRowIndexArray() {
        return rowIndex;
    }
    //end getters

    //printing
    public void printRowIndexArray() {
        System.out.println(Arrays.toString(rowIndex));
    }

    public String toString(){
        String result =  "RowArray: " + Arrays.toString(rowIndex) + "\n";
        for(int i = 0; i < 24; i++)
        {
            result += "-";
        }
        result += "\n";
        for(int i = 0; i < values.size(); i++)
        {
            result += i + ": " +  "x: " + values.get(i).getXIndex() + ", " + "y: " + values.get(i).getYIndex()+ ", " + "value: " + values.get(i).getValue() + "\n";
        }
        for(int i = 0; i < 24; i++)
        {
            result += "-";
        }
        result += "\n";
        return result;
    }

    public void printMatrix() {
        for(int i = 0; i < this.numberOfRows; i++)
        {
            for(int j = 0; j < this.numberOfCols; j++)
            {
                System.out.print(this.getValue(i, j) + "\t");
            }
            System.out.println();
        }
    }

    public void printAllContents() {
        printLine(50);
        System.out.println("Matrix: \n");
        this.printMatrix();
        printLine(26);
        System.out.println("Row Index Array: ");
        this.printRowIndexArray();
        printLine(26);
        System.out.println("Values Array: ");
        this.printValuesArray();
        printLine(50);
    }

    private void printLine(int length)
    {
        for(int i = 0; i < length; i++)
        {
            System.out.print("-");
        }
        System.out.println();
    }

    public void printValuesArray() {
        for(int i = 0; i < values.size(); i++)
        {
            System.out.print(i + ": " +  "x: " + values.get(i).getXIndex() + ", " + "y: " + values.get(i).getYIndex()+ ", " + "value: " + values.get(i).getValue());
            System.out.println();
        }
    }
    //end printing

    //setting 
    public void setValue(int xIndex, int yIndex, int value) { //true
        if(xIndex >= numberOfRows || xIndex < 0 || yIndex < 0 || yIndex >= numberOfRows)
        {
            throw new IllegalArgumentException("Out of bounds!");
        }
        //if row is empty (true)

        if(rowIndex[xIndex] == -1)
        {
            int index = xIndex ;
            while(index < rowIndex.length && rowIndex[index] == -1){
                index++;
            }
            if(index != rowIndex.length)
            {
                rowIndex[xIndex] = rowIndex[index];
                values.add(rowIndex[xIndex], new Number(xIndex, yIndex, value));
                for(int i = index; i < rowIndex.length; i++)
                {
                    if(rowIndex[i] != -1)
                    {
                        rowIndex[i]++;
                    }
                }
                return;
            }
            else
            {
                rowIndex[xIndex] = Number.getNumberOfElements();
                values.add(new Number(xIndex, yIndex, value));
                return;
            }
        }


        int upperlimit;
        try {
            int index = xIndex + 1;
            while(index < rowIndex.length && rowIndex[index] == -1){
                index++;
            }
            upperlimit = rowIndex[index];
        }
        catch(Exception e) {
            upperlimit = values.size();
        }
        
        //case if it exists
        int newIndex = 0;
        for(int i = rowIndex[xIndex]; i < upperlimit; i++)
        {
            if(values.get(i).getYIndex() == yIndex)
            {
                values.get(i).setValue(value);
                return;
            }
            else if(values.get(i).getYIndex() < yIndex)
            {
                newIndex++;
            }
            else
            {
                break;
            }
        }

        //case if it doesn't exist

        values.add(newIndex + rowIndex[xIndex], new Number(xIndex, yIndex, value));
        for(int i = xIndex + 1; i < rowIndex.length; i++)
        {
            if(rowIndex[i] != -1)
            {
                rowIndex[i]++;
            }
        }
    }
    //end setting

    //getting
    public int getValue(int xIndex, int yIndex) { //true
        if(xIndex >= numberOfRows || xIndex < 0 || yIndex < 0 || yIndex >= numberOfRows)
        {
            throw new IllegalArgumentException("Out of bounds!");
        }
        if(rowIndex[xIndex] == -1)
        {
            return 0;
        }
        int upperlimitRowIndex = xIndex;
        int upperlimit;
        if(xIndex == rowIndex.length - 1)
        {
            upperlimit = values.size();
        }
        else{
            while(upperlimitRowIndex < rowIndex.length)
            {
                upperlimitRowIndex++;
                if(upperlimitRowIndex >= rowIndex.length)
                {
                    break;
                }
                if(rowIndex[upperlimitRowIndex] != -1)
                {
                    break;
                }
            }
            if(upperlimitRowIndex == rowIndex.length)
            {
                upperlimit = Number.getNumberOfElements();
            }
            else{
                upperlimit = rowIndex[upperlimitRowIndex];
            }
            
        }
        
        for(int i = rowIndex[xIndex]; i < upperlimit; i++)
        {
            if(values.get(i).getYIndex() == yIndex)
            {
                return values.get(i).getValue();
            }
        }

        return 0;
    }
    //end getting
    
//////////////////////////////////////////////////////////////
    public static void main(String[] args){
        int[][] matrix = {
            {0, 0, 0}
            ,{0, 0, 0}
            ,{0, 0, 0}
        };

        //assaigning the sparse matrix
        SparseArray sparseArray = new SparseArray(matrix);

        //seting values
        int counter = 0;
        for(int i = matrix.length - 1; i >= 0; i--)
        {
            for(int j = matrix[i].length -1; j >= 0; j--)
            {
                sparseArray.setValue(i, j, counter);
                counter++;
                sparseArray.printAllContents();
            }
        }
    }
///////////////////////////////////////////////////////////////
}
