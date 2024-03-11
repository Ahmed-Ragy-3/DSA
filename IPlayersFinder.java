import java.awt.Point ;
import java.util.* ;

public class IPlayersFinder {
public class Main_class {

   ArrayList <Point> Centers = new ArrayList<>() ;

   int numberofPixels = 0 ;
   int x_min = matrix[0].length ;
   int x_max , y_max = 0 ;
   int y_min = matrix.length ;

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

   static byte[][] contructMatix(String[] photo, int team) {

      byte[][] matrix = new byte[photo.length][photo[0].length()] ;
      
      for(int i = 0; i < photo.length; i++) {
         for(int j = 0; j < photo[i].length(); j++) {
   
            if(Character.compare(photo[i].charAt(j), (char)(team + '0')) == 0) {
                  matrix[i][j] = 1;
            }else {
                  matrix[i][j] = 0;
            }
         }
      }
      return matrix;
   }
   
   public ArrayList<> findPlayers(String[] photo, int team, int threshold){
      contructMatix(photo, team) ;
      
   }

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

public static void main(String[] args) {



   
   Scanner sc = new Scanner(System.in);
   int n = sc.nextInt();
   String[] photo = new String[n];

   for(int i = 0; i < n; i++) {
      photo[i] = sc.next();
   }

   int team = sc.nextInt();
   byte[][] matrix = contructMatix(photo, team) ;

   findPlayers(String[] photo, int team, int threshold) ;

   for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[i].length; j++) {
         System.out.print(matrix[i][j] + " ") ;
      }
      System.out.println();
   }
   

}

}