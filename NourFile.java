import java.util.*;
import java.awt.Point;

public interface IPlayersFinder {
    /**
    * Search for players locations at the given photo
    * @param photo
    * Two dimension array of photo contents
    * Will contain between 1 and 50 elements, inclusive
    * @param team
    * Identifier of the team
    * @param threshold
    * Minimum area for an element
    * Will be between 1 and 10000, inclusive
    * @return
    * Array of players locations of the given team
    */
    java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}

/* *
//import IPlayersFinder ;
public class NourFile {
int numberofPixels = 0 ;
int x_min = matrix[0].length ;
int x_max , y_max = 0 ;
int y_min = matrix.length ;

public void go_find_indexes(byte matrix[][]) {

    for(int i=0 ; i<matrix.length ; i++) {
        for(int j=0 ; j<matrix[i].length ; j++) {
            //matrix[i][j] = 0 ;
            if(matrix[i][j] == 1) {
                find_indexes(matrix , i , j) ; // hntl3 mn el chain
                if(numberofPixels * 4 >= threshold) {

                    Point center = new Point(x_max + x_min + 1 , y_max + y_min + 1) ;
                    Centers.add(center) ;
                }
                numberofPixels = 0 ;
            }

        }
    }
}

public void find_indexes(byte matrix[][] , int x , int y) {
    if(x<0 || y<0 || x >= matrix[0].length || y >= matrix[0].length) {
        return ;
    }
    
    if(matrix[x][y] == 0) {return ;}

    else{
        numberofPixels++ ;
        if(x < x_min) {
            x_min = x ;
        }
        if(x > x_max) {
            x_max = x ;
        }
        if(y < y_min) {
            y_min = y ;
        }
        if(y > y_max) {
            y_max = y ;
        }

        matrix[x][y] = 0 ;

        find_indexes(matrix , x-1 , y) ;
        find_indexes(matrix , x+1 , y) ;
        find_indexes(matrix , x , y-1) ;
        find_indexes(matrix , x , y+1) ;
    }

}

}
*/
