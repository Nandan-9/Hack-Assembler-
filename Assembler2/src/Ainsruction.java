import java.util.ArrayList;
import java.util.HashMap;

public class Ainsruction {
    //    public static void main(String[] args) {
//        ArrayList<String> result = NoWhiteSpace.reader("AssemblyCode.asm");
//        int indexCounter = 0;
//        int counter = 16; // Changed variable name to lowercase 'counter'
//        for (String str : result) {
//            if ((str.charAt(0) != '@') && str.charAt(0) != '(') {
//            } else {
//                //System.out.println(str);
//
//                if (aInsruction(str, result, counter) != null) {
//                    result.set(indexCounter, aInsruction(str, result, counter));
//                }
//            }
//            indexCounter++;
//        }
//        System.out.println(result);
//    }
    public static String aInsruction(String data, ArrayList<String> list, int counter) {
        ListChecker checker = new ListChecker();
        HashMap<String, Integer> symbolTable = new HashMap<>();
        symbolTable.put("R0", 0);
        symbolTable.put("R1", 1);
        symbolTable.put("R2", 2);
        symbolTable.put("R3", 3);
        symbolTable.put("R4", 4);
        symbolTable.put("R5", 5);
        symbolTable.put("R6", 6);
        symbolTable.put("R7", 7);
        symbolTable.put("R8", 8);
        symbolTable.put("R9", 9);
        symbolTable.put("R10", 10);
        symbolTable.put("R11", 11);
        symbolTable.put("R12", 12);
        symbolTable.put("R13", 13);
        symbolTable.put("R14", 14);
        symbolTable.put("R15", 15);
        symbolTable.put("SP", 0);
        symbolTable.put("LCL", 1);
        symbolTable.put("ARG", 2);
        symbolTable.put("THIS", 3);
        symbolTable.put("THAT", 4);
        symbolTable.put("SCREEN", 16384);
        symbolTable.put("KBD", 24576);
        String instruction = data.substring(1);
        if (symbolTable.containsKey(instruction)) {
            return formatter(symbolTable.get(instruction));
        } else if (instruction.matches(".*\\d.*")) {
            return formatter(Integer.parseInt(instruction));
        } else if (symbolLabel(instruction, list) != -1) {
            return formatter(symbolLabel(instruction, list));
        } else if (Character.isLowerCase(instruction.charAt(0))) {
            //System.out.println(checker.checkAndAppend(instruction) + " " + instruction);
            return formatter(checker.checkAndAppend(instruction));
        }
        return null;
    }

    public static String formatter(int x) {
        String formatted = Integer.toBinaryString(x);
        if (formatted.length() < 16) {
            formatted = String.format("%16s", formatted).replaceAll(" ", "0");
        }
        return formatted;
    }

    private static int symbolLabel(String instruction, ArrayList<String> list) {
        for (String str : list) {
            if (str.charAt(0) == '(') {
                if (str.substring(1, str.length() - 1).equals(instruction)) {
                    return list.indexOf(str) + 1;
                }
            }
        }
        return -1;
    }
}
