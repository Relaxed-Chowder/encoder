class front{
    public static void main(String[] args) {
        int encrypt = 1;
        String[] input = {"4L", "6F", "73", "74"};
        int turns = 3;

        if(encrypt == 1){
            String[] encrypted = scytaleEncrypt(input, turns);
            for (String s : encrypted){ 
                System.out.println(s);
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
}
