/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Store;
import business.User;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raefo
 */
public class LogonServlet extends HttpServlet {

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
        //TODO get dynamic value for userid in logon jsp
        //TODO change initial value of url in this file
        //TODO use connection pools in this file instead of old way (remove dbUrl, dbUser, dbPw, hardcoded user)
        //TODO fix isAuthenticated in User pojo
        String url = "/StoreSelection.jsp";
        String sql = "";
        String msg = "";
        ArrayList<Store> stores = new ArrayList<>();
        
        String dbUrl = "jdbc:mysql://localhost:3306/henrybooks_is288?useSSL=false";
        String dbUser = "root";
        String dbPw = "sesame";
        
        User u = new User();
        u.setUserid(1234);
        u.setUsername("John Smith");
        u.setStoreid(1);
        u.setAdminLevel("Admn");
        request.getSession().setAttribute("user", u);
        if (u.isAuthenticated()) {
            try {
                //userId = request.getParameter("userid").trim();
                //pwAttempt = Long.parseLong(request.getParameter("password").trim());
                Connection conn = DriverManager.getConnection(dbUrl,dbUser,dbPw);
                Statement s = conn.createStatement();
                sql = "SELECT * FROM stores ORDER BY StoreName";
                ResultSet r = s.executeQuery(sql);
                
                while (r.next()) {
                    Store store = new Store();
                    store.setStoreid(r.getInt("storeid"));
                    store.setStoreName(r.getString("storename"));
                    store.setStoreAddress(r.getString("storeaddr"));
                    store.setNumEmployees(r.getInt("storeemp"));
                    stores.add(store);
                }
                if (stores.size() > 0) {
                    request.getSession().setAttribute("stores", stores);
                } else {
                    msg = "No stores read from stores table<br/>";
                }
            } catch (SQLException e) {
                msg = "SQL Exception: " + e.getMessage() + "<br/>";
            }
            RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
            disp.forward(request,response);
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
