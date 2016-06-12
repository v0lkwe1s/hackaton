<?php

	require_once "vendor/autoload.php";
	require_once "rss.php";
	$lastUpdate = null;
	$url = 'https://android.googleapis.com/gcm/send';

	while (true){
		$feedList = getRSSFeed(1);

		foreach ($feedList->get_items() as $feed) {

			if ($lastUpdate != $feed->get_date('d/m/Y H:i')){

				$conn = new PDO("pgsql:host=seu_host;port=sua_porta;dbname=seu_banco;user=seu_usuario;password=sua_senha");
				$conn->exec('SET search_path TO public');	
				$result = $conn->query("SELECT gcm_key FROM users"); 							
				
				$gcm_keys = array();
				foreach($result as $user){
					array_push($gcm_keys, $user["gcm_key"]);
				}
									
				$fields = array (
            		'registration_ids' => $gcm_keys,
            		'data' => array("title" => $feed->get_title()),
        		);

				$headers = array (
            		'Authorization: key=SUA_API_KEY',
            		'Content-Type: application/json',
        		);

				$ch = curl_init();
        		curl_setopt($ch, CURLOPT_URL, $url);
 
        		curl_setopt($ch, CURLOPT_POST, true);
        		curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
				curl_setopt($ch, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4 );
 
        		curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));

        		$result = curl_exec($ch);
        		if ($result === FALSE) {
            		die('Curl failed: ' . curl_error($ch));
        		}
        		curl_close($ch);
        		echo $result;

			}		
		}

		$lastUpdate = $feed->get_date('d/m/Y H:i');	
	}
?>
