package com.hanan.august;

public class WordDictionary {
    /**
     * Initialize your data structure here.
     */
    class TrieNode {
        TrieNode[] letters;
        boolean isWord;

        TrieNode() {
            letters = new TrieNode[26];
            isWord = false;
        }
    }

    TrieNode dict;

    /**
     * Initialize your data structure here.
     */

    public WordDictionary() {
        this.dict = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode ptr = this.dict;
        for (char c : word.toCharArray()) {
            if (ptr.letters[c - 'a'] == null)
                ptr.letters[c - 'a'] = new TrieNode();
            ptr = ptr.letters[c - 'a'];
        }
        ptr.isWord = true;
    }

    public boolean search(TrieNode ptr, String word, int index) {
        if (index >= word.length())
            return ptr.isWord;

        char c = word.charAt(index);

        if (c == '.') {
            for (TrieNode t : ptr.letters) {
                if (t != null && search(t, word, index + 1))
                    return true;
            }
            return false;
        }
        if (ptr == null || ptr.letters[c - 'a'] == null)
            return false;

        return search(ptr.letters[c - 'a'], word, index + 1);
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return search(this.dict, word, 0);

    }
}



