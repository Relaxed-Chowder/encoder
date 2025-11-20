class front{
    public static void main(String[] args) {
        int encrypt = 1;
        String[] input = {"46","6F","72","67","6F","74","74","65"};
        int turns = 3;

        if(encrypt == 1){
            //String[] encrypted = scytaleEncrypt(input, turns);
            double encrypted = scytaleEncrypt(input, turns);
            System.out.println(encrypted);
            //for (String s : encrypted) System.out.println(s);
        }
    }

    public static double scytaleEncrypt(String[] input, int turns) {
        int columns = (input.length + 1) / turns;
        String[][] grid;
        grid = new String[turns][columns];

        int index = 0;

        for(int i = 0; i<=turns; i++){
            for(int j = 0; j<=columns; j++){
                if(j < input.length){
                    grid[i][j] = input[index];
                }else{
                    grid[i][j] = input[index];
                }
            }
        }
        return columns;
    }
}
