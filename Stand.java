import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Stand {

    public static void main(String[] args) {
        BigInteger res[]=new BigInteger[1];
        DistributedFactorial df = new DistributedFactorial(10000, 0, res,0);

        long startTime=System.nanoTime();
        df.run();
        long endTime=System.nanoTime();

        System.out.println(endTime-startTime + " nanoseconds standard");
    }
}
