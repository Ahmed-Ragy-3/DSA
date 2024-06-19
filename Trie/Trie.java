import java.util.* ;

public class Trie /*implements Trie_interface*/ { 
   TrieNode head ;
   int size ;

   public Trie() {
      this.head = new TrieNode() ;
      this.size = 0 ;
   }

   public Trie(String...words) {
      this.head = new TrieNode() ;
      this.size = 0 ;
      for(String word : words) {
         this.insert(word);
      }
   }

   public Trie(List<String> words) {
      this.head = new TrieNode() ;
      this.size = 0 ;
      this.insert(words);
   }

   boolean isEmpty() {
      return (this.size == 0) ;
   }

   void clear() {
      this.size = 0 ;
      this.head = new TrieNode() ;
   }

   void insert(List<String> words) {
      for(String word : words) {
         insert(word);
      }
   }

   void insert(String...words) {
      for(String word : words) {
         insert(word);
      }
   }

   void insert(String word) {
      this.size++ ;
      TrieNode temp = head ;
      for(char c : word.toCharArray()) {

         if(temp.letters.containsKey(c)) {
            temp = temp.letters.get(c) ;

         }else {
            temp.letters.put(c , new TrieNode()) ;
            temp = temp.letters.get(c) ;
         }
      }
      temp.wordEnd = true ;
   }

   boolean search(String word) {
      TrieNode temp = head ;
      for(char c : word.toCharArray()) {

         if(!temp.letters.containsKey(c))
            return false ;

         temp = temp.letters.get(c) ;
      }
      return temp.wordEnd ;
   }

   boolean containsPrefix(String prefix) {
      TrieNode temp = head ;
      for(char c : prefix.toCharArray()) {

         if(!temp.letters.containsKey(c))
            return false ;

         temp = temp.letters.get(c) ;
      }
      return true ;
   }

   public void delete(String...words) {
      // Case 1 : deleting a word that doesn't exist in the Trie
      // Case 2 : ahmed         (delete ahmed) ---> remove (a) in first hashmap
      // Case 3 : do - done     (delete do)   ----> set wordEnd to false
      // Case 4 : do - done     (delete done) ----> remove hashmap of letter (o)
      // Case 5 : bear - bean   (delete bean) ----> remove key (n) in the hashmap of letter(a)
      for(String word : words) delete(word);
   }

   private void delete(String word) {
      if(!search(word)) return ; // Case 1
      this.size-- ;
      TrieNode temp = head ;
      Stack<TrieNode> stack = new Stack<>() ;
      char[] wordArr = word.toCharArray() ;

      for(char c : wordArr) {
         stack.push(temp.letters.get(c)) ;
         temp = temp.letters.get(c) ;
      }
      if(!stack.peek().letters.isEmpty()) { // Case 3
         stack.peek().wordEnd = false ;
         return ;
      }

      int i = wordArr.length - 1 ;
      boolean commonChar = false ;
      while (true) {
         temp = stack.pop() ; // First iteration ---> temp = last letter (must be removed)
         if(!commonChar) {
            if(stack.isEmpty()) break ;
            stack.peek().letters.remove(wordArr[i]) ;
         }
         i-- ;
         if(stack.isEmpty()) break ;
         commonChar = !stack.peek().letters.isEmpty() ;
         
      }
      
   }

   String largestCommonPrefix(String word) {
      String lcp = "" ;
      TrieNode temp = head ;
      for(char c : word.toCharArray()) {

         if(!temp.letters.containsKey(c)) break ;

         lcp += c ;
         temp = temp.letters.get(c) ;
      }
      return lcp ;
   }

   public static Trie combine(Trie...t) {
      Trie combination = new Trie() ;
      for(int i=0 ; i<t.length ; i++) {
         for(String word : t[i].toList()) {
            combination.insert(word);
         }
      }
      return combination ;
   }

   List<String> toList() {
      return toList(head , new ArrayList<>() , "") ;
   }

   protected List<String> toList(TrieNode tempNode , List<String> list , String str) {
      if(tempNode.wordEnd)
         list.add(str) ;

      for(char c : tempNode.letters.keySet()) {
         toList(tempNode.letters.get(c) , list , str + c) ;
      }

      return list ;
   }

   List<String> getWordsWithPrefix(String prefix) {
      List<String> list = new ArrayList<>() ;
      TrieNode tempNode = head ;
      for(char c : prefix.toCharArray()) {
         if(!tempNode.letters.containsKey(c)) return new ArrayList<>() ;
         tempNode = tempNode.letters.get(c) ;
      }
      list = toList(tempNode , list , "") ;
      for(int i=0 ; i<list.size() ; i++) {
         list.set(i , prefix.concat(list.get(i))) ;
         //System.out.println("----> " + str);
      }
      return list ;
   }

   void suggest(String prefix) {
      int count = countWordsWithPrefix(prefix) ;
      if(count == 0) {
         System.out.println("No suggestions");
         return ;
      } else if(count == 1) {
         System.out.println(count + " suggestion : ");
      }else {
         System.out.println(count + " suggestions : ");
      }
      
      System.out.println(getWordsWithPrefix(prefix));
   }

   public int countWordsWithPrefix(String prefix) {
      TrieNode tempNode = head ;
      for(char c : prefix.toCharArray()) {
         if(!tempNode.letters.containsKey(c)) return 0 ;
         tempNode = tempNode.letters.get(c) ;
      }
      return countWordsWithPrefix(tempNode , 0) ;
   }

   protected int countWordsWithPrefix(TrieNode tempNode , int count) {
      if(tempNode.wordEnd) count++ ;

      for(char c : tempNode.letters.keySet()) {
         count = countWordsWithPrefix(tempNode.letters.get(c) , count) ;
      }
      return count ;
   }

   protected void print(List<String> words) {
      for(String word : words) {
         System.out.println(word);
      }
   }
   
   void printTrieWords() {
      System.out.println("\n-------------- All words -------------") ;
      print(this.toList()) ;
      System.out.println("--------------------------------------") ;
   }


   /* Same as suggest method */
   // void printWordsWithPrefix(String prefix) {
   //    System.out.print("\n---------------") ;
   //    System.out.print(" Words with prefix \"" + prefix + "\" ");
   //    System.out.println("----------------");
   //    print(getWordsWithPrefix(prefix));
   //    System.out.println("-----------------------------------------------------") ;
   // }

   void printLevels() {
      // print the letters that is in front of the queue
      // then add all letters of the printed hashmap
      Queue<Map<Character , TrieNode>> queue = new LinkedList<>() ;
      queue.add(head.letters) ;
      int prev_level = 1 ;
      int current_level = 0 ;
      while (!queue.isEmpty()) {
         for(TrieNode tempNode : queue.peek().values()) {
            queue.add(tempNode.letters) ;
            current_level++ ;
         }
         System.out.print(queue.poll().keySet().toString() + " ") ;
         if(--prev_level == 0) {
            prev_level = current_level ;
            current_level = 0 ;
            System.out.println();
         }
         
      }
   }

   public static void main(String[] args) {
      Scanner input = new Scanner(System.in) ;
      Trie t1 = new Trie("ahmed" , "bean" , "bear" , "test" , "do" , "door" , "done") ;
      t1.insert("antibody", "antifreeze", "antithesis");
      t1.printLevels();

      Trie t2 = new Trie("IN TRIE 2") ;
      t2.insert("apple", "banana", "cherry", "date", "elderberry", 
      "fig", "grape", "honeydew", "kiwi", "lemon", "mango", "nectarine", "orange", 
      "papaya", "quince", "raspberry", "strawberry", "tangerine", "ugli fruit",
      "vanilla", "watermelon", "xigua", "yellow passion fruit", "zucchini") ;

      Trie t3 = new Trie("IN TRIE 3") ;
      t3.insert("asparagus", "broccoli", "carrot", "daikon", "eggplant") ;
      t3.insert( "fennel", "garlic", "horseradish", "iceberg lettuce", "jalapeno", 
      "kale", "leek", "mushroom", "nutmeg", "oregano", "parsley", "quinoa", "radish", 
      "spinach", "tomato", "upland cress", "victoria plum", "watercress", "yam") ; 

      Trie compu = new Trie("computer science trie") ;
      compu.insert("algorithm", "binary", "cache", "data", "encryption",
      "hardware", "interface", "javascript", "kernel", "logic gate", "machine learning", "network",
      "object-oriented", "protocol", "query", "recursion", "syntax", "thread", "URL", "variable", "web",
      "XML", "YAML", "z-buffer", "array", "bit", "compiler", "database", "exception", "function", "GPU",
      "hashing", "integer", "JSON", "key", "loop", "microprocessor", "node", "operating system", "pointer",
      "queue", "register", "stack", "token", "Unix", "virtual machine", "WiFi", "XPath", "yield", "zip file",
      "back-end", "class", "distributed system", "encryption key", "front-end", "garbage collection", "hypervisor",
      "index", "JVM", "Kubernetes", "lambda function", "metadata", "normalization","OOP", "polymorphism",
      "relational database", "SQL", "test case", "Unicode", "virtualization", "web socket", "XML schema", "zero-knowledge proof") ;

      compu.delete("XML", "YAML", "z-buffer", "array", "bit", "compiler", "database", "exception");

      System.out.println(compu.search("key")) ;
      System.out.println(compu.largestCommonPrefix("java"));
      
      //t2.printLevels();
      t2.delete("oihd" , "quince" , "xigua" , "kiwi");
      t2.printTrieWords();

      Trie big = combine(t1 , t2 , t3 , compu) ;
      //big.printTrieWords();
      big.suggest("encry");
      System.out.println(big.countWordsWithPrefix("d")) ;
      System.out.println(big.size);

      input.close();
   }

}
