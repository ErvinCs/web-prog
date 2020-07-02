package ubb.exam.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ubb.exam.model.Document;
import ubb.exam.model.Website;
import ubb.exam.repository.WebsiteRepository;

import java.util.List;

public class WebsiteController {
    private static final Logger logger = LogManager.getLogger(WebsiteController.class);

    private WebsiteRepository repo;
    private static WebsiteController Controller = new WebsiteController();

    public static WebsiteController GetInstance() { return Controller; }

    private WebsiteController() {
        repo = new WebsiteRepository();
    }

    public List<Document> getAllDocs()
    {
        return repo.getAllDocs();
    }

    public List<Website> getAllSites()
    {
        return repo.getAllWebsites();
    }
}
