using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using examasp.Models;
using MySql.Data.MySqlClient;

namespace examasp.DataAbstractionLayer
{
    public class DALItems
    {
        const string ConnectionString = "server=localhost;uid=root;pwd=;database=exam;";

        public Item GetItemById(int id)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            Item item = new Item();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from items where item_id=" + id;
                MySqlDataReader reader = cmd.ExecuteReader();

                if (reader.Read())
                {
                    item.Item_id = reader.GetInt32("item_id");
                    item.Name = reader.GetString("name");
                    item.Description = reader.GetString("description");
                    item.Value = reader.GetInt32("value");
                    item.User_id = reader.GetInt32("user_id");
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return item;
        }

        public List<Item> GetAllItems()
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            List<Item> itemList = new List<Item>();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from items";
                MySqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Item item = new Item();
                    item.Item_id = reader.GetInt32("item_id");
                    item.Name = reader.GetString("name");
                    item.Description = reader.GetString("description");
                    item.Value = reader.GetInt32("value");
                    item.User_id = reader.GetInt32("user_id");
                    itemList.Add(item);
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return itemList;
        }

        public List<Item> GetItemsByUserId(int userId)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            List<Item> itemList = new List<Item>();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from items where user_id=" + userId;
                MySqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Item item = new Item();
                    item.Item_id = reader.GetInt32("item_id");
                    item.Name = reader.GetString("name");
                    item.Description = reader.GetString("description");
                    item.Value = reader.GetInt32("value");
                    item.User_id = reader.GetInt32("user_id");
                    itemList.Add(item);
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return itemList;
        }

        public Item DeleteItem(int id)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            Item item = new Item();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmdFind = new MySqlCommand();
                cmdFind.Connection = conn;
                cmdFind.CommandText = "select * from items where item_id=" + id;
                MySqlDataReader readerFind = cmdFind.ExecuteReader();

                if (readerFind.Read())
                {
                    item.Item_id = readerFind.GetInt32("item_id");
                    item.Name = readerFind.GetString("name");
                    item.Description = readerFind.GetString("description");
                    item.Value = readerFind.GetInt32("value");
                    item.User_id = readerFind.GetInt32("user_id");
                }
                readerFind.Close();

                MySqlCommand cmdDel = new MySqlCommand();
                cmdDel.Connection = conn;
                cmdDel.CommandText = "delete from items where item_id=" + id;
                cmdDel.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return item;
        }

        public void AddItem(Item item)
        {
            MySql.Data.MySqlClient.MySqlConnection conn; 

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "insert into items (name, description, value, user_id) values ('" + item.Name + "','" + item.Description + "',"
                    + item.Value + "," + item.User_id + ")";
                cmd.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
        }

        public void UpdateItem(Item item)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "update items set name='" + item.Name + "',description='" + item.Description + "',value="
                    + item.Value + " where user_id=" + item.User_id + " and item_id=" + item.Item_id;
                cmd.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
        }
    }
}