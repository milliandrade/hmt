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
    $servername = "127.0.0.1";
    $username = "millena";
    $password = "Spoilt69";
    $dbname = "hmt";
    $table = "ox";

    // Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
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
}?>
    
    function onClick(uuid, nome, raca, peso, sexo, nome_mae, nome_pai, data_nascimento, data_abate, foto){ 
        document.getElementById('uuid_animal').innerHTML =  uuid;
        document.getElementById('nome_animal').innerHTML =  nome;
        document.getElementById('raca_animal').innerHTML =  raca;
        document.getElementById('peso_animal').innerHTML =  peso;
        document.getElementById('sexo_animal').innerHTML =  sexo;
        document.getElementById('mae_animal').innerHTML =  nome_mae;
        document.getElementById('pai_animal').innerHTML =  nome_pai;
        document.getElementById('data_nascimento_animal').innerHTML =  data_nascimento;
        document.getElementById('data_abate_animal').innerHTML =  data_abate;
        document.getElementById('foto_animal').src =  foto;
    }
    
    $(function(){
    $("#listagem input").keyup(function(){       
        var index = $(this).parent().index();
        var nth = "#listagem td:nth-child("+(index+1).toString()+")";
        var valor = $(this).val().toUpperCase();
        $("#listagem tbody tr").show();
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
       padding-top: 10px;
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
        width: 100%;
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
       div input.pesquisa{
        float: right;
        width: 50%;
       }
       table{
        border-collapse: collapse;
        width: 100%;
        font-size: 11px;
        overflow: hidden;
       }
   
       table th{
        border: 1px solid #ddd;
        padding: 2px;
        background-color: rgb(61, 104, 184);
        color: white;
        text-align: center;
       }
       table tbody td{
        border: 1px solid #ddd;
        padding: 4px;
        cursor: pointer;
       }
       table tbody td a{
        text-decoration: none;
}
       table tbody tr:hover{
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
        width: 50%;
        /*background-color: rgb(43, 226, 119);*/
        margin: 0 auto;
        float: left;
        line-height: 8px;
        margin-left: 10px;
       }
       div.foto{
        height: 100%;
        width: 45%;
        /*background-color: rgb(72, 65, 165);*/
        margin: 0 auto;
        float: right;
       }
       div.foto img{
        width: 60%;
        float: right;
        margin-top: 8px;
        margin-right: 20px;
        border: solid 3px darkgray;
       }
       p span.negrito{
        font-weight: "bold" ;
       }
       th input#txt_uuid{
        width: 90%;
       }
       th input#txt_nome{
        width: 80%;
       }
       th input#txt_peso{
        width: 80%;
       }
       th input#txt_raca{
        width: 80%;
       }
       th input#txt_sexo{
        width: 80%;
       }
       td.coluna_maior{
        width: 30%;
       }
       th.coluna_maior{
        width: 30%;
       }

    </style>
</head>
<body>
    
    <main>
        <header>
            <h2>Listar animais</h2>
        </header>
        <section class="esquerda">
            <div class="lista">
                <table id="listagem">
                    <thead>
                        <tr class="primeira_linha">
                        <th class="coluna_maior">UUID</th>
                        <th>Nome</th>
                        <th>Raça</th>
                        <th>Peso</th>
                        <th>Sexo</th>
                    </tr>
                    <tr class="segunda_linha">
                        <th class="coluna_maior"><input type="text" id="txt_uuid"/></th>
                        <th><input type="text" id="txt_nome"/></th>
                        <th><input type="text" id="txt_raca"/></th>
                        <th><input type="text" id="txt_peso"/></th>
                        <th><input type="text" id="txt_sexo"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                        for ($cont=0; $cont < count($nome); $cont++){
                        echo '<tr onclick="onClick(
                        \''.$uuid[$cont].'\',
                        \''.$nome[$cont].'\',
                        \''.$raca[$cont].'\',
                        \''.$peso[$cont].'\',
                        \''.$sexo[$cont].'\',
                        \''.$nome_mae[$cont].'\',
                        \''.$nome_pai[$cont].'\',
                        \''.$data_nascimento[$cont].'\',
                        \''.$data_abate[$cont].'\',
                        \''.$foto[$cont].'\'
                        )">'; 
                                             
                        echo "<td class=\"coluna_maior\">".$uuid[$cont]."</td>";
                        echo "<td>".$nome[$cont]."</td>";
                        echo "<td>".$raca[$cont]."</td>";
                        echo "<td>".$peso[$cont]."</td>";
                        echo "<td>".$sexo[$cont]."</td>";
                        echo "</tr>";
                        }
                        ?>
                    </tbody>
                </table>
            </div>
            <!--
            <div class="filtro">
                <label for="filtro">Filtro (Raça, Peso, Nome):</label>
                <input type="text" id="filtro" class="pesquisa">
            </div>
                    -->
        </section>
        <section class="direita">
            <div class="superior">
                <div class="info">
                <p class="esq">
                        <b>
                        UUID:
                        </b>                        
                        <span id="uuid_animal"></span></p>
                    <p class="esq">
                    <p class="esq">
                        <b>
                        Nome:
                        </b>
                        <span id="nome_animal"></span></p>
                    <p class="esq">
                        <b>
                        Raça:
                        </b>
                        <span id= "raca_animal"></span></p>
                    <p class="esq">
                        <b>
                        Peso:
                        </b>
                        <span id= "peso_animal"></span></p>
                    <p class="esq">
                        <b>
                        Sexo:
                        </b>
                        <span id= "sexo_animal"></span></p>
                    <p class="esq">
                        <b>
                        ID Mãe:
                        </b>
                        <span id= "mae_animal"></span></p>
                    <p class="esq">
                        <b>
                        ID Pai:
                        </b>
                        <span id= "pai_animal"></span></p>
                    <p class="esq">
                        <b>
                        Data Nascimento:
                        </b>
                        <span id= "data_nascimento_animal"></span></p>
                    <p class="esq">
                        <b>
                        Data Abate:
                        </b>
                        <span id= "data_abate_animal"></span></p>
                </div>
                <div class="foto">
                <img id="foto_animal" src="images/no-image.png" alt="">
                </div>
            </div>
            <div class="meio"></div>
            <div class="inferior"></div>
        </section>
    </main>
</body>
</html>
