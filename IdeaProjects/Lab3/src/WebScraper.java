import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;


public class WebScraper {

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */

    public static int count(String word) {
        if (word == null || word.isEmpty()) {
            return 0;
        }

        int wordCount = 0;

        boolean isWord = false;
        int endOfLine = word.length() - 1;
        char[] characters = word.toCharArray();

        for (int i = 0; i < characters.length; i++) {

            // if the char is a letter, word = true.
            if (Character.isLetter(characters[i]) && i != endOfLine) {
                isWord = true;

                // if char isn't a letter and there have been letters before,
                // counter goes up.
            } else if (!Character.isLetter(characters[i]) && isWord) {
                wordCount++;
                isWord = false;

                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetter(characters[i]) && i == endOfLine) {
                wordCount++;
            }
        }

        return wordCount;
    }


    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    public static void main (String args[]) {

        //System.out.println(urlToString("http://erdani.com/tdpl/hamlet.txt"));

        String trim = (urlToString("http://erdani.com/tdpl/hamlet.txt")).trim();
        int count = trim.split("\\s+").length;
        System.out.println(count);

        StringTokenizer tokens = new StringTokenizer(trim);
        System.out.println(tokens.countTokens());


        int c = trim.split("\\bPrince\\b").length - 1;
        System.out.println(c);

        System.out.println(count(trim));
    }
}
