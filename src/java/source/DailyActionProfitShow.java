/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;

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
            ArrayList<DailyActionProfitDTO> dap=DailyActionProfitDAO.getInstance().showAllDailyActionProfit();
            
            int strategyId=1;
            String brandCode="1301";
            //get strategy
            StrategyDTO strategyDTO=DAO.getInstance().getStrategyById(strategyId);
            JSONArray strategyJson=strategyDTO.getStrategyJson().getJSONArray("content");
            
//            //get daily stock price
            ArrayList<DailyStockPriceDTO> dailyStockPriceDTOArray=new ArrayList<DailyStockPriceDTO>();
            dailyStockPriceDTOArray=DAO.getInstance().getDailyStockPriceByBrandCode(brandCode);
            
            //sort daily stock price array by date
            Collections.sort(dailyStockPriceDTOArray, new Comparator<DailyStockPriceDTO>(){
                @Override
                public int compare(DailyStockPriceDTO dto1, DailyStockPriceDTO dto2){
                    return dto1.getDate().compareTo(dto2.getDate());
                }
            });
            
            //use strategy json and daily stock price to figure out price_increase and min, max
            JSONArray condition_hold=strategyJson.getJSONObject(0).getJSONArray("condition"),
                    condition_sell=strategyJson.getJSONObject(0).getJSONArray("condition");
            float price_increase=0,profit = 0,total_profit=0,
                    min_hold=Float.parseFloat(condition_hold.getJSONObject(0).getString("min")),
                    max_hold=Float.parseFloat(condition_hold.getJSONObject(0).getString("max")),
                    min_sell=Float.parseFloat(condition_sell.getJSONObject(0).getString("min")),
                    max_sell=Float.parseFloat(condition_sell.getJSONObject(0).getString("max"));
            int price_before,price_after;
            String action="sell";
            
            //roop start
//            int index=1;
            for(int index=1;index<dailyStockPriceDTOArray.size();index++){
                price_before=dailyStockPriceDTOArray.get(index-1).getClosingPrice();
                price_after=dailyStockPriceDTOArray.get(index).getClosingPrice();
                price_increase=price_after/price_before-1;
                
                //record profit
                if("hold".equals(action)){profit=price_increase; total_profit+=profit;}
                DailyActionProfitDTO dailyActionProfitDTO=new DailyActionProfitDTO();
                dailyActionProfitDTO.setDate(dailyStockPriceDTOArray.get(index).getDate());
                dailyActionProfitDTO.setBrandCode("brandCode");
                dailyActionProfitDTO.setStrategyId(strategyId);
                dailyActionProfitDTO.setAction(action);
                dailyActionProfitDTO.setProfit(profit);
                DAO.getInstance().setDailyActionProfit(dailyActionProfitDTO);

                //change status
                if(price_increase>=min_sell && price_increase<=max_sell){action="sell";}
                else if (price_increase>=min_hold && price_increase<=max_hold){action="hold";}
            }
            //html code maker
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testSublet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testSublet at " + request.getContextPath() + "</h1>");
            out.println(strategyDTO.getStrategyJson());
            out.println("<br><br>");
            out.println(dailyStockPriceDTOArray.size());
            out.println("<br><br>");
            out.println(condition_sell);
            out.println("</body>");
            out.println("</html>");
            
            
//            request.setAttribute("RESULT",result);
//            request.getRequestDispatcher("/daily_action_profit_show.jsp").forward(request, response);

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
