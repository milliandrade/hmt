
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
    $("#listagem input").keyup(function(){/*       
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
    <main>
        <header>
            <h2>Listar rastreamentos</h2>
        </header>
        <section class="esquerda">
            
                <table id="listagem">
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
                    <!--<tbody>
                    <tr onclick = "onClick('09dfbb50-11e4-4333-9c57-6723deb65e5b')">
<td>2022-12-11</td>
<td class="coluna_maior">09dfbb50-11e4-4333-9c57-6723deb65e5b</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('38eeeda0-7b4f-449d-b381-9c2abd929712')">
<td>2022-12-10</td>
<td class="coluna_maior">38eeeda0-7b4f-449d-b381-9c2abd929712</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('57f60fe7-e475-434b-9007-11016fe41715')">
<td>2022-12-11</td>
<td class="coluna_maior">57f60fe7-e475-434b-9007-11016fe41715</td>
<td>5 </td><td>1</td></tr><tr onclick = "onClick('58a2125f-e98b-4573-bd8e-d958b3a10adc')">
<td>2022-12-10</td>
<td class="coluna_maior">58a2125f-e98b-4573-bd8e-d958b3a10adc</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('58c864e7-23f4-484a-b185-47b2f3d162fe')">
<td>2022-12-10</td>
<td class="coluna_maior">58c864e7-23f4-484a-b185-47b2f3d162fe</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('5908e767-27da-491b-b187-90348703cbfb')">
<td>2022-12-10</td>
<td class="coluna_maior">5908e767-27da-491b-b187-90348703cbfb</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('5a80ac73-3248-420b-9f6b-49496ecee929')">
<td>2022-12-10</td>
<td class="coluna_maior">5a80ac73-3248-420b-9f6b-49496ecee929</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('5cc8fa72-9718-4aaa-bd34-360c7a81121b')">
<td>2022-12-10</td>
<td class="coluna_maior">5cc8fa72-9718-4aaa-bd34-360c7a81121b</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('63de90a9-bd4b-4965-bd53-cff5140f6d12')">
<td>2022-12-10</td>
<td class="coluna_maior">63de90a9-bd4b-4965-bd53-cff5140f6d12</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('6415b42d-2ee0-43df-afd9-5772fc3cfb72')">
<td>2022-12-10</td>
<td class="coluna_maior">6415b42d-2ee0-43df-afd9-5772fc3cfb72</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('70a595b6-a718-4c66-b80b-e4354f1bdc98')">
<td>2022-12-10</td>
<td class="coluna_maior">70a595b6-a718-4c66-b80b-e4354f1bdc98</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('726e1491-f923-4cb9-bb42-b10e7359eec2')">
<td>2022-12-10</td>
<td class="coluna_maior">726e1491-f923-4cb9-bb42-b10e7359eec2</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('74153aac-0868-4bf1-9868-36d65ccf2840')">
<td>2022-12-10</td>
<td class="coluna_maior">74153aac-0868-4bf1-9868-36d65ccf2840</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('74bd526a-e613-485f-b523-3675fa975282')">
<td>2022-12-10</td>
<td class="coluna_maior">74bd526a-e613-485f-b523-3675fa975282</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('768fae55-0702-4478-a611-a9942a6a05af')">
<td>2022-12-10</td>
<td class="coluna_maior">768fae55-0702-4478-a611-a9942a6a05af</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('78ad15bc-5fd3-4128-9f59-df1b4b6b53da')">
<td>2022-12-10</td>
<td class="coluna_maior">78ad15bc-5fd3-4128-9f59-df1b4b6b53da</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('803ca0df-d7b8-4798-a030-f941209041fb')">
<td>2022-12-10</td>
<td class="coluna_maior">803ca0df-d7b8-4798-a030-f941209041fb</td>
<td>2 </td><td>0</td></tr><tr onclick = "onClick('8136feff-bc82-4830-87e2-bbe7c5de85cf')">
<td>2022-12-10</td>
<td class="coluna_maior">8136feff-bc82-4830-87e2-bbe7c5de85cf</td>
<td>0 </td><td>0</td></tr><tr onclick = "onClick('88632d31-be49-4cce-bfdc-7c1d43ee168c')">
<td>2022-12-10</td>
<td class="coluna_maior">88632d31-be49-4cce-bfdc-7c1d43ee168c</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('89b7da1a-0204-4a66-afec-e5d70bd34c72')">
<td>2022-12-10</td>
<td class="coluna_maior">89b7da1a-0204-4a66-afec-e5d70bd34c72</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('9121bb38-f0f7-4788-9bcc-b5e1127a6085')">
<td>2022-12-10</td>
<td class="coluna_maior">9121bb38-f0f7-4788-9bcc-b5e1127a6085</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('929222c5-c8fa-41c0-84bc-ca2c877b8934')">
<td>2022-12-10</td>
<td class="coluna_maior">929222c5-c8fa-41c0-84bc-ca2c877b8934</td>
<td>0 </td><td>0</td></tr><tr onclick = "onClick('955b7499-40d4-4f7d-ba5b-118e5bb7913d')">
<td>2022-12-10</td>
<td class="coluna_maior">955b7499-40d4-4f7d-ba5b-118e5bb7913d</td>
<td>0 </td><td>0</td></tr><tr onclick = "onClick('9671b47f-3ff5-411c-883d-3de52e73a4d8')">
<td>2022-12-10</td>
<td class="coluna_maior">9671b47f-3ff5-411c-883d-3de52e73a4d8</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('97521abe-bb7a-4e13-bd36-036094b74488')">
<td>2022-12-10</td>
<td class="coluna_maior">97521abe-bb7a-4e13-bd36-036094b74488</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('97e31617-6e12-46f4-9c84-a5273874f2ff')">
<td>2022-12-10</td>
<td class="coluna_maior">97e31617-6e12-46f4-9c84-a5273874f2ff</td>
<td>0 </td><td>0</td></tr><tr onclick = "onClick('9c4fae85-5f80-4b8b-9ee3-fc46725501ab')">
<td>2022-12-10</td>
<td class="coluna_maior">9c4fae85-5f80-4b8b-9ee3-fc46725501ab</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('d97370b9-3603-4a1b-ae22-d762773048e6')">
<td>2022-12-10</td>
<td class="coluna_maior">d97370b9-3603-4a1b-ae22-d762773048e6</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('e5f834c0-a3a2-4af9-83cf-a4da00017fc0')">
<td>2022-12-10</td>
<td class="coluna_maior">e5f834c0-a3a2-4af9-83cf-a4da00017fc0</td>
<td>0 </td><td>0</td></tr><tr onclick = "onClick('ed1455c0-4bef-4797-839e-0cba00e37f51')">
<td>2022-12-10</td>
<td class="coluna_maior">ed1455c0-4bef-4797-839e-0cba00e37f51</td>
<td>3 </td><td>2</td></tr><tr onclick = "onClick('ed690064-d8c3-49f5-82af-9b73395a0188')">
<td>2022-12-10</td>
<td class="coluna_maior">ed690064-d8c3-49f5-82af-9b73395a0188</td>
<td>0 </td><td>2</td></tr><tr onclick = "onClick('f5023b45-6b70-49e0-9138-d2894f56e98d')">
<td>2022-12-10</td>
<td class="coluna_maior">f5023b45-6b70-49e0-9138-d2894f56e98d</td>
<td>5 </td><td>6</td></tr><tr onclick = "onClick('f7523b67-d47e-419c-9a3f-f52cb47e032c')">
<td>2022-12-10</td>
<td class="coluna_maior">f7523b67-d47e-419c-9a3f-f52cb47e032c</td>
<td>0 </td><td>0</td></tr><tr onclick = "onClick('fb1a1ddd-3792-49ab-84a3-1a410d39380d')">
<td>2022-12-10</td>
<td class="coluna_maior">fb1a1ddd-3792-49ab-84a3-1a410d39380d</td>
<td>5 </td><td>0</td></tr><tr onclick = "onClick('fd0acdb2-396c-4240-9da9-bb8c58bfabed')">
<td>2022-12-10</td>
<td class="coluna_maior">fd0acdb2-396c-4240-9da9-bb8c58bfabed</td>
<td>5 </td><td>6</td></tr>                    </tbody>-->
                </table>
            </div>
            
        </section>
    </main>
</body>
</html>