public class PrintJob {
    private int jobNum;     //print job number
    private int numBytes;   //number of bytes in job
    private String ownerLogonID;  //logon id of the owner of the job

    public PrintJob() {

    }

    //getter method for job number
    public int getJobNum() {
        return this.jobNum;
    }

    public boolean equals(PrintJob job2) {   //compares the job numbers of two print jobs
        if (this.jobNum == job2.getJobNum())
            return true;
        return false;
    }
}
