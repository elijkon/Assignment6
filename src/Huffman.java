import java.util.ArrayList;
import java.util.TreeMap;

public class Huffman {
    public TreeMap<Character, Integer> freqMap = new TreeMap<>();
    public TreeMap<Character, String> codeMap = new TreeMap<>();
    public Node root;
    public String originalText;
    public String encodedText;

    public void frequencyCount(String text) {
        this.originalText = text;
        this.freqMap.clear();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) + 1);
            } else {
                freqMap.put(c, 1);
            }
        }
    }


    public Node removeSmallest(ArrayList<Node> list) {
        if (list.size() == 0) {
            return null;
        }

        int smallestIndex = 0;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).frequency < list.get(smallestIndex).frequency) {
                smallestIndex = i;
            }
        }

        return list.remove(smallestIndex);
    }

    public void buildHuffman() {
        ArrayList<Node> nodeList = new ArrayList<>();

        for (char c : freqMap.keySet()) {
            int freq = freqMap.get(c);
            Node NewNode = new Node(c, freq, null, null);
            nodeList.add(NewNode);
        }

        while (nodeList.size() > 1) {
            Node leftChild = removeSmallest(nodeList);
            Node rightChild = removeSmallest(nodeList);

            int combinedFreq = leftChild.frequency + rightChild.frequency;
            Node parentNode = new Node('-', combinedFreq, leftChild, rightChild);

            nodeList.add(parentNode);
        }

        if (nodeList.size() > 0) {
            this.root = nodeList.get(0);
        }
    }

    public void genCode() {
        this.codeMap.clear();
        traverseTree(this.root, "");
    }

    public void traverseTree(Node node, String currentCode) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            codeMap.put(node.character, currentCode);
            return;
        }

        traverseTree(node.left, currentCode + "0");
        traverseTree(node.right, currentCode + "1");
    }

    public String encode(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            result += codeMap.get(text.charAt(i));
        }
        this.encodedText = result;
        return result;
    }

    public void printStats() {
        System.out.println("\nStats for \"" + originalText + "\"");

        System.out.println("\nFrequencies:");
        for (char c : freqMap.keySet()) {
            if (c == ' ') {
                System.out.println("(space) : " + freqMap.get(c));
            } else {
                System.out.println(c + ": " + freqMap.get(c));
            }
        }

        System.out.println("\nHuffman Codes:");
        for (char c : codeMap.keySet()) {
            if (c == ' ') {
                System.out.println("(space): " + codeMap.get(c));
            } else {
                System.out.println(c + ": " + codeMap.get(c));
            }
        }

        System.out.println("Encoded:\n" + encodedText);
    }
}
