
package servlets;

import business.Book;
import business.ConnectionPool;
import business.Store;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ekk
 */
public class ViewInventoryServlet extends HttpServlet {

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
        Book book = new Book();
        String url = "/UpdateInventory.jsp";
        String bookid = request.getParameter("bookid").trim();
        Store store = (Store) request.getSession().getAttribute("store");
        String msg = "";

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        
        String invsql = "SELECT onhand FROM bookinv WHERE storeID = '" + store.getStoreid() + "' and bookID = '" + bookid + "'";
        String booksql = "SELECT * FROM booklist WHERE bookID = '" + bookid + "'";
        try {
            ResultSet invr = conn.prepareStatement(invsql).executeQuery(invsql);
            if (invr.next()) {
                book.setOnhand(invr.getInt("OnHand"));
            }
                
            ResultSet bkr = conn.prepareStatement(booksql).executeQuery(booksql);
            if (bkr.next()) {
                book.setTitle(bkr.getString("title"));
                book.setAuthor(bkr.getString("author"));
                book.setBookid(bkr.getString("bookID"));
            }
        } catch (Exception e) {
            msg = "View error: " + e.getMessage() + "<br/>";
        }
        request.setAttribute("msg", msg);
        request.getSession().setAttribute("book", book);
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
