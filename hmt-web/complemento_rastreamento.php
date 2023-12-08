<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Complemento</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@500&display=swap" rel="stylesheet">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="icons//ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
    <script>
    <?php
    $uuid = $_POST['passauuid'];
    $config = parse_ini_file('config.ini');
         
    $servername = $config['servername'];
    $username = $config['username'];
    $password = $config['password'];
    $dbname = $config['dbname'];
    ?>
    </script>
    <style>
        main{
            font-family: 'Dosis', sans-serif;
            text-transform: uppercase;
            font-size: 12px;
            margin-top: 0px;
            height: 320px;
        }
        body{
            margin-top: 0px;
        }
        p{
            margin-bottom: 0px;
            font-weight: bold;
        }
        table{
        border-collapse: collapse;
        width: 60%;
        font-size: 10px;
        overflow-y: auto;    
        margin-bottom: 2px;    
       }
       table th{
        border: 1px solid #ddd;
        padding: 4px;
        background-color: rgb(61, 104, 184);
        color: white;
       }
       table td{
        border: 1px solid #ddd;
        padding: 4px;
        
       }
       div.cabeçalho{
        margin-top: 0px;
        height: 10%;
        margin-bottom: 2px;
       }
       div.presentes{
        margin-top: 1px;
        height: 35%;
        margin-bottom: 2px;
        overflow-y: auto;
       }
       div.presentes table{
       }
       div.ausentes{
        margin-top: 1px;
        height: 35%;
        overflow-y: auto;
       }

    </style>
</head>
<body>
    <main>
        <div class="cabeçalho">
        <?php 
       echo "<b>ID do rastreamento: </b>".$uuid."<BR>";
    // Create connection
        $conn = new mysqli($servername, $username, $password, $dbname);
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        $sql = "SELECT * FROM `graze` WHERE `id`='".$uuid."'";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            // output data of each row
                $i=0;
                while($row = $result->fetch_assoc()) {
                    $id[$i]= $row["id"];    
                    $data[$i]= $row["date"];
                    $hora_ini[$i]= $row["start_time"];
                    $hora_fim[$i]= $row["end_time"];
                    $i++;
                }
                $conn->close();
        }
       echo "<b>Data: </b>".$data[0]."<b>      |      </b>";
       echo "<b>Hora Início: </b>".$hora_ini[0]."<b>      |      </b>";
       echo "<b>Hora Fim: </b>".$hora_fim[0]."<BR>";
       ?>
        </div>
        <div class="h_presentes">
        <p>Presentes:</p>
        </div>
        <div class="presentes">
        <table>
        <tbody>
                    <tr class="primeira_linha">
                        <th>Nome</th>
                        <th>UUID</th>
                    </tr>
        <?php
   // Create connection
       $conn = new mysqli($servername, $username, $password, $dbname);
       // Check connection
       if ($conn->connect_error) {
           die("Connection failed: " . $conn->connect_error);
       }
       $sql = "SELECT * FROM `oxen_in_graze` WHERE `graze_id`='".$uuid."' AND `is_present` = true";
       $result = $conn->query($sql);

       if ($result->num_rows > 0) {
           // output data of each row
               $i=0;
               while($row = $result->fetch_assoc()) { 
                    echo "<tr>";
                    $ox_id = $row['ox_id'];
                        $conn2 = new mysqli($servername, $username, $password, $dbname);
                        // Check connection
                        if ($conn2->connect_error) {
                            die("Connection failed: " . $conn2->connect_error);
                        }
                        $sql2 = "SELECT `name` FROM `ox` WHERE `id` = '".$ox_id."'";
                        
                        $result2 = $conn->query($sql2);

                        if ($result2->num_rows > 0) {
                            // output data of each row
                                $i=0;
                                while($row2 = $result2->fetch_assoc()) { 
                                    //echo "<td>".$row2['name']."</td>";
                                    echo "<td>".$row2['name']."</td>";
                                    $i++;
                                }
                                $conn2->close();
                        }
                    echo "<td>".$row['ox_id']."</td>";
                    echo "</tr>";
                    $i++;
               }
               $conn->close();
       }
       ?>
       </tbody>
       </table>
        </div>
        <div class="h_ausentes">
        <p>Ausentes:</p>
        </div>
        <div class="ausentes">
            <table>
        <tbody>
                    <tr class="primeira_linha">
                        <th>Nome</th>
                        <th>UUID</th>
                    </tr>
        <?php
        // Create connection
       $conn = new mysqli($servername, $username, $password, $dbname);
       // Check connection
       if ($conn->connect_error) {
           die("Connection failed: " . $conn->connect_error);
       }
       $sql = "SELECT * FROM `oxen_in_graze` WHERE `graze_id`='".$uuid."' AND `is_present` = false";
       $result = $conn->query($sql);

       if ($result->num_rows > 0) {
           // output data of each row
               $i=0;
               while($row = $result->fetch_assoc()) { 
                echo "<tr>";
                $ox_id = $row['ox_id'];
                
                    // Create connection
                    $conn2 = new mysqli($servername, $username, $password, $dbname);
                    // Check connection
                    if ($conn2->connect_error) {
                        die("Connection failed: " . $conn2->connect_error);
                    }
                    $sql2 = "SELECT `name` FROM `ox` WHERE `id` = '".$ox_id."'";
                    
                    $result2 = $conn->query($sql2);

                    if ($result2->num_rows > 0) {
                        // output data of each row
                            $i=0;
                            while($row2 = $result2->fetch_assoc()) { 
                                //echo "<td>".$row2['name']."</td>";
                                echo "<td>".$row2['name']."</td>";
                                $i++;
                            }
                            $conn2->close();
                    }
                echo "<td>".$row['ox_id']."</td>";
                echo "</tr>";
                $i++;
           }
           $conn->close();
       }
       ?>
        </tbody>
        </table>
        </div>
    </main>
</body>
</html>
