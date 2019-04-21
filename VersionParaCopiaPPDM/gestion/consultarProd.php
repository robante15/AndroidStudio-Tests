<?php 
	//database conexion
	include ("config.php");
	$conn = mysqli_connect($db_host,$db_username,$db_password,$db_name);
	//Chequear si hay error en la conexion
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
	}
	
	//creating a query
	$sql="SELECT p.id_producto, p.descripcion, p.barcode, s.costo_promedio, p.porcentaje_utilidad1, p.imagen 
	FROM producto AS p
	JOIN stock AS s ON(p.id_producto=s.id_producto)
	GROUP BY p.id_producto
    ORDER BY p.id_producto
	";
	$stmt = $conn->prepare($sql);
	
	//ejecutar la query
	$stmt->execute();
	
	//asignar los resultados de la query
	$stmt->bind_result($id, $descripcion, $barcode, $costo, $porcent, $image);

	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){	
		
		$precio=round($costo+($porcent/100)*$costo,2);
		
		if( $barcode==""){
			$barcode="N/A";		
		}
		$temp = array();
		$temp['id'] = $id; 
		$temp['descripcion'] =  utf8_encode($descripcion); 
		$temp['barcode'] = $barcode; 
		$temp['costo'] = $costo; 
		$temp['precio'] = $precio; 
		$temp['image'] = $image; 
		array_push($products, $temp);
	}	
	//displaying the result in json format 
	echo json_encode($products);
$conn->close();	
?>
