public class PrintJob {
    private int jobNum;     //print job number
    private int numBytes;   //number of bytes in job
    private String ownerLogonID;  //logon id of the owner of the job

    //Constructor
    public PrintJob(int jobNum, int numBytes, String ownerLogonID) {
        this.jobNum = jobNum;
        this.numBytes = numBytes;
        this.ownerLogonID = ownerLogonID;
    }

    //getter method for job number
    public int getJobNum() {
        return this.jobNum;
    }

    public int getNumBytes() {
        return this.numBytes;
    }

    //replace with commented section if it doesn't work
    public boolean equals(PrintJob job2) {   //compares the job numbers of two print jobs
        if (this.jobNum == job2.getJobNum())
            return true;
        return false;
    }

    /*
    public boolean equals(Object job2) {   //compares the job numbers of two print jobs
        if(job2 == null || !(job2 instanceof PrintJob))
            return false;
        if (this.jobNum == ((PrintJob)job2).getJobNum())
            return true;
        return false;
    }
    */

    //temp toString
    public String toString() {
        return "jobNum: " + jobNum + " numBytes: " + numBytes + " owner: " + ownerLogonID;
    }
}
