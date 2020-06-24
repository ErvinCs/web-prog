<?php
require_once 'model.php';
require_once 'view.php';
session_start();
class Controller {
    private $view;
    private $model;

    public function __construct() {
        $this->model = new Model();
        $this->view  = new View();
    }

    public function service() {
        if(isset($_GET['action']) && !empty($_GET['action'])) {
            if($_GET['action'] == "getUser") {
                $this->{$_GET['action']}($_GET['user']);
            }
        }
    }

    public function getUser($username, $password) {
        $user = $this->model->getUser($username, $password);
        $this->view->output($user);
        return $user;
    }
}

$controller = new Controller();
$controller->service(); // Service on receiving a request
?>