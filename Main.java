

import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;




public class Main {
    
    public static BigInteger factorial(int n ,int m){
        if(n<2) {
            return BigInteger.valueOf(1);
        }
        if(m<1){
            m=1;
        }
        BigInteger result=BigInteger.valueOf(1);
        for(int i=m;i<=n;i++){
            result=result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigDecimal eSum(int n){
        BigInteger fact=BigInteger.valueOf(1);
        BigDecimal result=BigDecimal.valueOf(1);
        for(int i=1;i<n;i++){
            fact=fact.multiply(BigInteger.valueOf(4*i*i -2*i));
            result=result.add(BigDecimal.valueOf(2*i +1).divide(new BigDecimal(fact),n,RoundingMode.HALF_EVEN));
        }
        return result;
    }


   
    public static void main(String[] args) {

        int n=10000;
        int threadCount=4;
        double gran=1;
        boolean logs=false;

        for(int i=0;i<args.length;i++){
            if(args[i].compareTo("-n")==0){
                n=Integer.parseInt(args[i+1]);
            }
            if(args[i].compareTo("-t")==0){
                threadCount=Integer.parseInt(args[i+1]);
            }
            if(args[i].compareTo("-g")==0){
                gran=Double.parseDouble(args[i+1]);
            }
            if(args[i].compareTo("-logs")==0){
                logs=Boolean.parseBoolean(args[i+1]);
            }
        }

        Distributor dist=new Distributor(n, threadCount, gran,logs);
         long startTime;
         long endTime;

        startTime=System.nanoTime();
        BigDecimal distributed=dist.Distribute();
        endTime=System.nanoTime();
        System.out.println(endTime-startTime);



    

    }
}
