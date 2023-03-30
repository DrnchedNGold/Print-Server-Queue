import java.util.Scanner;

public class PrintServer {
    Printer[] printerArray;     //array of printers

    //Constructor
    public PrintServer(Scanner printerFile) {
        int numPrinters = 0, speed = 0;
        numPrinters = printerFile.nextInt();    //gets the number of printers from file
        //System.out.println("numPrinters: " + numPrinters);

        printerArray = new Printer[numPrinters];    //creates array to hold printers
        for(int i = 0; i < numPrinters; i++){
            speed = printerFile.nextInt();
            printerArray[i] = new Printer(i, speed);    //creates and initializes new printer
            //System.out.println("Printer " + i + " Speed: " + speed);
        }
        //System.out.println();
    }

    //check if necessary
    //bring a printer online
    public void activatePrinter(int printerNum, int currTime) {     
        //find printer using printer number and print activation message
        for(Printer printer: printerArray) {
            if (printer.getPrinterNum() == printerNum) {      //finds the right printer using printerNum
                printer.setPrinterStatus("online");   //change status of printer to online
                System.out.println("Time " + currTime + ": Printer " + printerNum + " online");
            }
        }
    }

    //choose printer to print the job (send the job to the printer which has the smallest (least number of bytes to print))
    public void print(PrintJob pJob, int currTime) {
        //find the printer with the least number of bytes to print
        int leastBytes = printerArray[0].getTotalSize();        //initialize leastBytes to size of first printer
        int leastPrinterNum = 0;                                //initialize leastPrinterNum to first printer
    //    int leastPrinterNoPrinting = -1;
        //boolean allPrinting = true;                    //true if all printers are printing
        
        for(int i = 0; i < printerArray.length; i++) {      //loop through array of printers
            if(!printerArray[i].printerStatus.equals("offline")) {    //checks if printer is not offline
                if(printerArray[i].getTotalSize() < leastBytes) {
                    leastPrinterNum = i;
                    System.out.println("leastPrinterNum: " + leastPrinterNum);
                }
            }
        }

        //send printJob to printer
        printerArray[leastPrinterNum].addPrintJob(pJob, currTime);
    }

    // remove the job from each print queue
    public void cancelJob(int jobNum, int currTime) {

    }

    // take a printer offline: the job (if any) that was printing is terminated, and any jobs waiting in that printer's queue are distributed to the other printers. Each job that is deleted from the offline printer's queue should be sent to the printer that has the shortest queue. (calculate which queue is the shortest for each print job)
    public void takeOffline(int printerNum) {

    }

    //check each printer to see if it has a print job finished at the current time. If so, then check each printer to see if it has a print job in queue that can start at the current time.
    public void checkPrinters(int currTime) {
        for(Printer printer: printerArray) {
            if(!printer.printerStatus.equals("offline")){   //checks if printer is not offline
                //System.out.println("Printer " + printer.getPrinterNum() + " finish time: " + printer.getFinishTime());
                if(printer.getFinishTime() == currTime) {   //checks if the printer has finished printing current job
                    System.out.println("Time " + currTime + ": Printer " + printer.getPrinterNum() + " completed job " + printer.getJobNum());

                    printer.printerStatus = "idle";         //update printer status
                }
                if(printer.printerStatus.equals("online") || printer.printerStatus.equals("idle")) {
                    //check queue if there another job to start
                    if(printer.getTotalSize() > 0)
                        printer.startPrintJob(currTime);
                }
            }
        }
    }

}

/*
 * for(int i = 0; i < printerArray.length; i++) {      //loop through array of printers
            if(!printerArray[i].printerStatus.equals("offline")) {    //checks if printer is not offline
                if(!printerArray[i].printerStatus.equals("printing")) {
                    allPrinting = false;
                    if(printerArray[i].getTotalSize() < leastBytes) {    //if printer size is lower than the lowest so far then it is the new leastPrinterNum
                        leastPrinterNum = i;
                    }
                } 
                else if(i == printerArray.length - 1) {    //if printing
                    if(printerArray[i].getTotalSize() < leastBytes) {    //if printer size is lower than the lowest so far then it is the new leastPrinterNum
                        leastPrinterNum = i;
                    }
                }
            }
        }
 */