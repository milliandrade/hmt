<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro Animal</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@500&display=swap" rel="stylesheet">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="icons//ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
    <script>
    <?php
        $config = parse_ini_file('config.ini');
         
        $servername = $config['servername'];
        $username = $config['username'];
        $password = $config['password'];
        $dbname = $config['dbname'];
        $table = "graze";

    // Create connection
        $conn = new mysqli($servername, $username, $password, $dbname);
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        //$sql = "SELECT * from ".$table;
        $sql = "SELECT * FROM `graze` ORDER BY `graze`.`time` DESC";

        //print $sql;

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
        }?>
    
    function onClick(uuid){
       document.getElementById("passagem").value =  uuid;
       document.getElementById("listagem").submit();
    }

    $(function(){
    $("#tab input").keyup(function(){       
        var index = $(this).parent().index();
        //alert(index);
        var nth = "#tab td:nth-child("+(index+1).toString()+")";
        var valor = $(this).val().toUpperCase();
        //alert(valor);
        $("#tab tbody tr").show();
        $(nth).each(function(){
            if($(this).text().toUpperCase().indexOf(valor) < 0){
                $(this).parent().hide();
            }
        });
    });
 
    $("#tabela input").blur(function(){
        $(this).val("");
    });
});
    </script>
    <style>
        main{
            font-family: 'Dosis', sans-serif;
            text-transform: uppercase;
        }
       header{
        width: 100%;
        height: 40px;
        clear: both;
       }
       header h2{
       color: black;
       padding-top: 0px;
       margin-top: 20px;
       }
       section.esquerda{
        width: 60%;
        height: 320px;
        float: left;     
        /*background-color: cadetblue;*/
       }
       div.lista{
        height: 93%;
        overflow-y: auto;
        /*background-color: coral;*/
       }
       td.col1{
        float: left;
        width: 40px;
       }
       td.col3{
        text-align: right;
        width: 40px;
       }
       div.filtro{
        height: 7%;
        /*background-color: brown;*/
       }
       section.direita{
        width: 40%;
        height: 320px;
        float: right;
        /*background-color: aqua;*/
       }
       iframe{
        width: 100%;
        height: 100%;
        margin-left: 12px;
        margin-top: 0px;
       }
       div input.pesquisa{
        float: right;
        width: 50%;
       }
       table{
        border-collapse: collapse;
        width: 100%;
        font-size: 12px;
        overflow: hidden;
       }
       table th{
        border: 1px solid #ddd;
        padding: 2px;
        background-color: rgb(61, 104, 184);
        color: white;
        text-align: center;
       }
       table td{
        border: 1px solid #ddd;
        padding: 4px;
        /*cursor: pointer;*/
       }
       table tr{
        cursor: pointer;
       }
       table td a{
        text-decoration: none;
}
       table tr:hover{
        background-color: lightgray;
       }
       section.direita div.superior{
        height: 40%;
        margin-bottom: 2px;
        /*background-color: blueviolet;*/
        font-size: 12px;
       }
       div.info{
        height: 100%;
        width: 40%;
        /*background-color: rgb(43, 226, 119);*/
        margin: 0 auto;
        float: left;
        line-height: 8px;
        margin-left: 40px;
       }
       div.foto{
        height: 100%;
        width: 50%;
        /*background-color: rgb(72, 65, 165);*/
        margin: 0 auto;
        float: right;
       }
       div.foto img{
        width: 70%;
        float: right;
        margin-top: 8px;
        margin-right: 30px;
        border: solid 3px darkgray;
       }
       div.info span{
        font-weight: bold;
       }
       input{
        width: 50%;
       }
       label{
        float: left;
       }
       th input#txt_data{
        width: 80%;
       }
       th input#txt_id{
        width: 80%;
       }
       th input#txt_presentes{
        width: 80%;
       }
       th input#txt_ausentes{
        width: 80%;
       }
       td.coluna_maior{
        width: 40%;
       }
       th.coluna_maior{
        width: 40%;
       }
       div {
        display: inline-block;
        margin-bottom: 10px;
       }
       div h2 {
        display: inline-block;
       }
       div.h_esq{
        height: 100%;
        width: 40%;
        /*background-color: blue;*/
        }
       div.h_dir{
        height: 100%;
        width: 50%;
        float: right;
        /*background-color: red;*/
        }
       div.h_dir img{
        float: center;
        height: 90%;
        margin: 0 auto;
        margin-left: 350px;
        margin-top: 10px;
        cursor: pointer;
        }
        div.h_dir p{
            float: right;
            margin-right: 230px;
        }
        input.btn{
            font-family: 'Dosis', sans-serif;
            text-transform: uppercase;
        /*text-decoration: none;*/
        background-color: rgb(61, 104, 184);
        position: relative;
        clear: both;
        width: 120px;
        color: white;
        padding: 2px 2px;
        border-radius: 3px;
        text-align: center;
        margin: auto;
        margin-left: 350px;
        margin-top: 20px;
        cursor: pointer;
       }
    </style>
</head>
<body>
    <main>
        <header>
            <div class= "h_esq">
                <h2>Listar rastreamentos</h2>
            </div>
            <div class= "h_dir">
                <!--<img src="images/drone2.png" alt="" onclick= "onSubmit()">
                <p>rastrear</p>-->
                <form method="POST" action="iniciar_rastreamento.php">
                    <input type="submit" value="Rastrear" class="btn">
                </form>
            </div>
        </header>
        <section class="esquerda">
            <div class="lista">
            <form method="post" id="listagem" action="complemento_rastreamento.php" target="complemento">
            <input type="hidden" id="passagem" name="passauuid" value="">
                <table id="tab">
                    <thead>
                    <tr class="primeira_linha">
                        <th>Data</th>
                        <th class="coluna_maior">ID Rastreamento</th>
                        <th>Presentes</th>
                        <th>Ausentes</th>
                    </tr>
                    <tr class="segunda_linha">
                        <th><input type="text" id="txt_data"/></th>
                        <th class="coluna_maior"><input type="text" id="txt_id"/></th>
                        <th><input type="text" id="txt_presentes"/></th>
                        <th><input type="text" id="txt_ausentes"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                        for ($cont=0; $cont < count($id); $cont++){
                            echo '<tr onclick = "onClick(\''.$id[$cont].'\')">'."\n";
                            echo "<td>".$data[$cont]."</td>\n";
                            echo "<td class=\"coluna_maior\">".$id[$cont]."</td>\n";
                            $conn = new mysqli($servername, $username, $password, $dbname);
                            // Check connection
                            if ($conn->connect_error) {
                                die("Connection failed: " . $conn->connect_error);
                            }
                            $sql = "SELECT (SELECT COUNT(*) FROM `oxen_in_graze` WHERE `graze_id`= '".$id[$cont]."' AND `is_present` = true) as 'presente', (SELECT COUNT(*) FROM `oxen_in_graze` WHERE `graze_id`= '".$id[$cont]."' AND `is_present` = false) as 'ausente'";
                            
                            $result = $conn->query($sql);

                            if ($result->num_rows > 0) {
                                // output data of each row
                                    $i=0;
                                    while($row = $result->fetch_assoc()) {
                                        $presente[$i]= $row["presente"];
                                        $ausente[$i]= $row["ausente"];
                                        echo "<td>".$presente[$i]." </td>";
                                        echo "<td>".$ausente[$i]."</td>";
                                        echo "</tr>";
                                        $i++;
                                    }
                                    
                                    $conn->close();
                                            
                                            }
                                        }
                                    
                    ?>
                    </tbody>
                </table>
                                    </form>
            </div>
            
        </section>
        <section class="direita">
            <iframe src="" frameborder="0" name="complemento"></iframe>
        </section>
        
    </main>
</body>
</html>