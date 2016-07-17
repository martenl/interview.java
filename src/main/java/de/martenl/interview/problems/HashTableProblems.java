package de.martenl.interview.problems;

import de.martenl.interview.datastructures.HashTable;
import de.martenl.interview.datastructures.LinkedList;

public class HashTableProblems {

    private static HashTable<Character,Integer> stringToCharMap(String s){
        HashTable<Character,Integer> charMap = new HashTable<>();
        for(char c : s.toCharArray()){
            if(charMap.contains(c)){
                charMap.put(c,charMap.get(c)+1);
            }else{
                charMap.put(c,1);
            }
        }
        return charMap;
    }

    public static String sortString(String s){
        final String lowerCased = s.toLowerCase();
        final int[] characters = new int[26];
        final int offset = 'a';
        for(char c : lowerCased.toCharArray()){
            characters[c-offset]++;
        }
        return characterCountToString(characters);
    }

    private static String characterCountToString(int[] characterCount){
        final int offset = 'a';
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i<characterCount.length;i++){
            char character =(char)(i+offset);
            for(int j = 0;j<characterCount[i];j++){
                builder.append(character);
            }
        }
        return builder.toString();
    }

    public static void anagram(LinkedList<String> dictionary){
        HashTable<String,LinkedList<String>> anagramMap = new HashTable<>();
        for(String word : dictionary){
            final String sortedWord = sortString(word);
            if(!anagramMap.contains(sortedWord)){
                anagramMap.put(sortedWord,new LinkedList<>());
            }
            anagramMap.get(sortedWord).add(word);
        }
    }

    public static boolean problem3(String text,String magazine){
        HashTable<Character,Integer> textCharMap = stringToCharMap(text);
        HashTable<Character,Integer> magazineCharMap = stringToCharMap(magazine);
        for(char c : textCharMap.keys()){
            if(!magazineCharMap.contains(c) || magazineCharMap.get(c) < textCharMap.get(c)){
                return false;
            }
        }
        return true;
    }
}
