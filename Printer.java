public class Printer {
    private int printerNum;     //printer number
    private String printerStatus = "offline";   //flags for printer status (online/offline, printing/idle)
    private int speed;      //speed of printer (bytes per second)
    
    private int jobNum;         //number of print job currently being processed
    private int finishTime;     //time at which the current job will finish
    private int totalSize;      //total size of all the jobs waiting to print

    private LinkedQueue<PrintJob> queue = new LinkedQueue<>();      //queue to hold print jobs waiting to print on this printer

    //Constructor
    public Printer(int printerNum, int speed) {
        this.printerNum = printerNum;
        this.speed = speed;
    }

    public void startPrintJob(PrintJob job, int currTime) {
        this.printerStatus = "printing";
        if (job == null) {
            job = queue.front.getInfo();
        }
        jobNum = job.getJobNum();
        totalSize -= job.getNumBytes();         //updates the total number of bytes in the queue
        finishTime = currTime + (totalSize/speed);
        //message
        System.out.println("Time " + currTime + ": Printer " + printerNum + " printing job " + job.getJobNum());
    }

    public void addPrintJob(PrintJob job, int currTime) {
        queue.enqueue(job);
        totalSize += job.getNumBytes();         //updates the total number of bytes in the queue
        finishTime = currTime + (totalSize/speed);
        //message
        System.out.println("Time " + currTime + ": Printer job number " + job.getJobNum() + " queued for printer " + printerNum);
    }

    //return boolean?
    //removes specific job from anywhere in printer queue
    public PrintJob removePrintJob(PrintJob job) {
        if (job != null) {
            totalSize -= job.getNumBytes();         //updates the total number of bytes in the queue
            return queue.cancel(job);               
        }
        else {  //removes oldest job in printer queue (from the front)
            totalSize -= queue.front.getInfo().getNumBytes();
            return queue.dequeue();                 //remove job from printer queue
        }
    }

    //checks if two printers are equal by comparing the printer number
    public boolean equals(Printer printer2) {   
        if (this.printerNum == printer2.getPrinterNum())
            return true;
        return false;
    }

    public void printQueue() {
        LLNode<PrintJob> temp = queue.front;
        System.out.println("PrinterNum: " + printerNum);
        while(temp != null) {
            System.out.println(temp.getInfo().toString());
            temp = temp.getLink();
        }
    }

    //getter method for printer number
    public int getPrinterNum() {
        return this.printerNum;
    }

    //getter method for total number of bytes in queue
    public int getTotalSize() {
        return this.totalSize;
    }

    //getter method for finish time of job
    public int getFinishTime() {
        return this.finishTime;
    }

    public void setPrinterStatus(String newStatus) {
        printerStatus = newStatus;
    }

}