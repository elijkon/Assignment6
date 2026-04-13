public class UnencryptedBinaryTree {
    
    public static boolean findElement(int[] bt, int target) {
        if (bt == null || bt.length == 0) {
            return false;
        }

        int[] unencrypted = new int[bt.length];

        if (bt[0] == -2) {
            unencrypted[0] = 1;

            if (unencrypted[0] == target) {
                return true;
            }
        }

        for (int i = 0; i < bt.length; i++) {

            if (bt[i] == -1) {
                continue;
            }

            int leftIndex = (2 * i) + 1;
            int rightIndex = (2 * i) + 2;


            if (leftIndex < bt.length && bt[leftIndex] == -2) {
                unencrypted[leftIndex] = (3 * unencrypted[i]) + 1;

                if (unencrypted[leftIndex] == target) {
                    return true;
                }
            }


            if (rightIndex < bt.length && bt[rightIndex] == -2) {
                unencrypted[rightIndex] = (2 * unencrypted[i]) + 5;

                if (unencrypted[rightIndex] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] bt = {-2, -2, -1, -2, -1};
        int target = 1;

        boolean result = findElement(bt, target);



        if (result) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
