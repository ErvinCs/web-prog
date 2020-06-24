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
            } else if ($_GET['action'] == "removeItem") {
                $this->{$_GET['action']}($_GET['itemId']);
            } else if ($_GET['action'] == "getAllItemsByUser") {
                $this->{$_GET['action']}();
            }
        }
    }

    public function getUser($username, $password) {
        $user = $this->model->getUser($username, $password);
        $this->view->output($user);
        return $user;
    }

    public function getAllItemsByUser() {
        $userId = $_SESSION["UserId"];

        $items = $this->model->getAllItemsByUser($userId);
        $this->view->output($items);

        $_SESSION["UserId"] = $userId;
        return $items;
    }

    public function removeItem($itemId) {
        $deleted = $this->model->removeItem($itemId);

        $userId = $_SESSION["UserId"];

        $items = $this->model->getAllItemsByUser($userId);
        $this->view->output($items);

        $_SESSION["UserId"] = $userId;
        return $items;
    }

}

$controller = new Controller();
$controller->service(); // Service on receiving a request
?>