/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.ConnectionPool;
import business.Store;
import business.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

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
        //TODO verify an unauthorized user cannot access any pages after logon
        String url = "/Logon.jsp";
        String msg = "";
        ArrayList<Store> stores = new ArrayList<>();
        int userId = 0;
        
        try {
            userId = Integer.parseInt(request.getParameter("userid").trim());
            int pwAttempt = Integer.parseInt(request.getParameter("password").trim());
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            String sql = "SELECT * FROM Users WHERE UserID = '" + userId + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet r = ps.executeQuery(sql);
            if (r.next()) {
                User user = new User();
                user.setUserid(userId);
                user.setPwAttempt(pwAttempt);
                user.setPassword(r.getInt("userPassword"));
                if (user.isAuthenticated()) {
                    user.setUsername(r.getString("userName"));
                    user.setStoreid(r.getInt("storeID"));
                    user.setAdminLevel(r.getString("adminLevel"));
                    msg = "Welcome, " + user.getUsername() + "!<br/>";
                    url = "/StoreSelection.jsp";
                } else {
                    msg = "Member failed to authenticate<br/>";
                }
                request.getSession().setAttribute("user", user);
            } else {
                msg = "User not found in DB<br/>";
            }
            
            sql = "SELECT * FROM stores ORDER BY StoreName";
            r = ps.executeQuery(sql);

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
        } catch (NumberFormatException e) {
            msg = "Illegal userid or password: "  + e.getMessage() + "<br/>";
        }
        Cookie uid = new Cookie("userid",Integer.toString(userId));
        uid.setMaxAge(60*10);
        uid.setPath("/");
        response.addCookie(uid);
        request.setAttribute("msg", msg);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
        disp.forward(request,response);
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
