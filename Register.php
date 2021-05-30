<?php
    $con = mysqli_connect("localhost", "root", "eunhyun", "eunhyun");
    mysqli_query($con,'SET NAMES utf8');

    (string)$userID = $_POST["userID"];
    (string)$userPassword = $_POST["userPassword"];
    (string)$userPhone=$_POST["userPhone"];
    (string)$userName = $_POST["userName"];
    (string)$userEmail=$_POST["userEmail"];

    $statement = mysqli_prepare($con, "INSERT INTO user VALUES (?,?,?,?,?)");
    $bind=mysqli_stmt_bind_param($statement,"sssss", $userID, $userPassword, $userPhone, $userName, $userEmail);
    $exec=mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;


    echo json_encode($response);

?>