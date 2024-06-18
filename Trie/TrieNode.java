import java.util.HashMap ;
import java.util.Map ;

public class TrieNode {
   
   Map<Character , TrieNode> letters ;
   boolean wordEnd ;
   
   public TrieNode() {
      this.wordEnd = false ;
      letters = new HashMap<>() ;
   }

   public TrieNode(Character c) {
      this.wordEnd = false ;
      letters = new HashMap<>() ;
      letters.put(c , new TrieNode()) ;
   }

   public TrieNode(Character c , boolean end) {
      this.wordEnd = end ;
      letters = new HashMap<>() ;
      letters.put(c , new TrieNode()) ;
   }

}