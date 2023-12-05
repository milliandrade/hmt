<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rastreamento</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@500&display=swap" rel="stylesheet">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="icons//ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
    <script>
        function onClick(caminho){
            document.getElementById('radar').src = caminho;

            for (let a = 0; a < 2; a++){
            let tbody = document.getElementById('tb_presentes');
            tbody.innerText = '';

            for (let i = 0; i < 20; i++){
                let tr = tbody.insertRow();

                let td_uuid = tr.insertCell();
                let td_nome = tr.insertCell();
                let td_raca = tr.insertCell();

                td_uuid.innerText = '1234'+i;
                td_nome.innerText = 'bilu'+i;
                td_raca.innerText = 'nelore'+i;

            }
            let tbody2 = document.getElementById('tb_ausentes');
            tbody2.innerText = '';
            for (let i = 0; i < 3; i++){
                let tr = tbody2.insertRow();

                let td_uuid = tr.insertCell();
                let td_nome = tr.insertCell();
                let td_raca = tr.insertCell();

                td_uuid.innerText = '1234'+i;
                td_nome.innerText = 'bilu'+i;
                td_raca.innerText = 'nelore'+i;

            }
        }
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
        section.esquerda{
        width: 50%;
        height: 320px;
        float: left;     
        /*background-color: cadetblue;*/
       }
       section.esquerda table{
       border-collapse: collapse;
        width: 100%;
        font-size: 12px;
       }
       table th{
        border: 1px solid #ddd;
        padding: 4px;
        background-color: rgb(61, 104, 184);
        color: white;
       }
       table tbody td{
        border: 1px solid #ddd;
        padding: 4px;
       }
       div.presentes{
        width: 100%;
        height: 40%;    
        /*background-color: blue;*/
        overflow-y: auto;
       }
       div.ausentes{
        width: 100%;
        height: 40%; 
        overflow-y: auto;   
        /*background-color: gray;*/
       }
       
       section.direita{
        width: 50%;
        height: 320px;
        float: right;
        /*background-color: aqua;*/
       }
       div.btn_rastrear{
        width: 20%;
        height: 40%;    
        /*background-color: red;*/
        margin: auto;
       }
       div.btn_rastrear img{
        height: 100%;
        margin: 0 auto;
        cursor: pointer;
        }

       div.acompanhamento_esq{
        width: 30%;
        height: 60%;    
        /*background-color: purple;*/
        float: left;
       }
       div.acompanhamento_esq img{
        height: 80%;
        margin: 0 auto;
        margin-left: 15px;
        margin-top: 20px;
        }
       div.acompanhamento_dir{
        width: 70%;
        height: 60%;    
        /*background-color: green;*/
        float: right;
       }
       div.acompanhamento_dir p{
        margin-left: 10px;
        font-size: 12px;
        margin-top: 0px;
       }
       h4{
        font-size: 12px;
        margin-bottom: 0;
       }
       h2{
        margin-bottom: -10px;
       }
       
    </style>
</head>
<body>
    
    <main>
        <header>
            <h2>Rastrear pastagem</h2>
        </header>
        <section class="esquerda">
        <h4>Presentes:</h4>
            <div class="presentes">
                <table>
                    <thead>
                        <tr>
                            <th>UUID</th>
                            <th>Nome</th>
                            <th>Raça</th>
                        </tr>
                    </thead>
                    <tbody id="tb_presentes">
                        <!--
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        -->
                    </tbody>
                </table>
            </div>
            <h4>Ausentes:</h4>
            <div class="ausentes">
            <table>
                    <thead>
                        <tr>
                            <th>UUID</th>
                            <th>Nome</th>
                            <th>Raça</th>
                        </tr>
                    </thead>
                    <tbody id="tb_ausentes">

                        <!--<tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>
                        <tr>
                        <td>0f40b75a-d50b-4313-8a63-dca08f9f365e</td>
                        <td>Biduzinha</td>
                        <td>GIR</td>
                        </tr>-->
                    </tbody>
                </table>
            </div>
        </section>
        <section class="direita">
            <div class="btn_rastrear">
                <img src="images/drone2.png" alt="" onclick= "onClick('images/radar.gif')">
            </div>
            <div class="acompanhamento_esq">
                <img src="images/radar2.PNG" alt="" id="radar">
            </div>
            <div class="acompanhamento_dir">
            <p> <b>Data/hora Início:</b>
                <span class="info">12/11/2022 - 12:23</span>
            </p>
            <p> <b>Data/hora Fim:</b>
                <span>12/11/2022 - 12:28</span>
            </p>
            <p> <b>Duração:</b>
                <span>5 minutos</span>
            </p>
            <p><b>Presentes:</b>
                <span>23</span>
            </p>
            <p><b>Ausentes:</b>
            <span>5</span>
            </p>
            </div>
        </section>
    </main>
</body>
</html>