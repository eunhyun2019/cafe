<?php
 
    header('Content-Type:text/html; charset=utf-8');
 
    $conn= mysqli_connect("localhost","root","eunhyun","eunhyun");
    //$conn= mysqli_connect("localhost","umul","aa142536!","umul");
 
    mysqli_query($conn, "set names utf8");    //한글 깨짐 방지
 

    //쿼리문 작성
    $sql="select tmi.menu_image_id, tmi.cafe_id, tmi.category, tmi.imgPath, tm.menu_id, tm.menu_name, tm.menu_price from t_menu_image tmi JOIN t_menu tm ON(tmi.menu_id=tm.menu_id AND tm.menu_id=tmi.menu_id);";
    $result=mysqli_query($conn, $sql);
 
    //$result : 결과 표
 
    //결과의 총 레코드 수(줄 수, 행의 개수)
    $rowCnt= mysqli_num_rows($result);
 
    //레코드 수 만큼 반복하여 한줄씩 데이터 읽어오기
    for($i=0; $i<$rowCnt; $i++){
        //데이터 한줄을 연관배열(키값으로 구분)로 받아오기
        $row= mysqli_fetch_array($result, MYSQLI_ASSOC);
        echo "$row[menu_image_id]&$row[cafe_id]&$row[category]&$row[imgPath]&$row[menu_id]&$row[menu_name]&$row[menu_price];";
    }
 
    mysqli_close($conn);
 
?>
