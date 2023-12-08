
<?php
header( "refresh:2;url=listar_animal.php" );      
$config = parse_ini_file('config.ini');
         
$servername = $config['servername'];
$username = $config['username'];
$password = $config['password'];
$dbname = $config['dbname'];
$table = "ox";
$nome = $_POST['nome'];
$uuid = $_POST['uuid'];
$peso = $_POST['peso'];
$raca = $_POST['raca'];
$sexo = $_POST['sexo'];
$nome_pai = $_POST['nome_pai'];
$nome_mae = $_POST['nome_mae'];
$data_nascimento = $_POST['data_nascimento'];
$imagem = $_POST['picture__input'];

$target_dir = "images/";
$target_file = $target_dir . basename($_FILES["picture__input"]["name"]);
$uploadOk = 1;
$imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));

// Check if $uploadOk is set to 0 by an error
if ($uploadOk == 0) {
  echo "Sorry, your file was not uploaded.";
// if everything is ok, try to upload file
} else {
  if (move_uploaded_file($_FILES["picture__input"]["tmp_name"], $target_file)) {
    //echo "O arquivo ". htmlspecialchars( basename( $_FILES["picture__input"]["name"])). " foi carregado com sucesso.";
  } else {
    //echo "Houve um erro no carregamento do arquivo.";
  }
}

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "INSERT INTO `ox` (`id`, `name`, `breed`, `weight`, `gender`, `mom_id`, `dad_id`, `birth`, `photo`) VALUES ('".$uuid."', '".$nome."', '".$raca."', '".$peso."', '".$sexo."', '".$nome_mae."', '".$nome_pai."', '".$data_nascimento."', '".$target_file."')";

//echo $sql;

$result = $conn->query($sql);
$conn->close();

$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "UPDATE `farm` SET `notify` = '2' WHERE `farm`.`name` = 'HMT Farm System'";

//echo $sql;

$result = $conn->query($sql);
$conn->close();

echo "Cadastro realizado com sucesso!";
//echo $nome;
?>
</body>
</html>
