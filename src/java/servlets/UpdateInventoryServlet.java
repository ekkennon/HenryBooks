
package servlets;

import business.Book;
import business.ConnectionPool;
import business.Store;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ekk
 */
public class UpdateInventoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "/Logon";
        String newOnHand = request.getParameter("onhand").trim();
        Store store = (Store) request.getSession().getAttribute("store");
        Book book = (Book) request.getSession().getAttribute("book");
        String msg = "";
        
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
        
            String sql = "UPDATE bookinv SET " +
                        "onhand = ? " +
                        "WHERE storeID = ? " +
                        "and bookID = ?;";
                
            PreparedStatement ps = conn.prepareStatement(sql);
                
            ps.setString(1, newOnHand);
            ps.setString(2, Integer.toString(store.getStoreid()));
            ps.setString(3, book.getBookid());
            
            
            int recordCount = ps.executeUpdate();
            
            
            switch (recordCount) {
                case 0:
                    msg += "Update failed - no changes<br/>";
                    break;
           
                case 1:
                    msg += "Book Updated!<br/>";
                    break;
            
                default:
                    msg += "fatal error: " + recordCount + " records were changed<br/>";
                    break;
            }
            
        } catch (Exception e) {
            msg += "Update Inv Error: " + e.getMessage() + "<br/>";
        }
        
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
