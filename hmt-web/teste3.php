
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

    $(function(){
    $("#listagem2 input").keyup(function(){/*       
        var index = $(this).parent().index();
        var nth = "#listagem td:nth-child("+(index+1).toString()+")";
        var valor = $(this).val().toUpperCase();
        $("#listagem tbody tr").show();
        $(nth).each(function(){
            if($(this).text().toUpperCase().indexOf(valor) < 0){
                $(this).parent().hide();
            }
        });*/
        alert ("teste");
    });
 
    /*$("#tabela input").blur(function(){
        $(this).val("");
    });*/
});
    </script>

</head>
<body>    
                <table id="listagem2">
                    <thead>
                        <tr class="primeira_linha">
                        
                        <th>Nome</th>
                        
                    </tr>
                    <tr class="segunda_linha">
                        
                        <th><input type="text" id="txt_nome"/></th>
                        
                    </tr>
                    </thead>
                </table>
</body>
</html>
