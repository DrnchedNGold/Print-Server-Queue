import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PrinterDriver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner printerFile = new Scanner(new File("printers.dat"));
        PrintServer pServer = new PrintServer(printerFile);
        Scanner printJobsFile = new Scanner(new File("printjobs.dat"));
        int currTime = 0;

//        int time = printJobsFile.nextInt();
//        String command = printJobsFile.next();
//        int num = printJobsFile.nextInt();
        int time = 0, num = 0;
        String command;

        int size;
        String owner, str;

        //System.out.println("time: " + time + " " + command + " " + num);
        
        time = printJobsFile.nextInt();
        while (printJobsFile.hasNext() && currTime < 5) {
            if (currTime == time) {     
                command = printJobsFile.next();
                num = printJobsFile.nextInt();
                System.out.print("time: " + time + " " + command + " " + num);
                switch(command) {
                    case "o":
                        //bring printer online
                        size = printJobsFile.nextInt();
                        System.out.println(" " + size);
                        pServer.activatePrinter(num, currTime);
                        break;
                    case "p":
                        //print print job
                        size = printJobsFile.nextInt();
                        owner = printJobsFile.next();
                        System.out.println(" " + size + " " + owner);

                        PrintJob job = new PrintJob(num, size, owner);
                        pServer.print(job, currTime);
                        break;
                    case "f":
                        //take printer offline
                        System.out.println();
                        break;
                    case "c":
                        //cancel printer
                        System.out.println();
                        break;
                }
                pServer.checkPrinters(currTime);
                //go to next line and get new time
                try {
                    time = printJobsFile.nextInt();
                } catch (NoSuchElementException e) {
                    break;
                }
            }
            else {
                currTime++;
                System.out.println("\ncurrTime: " + currTime);
            }
        }

        pServer.printPrinterQueues();
    }
}