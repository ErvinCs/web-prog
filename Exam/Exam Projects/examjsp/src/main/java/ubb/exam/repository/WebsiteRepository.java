package ubb.exam.repository;

import ubb.exam.config.DataSourceConfig;
import ubb.exam.model.Document;
import ubb.exam.model.Website;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WebsiteRepository {
    private final String tbl_sites = "websites";
    private final String tbl_docs  = "documents";

    private Document parseDoc(ResultSet rs) throws SQLException {
        Long docId = rs.getLong("doc_id");
        Long siteId = rs.getLong("site_id");
        String name = rs.getString("name");
        String key1 = rs.getString("keyword1");
        String key2 = rs.getString("keyword1");
        String key3 = rs.getString("keyword1");
        String key4 = rs.getString("keyword1");
        String key5 = rs.getString("keyword1");

        Document doc = new Document(siteId, name, key1, key2, key3, key4, key5);
        doc.setId(docId);

        return doc;
    }

    private Website parseWebsite(ResultSet rs) throws SQLException {
        Long siteId = rs.getLong("site_id");
        String url = rs.getString("url");

        Website site = new Website(url);
        site.setId(siteId);

        return site;
    }

    public List<Document> getAllDocs() {
        List<Document> docList = new ArrayList<>();
        String sql = "SELECT * FROM " + tbl_docs;
        try(Connection connection = DataSourceConfig.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Document doc = parseDoc(rs);
                docList.add(doc);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return docList;
    }

    public List<Website> getAllWebsites() {
        List<Website> siteList = new ArrayList<>();
        String sql = "SELECT * FROM " + tbl_sites;
        try(Connection connection = DataSourceConfig.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Website site = parseWebsite(rs);
                siteList.add(site);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return siteList;
    }
}
