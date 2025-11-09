import java.io.*;
import java.util.*;

public class Macropass1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs.txt"));
        FileWriter mntFile = new FileWriter("mnt.txt");
        FileWriter mdtFile = new FileWriter("mdt.txt");
        FileWriter alaFile = new FileWriter("ala.txt");
        FileWriter interFile = new FileWriter("intermediate.txt");

        String line;
        boolean inMacro = false;
        int mdtp = 1;  // MDT pointer
        int mntp = 1;  // MNT pointer

        LinkedHashMap<String, Integer> ALA = new LinkedHashMap<>();
        String currentMacro = "";

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.equalsIgnoreCase("MACRO")) {
                inMacro = true;
                line = br.readLine().trim();  // Macro header
                String[] parts = line.split("\\s+");
                currentMacro = parts[0];
                ALA.clear();
                int paramCounter = 1;

                // Process parameters
                if (parts.length > 1) {
                    String[] params = parts[1].split(",");
                    for (String p : params) {
                        p = p.trim();
                        if (p.startsWith("&")) p = p.substring(1);
                        ALA.put(p, paramCounter++);
                    }
                }

                // Write MNT
                mntFile.write(mntp + "\t" + currentMacro + "\t" + mdtp + "\n");
                mntp++;

                // Write ALA
                alaFile.write("ALA for " + currentMacro + ":\n");
                for (Map.Entry<String, Integer> e : ALA.entrySet()) {
                    alaFile.write(e.getKey() + " -> #" + e.getValue() + "\n");
                }
                alaFile.write("\n");
            }
            else if (line.equalsIgnoreCase("MEND")) {
                mdtFile.write(mdtp + "\tMEND\n");
                mdtp++;
                inMacro = false;
            }
            else if (inMacro) {
                // Replace parameters with #index
                StringBuilder modified = new StringBuilder();
                String[] words = line.split("\\s+|,");
                for (String w : words) {
                    w = w.trim();
                    if (w.startsWith("&")) {
                        String param = w.substring(1);
                        modified.append("#").append(ALA.get(param)).append(" ");
                    } else {
                        modified.append(w).append(" ");
                    }
                }
                mdtFile.write(mdtp + "\t" + modified.toString().trim() + "\n");
                mdtp++;
            }
            else if (!line.isEmpty()) {
                // Non-macro lines → intermediate code
                interFile.write(line + "\n");
            }
        }

        br.close();
        mntFile.close();
        mdtFile.close();
        alaFile.close();
        interFile.close();

        System.out.println("✅ Pass-I completed: MNT, MDT, ALA, and Intermediate code generated.");
    }
}
