public class Node {
    char character;
    int frequency;
    Node left;
    Node right;


    public Node(char c, int freq, Node l, Node r) {
        this.character = c;
        this.frequency = freq;
        this.left = l;
        this.right = r;
    }
}
