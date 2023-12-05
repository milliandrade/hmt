<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

</head>
<body>
    <?php
    //$servername = "45.56.126.161";
    $servername = "127.0.0.1";
    $username = "opa";
    $password = "Spoilt69";
    $dbname = "hmt";
    //$port = "3306";
    $table = "ox";

    // Create connection
$conn = new mysqli($servername, $username, $password,  $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * from ".$table;

//print $sql;

$result = $conn->query($sql);

$contador = 6;

if ($result->num_rows > 0) {
    // output data of each row
        $i=0;
        while($row = $result->fetch_assoc()) {
                $id[$i]= $row["id"];
                $uuid[$i]= $row["id"];
                $nome[$i]= $row["name"];
                $raca[$i]= $row["breed"];
                $peso[$i]= $row["weight"]." kg";
                $sexo[$i]= $row["gender"];
                $nome_mae[$i]= $row["mom_id"];
                $nome_pai[$i]= $row["dad_id"];
                $data_nascimento[$i]= $row["birth"];
                $data_abate[$i]= "n/a";
                $foto[$i]= $row["photo"];
                $i++;
        }
         $conn->close();
         //echo $sql;
}
echo $foto[0];
echo $sql;
//echo "teste";
?>
</body>
</html>
