import java.util.HashMap;
import java.util.Map;

public class Cinstruction {
    public static Map<String, Map<String, int[]>> comp = new HashMap<>();
    public static Map<String, int[]> dest = new HashMap<>();
    public static Map<String, int[]> jump = new HashMap<>();

    //    public static void main(String[] args) {
//        NoWhiteSpace fileReader = new NoWhiteSpace();
//        ArrayList<String> result = fileReader.reader("AssemblyCode.asm");
//
//        // Manipulate or use the returned string array as desired
//        int indexCounter = 0;
//        for (String str : result) {
//            if((str.charAt(0) != '@') && str.charAt(0) != '(') {
//               int[] array =  instruction(str , indexCounter);
//               String output ="";
//               for(int i = 0; i< array.length;i++){
//                   output += Integer.toString(array[i]);
//               }
//                System.out.println(output);
//               result.set(indexCounter,output);
//            }
//        }
//            indexCounter++;
//        }
    public static int[] instruction(String str, int index) {
        Comp();
        Jump();
        Dest();
        int[] array = new int[16];
        array[0] = 1;
        array[1] = 1;
        array[2] = 1;
        str = str.replaceAll("=", " ");
        str = str.replaceAll(";", " ");
        String[] command = str.split(" ", 0);
        //System.out.println(Arrays.toString(command));
        String d = command[0];
        if (dest.containsKey(d)) {
            int[] jumpArray = dest.get(d);
            array[10] = jumpArray[0];
            array[11] = jumpArray[1];
            array[12] = jumpArray[2];
        }
        try {
            String c = command[1];
            if (comp.get("0").containsKey(c)) {
                // System.out.println(Arrays.toString(comp.get("0").get(c)));
                int[] compArray = comp.get("0").get(c);
                array[3] = 0;
                array[4] = compArray[0];
                array[5] = compArray[1];
                array[6] = compArray[2];
                array[7] = compArray[3];
                array[8] = compArray[4];
                array[9] = compArray[5];
            } else {
                //System.out.println(comp.get("0").get(c));
                int[] compArray = comp.get("1").get(c);
                array[3] = 1;
                array[4] = compArray[0];
                array[5] = compArray[1];
                array[6] = compArray[2];
                array[7] = compArray[3];
                array[8] = compArray[4];
                array[9] = compArray[5];
            }
        } catch (Exception e) {

        }
        try {
            String j = command[2];
            if (jump.containsKey(j)) {
                int[] jumpArray = jump.get(j);
                array[13] = jumpArray[0];
                array[14] = jumpArray[1];
                array[15] = jumpArray[2];
            }

        } catch (Exception e) {
            String j = command[1];
            if (jump.containsKey(j)) {
                int[] jumpArray = jump.get(j);
                array[13] = jumpArray[0];
                array[14] = jumpArray[1];
                array[15] = jumpArray[2];
            }

        }
        return array;


    }


    public static void Comp() {
        Map<String, int[]> comp1 = new HashMap<>();
        comp1.put("M", new int[]{1, 1, 0, 0, 0, 0});
        comp1.put("!M", new int[]{1, 1, 0, 0, 0, 1});
        comp1.put("-M", new int[]{1, 1, 0, 0, 1, 1});
        comp1.put("M+1", new int[]{1, 1, 0, 1, 1, 1});
        comp1.put("M-1", new int[]{1, 1, 0, 0, 1, 0});
        comp1.put("D+M", new int[]{0, 0, 0, 0, 1, 0});
        comp1.put("D-M", new int[]{0, 1, 0, 0, 1, 1});
        comp1.put("M-D", new int[]{0, 0, 0, 1, 1, 1});
        comp1.put("D&M", new int[]{0, 0, 0, 0, 0, 0});
        comp1.put("D|M", new int[]{0, 1, 0, 1, 0, 1});

        Map<String, int[]> comp0 = new HashMap<>();
        comp0.put("0", new int[]{1, 0, 1, 0, 1, 0});
        comp0.put("1", new int[]{1, 1, 1, 1, 1, 1});
        comp0.put("-1", new int[]{1, 1, 1, 0, 1, 0});
        comp0.put("D", new int[]{0, 0, 1, 1, 0, 0});
        comp0.put("A", new int[]{1, 1, 0, 0, 0, 0});
        comp0.put("!D", new int[]{0, 0, 1, 1, 0, 1});
        comp0.put("!A", new int[]{1, 1, 0, 0, 0, 1});
        comp0.put("-D", new int[]{0, 0, 1, 1, 1, 1});
        comp0.put("-A", new int[]{1, 1, 0, 0, 1, 1});
        comp0.put("D+1", new int[]{0, 1, 1, 1, 1, 1});
        comp0.put("A+1", new int[]{1, 1, 0, 1, 1, 1});
        comp0.put("D-1", new int[]{0, 0, 1, 1, 1, 0});
        comp0.put("A-1", new int[]{1, 1, 0, 0, 1, 0});
        comp0.put("D+A", new int[]{0, 0, 0, 0, 1, 0});
        comp0.put("D-A", new int[]{0, 1, 0, 0, 1, 1});
        comp0.put("A-D", new int[]{0, 0, 0, 1, 1, 1});
        comp0.put("D&A", new int[]{0, 0, 0, 0, 0, 0});
        comp0.put("D|A", new int[]{0, 1, 0, 1, 0, 1});

        comp.put("1", comp1);
        comp.put("0", comp0);
    }

    public static void Dest() {
        dest.put("0", new int[]{0, 0, 0});
        dest.put("M", new int[]{0, 0, 1});
        dest.put("D", new int[]{0, 1, 0});
        dest.put("MD", new int[]{0, 1, 1});
        dest.put("A", new int[]{1, 0, 0});
        dest.put("AM", new int[]{1, 0, 1});
        dest.put("AD", new int[]{1, 1, 0});
        dest.put("AMD", new int[]{1, 1, 1});
    }

    public static void Jump() {
        jump.put("0", new int[]{0, 0, 0});
        jump.put("JGT", new int[]{0, 0, 1});
        jump.put("JEQ", new int[]{0, 1, 1});
        jump.put("JGE", new int[]{0, 1, 1});
        jump.put("JLT", new int[]{1, 0, 0});
        jump.put("JNE", new int[]{1, 0, 0});
        jump.put("JLE", new int[]{1, 1, 0});
        jump.put("JMP", new int[]{1, 1, 1});
    }


}