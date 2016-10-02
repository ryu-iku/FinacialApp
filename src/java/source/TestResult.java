/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.Serializable;
/**
 *
 * @author y.liu
 */
public class TestResult implements Serializable {
    private String brand;
    private int price;
    
    public String getBrand(){
        return brand;
    }
    
    public void setBrand(String brand){
        this.brand=brand;
    }
    
    public int getPrice(){
        return price;
    }
    
    public void setPrice(int price){
        this.price=price;
    }
}
