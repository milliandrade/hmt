<?php
header( "refresh:2;url=listar_rastreamento.php" );      
$servername = "127.0.0.1";
$username = "millena";
$password = "Spoilt69";
$dbname = "hmt";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "UPDATE `farm` SET `notify` = '1' WHERE `farm`.`name` = 'HMT Farm System'";

//echo $sql;

$result = $conn->query($sql);
$conn->close();

echo "Rastreamento iniciado!";
?>
