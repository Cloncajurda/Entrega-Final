/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = {"/CheckUser"})
public class CheckUser extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            Persistencia base = new Persistencia();
            ResultSet rs = base.consultaSQL("select * from usuarios where usuario=" + "'" 
                    + request.getParameter("validationNombre") + "'"  
                    + request.getParameter("validationApellido") + "'"   
                    + request.getParameter("validationDNI") + "'" 
                    + request.getParameter("validationCorreo") + "'" );
            
            if (rs==null){
                out.println("No hay usuarios que coincidan con la busqueda");
            }else{
                
                while(rs.next()){
                    out.println(rs.getString("Nombre"));
                    out.println(rs.getString("Apellido"));
                    out.println(rs.getString("DNI"));
                    out.println(rs.getString("Correo"));
                }
            
                out.println("<h1>Proyect: "+ request.getContextPath())+"</h1>");
                out.println("<h1>usuario: "+ request.getParameter("inputNombre")+"</h1>");
            }
        }catch (SQLExaception ex){
            Logger.getLogger(checkuser.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
