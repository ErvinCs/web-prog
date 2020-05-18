<?php
    class DBUtils {
        private $host    = '127.0.0.1';
        private $db      = 'lab7';
        private $user    = 'root';
        private $pass    = '';
        private $charset = 'utf8';

        public $pdo;
        public $error;

        public function __construct () {
            $dsn = "mysql:host=$this->host;dbname=$this->db;charset=$this->charset";
            $opt = array(PDO::ATTR_ERRMODE   => PDO::ERRMODE_EXCEPTION,
                PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
                PDO::ATTR_EMULATE_PREPARES   => false);
            try {
                $this->pdo = new PDO($dsn, $this->user, $this->pass, $opt);
            }
            catch(PDOException $e){
                $this->error = $e->getMessage();
                echo "Error connecting to DB: " . $this->error;
            }
        }

        public function selectUser($username, $password) {
            $stmt = $this->pdo->query("SELECT 1 FROM users WHERE username=:username AND password=:password");
            $stmt->execute(['username' => $username, 'password' => $password])
            return $stmt->fetch(PDO::FETCH_ASSOC);
        }

        public function selectKeyboardsPage($page) {
            $pageBegin = $page;
            $pageEnd = $page + 4;
            $stmt = $this->pdo->query("SELECT * FROM products WHERE category=:category LIMIT :pageStart,:pageFinish")
            $stmt->execute(['category' => 'keyboards', 'pageStart' => $pageBegin, 'pageFinish' =>$pageEnd])
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        }

        public function selectMicePage($page) {
            $pageBegin = $page;
            $pageEnd = $page + 4;
            $stmt = $this->pdo->query("SELECT * FROM products WHERE category=:category LIMIT :pageStart,:pageFinish")
            $stmt->execute(['category' => 'mice', 'pageStart' => $pageBegin, 'pageFinish' => $pageEnd])
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        }


    }
?>
