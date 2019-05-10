import java.math.BigInteger;


public class DistributedFactorial implements Runnable{
    private int start;
    private int end;
    private int num;
    private BigInteger[] res;

    public DistributedFactorial(int start,int end,BigInteger res[],int num){
        if(start<=end||start<0||end<0)
            throw new Error("invalid arguments");

        this.start=start;
        if(end==0)
            end=1;
        this.end=end;
        this.num=num;
        this.res=res;
    }
    public DistributedFactorial(int start,BigInteger res[],int num){
        this.start=start;
        this.end=1;
        this.res=res;
        this.num=num;
    }

    private static BigInteger fact(int n ,int m){
        //if(n<=1) return BigInteger.valueOf(1);
        if(n<=m){
            return BigInteger.valueOf(n);
        }
            
        return fact(n-1,m).multiply(BigInteger.valueOf(n));

    }
    public void run() {
        res[num]=fact(this.start,this.end);
    }
}
