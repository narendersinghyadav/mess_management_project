<?php

     include 'config.inc.php';
	 
	 // Check whether student_id or password is set from android	
     if(isset($_POST['student_id']))
     {
		  // Innitialize Variable
		  $result='';
	   	  $student_id = $_POST['student_id'];
		  // Query database for row exist or not
          $sql = 'SELECT dates from food_history where student_id=:student_id';
          $stmt = $conn->prepare($sql);
          $stmt->bindParam(':student_id', $student_id, PDO::PARAM_STR);
          $stmt->execute();
          if($stmt->rowCount())
          {
			while ($row = $stmt->fetch()) {
				echo $row['dates']."\n";
				}
          }  
          elseif(!$stmt->rowCount())
          {
			  	$result="false";
				  echo $result;
          }
  	}
	
?>