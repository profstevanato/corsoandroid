<?php
class MyClass {
   function __construct() {
       print "In constructor <br />";
       $this->name = "MyClass";
   }

   function __destruct() {
       print "Destroying " . $this->name . "<br />";
   }
}

$obj = new MyClass();
?>
