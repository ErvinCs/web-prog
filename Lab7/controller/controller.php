<?php
require_once '../model/model.php'
require_once '../view/view.php'

    class Controller {
        private $view;
        private $model;

        public function __construct() {
            $this->model = new Model();
            $this->view = new View();
        }

        public function getUser($username, $password) {
            $user = $this->model->getUser($username, $password);
            return $this->view->output($user);
        }

        public function getKeyboards($page) {
            $keyboards = $this->model->getKeyboards($page);
            return $this->view->outputKeyboards($keyboards);
        }

        public function getMice($page) {
            $mice = $this->model->getMice($page);
            return $this->view->outputMice($mice);
        }
    }

$controller = new Controller();

?>