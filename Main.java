import java.util.*;
import java.io.*;

class Main 
{
  public static void main(String[] args) throws FileNotFoundException
  {
    File wordsFile = new File("words.txt");
    Scanner wordRead = new Scanner(wordsFile);
    PrintStream output = new PrintStream(new File("userOutput.txt"));
    Random gen = new Random();
    Scanner userInput = new Scanner(System.in);
    String userGuess = "";

    String[] wordList = new String[5757];
    for (int i = 0; i < wordList.length; i++)
    {
      wordList[i] = wordRead.nextLine();
    }
    System.out.println("There are " + wordList.length + " words in this list, selecting a random one for you to guess...");
    String secretWord = "";
    int secretIndex = 0;
    while(true)
    {
      secretIndex = gen.nextInt(5757);
      secretWord = wordList[secretIndex];
      while (!userGuess.strip().equals(secretWord.strip()))
      {
        do
        {
          System.out.print("What guess do you have? (5 letters words): ");
          userGuess = userInput.next();
        }while (userGuess.length() != 5);
        doWordle(userGuess, secretWord, 0, output);
        System.out.println();
        output.println();
      }
    }
  }
  public static void doWordle(String guess, String word, int index, PrintStream output)
  {
    if (index == guess.length())
    {
      if(guess.equals(word))
      {
        System.out.println();
        System.out.println("Wow, you got it! Let's do another!");
      }
      return;
    }
    if(guess.charAt(index) == word.charAt(index))
    {
      System.out.print("[" + guess.charAt(index) + "]");
      output.print("[" + guess.charAt(index) + "]");
      doWordle(guess, word, index + 1, output);
      return;
    }
    for (int j = 0; j < 5; j++)
    {
      if (guess.charAt(index) == word.charAt(j))
      {
        System.out.print("*" + guess.charAt(index) + "*");
        output.print("*" + guess.charAt(index) + "*");
        doWordle(guess, word, index + 1, output);
        return;
      }
    }
    System.out.print(guess.charAt(index));
    output.print(guess.charAt(index));
    doWordle(guess, word, index + 1, output);
    return;
  }
}