/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import storage.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
            
            DailyActionProfitShowResult result=new DailyActionProfitShowResult();// storage for all the necessary result
            
            //get strategy
            int strategyId=1;
            StrategyDTO strategyDTO=DAO.getInstance().getStrategyById(strategyId);
            JSONArray strategyJson=strategyDTO.getStrategyJson().getJSONArray("content");
            result.setStrategy(strategyDTO);
            
            //get brand code list by market
            String market="%東証1部%";
            ArrayList <BrandAndMarketDTO> brandAndMarketList=DAO.getInstance().getBrandByMarket(market);
            System.out.println("number of brands in the market is "+brandAndMarketList.size());
            HashMap <String, Float> brandAndProfit=new HashMap();
            
            //start brandCodeList loop to record all profit data to HashMap
            for(BrandAndMarketDTO brandAndMarket:brandAndMarketList){

                
                String brandCode=brandAndMarket.getBrandCode();
                System.out.println("the brand code now is "+brandCode);
                
                //get daily stock price
                ArrayList<DailyStockPriceDTO> dailyStockPriceDTOArray=DAO.getInstance().getDailyStockPriceByBrandCode(brandCode);
                //stock price data check: skip if the data is empty or old
                if(dailyStockPriceDTOArray.size()<250){continue;};
                
                //sort daily stock price array by date
                Collections.sort(dailyStockPriceDTOArray, new Comparator<DailyStockPriceDTO>(){
                    @Override
                    public int compare(DailyStockPriceDTO dto1, DailyStockPriceDTO dto2){
                        return dto1.getDate().compareTo(dto2.getDate());
                    }
                });
                //use strategy json and daily stock price to figure out price_increase and min, max
                JSONArray condition_hold=strategyJson.getJSONObject(0).getJSONArray("condition"),
                        condition_sell=strategyJson.getJSONObject(1).getJSONArray("condition");
                float price_increase=0,profit = 0,total_profit=0,
                        min_hold=Float.parseFloat(condition_hold.getJSONObject(0).getString("min")),
                        max_hold=Float.parseFloat(condition_hold.getJSONObject(0).getString("max")),
                        min_sell=Float.parseFloat(condition_sell.getJSONObject(0).getString("min")),
                        max_sell=Float.parseFloat(condition_sell.getJSONObject(0).getString("max"));
                int price_before,price_after;
                String action="sell";

                System.out.println("min hold is "+min_hold+". max hold is "+max_hold+". min sell is "+min_sell+". max sell is "+max_sell);

                //start for loop in stock price DTO
                for(int index=1;index<dailyStockPriceDTOArray.size();index++){
                    price_before=dailyStockPriceDTOArray.get(index-1).getClosingPrice();
                    price_after=dailyStockPriceDTOArray.get(index).getClosingPrice();
                    price_increase=(float)price_after/price_before-1;
                    System.out.println("price before is "+price_before+". price after is "+price_after+"price increase is "+price_increase);

                    //record profit
                    if("hold".equals(action)){profit=price_increase; total_profit+=profit;}
                    System.out.println("the action now is "+action+"\nthe total_profit now is "+total_profit);

                    DailyActionProfitDTO dailyActionProfitDTO=new DailyActionProfitDTO();
                    dailyActionProfitDTO.setDate(dailyStockPriceDTOArray.get(index).getDate());
                    dailyActionProfitDTO.setBrandCode(brandCode);
                    dailyActionProfitDTO.setStrategyId(strategyId);
                    dailyActionProfitDTO.setAction(action);
                    dailyActionProfitDTO.setProfit(profit);
                    DAO.getInstance().setDailyActionProfit(dailyActionProfitDTO);

                    //change status
                    if(price_increase>=min_sell && price_increase<=max_sell){action="sell";}
                    else if (price_increase>=min_hold && price_increase<=max_hold){action="hold";}
                }
                brandAndProfit.put(brandCode, total_profit);
            }
            result.setBrandAndProfit(brandAndProfit);
            
            //html code maker
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testSublet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>this is Servlet testSublet  </h1>");
            out.println(strategyDTO.getStrategyJson());
            out.println("<br><br>");
            for (HashMap.Entry<String, Float> entry:result.getBrandAndProfit().entrySet()){
                out.println(entry.getKey()+" / ");
                out.println(entry.getValue());
                out.println("<br>");
            }
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
