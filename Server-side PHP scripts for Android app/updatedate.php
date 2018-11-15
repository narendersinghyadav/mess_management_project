<?php

     include 'config.inc.php';
	 
	 // Check whether student_id or password is set from android	
     if(isset($_POST['student_id']))
     {
		  // Innitialize Variable
		  $result='';
		  $da='';
	   	  $student_id = $_POST['student_id'];
		  
		  // Query database for row exist or not
          $sql = 'SELECT dates FROM mess_allow WHERE  student_id = :student_id';
          $stmt = $conn->prepare($sql);
          $stmt->bindParam(':student_id', $student_id, PDO::PARAM_STR);
          $stmt->execute();
          if($stmt->rowCount())
          {
			 $result=$stmt->fetch();
			 echo $result["dates"];
          }  
          elseif(!$stmt->rowCount())
          {
			  	$result="false";
				  echo $result;
          }
  	}
	
?>