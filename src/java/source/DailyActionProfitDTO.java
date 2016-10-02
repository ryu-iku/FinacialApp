package source;

import java.sql.Timestamp;
import java.util.Date;

public class DailyActionProfitDTO {
    private Date date;
    private String action;
    private float profit;
    private String brandCode;
    private int strategyId;
    
    public String getBrandCode(){
        return brandCode;
    }
    
    public void setBrandCode(String brandCode){
        this.brandCode=brandCode;
    }
    
    public int getStrategyId(){
        return strategyId;
    }
    
    public void setStrategyId(int strategyId){
        this.strategyId=strategyId;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date=date;
    }
    
    public String getAction(){
        return action;
    }
    
    public void setAction(String action){
        this.action=action;
    }
    
    public float getProfit(){
        return profit;
    }
    
    public void setProfit(float profit){
        this.profit=profit;
    }
    
}
