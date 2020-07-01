<?php

require_once 'database.php';
require_once 'user.php';

class Model {
    private $db;

    public function __construct() {
        $this->db = new DBUtils();
    }

    public function getUser($username, $password) {
        $result = $this->db->selectUser($username, $password);

        if (!$result) {
            return new User(-1, 'No such user!', 'None');
        }

        return new User($result['user_id'], $result['username'], $result['password']);
    }

}
?>