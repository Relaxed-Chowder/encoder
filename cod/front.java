import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
class front{
    public static void main(String[] args) {
        int encrypt = 1;
        String string = "46 6F 72 20 47 6F 64 20 73 6F 20 6C 6F 76 65 64 20 74 68 65 20 77 6F 72 6C 64";
        String seperator = "[\\s]";
        String[] input = string.split(seperator);

        int turns = 5; //online scytale + 1 = turns

        if(encrypt == 1){
            String[] encrypted1 = scytaleEncrypt(input, turns);
            for (String s : encrypted1){ 
                System.out.println("scytale " + s);
            }
            String[] encrypted2 = cesarEncrypt(encrypted1);
            for (String s : encrypted2){ 
                System.out.println("cesar " + s);
            }
            System.out.println(input.length);
        }
    }

    public static String[] scytaleEncrypt(String[] input, int turns) {
        int columns = (int) Math.ceil((double) input.length / turns);
        String[][] grid;
        grid = new String[turns][columns];

        int index = 0;

        for(int i = 0; i<turns; i++){
            for(int j = 0; j<columns; j++){
                if(index < input.length){
                    grid[i][j] = input[index];
                }else{
                    grid[i][j] = "00";
                }
            index++;
            }
        }


        String[] ciphertext;
        int cipheradd = 0;
        ciphertext = new String[turns*columns];

        for(int j = 0; j<columns; j++){
            for(int i = 0; i<turns; i++){
                ciphertext[cipheradd] = grid[i][j];
                cipheradd++;
            }
        }
        return ciphertext;
    }

    public static String[] cesarEncrypt(String[] encrypted1) {
        String[] hex = new String[256];
        for (int i = 0; i < 256; i++) {
            hex[i] = String.format("%02X", i);
        }

        String[] encrypted2 = new String[encrypted1.length];

        //for (String s : encrypted1){ 
            //System.out.println("begin:" + s);
        //}

        //String[] ciphertext;
        //ciphertext = new String[encrypted1.length];

        for (int j = 0; j < encrypted1.length; j++) {
            //for (String s : encrypted1){ 
                //System.out.println(s + " outer cycle:" + j);
            //}
            for (int i = 0; i < hex.length; i++) {
                if(encrypted1[j].equals(hex[i])){
                    int y = (i + 3) % 256;
                    encrypted2[j] = hex[y];
                }
            }
        }
    return encrypted2;
    }
    //public static String[] boxEncrypt(String[] encrypted1) {
        
    //}
}

