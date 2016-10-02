/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import source.TestResult;
import java.sql.*;

/**
 *
 * @author y.liu
 */
public class StrategyTest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        final String result_file_path="/test_result.jsp";
        
        PrintWriter out = response.getWriter();
        
        String brand=null;
        int price=0;
        
        
        // connect to sql then get brand and price data.
        Connection db_con=null;
        PreparedStatement db_st=null;
        ResultSet db_data=null;
                
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            db_con=DriverManager.getConnection("jdbc:mysql://localhost:3307/finapp", "finapp", "finapp");
            
            db_st=db_con.prepareStatement("select * from test_stock where price=?");
            db_st.setInt(1, 356);
            
            db_data=db_st.executeQuery();
            while(db_data.next()){
                brand=db_data.getString("brand");
                price=db_data.getInt("price");
            }
            
            db_data.close();
            db_st.close();
            db_con.close();
        } catch(SQLException e_sql){
            out.println("接続時にエラーが発生しました："+e_sql.toString());
        }catch(Exception e){
            out.println("接続時にエラーが発生しました："+e.toString());
        }finally {
            if(db_con!=null){
                try{
                    db_con.close();
                }catch(Exception e_con){
                    System.out.println(e_con.getMessage());
                }
            }
        }
        
        // pass data of brand and price to jsp
        TestResult result=new TestResult();
        result.setBrand(brand);
        result.setPrice(price);
        request.setAttribute("RESULT",result);
        
        RequestDispatcher rd=request.getRequestDispatcher(result_file_path);
        rd.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
