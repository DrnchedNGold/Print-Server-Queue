import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrinterDriver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner printerFile = new Scanner(new File("printers.dat"));
        PrintServer pServer = new PrintServer(printerFile);
        Scanner printJobsFile = new Scanner(new File("printjobs.dat"));
        
        int currTime = 0, time = 0, num = 0;
        String command;

        //time = printJobsFile.nextInt();
        while (printJobsFile.hasNext()) {
            System.out.println(printJobsFile.nextLine());
            currTime++;
        }
        
    }
}