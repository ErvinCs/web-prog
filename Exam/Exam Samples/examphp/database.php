<?php
class DBUtils {
    private $host    = '127.0.0.1';
    private $db      = 'exam';
    private $user    = 'root';
    private $pass    = '';
    private $charset = 'utf8';

    public $pdo;
    public $error;

    public function __construct () {
        header("Access-Control-Allow-Origin: *");
        $dsn = "mysql:host=$this->host;dbname=$this->db;charset=$this->charset";
        $opt = array(PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
                     PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
                     PDO::ATTR_EMULATE_PREPARES   => false);
        try {
            $this->pdo = new PDO($dsn, $this->user, $this->pass, $opt);
        }
        catch(PDOException $e){
            $this->error = $e->getMessage();
            echo "Error connecting to the Database: " . $this->error;
        }
    }

    public function selectUser($username, $password) {
        $stmt = $this->pdo->query("SELECT * FROM users WHERE username='".$username."' AND password='".$password."'");
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function addItem($name, $description, $value, $userId) {
        $rows = $this->pdo->exec("INSERT INTO items (name, description, value, user_id) VALUES ('".
            $name."','".$description."',".$value.",".$userId.")");
        return $rows;
    }

    public function removeItem($itemId) {
        $rows = $this->pdo->exec("DELETE FROM items WHERE item_id=".$itemId);
        return $rows;
    }

    public function updateItem($itemId, $name, $description, $value, $userId) {
        $rows = $this->pdo->exec("UPDATE items SET name='".$name."',description='".$description."',value=".
            $value." WHERE user_id=".$userId." AND item_id=".$itemId);
        return $rows;
    }

    public function getItemById($itemId) {
        $stmt = $this->pdo->query("SELECT * FROM items WHERE item_id=".$itemId);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function getAllItems() {
        $stmt = $this->pdo->query("SELECT * FROM items");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function getItemsByUser($userId) {
        $stmt = $this->pdo->query("SELECT * FROM items WHERE user_id=".$userId);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}
?>