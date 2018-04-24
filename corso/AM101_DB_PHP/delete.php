<?php

/* execute selecy
 * 
 */


require(__DIR__ . "/class/Query.php");

$str = "";

if(isset($_GET['_id'])) {
    $_id = $_GET['_id'];
    $str = $str . "DELETE FROM Products WHERE _id = $_id;" ;    
    // echo $str;
}

// run query
Query::run_json($str);
print json_encode(JSON::$response);


?>
