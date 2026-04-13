import java.util.ArrayList;
import java.util.Collections;

public class EncodingSystem {
    public Huffman myHuffman;
    public String[] myTexts;
    public ArrayList<String> encodedResults;

    public EncodingSystem(String[] inputTexts) {
        myHuffman = new Huffman();
        myTexts = inputTexts;
        encodedResults = new ArrayList<>();

        for (int i = 0; i < myTexts.length; i++) {
            String text = myTexts[i];
            myHuffman.frequencyCount(text);
            myHuffman.buildHuffman();
            myHuffman.genCode();

            String encoded = myHuffman.encode(text);
            encodedResults.add(encoded);
        }
    }

    public String highestCode() {
        if (encodedResults.size() == 0) {
            return null;
        }
        return Collections.max(encodedResults);
    }

    public ArrayList<String> shuffleCodes() {
        ArrayList<String> shuffledList = new ArrayList<>(encodedResults);
        Collections.shuffle(shuffledList);
        return shuffledList;
    }

    public void stats() {
        for (int i = 0; i < myTexts.length; i++) {
            String text = myTexts[i];
            myHuffman.frequencyCount(text);
            myHuffman.buildHuffman();
            myHuffman.genCode();
            myHuffman.encode(text);
            myHuffman.printStats();
        }
    }

    public static void main(String[] args) {
        String[] testWords = {"marcus fenix is a gear"};
        EncodingSystem system = new EncodingSystem(testWords);

        System.out.println("highestCode()");
        System.out.println("\"" + system.highestCode() + "\"");

        System.out.println("\nshuffleCodes()");
        ArrayList<String> shuffled = system.shuffleCodes();

        System.out.print("[");
        for (int i = 0; i < shuffled.size(); i++) {
            System.out.print("\"" + shuffled.get(i) + "\"");
            if (i < shuffled.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.println("\nstats()");
        system.stats();
    }
}
