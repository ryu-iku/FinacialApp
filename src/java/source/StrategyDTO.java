package source;

import org.json.JSONObject;

public class StrategyDTO {
    private int strategyId;
    private JSONObject strategyJson;
    
    public int getStrategyId(){
        return strategyId;
    }
    
    public void setStrategyId(int strategyId){
        this.strategyId=strategyId;
    }
    
    public JSONObject getStrategyJson(){
        return strategyJson;
    }
    
    public void setStrategyJson(JSONObject strategyJson){
        this.strategyJson=strategyJson;
    }
}
