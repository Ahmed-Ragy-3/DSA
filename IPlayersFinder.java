import java.awt.Point ;
import java.util.* ;
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

public static void main(String[] args) {

   Scanner sc = new Scanner(System.in);
   int n = sc.nextInt();
   String[] photo = new String[n];
   
   for(int i = 0; i < n; i++) {
      photo[i] = sc.next();
   }

   int team = sc.nextInt();
   byte[][] matrix = contructMatix(photo, team);

   for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[i].length; j++) {
         System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
   }
}