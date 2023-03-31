import java.util.Scanner;

public class PrintServer {
    private Printer[] printerArray;     //array of printers

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
        int leastPrinterNum = findLeastPrinter();
        //send printJob to printer
        printerArray[leastPrinterNum].addPrintJob(pJob, currTime);
    }

    // remove the job from each print queue
    public void cancelJob(int jobNum, int currTime) {

    }

    // take a printer offline: the job (if any) that was printing is terminated, and any jobs waiting in that printer's queue are distributed to the other printers. Each job that is deleted from the offline printer's queue should be sent to the printer that has the shortest queue. (calculate which queue is the shortest for each print job)
    public void takeOffline(int printerNum, int currTime) {
        Printer printer = printerArray[printerNum];
        int leastPrinterNum = -1;

        if(printer.getPrinterStatus().equals("printing")) {     //if printer is printing then terminate that print job
            System.out.println("Time " + currTime + ": Printer " + printerNum + " offline, Print job " + printer.getCurrentJobNum() + " not completed");
            //printer.setCurrJob(new PrintJob(-1, 0, null));
        }
        else {
            System.out.println("Time " + currTime + ": Printer " + printerNum + " offline");
        }

        printerArray[printerNum].setPrinterStatus("offline");   //set printer status to offline

        if (printerArray[printerNum].getTotalSize() > 0) {  //check if there any print jobs in queue and redistribute them based on smallest queue size
            LinkedQueue<PrintJob> LLjobs = printer.getAllJobs();
            while(!LLjobs.isEmpty()) {
                leastPrinterNum = findLeastPrinter();
                printerArray[leastPrinterNum].addPrintJob(LLjobs.dequeue(), currTime);
            }
        }
    }

    //check each printer to see if it has a print job finished at the current time. If so, then check each printer to see if it has a print job in queue that can start at the current time.
    public void checkPrinters(int currTime) {
        for(Printer printer: printerArray) {
            if(!printer.getPrinterStatus().equals("offline")){   //checks if printer is not offline
                //System.out.println("Printer " + printer.getPrinterNum() + " finish time: " + printer.getFinishTime());
                if(printer.getFinishTime() == currTime) {   //checks if the printer has finished printing current job
                    System.out.println("Time " + currTime + ": Printer " + printer.getPrinterNum() + " completed job " + printer.getCurrentJobNum());

                    printer.setPrinterStatus("idle");         //update printer status
                }
                if(printer.getPrinterStatus().equals("online") || printer.getPrinterStatus().equals("idle")) {
                    //check queue if there another job to start
                    if(printer.getTotalSize() > 0)
                        printer.startPrintJob(currTime);
                }
            }
        }
    }

    private int findLeastPrinter() {
        //find the printer with the least number of bytes to print
        int leastBytes = -1;        //initialize leastBytes to size of first printer
        int leastBytesNoPrinting = -1;                  
        int leastPrinterNum = -1;                                //initialize leastPrinterNum to first printer
        int leastPrinterNoPrinting = -1;
        //boolean allPrinting = true;                    //true if all printers are printing
        
        for(int i = 0; i < printerArray.length; i++) {      //loop through array of printers
            if(!printerArray[i].getPrinterStatus().equals("offline")) {    //checks if printer is not offline
                //System.out.println("Printer " + i + " status: " + printerArray[i].getPrinterStatus());
                if(!printerArray[i].getPrinterStatus().equals("printing")) {
                    if(leastPrinterNoPrinting == -1) {
                        leastBytesNoPrinting = printerArray[i].getTotalSize();
                        leastPrinterNoPrinting = i;
                    }
                    if(printerArray[i].getTotalSize() < leastBytesNoPrinting) {
                        leastPrinterNoPrinting = i;
                    }
                    //System.out.println("Printer " + i + " status: " + printerArray[i].getPrinterStatus() + " leastPrinterNoPrinting: " + leastPrinterNoPrinting);
                } 
                else {  //if all are printing then leastPrinterNoPrinting will be -1
                    if(leastBytes == -1) {
                        leastBytes = printerArray[i].getTotalSize();
                        leastPrinterNum = i;
                    }
                    if(printerArray[i].getTotalSize() < leastBytes) {
                        leastPrinterNum = i;
                    }
                    //System.out.println("Printer " + i + " status: " + printerArray[i].getPrinterStatus() + " leastPrinterNum: " + leastPrinterNum);
                }
            }
        }
        if(leastPrinterNoPrinting == -1)
            return leastPrinterNum;
        else
            return leastPrinterNoPrinting;
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