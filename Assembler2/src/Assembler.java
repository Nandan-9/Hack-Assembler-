import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Assembler {
    public static ArrayList<String> ConvertTOBinary(String filePath) {
        Ainsruction AInstruction = new Ainsruction();
        Cinstruction CInstruction = new Cinstruction();
        NoWhiteSpace fileReader = new NoWhiteSpace();
        ArrayList<String> result = NoWhiteSpace.reader(filePath);

        // Manipulate or use the returned string array as desired
        int indexCounter = 0;
        int[] array = new int[0];
        for (String str : result) {
            if ((str.charAt(0) != '@') && str.charAt(0) != '(') {
                array = Cinstruction.instruction(str, indexCounter);
                String output = "";
                for (int i = 0; i < array.length; i++) {
                    output += Integer.toString(array[i]);
                }
                //System.out.println(output + " " + str);
                result.set(indexCounter, output);
            } else {
                //System.out.println(str);
                int counter = 16;
                if (Ainsruction.aInsruction(str, result, counter) != null) {
                    result.set(indexCounter, Ainsruction.aInsruction(str, result, counter));
                    //System.out.println(Ainsruction.aInsruction(str, result, counter) + " " + str);
                }
            }
            indexCounter++;
        }
        result.remove("(LOOP)");
        result.remove("(INFINITE_LOOP)");

        return result;
    }
}