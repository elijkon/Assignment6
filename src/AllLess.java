import java.util.ArrayList;

public class AllLess {
    public static ArrayList<String> allLess(String[] s, int x) {
        ArrayList<String> result = new ArrayList<>();

        if (s == null) {
            return result;
        }

        for (int i = 0; i < s.length; i++) {
            String currentString = s[i];


            if (currentString != null && currentString.length() < x) {
                result.add(currentString);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] s = {"zero", " size", "nutella", "jojo", "luna", "isse", "astor", "as", "entretien", "", "cal"};
        int x = 3;

        ArrayList<String> matches = allLess(s, x);

        System.out.print("[");
        for (int i = 0; i < matches.size(); i++) {
            System.out.print("\"" + matches.get(i) + "\"");

            if (i < matches.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
