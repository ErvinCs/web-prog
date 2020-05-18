<?php
    class View {
        public function __construct() {
        }

        public function outputUser($user) {
            echo json_encode($user);
        }

        public function outputKeyboards($keyboards) {
            echo json_encode($keyboards);
        }

        public function outputMice($mice) {
            echo json_encode($mice);
        }
    }
?>