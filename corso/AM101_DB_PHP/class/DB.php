<?php
/*
* Mysql database class - as a singleton class
*/

require(__DIR__ . "/JSON.php");

class DB {
    
	private $_connection;
	private static $_instance; // The single instance
    
	private $_username;
	private $_password;
	private $_database;

	/*
	Get an instance of the Database (_istance is a static member self is the class)
	@return Instance
	*/
	public static function getInstance() {
		if(!self::$_instance) { // If no instance then make one
			self::$_instance = new self();
		}
		return self::$_instance;
	}

	// Constructor
	private function __construct() {
        // Load configuration as an array. Use the actual location of your configuration file
        $config = parse_ini_file(__DIR__ . '/../../db_config.ini'); 
        $this->_connection = new mysqli('localhost', $config['username'], $config['password'], $config['dbname']);
		// Error handling
		// If connection was not successful, handle the error
        if($this->_connection->connect_errno) {
            // Handle error - notify administrator, log to a file, show an error screen, etc.
            // echo "Failed to connect to MySQL: (" . $this->_connection->connect_errno . ") <br /> " . $this->_connection->connect_error . " <br /> ";
            JSON::$response[] = array("connection"=>"nok");
            exit;
        }
        JSON::$response[] = array("connection"=>"ok");
	}

	// Magic method clone is empty to prevent duplication of connection
	private function __clone() { }

	// Get mysqli connection
	public function getConnection() {
		return $this->_connection;
	}
}


// TEST
// $connection = DB::getInstance();
// print json_encode(JSON::$response);

?>
