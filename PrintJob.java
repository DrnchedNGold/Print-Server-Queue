import javax.print.attribute.Size2DSyntax;

public class PrintJob {
    private int jobNum;     //print job number
    private int numBytes;   //number of bytes in job
    private String ownerLogonID;  //logon id of the owner of the job

    public PrintJob(int jobNum, int numBytes, String ownerLogonID) {
        this.jobNum = jobNum;
        this.numBytes = numBytes;
        this.ownerLogonID = ownerLogonID;
    }

    //getter method for job number
    public int getJobNum() {
        return this.jobNum;
    }

    //getter method for number of bytes
    public int getNumBytes() {
        return this.numBytes;
    }

    public boolean equals(Object job2) {   //compares the job numbers of two print jobs
        if(job2 == null || !(job2 instanceof PrintJob))
            return false;
        if (this.jobNum == ((PrintJob)job2).getJobNum())
            return true;
        return false;
    }
}
