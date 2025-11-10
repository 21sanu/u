import java.io.*;
import java.util.*;

public class Macropass1Simple {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs.txt"));
        FileWriter mntFile = new FileWriter("mnt.txt");
        FileWriter mdtFile = new FileWriter("mdt.txt");
        FileWriter interFile = new FileWriter("intermediate.txt");

        String line;
        boolean inMacro = false;
        int mdtp = 1;  // MDT pointer
        int mntp = 1;  // MNT pointer
        String currentMacro = "";

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.equalsIgnoreCase("MACRO")) {
                // Start of a macro definition
                inMacro = true;

                // Next line is the macro name and its parameters
                line = br.readLine().trim();
                String[] parts = line.split("\\s+", 2);
                currentMacro = parts[0];

                // Write macro name into MNT
                mntFile.write(mntp + "\t" + currentMacro + "\t" + mdtp + "\n");
                mntp++;
            }
            else if (line.equalsIgnoreCase("MEND")) {
                // End of macro
                mdtFile.write(mdtp + "\tMEND\n");
                mdtp++;
                inMacro = false;
            }
            else if (inMacro) {
                // Inside macro definition — write each line to MDT
                mdtFile.write(mdtp + "\t" + line + "\n");
                mdtp++;
            }
            else if (!line.isEmpty()) {
                // Lines outside macros go to intermediate file
                interFile.write(line + "\n");
            }
        }

        br.close();
        mntFile.close();
        mdtFile.close();
        interFile.close();

        System.out.println("✅ Pass-I completed (ALA removed): MNT, MDT, and Intermediate code generated.");
    }
}
