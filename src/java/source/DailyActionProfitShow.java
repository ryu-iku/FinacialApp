/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author y.liu
 */
public class DailyActionProfitShow extends HttpServlet {

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
        try{
            int strategyId=1;
            String brandCode="1301";
            //get strategy
            StrategyDTO strategyDTO=DAO.getInstance().getStrategyById(strategyId);
            JSONObject strategyJson=strategyDTO.getStrategyJson();
            
            //get daily stock price
            ArrayList<DailyStockPriceDTO> dailyStockPriceDTOArray=DAO.getInstance().getDailyStockPriceByBrandCode(brandCode);
            
            //sort daily stock price array by date
            Collections.sort(dailyStockPriceDTOArray, new Comparator<DailyStockPriceDTO>(){
                @Override
                public int compare(DailyStockPriceDTO dto2, DailyStockPriceDTO dto1){
                    return dto1.getDate().compareTo(dto2.getDate());
                }
            });
            
            //use strategy json and daily stock price to figure out profits
            
            
            //html code maker
            response.setContentType("text/html;charset=UTF-8");
            ArrayList<DailyActionProfitDTO> result=new ArrayList<DailyActionProfitDTO>();
            
            result=DAO.getInstance().getDailyActionProfitByStrategyId(strategyId);
            request.setAttribute("RESULT",result);
            request.getRequestDispatcher("/daily_action_profit_show.jsp").forward(request, response);

        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
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
