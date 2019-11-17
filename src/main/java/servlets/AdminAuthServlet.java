package servlets;

import freemarker.template.TemplateExceptionHandler;
import helpers.Config;
import models.Model;
import models.Role;
import models.User;
import repository.interfaces.UsersRepository;
import repository.implementations.UsersRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/admin/manage")
public class AdminAuthServlet extends HttpServlet {

    private UsersRepository usersRepository;


    @Override
    public void init() {
        usersRepository = new UsersRepositoryImpl();
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionAdmin = (String) request.getSession().getAttribute("user");
        Map<String, Object> root = new HashMap<>();
        if (sessionAdmin != null) {
            root.put("user", new User("admin", "admin", Role.ADMIN));
        }
        response.setContentType("text/html");
        Config.render(request, response, "AdminPanel.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> user = usersRepository.getUserByUsername(username);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password) && user.get().getRole().equals(Role.ADMIN)) {
                req.getSession().setAttribute("user", "adminUser");
                resp.sendRedirect("/admin/manage/addcar");
            }
        } else {
            resp.sendRedirect("/admin/manage");
        }
    }
}
