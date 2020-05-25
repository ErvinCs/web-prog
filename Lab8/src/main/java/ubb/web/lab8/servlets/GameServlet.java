package ubb.web.lab8.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/gameServlet"})
public class GameServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GameServlet.class);

}
