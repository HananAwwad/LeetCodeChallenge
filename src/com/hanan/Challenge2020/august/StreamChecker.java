package com.hanan.Challenge2020.august;

import java.util.Deque;
import java.util.LinkedList;

public class StreamChecker {

    class Trie {
        public Trie[] children;
        public boolean endOfWord;

        public Trie() {
            endOfWord = false;
            children = new Trie[26];
        }

        public void insert(String s) {
            Trie t = this;
            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null)
                    t.children[c - 'a'] = new Trie();
                t = t.children[c - 'a'];
            }
            t.endOfWord = true;
        }

        public boolean search(Deque<Character> s) {
            Trie t = this;
            for (char c : s) {
                if (t.children[c - 'a'] == null) return false;
                t = t.children[c - 'a'];
                if (t.endOfWord) return true;
            }
            return false;
        }
    }

    ;

    public Trie t = new Trie();
    public Deque<Character> stream = new LinkedList();

    public StreamChecker(String[] words) {
        for (String w : words) {
            String w_rev = new StringBuilder(w).reverse().toString();
            t.insert(w_rev);
        }
    }

    public boolean query(char letter) {
        stream.addFirst(letter);
        return t.search(stream);
    }
}
