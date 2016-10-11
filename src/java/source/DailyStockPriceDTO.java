package source;

import java.util.Date;

public class DailyStockPriceDTO {
    private String brandCode;
    private Date date;
    private int openingPrice;
    private int highPrice;
    private int lowPrice;
    private int closingPrice;
    private int volume;
    private int tradingValue;
    
    public String getBrandCode(){
        return brandCode;
    }
    public Date getDate(){
        return date;
    }
    public int getOpeningPrice(){
        return openingPrice;
    }
    public int getHighPrice(){
        return highPrice;
    }
    public int getLowPrice(){
        return lowPrice;
    }
    public int getClosingPrice(){
        return closingPrice;
    }
    public int getVolume(){
        return volume;
    }
    public int getTradingValue(){
        return tradingValue;
    }
    
    public void setBrandCode(String brandCode){
        this.brandCode=brandCode;
    }
    public void setDate(Date date){
        this.date=date;
    }
    public void setOpeningPrice(int openingPrice){
        this.openingPrice=openingPrice;
    }
    public void setHighPrice(int highPrice){
        this.highPrice=highPrice;
    }
    public void setLowPrice(int lowPrice){
        this.lowPrice=lowPrice;
    }
    public void setClosingPrice(int closingPrice){
        this.closingPrice=closingPrice;
    }
    public void setVolume(int volume){
        this.volume=volume;
    }
    public void setTradingValue(int tradingValue){
        this.tradingValue=tradingValue;
    }
}
