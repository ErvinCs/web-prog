using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using examasp.DataAbstractionLayer;
using examasp.Models;

namespace examasp.Controllers
{
    public class MainController : Controller
    {
        // GET: Main
        public ActionResult Index()
        {
            return View("MainView");
        }

        public string GetItemsByUser()
        {
            int userId = -1;
            string username = "";
            HttpCookie cookieId = Request.Cookies["UserId"];
            HttpCookie cookieUsername = Request.Cookies["Username"];
            if (cookieId == null || cookieUsername == null)
            {
                Response.Redirect("../Shared/Error.cshtml");
            }
            else
            {
                userId = Int32.Parse(cookieId.Value);
                username = cookieUsername.Value;
            }


            DAL dal = new DAL();
            List<Item> itemList = dal.GetItemsByUserId(userId);
            ViewData["itemList"] = itemList;

            string result = "User: " + username + "<br/>";

            result += "<table><thead><th>Name</th><th>Description</th><th>Value</th><th>Remove</th></thead>";
            foreach (Item i in itemList)
            {
                result += "<tr>";
                result += "<td>" + i.Name + "</td><td>" + i.Description + "</td><td>" + i.Value + "</td>";
                result += "<td><button type='button' id='delete' name=" + i.Item_id + "> Remove </button></td>";
                result += "</tr>";
            }

            result += "</table>";
            return result;
        }

        public string RemoveItem()
        {
            int userId = -1;
            string username = "";
            HttpCookie cookieId = Request.Cookies["UserId"];
            HttpCookie cookieUsername = Request.Cookies["Username"];
            if (cookieId == null || cookieUsername == null)
            {
                Response.Redirect("../Shared/Error.cshtml");
            }
            else
            {
                userId = Int32.Parse(cookieId.Value);
                username = cookieUsername.Value;
            }

            int itemId = int.Parse(Request.Params["itemId"]);

            DAL dal = new DAL();
            dal.DeleteItem(itemId);
            List<Item> itemList = dal.GetItemsByUserId(userId);
            ViewData["itemList"] = itemList;

            string result = "User: " + username + "<br/>";

            result += "<table><thead><th>Name</th><th>Description</th><th>Value</th><th>Remove</th></thead>";
            foreach (Item i in itemList)
            {
                result += "<tr>";
                result += "<td>" + i.Name + "</td><td>" + i.Description + "</td><td>" + i.Value + "</td>";
                result += "<td><button type='button' id='delete' name=" + i.Item_id + "> Remove </button></td>";
                result += "</tr>";
            }

            result += "</table>";
            return result;
        }
    }
}