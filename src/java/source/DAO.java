package source;

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
            System.out.println("start database searching!!");
            con=DBManager.getConnection();
            
            st=con.prepareStatement("SELECT * FROM strategy where strategy_id=?");
            st.setInt(1, strategyId);
            
            data=st.executeQuery();
            while(data.next()){
//                JSONParser parser=new JSONParser();
//                Object obj=parser.parse(data.getString("strategy_json"));
//                JSONArray array=(JSONArray)obj;
                JSONObject json=new JSONObject(data.getString("strategy_json"));
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
    
    public void setStrategy(StrategyDTO strategyDTO) throws SQLException{
        Connection con=null;
        PreparedStatement st=null;
        
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
        
        try{
            System.out.println("start database inserting!!");
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
                dto.setBrandName(data.getString("brand_name"));
                dto.setMarket(data.getString("market"));
                dto.setDate(data.getDate("date"));
                dto.setOpeningPrice(data.getInt("opening_price"));
                dto.setHighPrice(data.getInt("high_price"));
                dto.setLowPrice(data.getInt("low_price"));
                dto.setClosingPrice(data.getInt("closing_price"));
                dto.setVolume(data.getInt("volume"));
                dto.setTradingValue(data.getInt("trading_value"));
                
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
