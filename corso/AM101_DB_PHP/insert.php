<?php

/* execute selecy
 * 
 */


require(__DIR__ . "/class/Query.php");

$str = "";

if(isset($_GET['name']) and isset($_GET['description'])) {
    $name = $_GET['name'];
    $description = $_GET['description'];
    $str = $str . "INSERT INTO Products(name, description) VALUES (\"$name\", \"$description \");" ;    
    // echo $str;
}

// run query
Query::run_json($str);
print json_encode(JSON::$response);


?>
