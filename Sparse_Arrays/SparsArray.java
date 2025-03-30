package Sparse_Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class SparsArray {
    static int numberOfSparsArrays = 0;

    ArrayList<Integer> values;
    boolean[][] bitmab;

    public SparsArray(int[][] values) {
        // locating the arrays int the memory
        this.values = new ArrayList<>();
        this.bitmab = new boolean[values.length][];
        // building the bitmab array
        for(int i = 0; i < values.length; i++)
        {
            this.bitmab[i] = new boolean[values[i].length];
        }

        // putting the values in the arrays
        for(int i = 0; i < values.length; i++)
        {
            for(int j = 0; j < values[i].length; j++)
            {
                if(values[i][j] == 0){
                    this.bitmab[i][j] = false;
                }
                else{
                    this.bitmab[i][j] = true;
                    this.values.add(values[i][j]);
                }
            }
        }
        numberOfSparsArrays++;
    }

    public int getValue(int xIndex, int yIndex)
    {
        if(this.bitmab[xIndex][yIndex] == false){
            return 0;
        }
        else{
            return this.values.get(this.getNumberOfValuesBefore(xIndex, yIndex));
        }
    }

    public void setValue(int xIndex, int yIndex, int value)
    {  
        if(this.bitmab[xIndex][yIndex] == false){
            if(value == 0){
                this.bitmab[xIndex][yIndex] = false;
                this.values.remove(this.getNumberOfValuesBefore(xIndex, yIndex));
            }
            else{
                this.bitmab[xIndex][yIndex] = true;
                this.values.add(this.getNumberOfValuesBefore(xIndex, yIndex), Integer.valueOf(value));;
            }
        }
        else{
            if(value == 0){
                this.bitmab[xIndex][yIndex] = false;
                this.values.remove(this.getNumberOfValuesBefore(xIndex, yIndex));
            }
            else{
                int index = this.getNumberOfValuesBefore(xIndex, yIndex);
                this.bitmab[xIndex][yIndex] = true;
                this.values.set(index, Integer.valueOf(value));
            }
        }
        
    }

    public int getNumberOfValuesBefore(int xIndex, int yIndex)
    {
        int countOfOnes = 0;
        boolean end = false;
        for(int i = 0; i < this.bitmab.length; i++){
            for(int j = 0; j < this.bitmab[i].length; j++){
                if(this.bitmab[i][j])
                {
                    if(i == xIndex && j == yIndex){
                        end = true;
                        break;
                    }
                    countOfOnes++;
                }
            }
            if(end){
                break;
            }
        }
        return countOfOnes;
    }

    public boolean[][] getBitmabMatrix() {
        return bitmab;
    }
    
    public ArrayList<Integer> getValuesArray() {
        return values;
    }

    public static int getNumberOfSparsArrays() {
        return numberOfSparsArrays;
    }

    public void printBitMap()
    {
        System.out.println("BitMap Matrix: ");
        for(int i = 0; i < bitmab.length; i++)
        {
            for(int j = 0; j < bitmab[i].length; j++)
            {
                System.out.print(bitmab[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void printValuesArray()
    {
        System.out.println("Values Array:" + values.toString());
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = input.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = input.nextInt();

        int[][] values = new int[rows][columns];

        for(int i = 0; i < values.length; i++)
        {
            for(int j = 0; j < values[i].length; j++)
            {
                values[i][j] = input.nextInt();
            }
        }

        SparsArray sparsArray = new SparsArray(values);
        sparsArray.printBitMap();
        sparsArray.printValuesArray();

        System.out.print("Enter the index of the row: ");
        int xIndex = input.nextInt();
        System.out.print("Enter the index of the column: ");
        int yIndex = input.nextInt();
        System.out.print("Enter the value: "); 
        int value = input.nextInt();
        sparsArray.setValue(xIndex, yIndex, value);


        sparsArray.printBitMap();
        sparsArray.printValuesArray();

        System.out.println("value: " + sparsArray.getValue(xIndex, yIndex));
        input.close();
        
    }
}
