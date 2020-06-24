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
}
?>