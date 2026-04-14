import java.util.ArrayList;

public class BRT {
    public Node2 root;

    public BRT() {
        this.root = null;
    }

    public void inRange(int a, int b) {
        ArrayList<Integer> valuesToDelete = new ArrayList<>();

        collectInRange(this.root, a, b, valuesToDelete);

        for (int i = 0; i < valuesToDelete.size(); i++) {
            int targetValue = valuesToDelete.get(i);
            this.root = deleteNode(this.root, targetValue);
        }
    }

    public void collectInRange(Node2 currentNode, int a, int b, ArrayList<Integer> list) {
        if (currentNode == null) {
            return;
        }

        collectInRange(currentNode.left, a, b, list);

        if (currentNode.val >= a && currentNode.val <= b) {
            list.add(currentNode.val);
        }

        collectInRange(currentNode.right, a, b, list);
    }

    public Node2 deleteNode(Node2 currentNode, int value) {
        if (currentNode == null) {
            return null;
        }

        if (value < currentNode.val) {
            currentNode.left = deleteNode(currentNode.left, value);
        } else if (value > currentNode.val) {
            currentNode.right = deleteNode(currentNode.right, value);
        }
        else {
            if (currentNode.left == null) {
                return currentNode.right;
            } else if (currentNode.right == null) {
                return currentNode.left;
            }
            int smallestRightValue = findMin(currentNode.right);

            currentNode.val = smallestRightValue;

            currentNode.right = deleteNode(currentNode.right, smallestRightValue);
        }

        return currentNode;
    }

    public int findMin(Node2 node) {
        int min = node.val;
        while (node.left != null) {
            min = node.left.val;
            node = node.left;
        }
        return min;
    }


    public void insert(int val) {
        this.root = insertRec(this.root, val);
    }

    public Node2 insertRec(Node2 root, int val) {
        if (root == null) {
            return new Node2(val);
        }
        if (val < root.val) {
            root.left = insertRec(root.left, val);
        } else if (val > root.val) {
            root.right = insertRec(root.right, val);
        }
        return root;
    }

    public void printTree() {
        System.out.print("{");
        printInOrder(this.root);
        System.out.println("}");
    }

    public void printInOrder(Node2 node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.val + " ");
            printInOrder(node.right);
        }
    }



    public static void main(String[] args) {

        System.out.println("Test 1");
        BRT tree1 = createSampleTree();
        System.out.print("Original Keys: ");
        tree1.printTree();

        tree1.inRange(15, 20);
        System.out.print("After inRange(15, 20): ");
        tree1.printTree();

        System.out.println("\nTest 2");
        BRT tree2 = createSampleTree();
        tree2.inRange(0, 2);
        System.out.print("After inRange(0, 2): ");
        tree2.printTree();

        System.out.println("\nTest 3");
        BRT tree3 = createSampleTree();
        tree3.inRange(25, 60);
        System.out.print("After inRange(25, 60): ");
        tree3.printTree();
    }


    public static BRT createSampleTree() {
        BRT tree = new BRT();
        tree.insert(30);
        tree.insert(19);
        tree.insert(55);
        tree.insert(10);
        tree.insert(20);
        tree.insert(42);
        tree.insert(77);
        return tree;
    }
}
