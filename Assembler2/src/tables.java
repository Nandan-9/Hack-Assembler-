import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

class symbol {
    static HashMap<String, Integer> table = new HashMap();
    //table=new HashMap();

    static {

        for (int i = 0; i < 16; i++) {
            table.put(("R" + i), i);
        }
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);
        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
    }
    public static void main(String[] args)throws Exception{


        BufferedReader reader = new BufferedReader(new FileReader("/mnt/E24C205E4C202FA9/semester/EOC/Hack-Assembler-/Assembler2/NoWhiteSpace.asm"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("sysmboltable.asm"));
        int c=1;
        int i=16;
        String st;
        while((st=reader.readLine())!=null){
            c++;
            if(st.contains("(")){
                System.out.println(st+c);
                table.put(st,c);

            }
            if(st.contains("@" )){
                char ch = st.charAt(1);
                if(Character.isLowerCase(ch)){
                    System.out.println(st);
                    table.putIfAbsent(st,i);
                    i++;}
            }}
        System.out.println(table);

        for (String key : table.keySet()) {
            int value = table.get(key);
            writer.write(key + ": " + value + "\n");
        }

        writer.close();

    }


}
public class tables extends symbol {
    public static void main(String[] args) {
        System.out.println(symbol.table);
        // Access the table HashMap from the symbol class
        System.out.println(symbol.table.get("R0"));
        System.out.println(symbol.table.get("@counter"));}
}