package storage;

import java.util.Date;

public class BrandAndMarketDTO {
    private String brandCode;
    private String brandName;
    private String market;
    
    public String getBrandCode(){
        return brandCode;
    }
    public String getBrandName(){
        return brandName;
    }
    public String getMarket(){
        return market;
    }
    
    public void setBrandCode(String brandCode){
        this.brandCode=brandCode;
    }
    public void setBrandName(String brandName){
        this.brandName=brandName;
    }
    public void setMarket(String market){
        this.market=market;
    }
    
}
