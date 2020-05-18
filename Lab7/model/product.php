<?php
    class Product implements JSONSerializable {
        private $id;
        private $name;
        private $price;
        private $category;

        public function __construct($id, $name, $price, $category) {
            $this->id = $id;
            $this->name = $name;
            $this->price = $price;
            $this->category = $category;
        }

        public function getId() {
            return $id;
        }

        public function getName() {
            return $name;
        }

        public function getPrice() {
            return $price;
        }

        public function getCategory() {
            return $category;
        }

        public function jsonSerialize() {
            $json = get_object_vars($this);
            return $json;
        }

    }
?>