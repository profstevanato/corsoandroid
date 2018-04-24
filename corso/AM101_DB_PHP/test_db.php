<?php

require './class/Query.php';

$rows = Query::run_json("SELECT name, description FROM Products;");
if($rows === false) {
    $error = db_error();
    // Handle error - inform administrator, log to file, show error page, etc.
}

?>
