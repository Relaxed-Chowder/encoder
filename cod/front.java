import java.util.ArrayList;
import java.util.List;
class front{
    public static void main(String[] args) {
        int encrypt = 1;
        String[] input = {"A1","B2","C3","D4","E5"};
        int rows = 3;

        if(encrypt == 1){
            String[] encrypted = scytaleEncrypt(input, rows);
        System.out.println("Encrypted:");
        for (String s : encrypted) System.out.println(s);


        }
    }

    public static String[] scytaleEncrypt(String[] input, int rows) {
        List<String> paddedInput  = new ArrayList<>();
        int cols = (int) Math.ceil((double) input.length / rows);
        int totalElements = rows * cols;
        while (paddedInput.size() < totalElements) {
            paddedInput.add("_"); // padding string
        }

        // Read column by column
        List<String> encrypted = new ArrayList<>();
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                int index = r * cols + c;
                if (index < input.length) {
                    encrypted.add(input[index]);
                }
            }
        }

        return encrypted.toArray(new String[0]);
    }
}
    