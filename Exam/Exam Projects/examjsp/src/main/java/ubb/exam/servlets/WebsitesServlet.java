package ubb.exam.servlets;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.exam.controller.WebsiteController;
import ubb.exam.model.Website;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jsp/websites")
public class WebsitesServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(WebsitesServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(true);
        WebsiteController con = WebsiteController.GetInstance();

        List<Website> siteList = new ArrayList<>(con.getAllSites());
        Gson itemListData = new Gson();
        String data = itemListData.toJson(siteList);

        resp.getWriter().write(data);

    }
}
