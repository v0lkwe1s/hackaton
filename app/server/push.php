<?php

	require_once "vendor/autoload.php";
	require_once "rss.php";
	$api_key = "SUA_API_KEY";
	$url = 'https://android.googleapis.com/gcm/send';

	while (true){
		$feedList = getRSSFeed(1);
		$lastUpdate = null;

		foreach ($feedList->get_items() as $feed) {

			if ($lastUpdate != null){

				if ($lastUpdate != $feed->get_date('d/m/Y H:i'){

					$fields = array(
            			'registration_ids' => $registatoin_ids,
            			'data' => $message,
        			);

					$headers = array(
            			'Authorization: key=' . $api_key,
            			'Content-Type: application/json'
        			);

					$ch = curl_init();
        			curl_setopt($ch, CURLOPT_URL, $url);
 
        			curl_setopt($ch, CURLOPT_POST, true);
        			curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        			curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
 
        			curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));

        			$result = curl_exec($ch);
        			if ($result === FALSE) {
            			die('Curl failed: ' . curl_error($ch));
        			}
        			curl_close($ch);
        			echo $result;

				}		
			}
		}

		$lastUpdate = $feed->get_date('d/m/Y H:i');	
	}
?>
