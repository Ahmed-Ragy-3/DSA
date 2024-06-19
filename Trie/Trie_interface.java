import java.util.List;

public interface Trie_interface {
   /**
    * Inserts a word into the trie.
    *
    * @param word the word to be inserted
    */
   void insert(String word);

   /**
    * Returns if the word is in the trie.
    *
    * @param word the word to search for
    * @return true if the word is in the trie, false otherwise
    */
   boolean search(String word);

   /**
    * Returns if there is any word in the trie that starts with the given prefix.
    *
    * @param prefix the prefix to search for
    * @return true if there is any word in the trie that starts with the prefix, false otherwise
    */
   boolean containsPrefix(String prefix);

   /**
    * Deletes a word from the trie.
    *
    * @param word the word to be deleted
    */
   void delete(String word);

   /**
    * Returns a list of all words in the trie with the given prefix.
    *
    * @param prefix the prefix to search for
    * @return a list of words in the trie with the given prefix
    */
   List<String> getWordsWithPrefix(String prefix);

   /**
    * Returns the total number of words stored in the trie.
    *
    * @return the number of words in the trie
    */
   int size();

   /**
    * Clears all the words from the trie.
    */
   void clear();

   /**
     * Counts the number of words in the trie that start with the given prefix.
     *
     * @param prefix the prefix to count
     * @return the number of words with the given prefix
     */
   int countWordsWithPrefix(String prefix);

   // FROM ME

   /**
    * combine 2 given heads of tries
    * @param heads of the tries
    * @return head of the first trie after combination
    */
   TrieNode combine(Trie t1 , Trie t2) ;

   /**
    * 
    * @return the largest common prefix between trie and a given word
    */
   String largestCommonPrefix(String word) ;

   /**
    * @param head of the trie
    * @return List of all words in the trie
    */
   List<String> toList() ;

   /**
    * @param head of the trie
    * print all words in the trie 
    */
   void printTrie() ;

   /**
    * @return whether trie is empty or not
    */
   boolean isEmpty() ;

   /**
    * print trie by level order
    */
   void printLevels();

   /**
    * Auto complete given word
    */
   void suggest(String prefix);
}
