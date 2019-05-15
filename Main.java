import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        BigInteger res[]=new BigInteger[1];
        DistributedFactorial df = new DistributedFactorial(100000, 0, res,0);

        

        BigInteger res_thread[]=new BigInteger[4];
        Thread tr[] = new Thread[4];
        
        long startTimeThread=System.nanoTime();
        // tr[0]=new Thread(new DistributedFactorial(100000,81001,res_thread,0));
        // tr[0].start();

        // tr[1]=new Thread(new DistributedFactorial(81000,59001,res_thread,1));
        // tr[1].start();

        // tr[2]=new Thread(new DistributedFactorial(59000,36001,res_thread,2));
        // tr[2].start();

        // tr[3]=new Thread(new DistributedFactorial(36000,0,res_thread,3));
        // tr[3].start();

        tr[0]=new Thread(new DistributedFactorial(100000,75001,res_thread,0));
        tr[0].start();

        tr[1]=new Thread(new DistributedFactorial(75000,50001,res_thread,1));
        tr[1].start();

        tr[2]=new Thread(new DistributedFactorial(50000,25001,res_thread,2));
        tr[2].start();

        tr[3]=new Thread(new DistributedFactorial(25000,0,res_thread,3));
        tr[3].start();

        try{
            tr[0].join();
            tr[1].join();
            res_thread[0]=res_thread[0].multiply(res_thread[1]);
            tr[2].join();
            res_thread[0]=res_thread[0].multiply(res_thread[2]);
            tr[3].join();
            res_thread[0]=res_thread[0].multiply(res_thread[3]);
        }
        catch(Exception e){}

        long endTimeThread=System.nanoTime();
        System.out.println(endTimeThread-startTimeThread+ " nanoseconds distributed");


        long startTime=System.nanoTime();
        df.run();
        long endTime=System.nanoTime();

        System.out.println(endTime-startTime + " nanoseconds standard");
    }
}
