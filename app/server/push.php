<?php

	require_once "vendor/autoload.php";
	require_once "rss.php";
	$lastUpdate = null;
	$url = 'https://android.googleapis.com/gcm/send';

	while (true){
		$feedList = getRSSFeed(1);

		foreach ($feedList->get_items() as $feed) {

			if ($lastUpdate != $feed->get_date('d/m/Y H:i')){

				$fields = array (
            		'registration_ids' => array('id'),
            		'data' => array("title" => $feed->get_title()),
        		);

				$headers = array (
            		'Authorization: key=SUA_KEY',
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
