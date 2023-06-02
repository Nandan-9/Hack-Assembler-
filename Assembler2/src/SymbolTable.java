import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public class SymbolTable {
    public static void main(String[] args) {
        HashMap<String, String> symbolTable = new HashMap<>();
        symbolTable.put("R0", "0");
        symbolTable.put("R1", "1");
        symbolTable.put("R2", "2");
        symbolTable.put("R3", "3");
        symbolTable.put("R4", "4");
        symbolTable.put("R5", "5");
        symbolTable.put("R6", "6");
        symbolTable.put("R7", "7");
        symbolTable.put("R8", "8");
        symbolTable.put("R9", "9");
        symbolTable.put("R10", "10");
        symbolTable.put("R11", "11");
        symbolTable.put("R12", "12");
        symbolTable.put("R13", "13");
        symbolTable.put("R14", "14");
        symbolTable.put("R15", "15");
        symbolTable.put("SP", "0");
        symbolTable.put("LCL", "1");
        symbolTable.put("ARG", "2");
        symbolTable.put("THIS", "3");
        symbolTable.put("THAT", "4");
        symbolTable.put("SCREEN", "16384");
        symbolTable.put("KBD", "24576");
            NoWhiteSpace FileReader = new NoWhiteSpace();
            ArrayList<String> result = FileReader.reader("AssemblyCode.asm");
            for(String str: result){
                if(identifier(str) == true){
                    System.out.println(str);
                }
            }
    }
    public static boolean identifier(String result){
        if(result.charAt(0) == '@'){
            if(Character.isUpperCase(result.charAt(1))){
                return true;
            } else if (result.charAt(1) == 'R' || Character.isLowerCase(result.charAt(1))) {
                return true;
            }
        } else if (result.charAt(0) == '(') {
            return true;
        }
        return false;
    }

}
