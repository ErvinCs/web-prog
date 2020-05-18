<?php

require_once '../config.php'
require_once 'user.php'
require_once 'product.php'
require_once 'model.php'

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

            return new User($result['id'], $result['username'], $result['password']);
        }

        public function getKeyboards($page) {
            $results = $this->db->selectKeyboardsPage($page);
            $keyboards = array();
            foreach($results as $key => $val) {
                $keyboard = $val;
                //FK For product type (?) proly won't need it tho
                array_push($keyboards, $keyboard);
            }
            return $keyboards;
        }

        public function getMice($page) {
            $results = $this->db->selectMicePage($page);
            $mice = array();
            foreach($results as $key => $val) {
                $mouse = $val;
                //FK For product type (?) proly won't need it tho
                array_push($mice, $mouse);
            }
            return $mice;
        }

    }

?>