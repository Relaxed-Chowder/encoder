import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

class boxtrouble{
    public static void main(String[] args) {
        int encrypt = 2;
        //String string = "2D 52 69 6A 6E 64 61 65 6C 2A 2D 52 69 6A 6E 64 61 65 6C 3D 52 69 6A 6E 64 61 65 6C";
        String string = "36 E9 14 04 FF 23 91 84 3B F7 A1 44 F7 3D D1 06 54 E6 E7 67 7E A3 65 C2 BB 1F E1 8B 2F FB 1B 15";
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

        int[][] rowKey = {{0,1,2,3},{0,1,2,3},{0,1,2,3},{0,1,2,3}}; //0-3
        int[][] columnKey = {{2,3,1,1},{1,2,3,1},{1,1,2,3},{3,1,1,2}}; //0-9
        String[] roundKey = {"0F","1F","2F","3F","4F","5F","6F","7F","8F","9F","AF","BF","CF","DF","EF","71"}; //00-254
        // change round key too hexadecimal

        if(encrypt == 1){
            String[] encrypted3 = boxEncrypt(input, rowKey, columnKey, roundKey);
            System.out.println(Arrays.toString(encrypted3));
            System.out.println("after box");
            System.out.println();
            int i = 0;
            for(String s : encrypted3){
                if(i%2 == 0 && i != 0){
                    System.out.print(" ");
                }
                i++;
                System.out.print(s);
                i++;
            }
            System.out.println();
            System.out.println("final");


        }else if (encrypt == 2){
            String[][][] decrypted = boxDencrypt(input, rowKey, columnKey, roundKey);
            System.out.println(Arrays.toString(decrypted));
            System.out.println("after box");
            System.out.println();
        }
    }
     public static String[] boxEncrypt(String[] encrypted2, int[][] rowKey, int[][] columnKey, String[] roundKey){
        Map<String, String> Sbox = new LinkedHashMap<>();

        int total = (int)Math.ceil(encrypted2.length / 16.0);
        String[][][] blocks = new String[total][4][4];
        String[][][] blocks1 = new String[total][4][4];
        int index = 0;
        String[] encrypted3;

        int subc = 0;

        int l = 0;

        String[] box = {
            "63","7C","77","7B","F2","6B","6F","C5","30","01","67","2B","FE","D7","AB","76",
            "CA","82","C9","7D","FA","59","47","F0","AD","D4","A2","AF","9C","A4","72","C0",
            "B7","FD","93","26","36","3F","F7","CC","34","A5","E5","F1","71","D8","31","15",
            "04","C7","23","C3","18","96","05","9A","07","12","80","E2","EB","27","B2","75",
            "09","83","2C","1A","1B","6E","5A","A0","52","3B","D6","B3","29","E3","2F","84",
            "53","D1","00","ED","20","FC","B1","5B","6A","CB","BE","39","4A","4C","58","CF",
            "D0","EF","AA","FB","43","4D","33","85","45","F9","02","7F","50","3C","9F","A8",
            "51","A3","40","8F","92","9D","38","F5","BC","B6","DA","21","10","FF","F3","D2",
            "CD","0C","13","EC","5F","97","44","17","C4","A7","7E","3D","64","5D","19","73",
            "60","81","4F","DC","22","2A","90","88","46","EE","B8","14","DE","5E","0B","DB",
            "E0","32","3A","0A","49","06","24","5C","C2","D3","AC","62","91","95","E4","79",
            "E7","C8","37","6D","8D","D5","4E","A9","6C","56","F4","EA","65","7A","AE","08",
            "BA","78","25","2E","1C","A6","B4","C6","E8","DD","74","1F","4B","BD","8B","8A",
            "70","3E","B5","66","48","03","F6","0E","61","35","57","B9","86","C1","1D","9E",
            "E1","F8","98","11","69","D9","8E","94","9B","1E","87","E9","CE","55","28","DF",
            "8C","A1","89","0D","BF","E6","42","68","41","99","2D","0F","B0","54","BB","16"
        };

        String[] hex = new String[256];
        for (int i = 0; i < 256; i++) {
            hex[i] = String.format("%02X", i);
            String S = String.format("%02X", i);
            Sbox.put(S, box[i]);
        }

        // creating box
        for(int b = 0; b < total; b++){
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(index < encrypted2.length){
                        blocks[b][i][j] = encrypted2[index];
                    }else{
                        blocks[b][i][j] = "00"; // padding
                    }

                    index++;
                }
            }
        }
        
        
        // check box
        /*
        for (int b = 0; b < blocks.length; b++){
            for (int i = 0; i < blocks[b].length; i++){
                for (int j = 0; j < blocks[b][i].length; j++){
                    System.out.print(blocks[b][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("box");
            System.out.println();
        }
        */
        while(l<4){
            // subsitution
            for(int b = 0; b < total; b++){
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 4; j++){
                        String value = blocks[b][i][j];
                        blocks[b][i][j] = Sbox.get(value);
                    }
                }
            }

            
            // check subsitution
            /*
            for(int b = 0; b < blocks.length; b++){
                for(int i = 0; i < blocks[b].length; i++){
                    for(int j = 0; j < blocks[b][i].length; j++){
                        System.out.print(blocks[b][i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("sub");
                System.out.println();
            }
            */
            
            // row shift
            for (int b = 0; b < blocks.length; b++){
                for (int i = 0; i < blocks[b].length; i++){
                    for (int j = 0; j < blocks[b][i].length; j++){
                        blocks1[b][i][j] = blocks[b][i][(j + rowKey[l][j]) % blocks[b].length];
                    }
                }
            }

            // check row
            /*
            for(int b = 0; b < blocks1.length; b++){
                for(int i = 0; i < blocks1[b].length; i++){
                    for(int j = 0; j < blocks1[b][i].length; j++){
                        System.out.print(blocks1[b][i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("row");
                System.out.println();
            }
            */

            for (int b = 0; b < blocks.length; b++){
                for (int i = 0; i < blocks[b].length; i++){
                    for (int j = 0; j < blocks[b][i].length; j++){
                        int num = Integer.parseInt(blocks[b][i][j],16);
                        int bitwise = (num+columnKey[l][j])%256;
                        //System.out.println("bitwise " + num + " " + bitwise);
                        blocks[b][i][j] = hex[bitwise];
                    }
                }
            }

            // check column
            /*
            for (int b = 0; b < blocks.length; b++){
                for (int i = 0; i < blocks[b].length; i++){
                    for (int j = 0; j < blocks[b][i].length; j++){
                        System.out.print(blocks[b][i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("column");
                System.out.println();
            }
            */

            if(l > 0){
                for (int b = 0; b < blocks.length; b++){
                    for (int i = 0; i < blocks[b].length; i++){
                        for (int j = 0; j < blocks[b][i].length; j++){
                            int num = Integer.parseInt(blocks[b][i][j],16);
                            int key = Integer.parseInt(roundKey[j + (i * 4)],16);
                            int bitwise = ~(num ^ key) & 0xFF;
                            //System.out.println("bitwise " + num + " " + bitwise);
                            blocks[b][i][j] = hex[bitwise];
                        }
                    }
                }

                // check bitwise
                /*
                for (int b = 0; b < blocks.length; b++){
                    for (int i = 0; i < blocks[b].length; i++){
                        for (int j = 0; j < blocks[b][i].length; j++){
                            System.out.print(blocks[b][i][j] + " ");
                        }
                    System.out.println();
                    }
                System.out.println("XNOR");
                System.out.println();
                }
                */
            }
            
            l++;
        }
    encrypted3 = Arrays.stream(blocks).flatMap(Arrays::stream).flatMap(Arrays::stream).toArray(String[]::new);

    return encrypted3;
    }



    public static String[][][] boxDencrypt(String[] input, int[][] rowKey, int[][] columnKey, String[] roundKey){
        Map<String, String> boxS = new LinkedHashMap<>();

        int total = (int)Math.ceil(input.length / 16.0);
        String[][][] blocks = new String[total][4][4];
        String[][][] blocks1 = new String[total][4][4];
        int index = 0;
        int l = 3;

        String[] S = {
            "52","09","6A","D5","30","36","A5","38","BF","40","A3","9E","81","F3","D7","FB",
            "7C","E3","39","82","9B","2F","FF","87","34","8E","43","44","C4","DE","E9","CB",
            "54","7B","94","32","A6","C2","23","3D","EE","4C","95","0B","42","FA","C3","4E",
            "08","2E","A1","66","28","D9","24","B2","76","5B","A2","49","6D","8B","D1","25",
            "72","F8","F6","64","86","68","98","16","D4","A4","5C","CC","5D","65","B6","92",
            "6C","70","48","50","FD","ED","B9","DA","5E","15","46","57","A7","8D","9D","84",
            "90","D8","AB","00","8C","BC","D3","0A","F7","E4","58","05","B8","B3","45","06",
            "D0","2C","1E","8F","CA","3F","0F","02","C1","AF","BD","03","01","13","8A","6B",
            "3A","91","11","41","4F","67","DC","EA","97","F2","CF","CE","F0","B4","E6","73",
            "96","AC","74","22","E7","AD","35","85","E2","F9","37","E8","1C","75","DF","6E",
            "47","F1","1A","71","1D","29","C5","89","6F","B7","62","0E","AA","18","BE","1B",
            "FC","56","3E","4B","C6","D2","79","20","9A","DB","C0","FE","78","CD","5A","F4",
            "1F","DD","A8","33","88","07","C7","31","B1","12","10","59","27","80","EC","5F",
            "60","51","7F","A9","19","B5","4A","0D","2D","E5","7A","9F","93","C9","9C","EF",
            "A0","E0","3B","4D","AE","2A","F5","B0","C8","EB","BB","3C","83","53","99","61",
            "17","2B","04","7E","BA","77","D6","26","E1","69","14","63","55","21","0C","7D"
        };

        String[] hex = new String[256];
        for (int i = 0; i < 256; i++){
            hex[i] = String.format("%02X", i);
            String box = String.format("%02X", i);
            boxS.put(box, S[i]);
        }

        // creating box
        for(int b = 0; b < total; b++){
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(index < input.length){
                        blocks[b][i][j] = input[index];
                    }

                    index++;
                }
            }
        }

        while(l >= 0){
            if(l > 0){
                // bitwise
                for (int b = blocks.length-1; b >= 0; b--){
                    for (int i = blocks[b].length-1; i >= 0; i--){
                        for (int j = blocks[b][i].length-1; j >= 0; j--){
                            int num = Integer.parseInt(blocks[b][i][j],16);
                            int key = Integer.parseInt(roundKey[j + (i * 4)],16);
                            int bitwise = ~(num ^ key) & 0xFF;
                            //System.out.println("bitwise " + num + " " + bitwise);
                            blocks[b][i][j] = hex[bitwise];
                        }
                    }
                }

                // check bitwise
                
                for (int b = 0; b < blocks.length; b++){
                    //System.out.println("l is " + l);
                    for (int i = 0; i < blocks[b].length; i++){
                        for (int j = 0; j < blocks[b][i].length; j++){
                            System.out.print(blocks[b][i][j] + " ");
                        }
                    System.out.println();
                    }
                System.out.println("XNOR");
                System.out.println();
                } 
            }

            // column
            for (int b = blocks.length-1; b >= 0; b--){
                for (int i = blocks[b].length-1; i >= 0; i--){
                    for (int j = blocks[b][i].length-1; j >= 0; j--){
                        int num = Integer.parseInt(blocks[b][i][j],16);
                        int bitwise = (num-columnKey[l][j]);
                        if(bitwise < 0){
                            bitwise += 256;
                        }
                        //System.out.println("columnKey: " + columnKey[i][j]);
                        //System.out.println("bitwise " + num + " " + bitwise);
                        blocks[b][i][j] = hex[bitwise];
                    }
                }
            }
            

            // check column
            
            for (int b = 0; b < blocks.length; b++){
                for (int i = 0; i < blocks[b].length; i++){
                    for (int j = 0; j < blocks[b][i].length; j++){
                        System.out.print(blocks[b][i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("column");
                System.out.println();
            }

            // row
            for (int b = blocks.length-1; b >= 0; b--){
                for (int i = blocks[b].length-1; i >= 0; i--){
                    for(int j = blocks[b][i].length-1; j >= 0; j--){
                        int bit = j - rowKey[l][i];
                        if(bit < 0){
                            blocks1[b][i][j] = blocks[b][i][(j - rowKey[l][i])+4];
                        }else{
                            blocks1[b][i][j] = blocks[b][i][j - rowKey[l][i]];
                        }
                    }
                }
            }

            // check row
            
            for(int b = 0; b < blocks1.length; b++){
                for(int i = 0; i < blocks1[b].length; i++){
                    for(int j = 0; j < blocks1[b][i].length; j++){
                        System.out.print(blocks1[b][i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("row");
                System.out.println();
            }
            
            l--;
        }

        return blocks;
     }
}

