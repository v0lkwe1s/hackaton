<?php

	header("Content-type: application/json; charset=utf-8");

	require_once "vendor/autoload.php";
	require_once "rss.php";

	if ($_SERVER['REQUEST_METHOD'] == 'GET'){

		$feedList = getRSSFeed(null);

		$lista_json = array("results" => array());
		foreach ($feedList->get_items() as $feed) {
			$description = substr($feed->get_description(), 0, stripos($feed->get_description(), ".")).".";
			$feedJsonObject = array(
    			"title" => $feed->get_title(),
    			"description" => $description,
				"link" => $feed->get_link(),
    			"date" => $feed->get_date('d/m/Y H:i')
			);

			array_push($lista_json["results"], $feedJsonObject);
		}

		echo json_encode($lista_json);
	}

?>
