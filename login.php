<?php
    $con=mysqli_connect("localhost","root","eunhyun","eunhyun");
    mysqli_query($con, 'SET NAMES utf8');

    $userID=$_POST["userID"];
    $userPassword=$_POST["userPassword"];

    $statement=mysqli_prepare($con, "SELECT * FROM user WHERE userID=? AND userPassword=?");
    mysqli_stmt_bind_param($statement, "ss",$userID, $userPassword);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $userPassword, $userPhone, $userName,$userEmail);

    $response=array();
    $response["success"]=false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"]=true;
        $response["userID"]=$userID;
        $response["userPassword"]=$userPassword;
        $response["userPhone"]=$userPhone;
        $response["userName"]=$userName;
        $response["userEmail"]=$userEmail;
    }

    echo json_encode($response);

    ?>