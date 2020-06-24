using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using examasp.Models;
using MySql.Data.MySqlClient;

namespace examasp.DataAbstractionLayer
{
    public class DALUsers
    {
        const string ConnectionString = "server=localhost;uid=root;pwd=;database=exam;";

        public User GetUserByCredentials(string username, string password)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            User user = new User();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from users where username='" + username + "' and password='" + password + "'";
                MySqlDataReader reader = cmd.ExecuteReader();

                if (reader.Read())
                {
                    user.User_id = reader.GetInt32("user_id");
                    user.Username = reader.GetString("username");
                    user.Password = reader.GetString("password");
                }
                else
                {
                    user.User_id = -1;
                    user.Username = "";
                    user.Password = "";
                }
                reader.Close();

            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return user;
        }
    }
}