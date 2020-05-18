<?php
    class Category implements JSONSerializable {
        private $category;

        public function __construct($category) {
            $this->category = $category;
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