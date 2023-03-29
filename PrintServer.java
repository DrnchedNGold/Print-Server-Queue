import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrintServer {
    private final int DEFCAP = 5;       //default capacity
    private int numPrinters;            //original capacity
    private Printer[] printerArray = new Printer[DEFCAP];     //array of printers

    //Constructor
    //add conditions for if the file is empty
    public PrintServer(Scanner printersFile) {
        numPrinters = printersFile.nextInt();       //gets number of printers from file
        printerArray = new Printer[numPrinters];    //makes new array with adjusted size
        for(int i = 0; i < numPrinters; i++) {
            printerArray[i] = new Printer(i, printersFile.nextInt());   //adds new printer obj to array with printerNum and speed
        }
    }

    //check if necessary
    //bring a printer online
    public void activatePrinter(int printerNum, int currTime) {     
        //find printer using printer number and print activation message
        for(Printer i: printerArray) {
            if (i.getPrinterNum() == printerNum) {
                i.setPrinterStatus("online");
                //message
                System.out.println("Time " + currTime + ": Printer " + printerNum + " online");
            }
        }
    }

    //choose printer to print the job (send the job to the printer which has the smallest queue (least number of bytes to print))
    public void print(PrintJob pJob, int currTime) {
        //find the printer with the smallest queue
        int leastQueueBytes = printerArray[0].getTotalSize();
        int leastQueueNum = 0;
        for(Printer i: printerArray) {
            if(i.getTotalSize() < leastQueueBytes)
                leastQueueNum = i.getPrinterNum();
        }
        //add print job to printer queue
        printerArray[leastQueueNum].addPrintJob(pJob, currTime);
    }

    // remove the job from each print queue
    public void cancelJob(int jobNum, int currTime) {
        PrintJob temp = new PrintJob(jobNum, 0, " ");
        for(Printer i: printerArray) {
            i.removePrintJob(temp);
        }
    }

    // take a printer offline: the job (if any) that was printing is terminated, and any jobs waiting in that printer's queue are distributed to the other printers. Each job that is deleted from the offline printer's queue should be sent to the printer that has the shortest queue. (calculate which queue is the shortest for each print job)
    public void takeOffline(int printerNum) {

    }

    //check each printer to see if it has a print job finished at the current time. If so, then check each printer to see if it has a print job in queue that can start at the current time.
    public void checkPrinters(int currTime) {
        System.out.println("here1");
        for(Printer i: printerArray) {
            System.out.println("totalSize: " + i.getTotalSize());
            System.out.println("finishTime: " + i.getFinishTime());
            if (i.getFinishTime() == currTime) {    //checks if the printer has finished its print job at the current time
                System.out.println("here2");
                try {
                    i.removePrintJob(null);             //dequeues job
                } catch (QueueUnderflowException e) {
                    System.out.println("queue underflow");  //try catch instead of checking if  printer queue is empty
                }
                
                if (i.getTotalSize() > 0) {         //checks if there is another job in the queue
                    //if there is another job in the queue then start it
                    i.startPrintJob(null, currTime);    //starts the next print job in the queue
                }
            }
        }
    }

    public void printPrinterQueues() {
        for (Printer i : printerArray) {
            i.printQueue();
        }
    }

}