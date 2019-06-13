import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RangedESum implements Runnable{

    private int start;
    private int end;
    private int num;
    private int percision;
    private BigDecimal results[];
    private BigInteger factorials[];

    public RangedESum(int start, int end,int percision, BigDecimal results[], BigInteger factorials[],int num) {
        if (start < 0 || end < 0) {

            throw new Error("invalid arguments:" + start + " " + end);
        }

        this.start = start;
        this.end = end;
        this.num = num;
        this.percision=percision;
        this.results = results;
        this.factorials= factorials;
    }


    public BigDecimal eSum(int start, int end,int perc){
        BigInteger fact=BigInteger.valueOf(2*start*(2*start-1));
        if(start==0) fact = BigInteger.ONE;
        BigDecimal result=BigDecimal.valueOf(2*start +1).divide(new BigDecimal(fact),perc,RoundingMode.HALF_EVEN);
        for(int i=start+1;i<end;i++){
            fact=fact.multiply(BigInteger.valueOf(2*i*(2*i - 1)));
            result=result.add(BigDecimal.valueOf(2*i +1).divide(new BigDecimal(fact),perc,RoundingMode.HALF_EVEN));
        };
        factorials[num]=fact;
        return result;
    }
    

    public void run() {
        results[num]=eSum(this.start,this.end,this.percision);
    }

}