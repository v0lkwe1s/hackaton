<?php
	
		try {
			$conn = new PDO("pgsql:host=localhost;port=5432;dbname=uppatobranco;user=seu_usuario;password=sua_senha");
			$conn->exec('SET search_path TO public');
			$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$stmt = $conn->prepare("INSERT INTO users(gcm_key) SELECT :gcm_key WHERE NOT EXISTS (SELECT gcm_key FROM users WHERE gcm_key = :gcm_key)");
			$stmt->bindParam(":gcm_key", $gcm_key);
			$gcm_key = $_GET['gcm_key'];
			$stmt->execute();
			echo 1;
		} catch (PDOException $e){
			echo $e->getMessage();
		}
?>
