public class Printer {
    private LinkedQueue<PrintJob> queue = new LinkedQueue<>();      //queue to hold print jobs waiting to print on this printer
    private PrintJob currJob;

    private int printerNum;     //printer number
    private String printerStatus = "offline";   //flags for printer status (online/offline, printing/idle)
    private double speed;      //speed of printer (bytes per second)
    
    private int jobNum = -1;         //number of print job currently being processed
    private int finishTime = 0;     //time at which the current job will finish
    private int totalSize = 0;      //total size of all the jobs waiting to print

    //Constructor
    public Printer(int printerNum, int speed) {
        this.printerNum = printerNum;
        this.speed = speed;
    }

    //when starting a print job you must set the printer to "printing", calculate the end time of the job, set the number of the job being printed, and update the total number of bytes of all jobs in the queue; you need the current time so you can calculate the end time 
    public void startPrintJob(int currTime) {
        currJob = queue.dequeue();
        jobNum = currJob.getJobNum();     //updates jobNum to that of job currently being printed
        this.printerStatus = "printing";            //changed printer status to printing
        finishTime = currTime + (int) Math.ceil(currJob.getNumBytes()/speed);      //end time of job
        totalSize -= currJob.getNumBytes();   //remove size of job from total size of queue

        System.out.println("Time " + currTime + ": Printer " + printerNum + " printing job " + jobNum);
        //System.out.println("finishTime: " + finishTime);
    }

    //adds a print job to the printer's queue and updates the total number of bytes of all jobs in the queue
    public void addPrintJob(PrintJob job, int currTime) {
        queue.enqueue(job);     //adds job to queue
        totalSize += job.getNumBytes();         //updates the total number of bytes in the queue
        
        System.out.println("Time " + currTime + ": Print job number " + job.getJobNum() + " queued for printer " + printerNum);
    }

    public PrintJob cancelPrintJob(PrintJob job, int currTime) {
        PrintJob removedJob = queue.cancel(job);

        if(removedJob != null) //job was not found in queue
            totalSize -= removedJob.getNumBytes();
        return removedJob;
    }

    public LinkedQueue<PrintJob> getAllJobs() {   //dequeues from queue
        return queue;
        //System.out.println("Print Job " + jobNum + " removed from Printer " + printerNum);
    }

    //getter method for current job that is printing
    public PrintJob getCurrJob() {
        return this.currJob;
    }

    //getter method for printer number
    public int getPrinterNum() {
        return this.printerNum;
    }

    //getter method for job number of job that is currently printing
    public int getCurrentJobNum() {
        return this.jobNum;
    }

    //getter method for total number of bytes to print
    public int getTotalSize() {
        return this.totalSize;
    }

    //getter method for finish time of current job that is printing
    public int getFinishTime() {
        return this.finishTime;
    }
    
    //getter method for printer status
    public String getPrinterStatus() {
        return this.printerStatus;
    }

    //setter method for printer status
    public void setPrinterStatus(String newStatus) {
        printerStatus = newStatus;
    }

    //setter method for current print job
    public void setCurrJob(PrintJob newJob) {
        this.currJob = newJob;
        this.jobNum = newJob.getJobNum();
    }

}