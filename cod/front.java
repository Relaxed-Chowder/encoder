class front{
    public static void main(String[] args) {
        int encrypt = 1;
        String[] input = {"4C", "6F", "73", "74"};
        int turns = 3;

        if(encrypt == 1){
            String[] encrypted1 = scytaleEncrypt(input, turns);
            String[] encrypted2 = cesarEncrypt(encrypted1);
            for (String s : encrypted2){ 
                //System.out.println(s);
            }
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
        int z = 0;
        String[] hex = new String[256];
        for (int i = 0; i < 256; i++) {
            hex[i] = String.format("%02X", i);
        }

        //for (String s : encrypted1){ 
        //    System.out.println("begin:" + s);
        //}

        //String[] ciphertext;
        //ciphertext = new String[encrypted1.length];

        for (int j = 0; j < encrypted1.length; j++) {
                    //for (String s : encrypted1){ 
                    //    System.out.println(s + " outer cycle:" + j);
                    //}
            for (int i = 0; i < hex.length; i++) {
                if (encrypted1[j].equals(hex[i])){
                    System.out.println("encrip " + encrypted1[j]);
                    System.out.println("hex " + hex[i]);
                    int x=0;
                    int y=i;

                    while(x < 3){
                        if (encrypted1[j].equals("FF")){
                            encrypted1[j] = "00";
                        }

                        if (y == 255){
                            y = 0;
                        }
                        y++;
                        encrypted1[j] = hex[y];
                        //System.out.println(x);
                        x++;
                    }
                }
            }
        }
    return encrypted1;
    }
}
