<?php

function db_connect() {

    // Define connection as a static variable, to avoid connecting more than once 
    static $connection;

    // Try and connect to the database, if a connection has not been established yet
    if(!isset($connection)) {
         // Load configuration as an array. Use the actual location of your configuration file
        $config = parse_ini_file('../db_config.ini'); 
        $connection = mysqli_connect('localhost', $config['username'], $config['password'], $config['dbname']);
        // print json_encode(array("connection"=>"ok"));
    }

    // If connection was not successful, handle the error
    if($connection === false) {
        // Handle error - notify administrator, log to a file, show an error screen, etc.
        // print json_encode(array("connection"=>"nok"));
        return $connection->connect_error; 
        
    }
    return $connection;
}


function db_query($sql) {
    // Connect to the database
    $connection = db_connect();

    // Query the database
    $result = $connection->query($sql);
    // $result = mysqli_query($connection,$sql);
    return $result;
}


function db_select($sql) {
    $rows = array();
    $result = db_query($sql);

    // If query failed, return `false`
    if($result === false) {
        return false;
    }

    // If query was successful, retrieve all the rows into an array
    print( "<table>");
    while ($row = $result->fetch_assoc()) {
        $rows[] = $row;
        printf ("<tr><td><b>name</b>: %s </td><td><b>description</b>: %s</td></tr>", $row["name"], $row["description"]);
    }
    print("</table>");
    return $rows;
}

function db_select_json($query) {
    $result = db_query($query);

    // If query failed, return `false`
    if($result === false) {
        print json_encode(array("sql"=>"nok"));
        return;
    }
    // If query was successful, retrieve all the rows into an array
    print json_encode(array("sql"=>"ok"));
    while ($row = $result->fetch_assoc()) {
        $output[] = $row;
        // echoing JSON response
        print json_encode($row);
    }
}

function db_insert($query) {
    $result = db_query($query);

    // If query failed, return `false`
    if($result === false) {
        print json_encode(array("sql"=>"nok"));
        return;
    }
    // If query was successful, retrieve all the rows into an array
    print json_encode(array("sql"=>"ok"));
    while ($row = $result->fetch_assoc()) {
        $output[] = $row;
        // echoing JSON response
        print json_encode($row);
    }
}

// TEST
$str = "SELECT name, description FROM Products";
if(isset($_GET['name']) and isset($_GET['description'])) {
    $name = $_GET['name'];
    $description = $_GET['description'];
    $str = $str . " WHERE name = \"" . $name ."\" AND description = \"" . $description . "\";" ;
} elseif (isset($_GET['name'])){
    $name = $_GET['name'];
    $str = $str . " WHERE name = \"" . $name ."\";";
} elseif (isset($_GET['description'])){
    $description = $_GET['description'];
    $str = $str . " WHERE description = \"" . $description ."\";";
} else {
    $str = $str . ";";
}



print("TEST<br />");
db_select_json("SELECT name, description FROM Products;");
print("<br />TEST<br />");
db_select_json("SELECT name, description FROM Products WHERE name = \"pasta\";");
print("<br />TEST<br />");
db_select_json("SELECT name, description FROM Products WHERE name = \"gioppini\";");
print("<br />TEST<br />");
echo $str;

?>
