import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;


public class Main {

    public static BigDecimal eSumDist_optimized(int n,int threads){
        BigInteger fact=BigInteger.valueOf(1);
        BigDecimal result=BigDecimal.valueOf(2*0 +1).divide(new BigDecimal(fact),n,RoundingMode.HALF_UP);
        for(int i=1;i<n;i++){
            fact=fact.multiply(Distributor.DistributedFactorial(2*i,2*(i-1)+1,threads));
            result=result.add(BigDecimal.valueOf(2*i +1).divide(new BigDecimal(fact),n,RoundingMode.HALF_UP));
        }
        return result;
    }

    public static BigDecimal eSum_optimized(int n){
        BigInteger fact=BigInteger.valueOf(1);
        BigDecimal result=BigDecimal.valueOf(2*0 +1).divide(new BigDecimal(fact),n,RoundingMode.HALF_UP);
        for(int i=1;i<n;i++){
            fact=fact.multiply(Factorial.factIter(2*i,2*(i-1)+1));
            result=result.add(BigDecimal.valueOf(2*i +1).divide(new BigDecimal(fact),n,RoundingMode.HALF_UP));
        }
        return result;
    }

    public static BigDecimal eSumDist(int n,int threads){
        BigDecimal result=BigDecimal.ONE;
        for(int i=1;i<n;i++){
            result=result.add(BigDecimal.valueOf(2*i +1).divide(new BigDecimal(Distributor.DistributedFactorial(2*i,1,threads)),n,RoundingMode.HALF_UP));
        }
        return result;
    }
    public static BigDecimal eSum(int n){
        BigDecimal result=BigDecimal.ONE;
        for(int i=1;i<n;i++){
            result=result.add(BigDecimal.valueOf(2*i +1).divide(new BigDecimal(Factorial.factIter(2*i,1)),n,RoundingMode.HALF_UP));
        }
        return result;
    }


    public static void main(String[] args) {

        int n=1000;
        int threads=4;
        for(int i=0;i<args.length;i++){
            if(args[i].compareTo("-n")==0){
                n=Integer.parseInt(args[i+1]);
            }
            if(args[i].compareTo("-t")==0){
                threads=Integer.parseInt(args[i+1]);
            }
        }

        long startTime=System.nanoTime();
        eSumDist_optimized(n,threads);
        long endTime=System.nanoTime();
        System.out.println((double)(endTime-startTime)/1_000_000_000.0+"  threaded optimized");


        startTime=System.nanoTime();
        eSumDist(n,threads);
        endTime=System.nanoTime();
        System.out.println((double)(endTime-startTime)/1_000_000_000.0+"  threaded");

        startTime=System.nanoTime();
        eSum_optimized(n);
        endTime=System.nanoTime();
        System.out.println((double)(endTime-startTime)/1_000_000_000.0+"  optimized");

        startTime=System.nanoTime();
        eSum(n);
        endTime=System.nanoTime();
        System.out.println((double)(endTime-startTime)/1_000_000_000.0);










//        int n=100000;
//        long startTimeThread=System.nanoTime();
//        Distributor.DistributedFactorial(n,4);
//        long endTimeThread=System.nanoTime();
//        System.out.println((double)(endTimeThread-startTimeThread)/1_000_000_000.0+ " distributed");
//
//        long startTime=System.nanoTime();
//        Factorial.factIter(n,1);
//        long endTime=System.nanoTime();
//        System.out.println((double)(endTime-startTime)/1_000_000_000.0+ " distributed");



























//        BigInteger res[]=new BigInteger[1];
//        Factorial df = new Factorial(100000, 0, res,0);
//
//
//
//        BigInteger res_thread[]=new BigInteger[4];
//        Thread tr[] = new Thread[4];
//
//        long startTimeThread=System.nanoTime();
//        // tr[0]=new Thread(new DistributedFactorial(100000,81001,res_thread,0));
//        // tr[0].start();
//
//        // tr[1]=new Thread(new DistributedFactorial(81000,59001,res_thread,1));
//        // tr[1].start();
//
//        // tr[2]=new Thread(new DistributedFactorial(59000,36001,res_thread,2));
//        // tr[2].start();
//
//        // tr[3]=new Thread(new DistributedFactorial(36000,0,res_thread,3));
//        // tr[3].start();
//
//        tr[0]=new Thread(new Factorial(1000000,750001,res_thread,0));
//        tr[0].start();
//
//        tr[1]=new Thread(new Factorial(750000,500001,res_thread,1));
//        tr[1].start();
//
//        tr[2]=new Thread(new Factorial(500000,250001,res_thread,2));
//        tr[2].start();
//
//        tr[3]=new Thread(new Factorial(250000,0,res_thread,3));
//        tr[3].start();
//
//        try{
//            tr[0].join();
//            tr[1].join();
//            res_thread[0]=res_thread[0].multiply(res_thread[1]);
//            tr[2].join();
//            res_thread[0]=res_thread[0].multiply(res_thread[2]);
//            tr[3].join();
//            res_thread[0]=res_thread[0].multiply(res_thread[3]);
//        }
//        catch(Exception e){}
//
//        long endTimeThread=System.nanoTime();
//        System.out.println(endTimeThread-startTimeThread+ " nanoseconds distributed");
//
//
//        long startTime=System.nanoTime();
//        df.run();
//        long endTime=System.nanoTime();
//
//        System.out.println(endTime-startTime + " nanoseconds standard");
    }
}
