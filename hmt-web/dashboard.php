<!DOCTYPE html>
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
</head>
<html lang="en">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js">
    </script>
    <?php
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
    
    $sql = "SELECT (SELECT COUNT(*) FROM `ox` WHERE `weight` < 200) as 'menor200', (SELECT COUNT(*) FROM `ox` WHERE `weight` > 200 AND `weight` < 300) as '200e300', (SELECT COUNT(*) FROM `ox` WHERE `weight` > 300 AND `weight` < 400) as '300e400', (SELECT COUNT(*) FROM `ox` WHERE `weight` > 400) as 'maior400'";
    
    $result = $conn->query($sql);
    
    if ($result->num_rows > 0) {
        // output data of each row
            $i=0;
            while($row = $result->fetch_assoc()) {
                    $menor200[$i]= $row["menor200"];
                    $entre200e300[$i]= $row["200e300"];
                    $entre300e400[$i]= $row["300e400"];
                    $maior400[$i]= $row["maior400"];
                    $i++;
            }
             $conn->close();
        }
        $conn2 = new mysqli($servername, $username, $password, $dbname);
    // Check connection
    if ($conn2->connect_error) {
        die("Connection failed: " . $conn2->connect_error);
    }
    
    //$sql2 = "SELECT (SELECT COUNT(*) FROM `ox` WHERE `weight` < 200) as 'menor200', (SELECT COUNT(*) FROM `ox` WHERE `weight` > 200 AND `weight` < 300) as '200e300', (SELECT COUNT(*) FROM `ox` WHERE `weight` > 300 AND `weight` < 400) as '300e400', (SELECT COUNT(*) FROM `ox` WHERE `weight` > 400) as 'maior400'";
    $sql2 = "SELECT `id` FROM `graze` ORDER BY `time` DESC LIMIT 1";
    $result2 = $conn2->query($sql2);
    
    if ($result2->num_rows > 0) {
        // output data of each row
            $i=0;
            while($row2 = $result2->fetch_assoc()) {
                    $graze_id_f[$i]= $row2["id"];
                    $i++;
            }
             $conn2->close();
        }
             $conn3 = new mysqli($servername, $username, $password, $dbname);
             // Check connection
             if ($conn3->connect_error) {
                 die("Connection failed: " . $conn3->connect_error);
             }
             $graze_id = $graze_id_f[0];
             //$graze_id = 'ed690064-d8c3-49f5-82af-9b73395a0188';
             //$graze_id = '57f60fe7-e475-434b-9007-11016fe41715';
             //$sql = "SELECT (SELECT COUNT(*) FROM `ox` WHERE `weight` < 200) as 'menor200', (SELECT COUNT(*) FROM `ox` WHERE `weight` > 200 AND `weight` < 300) as '200e300', (SELECT COUNT(*) FROM `ox` WHERE `weight` > 300 AND `weight` < 400) as '300e400', (SELECT COUNT(*) FROM `ox` WHERE `weight` > 400) as 'maior400'";
             $sql3 = "SELECT (SELECT COUNT(*) FROM `oxen_in_graze` WHERE `graze_id`= '".$graze_id."' AND `is_present` = true) as 'presente', (SELECT COUNT(*) FROM `oxen_in_graze` WHERE `graze_id`= '".$graze_id."' AND `is_present` = false) as 'ausente'";
             $result3 = $conn3->query($sql3);
             
             if ($result3->num_rows > 0) {
                 // output data of each row
                     $i=0;
                     while($row3 = $result3->fetch_assoc()) {
                        $presente[$i]= $row3["presente"];
                        $ausente[$i]= $row3["ausente"];
                             $i++;
                     }
                      $conn3->close();
            }
            $conn4 = new mysqli($servername, $username, $password, $dbname);
    // Check connection
    if ($conn4->connect_error) {
        die("Connection failed: " . $conn4->connect_error);
    }
    
    $sql4 = "SELECT id FROM `graze`";
    
    $result4 = $conn4->query($sql4);
    
    if ($result->num_rows > 0) {
        // output data of each row
            $i4=0;
            $cont_ausente = 0;
            while($row4 = $result4->fetch_assoc()) {
                    $id_graze[$i4]= $row4["id"];
                    $conn5 = new mysqli($servername, $username, $password, $dbname);
                      // Check connection
                      if ($conn5->connect_error) {
                          die("Connection failed: " . $conn5->connect_error);
                      }
                      
                      $sql5 = "SELECT (SELECT COUNT(*) FROM `oxen_in_graze` WHERE `graze_id`= '".$id_graze[$i4]."' AND `is_present` = false) as 'ausente'";
                      
                      $result5 = $conn5->query($sql5);
                      
                      if ($result5->num_rows > 0) {
                          // output data of each row
                              $i5=0;
                              
                              while($row5 = $result5->fetch_assoc()) {
                                      $qtd_ausente[$i5]= $row5["ausente"];
                                      if ($qtd_ausente[$i5]){
                                        $cont_ausente ++;
                                      }
                                      $i5++;
                              }
                              $conn5->close();
                          }
                    $i4++;
            }
             $conn4->close();
             
          }
    ?>

     <script type="text/javascript">
      // Load Charts and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Draw the pie chart for Sarah's pizza when Charts is loaded.
      google.charts.setOnLoadCallback(drawPesoChart);

      // Draw the pie chart for the Anthony's pizza when Charts is loaded.
      google.charts.setOnLoadCallback(drawRastreamentoTotalChart);

      // Draw the pie chart for the Anthony's pizza when Charts is loaded.
      google.charts.setOnLoadCallback(drawUltimoRastreamentoChart);

      // Callback that draws the pie chart for Sarah's pizza.
      function drawPesoChart() {

        var data = new google.visualization.DataTable();
      var data = google.visualization.arrayToDataTable([
        ['Peso', 'QTD', { role: 'style' }],
        ['Abaixo de 200 KG', <?php echo $menor200[0]; ?>, '#3D68B8'],            // RGB value
        ['Entre 200 KG e 300 KG', <?php echo $entre200e300[0]; ?>, '#3D68B8'],            // English color name
        ['Entre 300 KG e 400 KG', <?php echo $entre300e400[0]; ?>, '#3D68B8'],
        ['Acima de 400 KG', <?php echo $maior400[0]; ?>, '#3D68B8'], // CSS-style declaration
      ]);

      var options = {
        title: 'Distribuição do gado por peso',
        width:400,
        height:300,
        hAxis: {
          title: 'Divisão por peso',
          viewWindow: {
            min: [7, 20, 0],
            max: [17, 20, 0]
          }
        },
        vAxis: {
          title: '(Escala de 1 a 10)'
        }
      };

      var chart = new google.visualization.ColumnChart(
        document.getElementById('Peso_div'));

      chart.draw(data, options);
      }

      // Callback that draws the pie chart for Anthony's pizza.
      function drawRastreamentoTotalChart() {

        // Create the data table for Anthony's pizza.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Sem ausências', <?php echo $i4 - $cont_ausente; ?>],
          ['Com ausências', <?php echo $cont_ausente; ?>]
        ]);

        // Set options for Anthony's pie chart.
        var options = {title:'Análise de todos os rastreamentos',
                       width:400,
                       height:300};

        // Instantiate and draw the chart for Anthony's pizza.
        var chart = new google.visualization.PieChart(document.getElementById('Rastreamento_total_div'));
        chart.draw(data, options);
      }

      
      // Callback that draws the pie chart for Anthony's pizza.
      function drawUltimoRastreamentoChart() {

        // Create the data table for Anthony's pizza.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
        ['Presentes', <?php echo $presente[0]; ?>],
        ['Ausentes', <?php echo $ausente[0]; ?>]
        ]);

        // Set options for Anthony's pie chart.
        var options = {title:'Análise do último rastreamento',
                    width:400,
                    height:300};

        // Instantiate and draw the chart for Anthony's pizza.
        var chart = new google.visualization.PieChart(document.getElementById('Ultimo_rastreamento_div'));
        chart.draw(data, options);
        }
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
    </style>
  </head>
  <body>
  <main>
    <header>
      <h2>Dashboard</h2>
    </header>
    <section>
  <table class="columns">
      <tr>
        <td><div id="Peso_div" style="border: 1px solid #ccc"></div></td>
        <td><div id="Rastreamento_total_div" style="border: 1px solid #ccc"></div></td>
        <td><div id="Ultimo_rastreamento_div" style="border: 1px solid #ccc"></div></td>
      </tr>
      <?php //echo $presente[0]; 
        //echo $sql2;
        //echo $id_graze[0];
        //echo $id_graze[10];
      ?>
    </table>
    </section>
    </main>
  </body>
</html>