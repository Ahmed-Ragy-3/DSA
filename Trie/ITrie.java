import java.util.List;

public interface ITrie {
  /**
   * Inserts a word into the trie.
   *
   * @param word the word to be inserted
   */
  public void insert(String... word);

  /**
   * Returns if the word is in the trie.
   *
   * @param word the word to search for
   * @return true if the word is in the trie, false otherwise
   */
  public boolean search(String word);

  /**
   * Deletes a word from the trie.
   *
   * @param word the word to be deleted
   */
  public void delete(String... word);

  /**
   * Returns the total number of words stored in the trie.
   *
   * @return the number of words in the trie
   */
  public int size();

  /**
   * Clears all the words from the trie.
   */
  public void clear();

  /**
   * @return whether trie is empty or not
   */
  public boolean isEmpty();

  /**
   * Returns if there is any word in the trie that starts with the given prefix.
   *
   * @param prefix the prefix to search for
   * @return true if there is any word in the trie that starts with the prefix,
   *         false otherwise
   */
  public boolean containsPrefix(String prefix);

  /**
   * Returns a list of all words in the trie with the given prefix.
   *
   * @param prefix the prefix to search for
   * @return a list of words in the trie with the given prefix
   */
  public List<String> getWordsWithPrefix(String prefix);

  /**
   * Counts the number of words in the trie that start with the given prefix.
   *
   * @param prefix the prefix to count
   * @return the number of words with the given prefix
   */
  public int countWordsWithPrefix(String prefix);

  /**
   * combine 2 given heads of tries
   * 
   * @param heads of the tries
   * @return result trie after combination
   */
  public static Trie combine(Trie... t) {
    Trie combination = new Trie();
    for (int i = 0; i < t.length; i++) {
      for (String word : t[i].toList()) {
        combination.insert(word);
      }
    }
    return combination;
  }

  /**
   * 
   * @return the largest common prefix between trie and a given word
   */
  public String largestCommonPrefix(String word);

  /**
   * @param head of the trie
   * @return List of all words in the trie
   */
  public List<String> toList();

  /**
   * @param head of the trie
   *             print all words in the trie
   */
  public void printTrie();

  /**
   * print trie by level order
   */
  public void printLevels();

  /**
   * Auto complete given word
   */
  public void suggest(String prefix);
}
