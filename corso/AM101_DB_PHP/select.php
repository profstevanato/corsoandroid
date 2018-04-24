<?php

/* execute selecy
 * 
 */


require(__DIR__ . "/class/Query.php");


$str = "SELECT _id, name, description FROM Products";

if(isset($_GET['name']) and isset($_GET['description'])) {
    $name = $_GET['name'];
    $description = $_GET['description'];
    $str = $str . " WHERE name = \"$name\" AND description = \"$description\";" ;
} elseif (isset($_GET['name'])){
    $name = $_GET['name'];
    $str = $str . " WHERE name = \"$name\";";
} elseif (isset($_GET['description'])){
    $description = $_GET['description'];
    $str = $str . " WHERE description = \"$description\";";
} else {
    $str = $str . ";";
}


// run query

Query::run_json($str);
print json_encode(JSON::$response);

?>
