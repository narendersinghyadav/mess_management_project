<?php
	include 'config.inc.php';
	if(isset($_POST['student_id'])){
		$result='';
		$student_id=$_POST['student_id'];
		$dt=date('d/m/Y');
		
		$sql4='select * from mess_allow where dates=:dt1 and student_id=:student_id';
		$stmt4=$conn->prepare($sql4);
		$stmt4->bindParam(':dt1',$dt,PDO::PARAM_STR);
		$stmt4->bindParam(':student_id',$student_id,PDO::PARAM_STR);
		$stmt4->execute();
		
		if($stmt4->rowCOunt()){
			echo "false";
		}
		else{
		$sql='Update login_details set Balance=Balance-100 where student_id=:student_id and Balance>100';
		$stmt=$conn->prepare($sql);
		$stmt->bindParam(':student_id',$student_id,PDO::PARAM_STR);
		$stmt->execute();
		if($stmt->rowCOunt()){
			$result="true";
		}
		else{
			$result="false";
		}
		echo $result;
		
		$sql1='select * from mess_allow where student_id=:student_id';
		$stmt1=$conn->prepare($sql1);
		$stmt1->bindParam(':student_id',$student_id,PDO::PARAM_STR);
		$stmt1->execute();
		if($stmt1->rowCOunt()){
		$sql2 ='Update mess_allow set dates=:dt where student_id=:student_id ';
		$stmt2=$conn->prepare($sql2);
		$stmt2->bindParam(':student_id',$student_id,PDO::PARAM_STR);
		$stmt2->bindParam(':dt',$dt,PDO::PARAM_STR);
		$stmt2->execute();
		}
		else{
			$sql3='insert into mess_allow values(:student_id,"0/0/0")';
			$stmt3=$conn->prepare($sql3);
			$stmt3->bindParam(':student_id',$student_id,PDO::PARAM_STR);
			$stmt3->execute();
			$sql2 ='Update mess_allow set dates=:dt where student_id=:student_id ';
			$stmt2=$conn->prepare($sql2);
			$stmt2->bindParam(':student_id',$student_id,PDO::PARAM_STR);
			$stmt2->bindParam(':dt',$dt,PDO::PARAM_STR);
			$stmt2->execute();	
			
		}
		$sql5='insert into food_history values(NULL,:student_id,:dates)';
		$stmt5=$conn->prepare($sql5);
		$stmt5->bindParam(':student_id',$student_id,PDO::PARAM_STR);
		$stmt5->bindParam(':dates',$dt,PDO::PARAM_STR);
		$stmt5->execute();
	}
	
	}
?>