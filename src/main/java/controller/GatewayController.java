package controller;

import DAO.GatewayDAO;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/execute")
public class GatewayController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ServletContext sc = getServletContext();

        String statement = req.getParameter("statement");
        
        GatewayDAO gatewayDAO = new GatewayDAO(); 
        String result = gatewayDAO.executeStatement(statement);

        req.setAttribute("result", result);
        req.setAttribute("statement", statement);

        sc.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}