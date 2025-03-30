package Labs.Lab3;

import java.awt.Point;
import java.util.*;

public class IPlayersFinder {
   static int threshold = 0;
   static int numberofPixels = 0;
   static byte[][] matrix;
   static ArrayList<Point> Centers = new ArrayList<>();
   static int x_min;
   static int x_max, y_max = 0;
   static int y_min;

   static void bubble_sort() {
      for (int i = 0; i < Centers.size(); i++) {
         for (int j = 0; j < Centers.size() - 1; j++) {
            if (Centers.get(j).getX() > Centers.get(j + 1).getX()) {

               Point temp = Centers.get(j);
               Centers.set(j, Centers.get(j + 1));
               Centers.set(j + 1, temp);

            } else if (Centers.get(j).getX() == Centers.get(j + 1).getX() &&
                  Centers.get(j).getY() > Centers.get(j + 1).getY()) {

               Point temp = Centers.get(j);
               Centers.set(j, Centers.get(j + 1));
               Centers.set(j + 1, temp);
            }
         }
      }
   }

   static void contructMatix(String[] photo, int team) {
      matrix = new byte[photo.length][photo[0].length()];
      x_min = matrix[0].length;
      y_min = matrix.length;
      for (int i = 0; i < photo.length; i++) {
         for (int j = 0; j < photo[i].length(); j++) {

            if (Character.compare(photo[i].charAt(j), (char) (team + '0')) == 0) {
               matrix[i][j] = 1;
            } else {
               matrix[i][j] = 0;
            }
         }
      }
      return;
   }

   static public void go_find_indexes() {
      
      for(int i=0 ; i<matrix.length ; i++) {
         for(int j=0 ; j<matrix[i].length ; j++) {
            if(matrix[i][j] == 1) { // new chain
               x_min = matrix[0].length;
               y_min = matrix.length ;
               x_max = 0;
               y_max = 0;
               
               find_indexes(i , j) ; // hntl3 mn el chain
               if(numberofPixels * 4 >= threshold) {
                  Centers.add(new Point(y_max + y_min + 1 , x_max + x_min + 1)) ;
               }

            }
            numberofPixels = 0;
         }
      }
   }

   static public void find_indexes(int x, int y) {

      if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) {
         return;
      }

      if (matrix[x][y] == 0) {
         return;
      }

      else {
         numberofPixels++;
         if (x < x_min) {
            x_min = x;
         }
         if (x > x_max) {
            x_max = x;
         }
         if (y < y_min) {
            y_min = y;
         }
         if (y > y_max) {
            y_max = y;
         }

         matrix[x][y] = 0;

         find_indexes(x - 1, y);
         find_indexes(x + 1, y);
         find_indexes(x, y - 1);
         find_indexes(x, y + 1);
      }

   }

   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      int n = 0;
      String str = input.next();
      //String x = input.next();
      if (str.length() == 3) {
         n += Character.digit(str.charAt(0), 10) * 10 + Character.digit(str.charAt(1), 10);
      } else if (str.length() == 4) {
         n += Character.digit(str.charAt(0), 10) * 100 + Character.digit(str.charAt(1), 10) * 10
               + Character.digit(str.charAt(2), 10);
      } else if (str.length() == 5) {
         n += Character.digit(str.charAt(0), 10) * 1000 + Character.digit(str.charAt(1), 10) * 100
               + Character.digit(str.charAt(2), 10) * 10 + Character.digit(str.charAt(3), 10);
      } else if (str.length() == 2) {
         n = Character.digit(str.charAt(0), 10);
      }
      String[] photo = new String[n];

      for (int i = 0; i < n; i++) {
         photo[i] = input.next();
      }

      int team = input.nextInt();
      threshold = input.nextInt();
      contructMatix(photo, team);

      go_find_indexes();
      bubble_sort();
      System.out.print("[");
      for (int i = 0; i < Centers.size(); i++) {
         System.out.print("(" + (int) Centers.get(i).getX() + ", " + (int) Centers.get(i).getY() + ")");
         if (i != Centers.size() - 1) {
            System.out.print(", ");
         }
      }
      System.out.print("]");

      input.close();
   }
}