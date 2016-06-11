<?php

	function getRSSFeed($limit){
		$feedList = new SimplePie();

		$feedList->set_feed_url(array(
    		'http://www.camarapatobranco.com.br/noticias/feed'
    	));
		$feedList->enable_cache(true);

		if ($limit != null){
			$feedList->set_item_limit($limit);
		}

		$feedList->init();
		return $feedList;
	}

?>
