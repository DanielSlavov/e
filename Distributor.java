import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Distributor {
    private int n;
    private double gran;
    private boolean logs;
    private int threadCount;
    private Thread threads[];
    public BigDecimal results[];
    public BigInteger factorials[];
    public BigDecimal result;

    public Distributor(int n, int threadCount, double gran,boolean logs) {
        this.logs=logs;
        this.n = n;
        this.gran = gran;
        this.threadCount = threadCount;
        this.results = new BigDecimal[threadCount];
        this.factorials = new BigInteger[threadCount];
        this.threads = new Thread[threadCount];
        this.result = BigDecimal.valueOf(0);
    }

    public BigDecimal Distribute() {
        int chunk= (int)(n*gran);

        for(int k=0;k<=n/chunk;k++){
            
            for (int i = 0; i < threadCount  ;i++) {
                if(k!=0){
                    try{
                        threads[i].join();
                        if(logs)
                        System.out.println("Thread "+i+" finished");
                    }catch(Exception e){
                        System.out.println("Error");
                    }
                    if(i!=0){
                    factorials[i] = factorials[i].multiply(factorials[i - 1]);
                    result=result.add(results[i].divide(new BigDecimal(factorials[i - 1]),n,RoundingMode.HALF_EVEN));
                    }
                    else if(k!=1){
                        factorials[i] = factorials[i].multiply(factorials[threadCount - 1]);
                        result=result.add(results[i].divide(new BigDecimal(factorials[threadCount - 1]),n,RoundingMode.HALF_EVEN));
                    }else{
                        result=results[0];
                    }
                }
                if( chunk*k + (double)(chunk*(i / threadCount)) > n ){
                    // System.out.println("breaked");
                     break;
                }
                if(k<n/chunk){
                int start = chunk*k + i*chunk* 1 / threadCount;
                int end = chunk*k + (i+1)*chunk*1/ threadCount;
                threads[i] = new Thread(new RangedESum(start, end, n, results, factorials, i));
                threads[i].start();
                if(logs)
                System.out.println("Thread "+i+" started: "+(start + "  -  "+end));}
            }
        }


                
        return result;

    }
}