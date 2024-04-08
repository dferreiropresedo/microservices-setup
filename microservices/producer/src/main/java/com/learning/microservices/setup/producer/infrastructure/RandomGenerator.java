package com.learning.microservices.setup.producer.infrastructure;

import java.util.Arrays;
import java.util.Random;

public class RandomGenerator {

  private static final String[] WORDS = new String[]{"Nunc", "fermentum", "ipsum", "id", "nulla",
      "accumsan", "fringilla.", "Phasellus", "vestibulum", "congue", "interdum.", "Morbi",
      "volutpat", "tortor", "eu", "arcu", "faucibus,", "et", "sagittis", "sapien."};

  public static String sentence(String... extraWords) {
    String[] dictionary = buildDictionary(extraWords);
    int numberOfWords = new Random().nextInt(1, 40);
    StringBuilder builder = new StringBuilder(numberOfWords);
    
    for (int i = 0; i < numberOfWords; i++) {
      int position = new Random().nextInt(0, dictionary.length);
      builder.append(dictionary[position]);
      builder.append(" ");
    }
    return builder.toString().trim();
  }

  static String[] buildDictionary(String... extraWords) {
    String[] result = Arrays.copyOf(WORDS, WORDS.length + extraWords.length);
    System.arraycopy(extraWords, 0, result, WORDS.length, extraWords.length);
    return result;
  }

}
