package source;

//import base.DBManager;--make base class in src folder if neccessary.
import storage.DailyActionProfitDTO;
import base.DBManager;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DailyActionProfitDAO {

    public static DailyActionProfitDAO getInstance(){
        return new DailyActionProfitDAO();
    }
    
//    show all data at once
    public ArrayList<DailyActionProfitDTO> showAllDailyActionProfit() throws SQLException {
        Connection con=null;
        PreparedStatement st=null;
        ResultSet data=null;
        
        ArrayList<DailyActionProfitDTO> result_list=new ArrayList<DailyActionProfitDTO>();
        
        try{
            System.out.println("start database searching!!");
            con=DBManager.getConnection();
            st=con.prepareStatement("SELECT * FROM daily_action_profit where action=?");
            st.setString(1,"持つ");
            
            data=st.executeQuery();
            while(data.next()){
                DailyActionProfitDTO one_result=new DailyActionProfitDTO();
                one_result.setDate(data.getDate("date"));
                one_result.setAction(data.getString("action"));
                one_result.setProfit(data.getFloat("profit"));
                result_list.add(one_result);
            }
            data.close();
            
            st.close();
            con.close();
            System.out.println("search completed");
            return result_list;
            
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