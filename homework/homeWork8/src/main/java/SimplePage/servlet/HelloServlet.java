package SimplePage.servlet;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by marva on 10.04.16.
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action").toString();
        System.out.println(action);
        if(action.equals("add")) {
            System.out.println("adding");
            String item = req.getParameter("data");
            SimplePage.EntityManager.newItem(item);
            SimplePage.EntityManager.showItems();
            System.out.println(item);
            resp.setStatus(200);
        } else if(action.equals("del")) {
            System.out.println("sfsfs");
            long delId = Long.parseLong(req.getParameter("data"));
            SimplePage.EntityManager.deleteItem(delId);
            SimplePage.EntityManager.showItems();
            //System.out.println(delId);
            resp.setStatus(200);
        }
    }
}
