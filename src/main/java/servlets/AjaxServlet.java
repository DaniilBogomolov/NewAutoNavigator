package servlets;

import org.json.JSONArray;
import org.json.JSONObject;
import services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addFavourite")
public class AjaxServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        usersService = new UsersService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer carID = Integer.parseInt(req.getParameter("id").substring(6));
        String imageSource = req.getParameter("src");
        String username = req.getParameter("username");
        JSONObject object = new JSONObject();
        if (imageSource.equals("../images/star-regular.svg")) {
            usersService.addFavourite(carID, username);
            object.put("src", "../images/star-solid.svg");
        } else {
            usersService.removeFavourite(carID, username);
            object.put("src", "../images/star-regular.svg");
        }
        resp.setContentType("text/json");
        resp.getWriter().write(object.toString());
//        resp.sendRedirect("req.getRequestURI()");
    }
}
