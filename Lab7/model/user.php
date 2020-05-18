<?php
    class User implements JSONSerializable {
        private $id;
        private $username;
        private $password;

        public function __construct($id, $username, $password) {
            $this->id = $id;
            $this->username = $username;
            $this->password = $password;
        }

        public function getId() {
            return $id;
        }

        public function getUsername() {
            return $username;
        }

        public function getPassword() {
            return $password;
        }

        public function jsonSerialize() {
            $json = get_object_vars($this);
            return $json;
        }
    }
?>