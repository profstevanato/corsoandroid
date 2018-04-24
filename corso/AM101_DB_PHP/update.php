<?php

/* execute selecy
 * 
 */


require(__DIR__ . "/class/Query.php");

$str = "";

if(isset($_GET['name']) and isset($_GET['description']) and isset($_GET['_id'])) {
    $_id = $_GET['_id'];
    $name = $_GET['name'];
    $description = $_GET['description'];
    $str = $str . "UPDATE Products SET name=\"$name\", description=\"$description\" WHERE _id=$_id;";    
    // echo $str;
}

// run query
Query::run_json($str);
print json_encode(JSON::$response);


?>
