import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PrinterDriver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner printerFile = new Scanner(new File("printers.dat"));
        PrintServer pServer = new PrintServer(printerFile);
        Scanner printJobsFile = new Scanner(new File("printjobs.dat"));
        
        int currTime = 0, time = 0, num = 0, size = 0;
        String command, owner;

        time = printJobsFile.nextInt();
        while (printJobsFile.hasNext()) {
            System.out.println("currTime: " + currTime);
            if(currTime == time) {
                command = printJobsFile.next();
                num = printJobsFile.nextInt();
                System.out.print(time + " " + command + " " + num);
                switch(command) {
                    case "o": 
                        //The print server should bring the printer online, so print jobs can be added to its queue and it can start printing them.
                        size = printJobsFile.nextInt();
                        System.out.println(" " + size);
                        break;
                    case "p":
                        //get input for size and owner logonId and put new print job into a print queue
                        size = printJobsFile.nextInt();
                        owner = printJobsFile.next();
                        PrintJob newJob = new PrintJob(num, size, owner);
                        System.out.println(" " + size + " " + owner);
                        break;
                    case "f":
                        //make a printer go offline
                        System.out.println();
                        break;
                    case "c":
                        //cancel a printjob from each printer
                        System.out.println();
                        break;
                }

                try {
                    time = printJobsFile.nextInt();
                } catch (NoSuchElementException e) {
                    System.out.println("no such element");
                }
            } else {
                currTime++;
            }
        }
        
    }
}