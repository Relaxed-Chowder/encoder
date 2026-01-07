import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

class front{
    public static void main(String[] args) {
        int encrypt = 1;
        String string = "52 69 6A 6E 64 61 65 6C";
        String seperator = "[\\s]";
        String[] input = string.split(seperator);
        /*
        for (String s : input){ 
            System.out.print(s);
        }
        System.out.println();
        */

        //String out = "A0 DE 09 42 96 D9 4A A0 BF FE 2C 6D AF AA C2 2A 89 DE F1 3A B1 8A 4A 59 DA E9 26 6E BC 02 47 07";
        //String[] deinput = out.split(seperator);
        /*
        for (String s : deinput){ 
            System.out.println(s);
        }
        System.out.println();
        */

        int[] rowKey = {0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3}; //0-3
        int[] columnKey = {2,3,1,1,1,2,3,1,1,1,2,3,3,1,1,2}; //0-9
        int[] roundKey = {15,31,47,63,79,95,111,127,143,159,175,191,207,223,239,255}; //0-255

        if(encrypt == 1){
            String[] string = boxEncrypt(string, rowKey, columnKey, roundKey);
            System.out.println(Arrays.toString(string));
            System.out.println("after box");
            System.out.println();
            int i = 0;
            for(String s : string){
                if(i%2 == 0){
                    System.out.print(" ");
                }
                i++;
                System.out.print(s);
                i++;
            }
            System.out.println();
            System.out.println("final");


        }else if (encrypt == 2) {
            String[] string = boxDencrypt(deinput, rowKey, columnKey, roundKey);
            System.out.println(Arrays.toString(string));
            System.out.println("after box");
            System.out.println();
        }
    }
}
