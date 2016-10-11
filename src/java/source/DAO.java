package source;

import base.DBManager;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;


public class DAO {
    public static DAO getInstance(){
        return new DAO();
    }
    
    public StrategyDTO getStrategyById(int strategyId) throws SQLException, ParseException{
        Connection con=null;
        PreparedStatement st=null;
        ResultSet data=null;
        
        StrategyDTO result=new StrategyDTO();
        
        try{
            System.out.println("start database searching!!");
            con=DBManager.getConnection();
            
            st=con.prepareStatement("SELECT * FROM strategy where strategy_ID=?");
            st.setInt(1, strategyId);
            
            data=st.executeQuery();
            while(data.next()){
                JSONParser parser=new JSONParser();
                Object obj=parser.parse(data.getString("strategy"));
                JSONArray array=(JSONArray)obj;
                JSONObject json=(JSONObject)array.get(0);
                result.setStrategyJson(json);
                result.setStrategyId(strategyId);
            }
            
            data.close();
            st.close();
            con.close();
            System.out.println("search completed");
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
    
    public void setStrategy(StrategyDTO strategyDTO) throws SQLException, ParseException{
        Connection con=null;
        PreparedStatement st=null;
        ResultSet data=null;
        
        try{
            System.out.println("start database inserting!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("INSERT INTO strategy (strategy_id, strategy_json) VALUES(?,?)");
            st.setInt(1, strategyDTO.getStrategyId());
            st.setString(2, strategyDTO.getStrategyJson().toString());
            
            st.close();
            con.close();
            System.out.println("completed!");
            
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
        ResultSet data=null;
        
        try{
            System.out.println("start database inserting!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("INSERT INTO daily_action_profit (date, action, profit, brand_code, strategy_id) VALUES(?,?,?,?)");
            java.sql.Date sqlDate=new java.sql.Date(dailyActionProfitDTO.getDate().getTime());
            st.setDate(1, sqlDate);
            st.setString(2, dailyActionProfitDTO.getAction());
            st.setFloat(3, dailyActionProfitDTO.getProfit());
            st.setString(4, dailyActionProfitDTO.getBrandCode());
            st.setInt(5,dailyActionProfitDTO.getStrategyId());
            
            data=st.executeQuery();
            
            data.close();
            st.close();
            con.close();
            System.out.println("completed");
            
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
            System.out.println("start database searching!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("SELECT * FROM daily_action_profit where strategyId=?");
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
            System.out.println("search completed");
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
            System.out.println("start database searching!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("SELECT * FROM daily_stock_price where brand_code=?");
            st.setString(1, brandCode);
            data=st.executeQuery();
            while(data.next()){
                DailyStockPriceDTO dto=new DailyStockPriceDTO();
                dto.setBrandCode(data.getString("brand_code"));
                dto.setDate(data.getDate("date"));
                dto.setOpeningPrice(Integer.parseInt(data.getString("opening_price")));
                dto.setHighPrice(Integer.parseInt(data.getString("high_price")));
                dto.setLowPrice(Integer.parseInt(data.getString("low_price")));
                dto.setClosingPrice(Integer.parseInt(data.getString("closing_price")));
                dto.setVolume(Integer.parseInt(data.getString("volume")));
                dto.setTradingValue(Integer.parseInt(data.getString("trading_value")));
                
                result.add(dto);
            }
            data.close();
            st.close();
            con.close();
            System.out.println("search completed");
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
            System.out.println("start database searching!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("SELECT * FROM brand_and_market where market=?");
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
            System.out.println("search completed");
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
