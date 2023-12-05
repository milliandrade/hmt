<?php
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
    echo "O arquivo ". htmlspecialchars( basename( $_FILES["picture__input"]["name"])). " foi carregado com sucesso.";
  } else {
    echo "Houve um erro no carregamento do arquivo.";
  }
}

?>