using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using examasp.Models;
using examasp.DataAbstractionLayer;

namespace examasp.Controllers
{
    public class LoginController : Controller
    {
        public ActionResult Index()
        {
            return View("LoginView");
        }

        public string GetUserByCredentials()
        {
            ClearCookies();
            string username = Request.Params["username"];
            string password = Request.Params["password"];
            DALUsers dal = new DALUsers();
            User user = dal.GetUserByCredentials(username, password);

            if (user.User_id == -1)
            {
                string result = "0";
                return result;
            }
            else
            {
                string result = "1";
                Response.Cookies.Add(new HttpCookie("Username", username));
                Response.Cookies.Add(new HttpCookie("UserId", user.User_id.ToString()));
                Response.Redirect("../Main");
                return result;
            }
        }

        public void Logout()
        {
            ClearCookies();
            Response.Redirect("../Login");
        }

        private void ClearCookies()
        {
            if (Request.Cookies["Username"] != null)
            {
                Response.Cookies["Username"].Expires = DateTime.Now.AddDays(-1);
            }
            if (Request.Cookies["UserId"] != null)
            {
                Response.Cookies["UserId"].Expires = DateTime.Now.AddDays(-1);
            }
        }
    }
}