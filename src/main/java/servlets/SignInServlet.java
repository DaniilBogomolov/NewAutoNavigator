package servlets;

import models.User;
import repository.interfaces.UsersRepository;
import repository.implementations.UsersRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String rememberMe = req.getParameter("rememberMe");

        Optional<User> possibleUser = usersRepository.getUserByUsername(username);
        if (possibleUser.isPresent()) {
            System.out.println(password);
            System.out.println(username);
            if (password.equals(possibleUser.get().getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("user", username);
                if (rememberMe != null && rememberMe.equals("on")) {
                    Cookie cookie = new Cookie("user", username);
                    cookie.setMaxAge(99999999);
                    resp.addCookie(cookie);
                }
                resp.sendRedirect("/home");
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
