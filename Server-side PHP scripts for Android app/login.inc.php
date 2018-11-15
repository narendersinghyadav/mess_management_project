<?php

     include 'config.inc.php';
	 
	 // Check whether student_id or password is set from android	
     if(isset($_POST['student_id']) && isset($_POST['password']))
     {
		  // Innitialize Variable
		  $result='';
	   	  $student_id = $_POST['student_id'];
          $password = $_POST['password'];
		  
		  // Query database for row exist or not
          $sql = 'SELECT * FROM login_details WHERE  student_id = :student_id AND password = :password';
          $stmt = $conn->prepare($sql);
          $stmt->bindParam(':student_id', $student_id, PDO::PARAM_STR);
          $stmt->bindParam(':password', $password, PDO::PARAM_STR);
          $stmt->execute();
          if($stmt->rowCount())
          {
			 $result="true";	
          }  
          elseif(!$stmt->rowCount())
          {
			  	$result="false";
          }
		  
		  // send result back to android
   		  echo $result;
  	}
	
?>