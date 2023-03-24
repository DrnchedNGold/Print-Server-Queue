public class PrintServer {
    Printer[] printerArray;     //array of printers

    //Constructor
    public PrintServer() {

    }

    //check if necessary
    //bring a printer online
    public void activatePrinter(int printerNum, int currTime) {     
        //find printer using printer number and print activation message
        for(Printer i: printerArray) {
            if (i.getPrinterNum() == printerNum) {
                i.setPrinterStatus("online");
                System.out.println("Time " + currTime + ": Printer " + printerNum + " online");
            }
        }
    }

    //choose printer to print the job (send the job to the printer which has the smallest (least number of bytes to print))
    public void print(PrintJob pJob, int currTime) {

    }

    // remove the job from each print queue
    public void cancelJob(int jobNum, int currTime) {

    }

    // take a printer offline: the job (if any) that was printing is terminated, and any jobs waiting in that printer's queue are distributed to the other printers. Each job that is deleted from the offline printer's queue should be sent to the printer that has the shortest queue. (calculate which queue is the shortest for each print job)
    public void takeOffline(int printerNum) {

    }

    //check each printer to see if it has a print job finished at the current time. If so, then check each printer to see if it has a print job in queue that can start at teh current time.
    public void checkPrinters() {

    }

}