<?php

require "config.inc.php";
 
/* [FETCH IMAGE] */
 if(isset($_GET['student_id'])){
	 $student_id = $_GET['student_id'];
	$stmt = $conn->prepare("SELECT photo FROM student_details WHERE student_id=:student_id");
	$stmt->bindParam(':student_id', $student_id, PDO::PARAM_STR);
	$stmt->execute();
	$img = $stmt->fetch(PDO::FETCH_ASSOC);
	header("Content-type: image/jpg");
echo $img['photo'];
 }
 else{
	 echo "false";
 }
?>