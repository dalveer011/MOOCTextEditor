package spelling;

import java.util.List;
import java.util.Set;

import sun.misc.Queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie. For the basic part of the assignment (part 2),
	 * you should convert the string to all lower case before you insert it.
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes into
	 * the trie, as described outlined in the videos for this week. It should
	 * appropriately use existing nodes in the trie, only creating new nodes when
	 * necessary. E.g. If the word "no" is already in the trie, then adding the word
	 * "now" would add only one additional node (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 *         in the dictionary.
	 */
	public boolean addWord(String word) {
		// TODO: Implement this method.
		String wordInLowerCase = word.toLowerCase();
		boolean alreadyThere = false;
		int i = 0;
		TrieNode start = this.root;
		
		while (i < wordInLowerCase.length()) {
			
			if(start.getText().equals(wordInLowerCase)) {
				alreadyThere = true;
				break;
			}
			TrieNode child = start.getChild(wordInLowerCase.charAt(i));
			if (child == null) {
				TrieNode newWord = start.insert(wordInLowerCase.charAt(i));
				if (i == wordInLowerCase.length() - 1) {
					newWord.setEndsWord(true);
					size++;
					break;
			   }
				start = newWord;
			}else {
				if (child.getText().equals(wordInLowerCase)) {
					 if(i == wordInLowerCase.length() - 1) {
						 if(!child.endsWord()) {
							 child.setEndsWord(true);
							 size++;	 
						 }
						
					 }
					return alreadyThere;
				}
				start = child;
			}
			i++;
		}
		return !alreadyThere;
	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same as the number of TrieNodes in the trie.
	 */
	public int size() {
		// TODO: Implement this method
		return this.size;
	}

	/**
	 * Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week.
	 */
	@Override
	public boolean isWord(String s) {
		// TODO: Implement this method
		TrieNode start = this.root;
		
		int i=0;
		while(i < s.length()) {
			TrieNode child = start.getChild(s.toLowerCase().charAt(i));
			if(child == null) {
				return false;
			}
			if(child.endsWord() && child.getText().equals(s.toLowerCase())) {
				return true;
			}
			start = child;
			i++;
		}
		return false;
	}

	/**
	 * Return a list, in order of increasing (non-decreasing) word length,
	 * containing the numCompletions shortest legal completions of the prefix
	 * string. All legal completions must be valid words in the dictionary. If the
	 * prefix itself is a valid word, it is included in the list of returned words.
	 * 
	 * The list of completions must contain all of the shortest completions, but
	 * when there are ties, it may break them in any order. For example, if there
	 * the prefix string is "ste" and only the words "step", "stem", "stew", "steer"
	 * and "steep" are in the dictionary, when the user asks for 4 completions, the
	 * list must include "step", "stem" and "stew", but may include either the word
	 * "steer" or "steep".
	 * 
	 * If this string prefix is not in the trie, it returns an empty list.
	 * 
	 * @param prefix         The text to use at the word stem
	 * @param numCompletions The maximum number of predictions desired.
	 * @return A list containing the up to numCompletions best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		// TODO: Implement this method
		// This method should implement the following algorithm:
		// 1. Find the stem in the trie. If the stem does not appear in the trie, return
		// an
		// empty list
		List<String> possibleWords = new ArrayList<String>();
		String prefixInLowerCase = prefix.toLowerCase();
		TrieNode stem = this.findStem(prefix);
		
		if(stem == null || numCompletions == 0) {
			return possibleWords;
		}
		
		// 2. Once the stem is found, perform a breadth first search to generate
		// completions
		// using the following algorithm:
		// Create a queue (LinkedList) and add the node that completes the stem to the
		// back
		// of the list.
		// Create a list of completions to return (initially empty)
		// While the queue is not empty and you don't have enough completions:
		// remove the first Node from the queue
		// If it is a word, add it to the completions list
		// Add all of its child nodes to the back of the queue
		// Return the list of completions
		Queue<TrieNode> helper = new Queue<TrieNode>();
		helper.enqueue(stem);
		TrieNode start = null;
		
		while(!helper.isEmpty()) {
			try {
				start = helper.dequeue();
				if(start.endsWord()) {
					possibleWords.add(start.getText());
					if(possibleWords.size() == numCompletions) {
						break;
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			for(char key : start.getValidNextCharacters()) {
				if(start.getChild(key) != null) {
					helper.enqueue(start.getChild(key));
				}
			}
		}
		return possibleWords;
	}
	public TrieNode findStem(String prefix) {
		String prefixLowerCase = prefix.toLowerCase();
		int i= 0;
		TrieNode start = this.root;
		TrieNode stem = null;
		if(prefix.length() == 0) {
			stem = this.root;
		}
		while(i < prefix.length()) {
			char key = prefixLowerCase.charAt(i);
			if(start.getText().equals(prefixLowerCase)) {
				stem = start;
				break;
			}
			TrieNode child = start.getChild(key);
			
			if(child == null) {
				break;
			}
			if(i == prefix.length()-1 && child.getText().equals(prefixLowerCase)) {
				stem = child;
				break;
			}
			start = child;
			i++;
		}
		return stem;
	}
	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null)
			return;

		System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}

}