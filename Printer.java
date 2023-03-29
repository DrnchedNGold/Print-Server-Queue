public class Printer {
    private int printerNum;     //printer number
    private String printerStatus = "offline";   //flags for printer status (online/offline, printing/idle)
    private int speed;      //speed of printer (bytes per second)
    
    private int jobNum;         //number of print job currently being processed
    private int finishTime;     //time at which the current job will finish
    private int totalSize;      //total size of all the jobs waiting to print

    LinkedQueue<PrintJob> queue = new LinkedQueue<>();      //queue to hold print jobs waiting to print on this printer

    //Constructor
    public Printer(int printerNum, int speed) {
        this.printerNum = printerNum;
        this.printerStatus = "online";
        this.speed = speed;
    }

    //checks if two printers are equal by comparing the printer number
    public boolean equals(Printer printer2) {   
        if (this.printerNum == printer2.getPrinterNum())
            return true;
        return false;
    }

    //getter method for printer number
    public int getPrinterNum() {
        return this.printerNum;
    }

    public void setPrinterStatus(String newStatus) {
        printerStatus = newStatus;
    }

}