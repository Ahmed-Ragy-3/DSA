import java.util.*;
import java.awt.Point;

public class NourFile {

static void bubble_sort(ArrayList<Point> Centers) {
    
    for(int i = 0; i < Centers.size() ; i++) {
        for(int j = 0; j < Centers.size()-1; j++) {
            
            if(Centers.get(j).getX() > Centers.get(j+1).getX()){

                Point temp = Centers.get(j) ;
                Centers.set(j , Centers.get(j+1)) ;
                Centers.set(j+1 , temp) ;

            }else if(Centers.get(j).getX() == Centers.get(j+1).getX() && 
                    Centers.get(j).getY() > Centers.get(j+1).getY()) {

                Point temp = Centers.get(j) ;
                Centers.set(j , Centers.get(j+1)) ;
                Centers.set(j+1 , temp) ;

            }

        }

    }
}

public static void main(String[] args) {

    ArrayList <Point> Centers = new ArrayList<>() ;
    Centers.add(new Point(1 , 10)) ;
    Centers.add(new Point(1 , 8)) ;
    Centers.add(new Point(1 , 9)) ;
    Centers.add(new Point(1 , 6)) ;
    Centers.add(new Point(5 , 4)) ;
    Centers.add(new Point(5 , 2)) ;
    Centers.add(new Point(5 , 0)) ;
    Centers.add(new Point(5 , 75)) ;
    Centers.add(new Point(5 , 17)) ;
    
    bubble_sort(Centers) ;
    System.out.println("ArrayList of Points:") ;

    for (Point Center : Centers) {
        System.out.println("X: " + Center.x + ", Y: " + Center.y) ;
    }
}

}
