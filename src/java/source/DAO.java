package source;

import storage.*;
import base.DBManager;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;
import java.util.ArrayList;


public class DAO {
    public static DAO getInstance(){
        return new DAO();
    }
    
    public StrategyDTO getStrategyById(int strategyId) throws SQLException{
        Connection con=null;
        PreparedStatement st=null;
        ResultSet data=null;
        
        StrategyDTO result=new StrategyDTO();
        
        try{
            System.out.println("getStrategyById start database connecting!!");
            con=DBManager.getConnection();
            
            st=con.prepareStatement("SELECT * FROM strategy where strategy_id=?");
            st.setInt(1, strategyId);
            
            data=st.executeQuery();
            while(data.next()){
                JSONObject json=new JSONObject(data.getString("strategy_json"));
                result.setStrategyJson(json);
                result.setStrategyId(strategyId);
            }
            
            data.close();
            st.close();
            con.close();
            System.out.println("getStrategyById completed");
            return result;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public void setStrategyWithJson(JSONObject strategyJson) throws SQLException{
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            System.out.println("setStrategy start database connecting!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("INSERT INTO strategy (strategy_json) VALUES(?)");
            st.setString(1, strategyJson.toString());
            
            st.close();
            con.close();
            System.out.println("setStrategy completed!");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public void setDailyActionProfit(DailyActionProfitDTO dailyActionProfitDTO) throws SQLException{
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            System.out.println("setDailyActionProfit start database connecting!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("INSERT INTO daily_action_profit (date, action, profit, brand_code, strategy_id) VALUES(?,?,?,?,?)");
            java.sql.Date sqlDate=new java.sql.Date(dailyActionProfitDTO.getDate().getTime());
            st.setDate(1, sqlDate);
            st.setString(2, dailyActionProfitDTO.getAction());
            st.setFloat(3, dailyActionProfitDTO.getProfit());
            st.setString(4, dailyActionProfitDTO.getBrandCode());
            st.setInt(5,dailyActionProfitDTO.getStrategyId());
            
            st.executeUpdate();
            
            st.close();
            con.close();
            System.out.println("setDailyActionProfit completed");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
        
    }
    
    public ArrayList<DailyActionProfitDTO> getDailyActionProfitByStrategyId(int strategyId) throws SQLException {
        Connection con=null;
        PreparedStatement st=null;
        ResultSet data=null;
        
        ArrayList<DailyActionProfitDTO> result=new ArrayList<DailyActionProfitDTO>();
        
        try{
            System.out.println("getDailyActionProfitByStrategyId start database connecting!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("SELECT * FROM daily_action_profit where strategy_id=?");
            st.setInt(1, strategyId);
            
            data=st.executeQuery();
            while(data.next()){
                DailyActionProfitDTO dailyActionProfitDTO=new DailyActionProfitDTO();
                dailyActionProfitDTO.setDate(data.getDate("date"));
                dailyActionProfitDTO.setAction(data.getString("action"));
                dailyActionProfitDTO.setProfit(data.getFloat("profit"));
                dailyActionProfitDTO.setBrandCode(data.getString("brand_code"));
                dailyActionProfitDTO.setStrategyId(strategyId);
                result.add(dailyActionProfitDTO);
            }
            data.close();
            st.close();
            con.close();
            System.out.println("getDailyActionProfitByStrategyId completed");
            return result;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
        
    }
    
    public ArrayList<DailyStockPriceDTO> getDailyStockPriceByBrandCode(String brandCode) throws SQLException{
        Connection con=null;
        PreparedStatement st=null;
        ResultSet data=null;
        
        ArrayList<DailyStockPriceDTO> result=new ArrayList<DailyStockPriceDTO>();
        
        try{
            System.out.println("getDailyStockPriceByBrandCode start database connecting!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("SELECT * FROM daily_stock_price where brand_code=?");
            st.setString(1, brandCode);
            data=st.executeQuery();
            while(data.next()){
                DailyStockPriceDTO dto=new DailyStockPriceDTO();
                dto.setBrandCode(data.getString("brand_code"));
                dto.setDate(data.getDate("date"));
                System.out.println(data.getDate("date"));
                System.out.println((int)Double.parseDouble(data.getString("opening_price")));
                dto.setOpeningPrice((int)Double.parseDouble(data.getString("opening_price")));
                dto.setHighPrice((int)Double.parseDouble(data.getString("high_price")));
                dto.setLowPrice((int)Double.parseDouble(data.getString("low_price")));
                dto.setClosingPrice((int)Double.parseDouble(data.getString("closing_price")));
                dto.setVolume(Double.parseDouble(data.getString("volume")));
                dto.setTradingValue(Double.parseDouble(data.getString("trading_value")));
                
                result.add(dto);
            }
            data.close();
            st.close();
            con.close();
        System.out.println("getDailyStockPriceByBrandCode completed");
            return result;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public ArrayList<BrandAndMarketDTO> getBrandByMarket(String market) throws SQLException{
        Connection con=null;
        PreparedStatement st=null;
        ResultSet data=null;
        ArrayList<BrandAndMarketDTO> result=new ArrayList<BrandAndMarketDTO>();
        
        try{
            System.out.println("getBrandByMarket start database connecting!! the market you chose is "+market);
            con=DBManager.getConnection();
            st=con.prepareStatement("SELECT * FROM brand_and_market WHERE BINARY market LIKE ?");
            st.setString(1,market);
            data=st.executeQuery();
            while(data.next()){
                BrandAndMarketDTO dto=new BrandAndMarketDTO();
                dto.setBrandCode(data.getString("brand_code"));
                dto.setBrandName(data.getString("brand_name"));
                dto.setMarket(data.getString("market"));
                
                result.add(dto);
            }
            
            data.close();
            st.close();
            con.close();
            System.out.println("getBrandByMarket completed");
            return result;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
}
