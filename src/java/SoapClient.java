/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.dmktsp.anime.back.ws.PurchaseQueryResult;

/**
 *
 * @author fujiyohi
 */
public class SoapClient extends HttpServlet {

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
        
        // add start
        jp.dmktsp.anime.back.ws.PurchaseQueryInfo info = new jp.dmktsp.anime.back.ws.PurchaseQueryInfo();
        info.setCustomerId("00000");
        info.setAuthPass("pw");
        info.setUserId("20160119155755cC0jR8HVWoRdgrTNgH");
        info.setKeyId("1046399912");
        PurchaseQueryResult PQResult = getPurchaseInfo(info);
        // add end
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SoapClient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SoapClient at " + request.getContextPath() + "]</h1>");
            out.println("getQueryResult = [" + PQResult.getQueryResult() + "]");
            out.println("getBusinessModel = [" + PQResult.getBusinessModel() + "]");
            out.println("getBeginDate = [" + PQResult.getBeginDate() + "]");
            out.println("getExpirationDate = [" + PQResult.getExpirationDate() + "]");
            out.println("getFirstPlayExpiration = [" + PQResult.getFirstPlayExpiration() + "]");
            out.println("</body>");
            out.println("</html>");
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


    /**
     * 実際にSOAPサーバにgetPurchaseInfoを投げる処理
     * @param info PurchaseQueryInfo
     * @return result PurchaseQueryResult
     * @todo https://netbeans.org/kb/docs/websvc/client_ja.html#creatingtheclient
     * 本番サーバに投げているので注意
     * https://anime.dmkt-sp.jp/animestore/services/PurchaseInformation?wsdl
     * 
     */
    private static PurchaseQueryResult getPurchaseInfo(jp.dmktsp.anime.back.ws.PurchaseQueryInfo info) {
        jp.dmktsp.anime.back.ws.PurchaseInformation service = new jp.dmktsp.anime.back.ws.PurchaseInformation();
        jp.dmktsp.anime.back.ws.PurchaseInformationSoap port = service.getPurchaseInformationSoap();
        return port.getPurchaseInfo(info);
    }

}
