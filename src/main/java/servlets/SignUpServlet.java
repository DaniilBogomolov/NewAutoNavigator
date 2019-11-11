package servlets;

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

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!usersRepository.getUserByUsername(username).isPresent()) {
            usersRepository.save(new User(username, password, Role.USER));
            req.getSession().setAttribute("user", username);
            resp.sendRedirect("/home");
        } else {
            throw new IllegalArgumentException();
        }
    }
}
