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
        double ceilcolumns = input.length / turns;
        double columns = Math.ceil(ceilcolumns);
        return columns;
    }
}

    
