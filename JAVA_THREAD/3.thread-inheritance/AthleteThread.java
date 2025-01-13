public class AthleteThread extends Thread {
    private int bibNumber;
    public AthleteThread(int bibNumber,int priority,String name){
        this.setName(name);
        this.bibNumber=bibNumber;
        this.setPriority(priority);
    }

    public int getBibNumber() {
        return this.bibNumber;
    }

    public void setBibNumber(int bibNumber) {
        this.bibNumber = bibNumber;
    }
    @Override
    public void run(){
        double dist=10;
        double steps=0.1;
        for(double i=0;i<=dist;i+=steps){
            if(Math.abs(i-dist)<steps){
                System.out.println(bibNumber+"Race done"+this.getName());
                break;
            }
        }
    }
}