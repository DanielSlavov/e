import java.math.BigInteger;

public class Distributor{

    private static void distribute(int n,int m,int threads,int args[]){
        for(int i=0;i<=threads;i++){
            args[threads-i]=m+(int)Math.ceil((n-m)*((double)i/threads));
        }
        //args[0]=m-1;
    }

    public static BigInteger DistributedFactorial(int n,int m,int threadNum){
        while(n<2*threadNum && threadNum>1){
            threadNum=(int)Math.ceil((double)threadNum/2);

        }
        int args[]=new int[threadNum+1];
        distribute(n,m-1,threadNum,args);
        BigInteger res[]=new BigInteger[threadNum];

        Thread threads[]=new Thread[threadNum];
        for(int i=0;i<threadNum;i++){
            threads[i]=new Thread(new Factorial(args[i],args[i+1]+1,res,i));
            threads[i].start();
        }

        try{
            threads[0].join();
            for(int i=1;i<threadNum;i++){
                threads[i].join();
                res[0]=res[0].multiply(res[i]);
            }
        }catch(Exception e){}


        return res[0];
    }


}