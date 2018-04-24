<?php

require(__DIR__ . "/class/DB.php");

// TEST
$connection = DB::getInstance();
print json_encode(JSON::$response);

?>
