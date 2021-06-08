<?php

    header("Content-Type:text/html; charset=UTF-8");
    
    $con = mysqli_connect("localhost", "root", "eunhyun", "eunhyun");//데이터베이스 연결
    mysqli_query($con,'SET NAMES utf8');

    $query="select * from t_menu_image";//쿼리문 작성

    $result=mysqli_query($con, $query);//데이터베이스에 대한 쿼리 실행

    $rowCnt=mysqli_num_rows($result);
    $arr=array();

    for($i=0;$i<$rowCnt;$i++){
        $row= mysqli_fetch_array($result, MYSQLI_ASSOC);
        //각 각의 row를 $arr에 추가
        $arr[$i]= $row;
        
    }

    $jsonData=json_encode($arr, JSON_UNESCAPED_UNICODE);
    echo "$jsonData";

    mysqli_close($con);
?>