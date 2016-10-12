package storage;
import java.util.HashMap;

public class DailyActionProfitShowResult {
    private StrategyDTO strategy;
    private HashMap <String, Float> brandAndProfit;
    
    public StrategyDTO getStrategy(){
        return strategy;
    }
    public HashMap <String, Float> getBrandAndProfit(){
        return brandAndProfit;
    }
    
    public void setStrategy(StrategyDTO strategy){
        this.strategy=strategy;
    }
    public void setBrandAndProfit(HashMap <String, Float> brandAndProfit){
        this.brandAndProfit=brandAndProfit;
    }
}
