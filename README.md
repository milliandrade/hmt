# HMT Application

## Instalation Guide
The HMT application is composed of two layers: the backend application, implemented in Java and responsible for all business and communication modeling, and the frontend application, developed in PHP and responsible for the user interface. 
The backend application runs independently, but the web interface cannot execute all functionalities without the backend module, such as initializing a grazing tracking or getting information from the farm module.

### Backend application
In order to run the backend application, you need to have JavaSE 17 or higher and Maven installed.

To run the application, first you need to configure the database, by installing MySQL version 8.0.31, or higher, and configuring it with the database structure avaliable in file [hmt.sql](https://github.com/milliandrade/hmt_sw/blob/main/hmt.sql). For initializing it, run the following command:

    $ mysql -u <username> -p hmt < htm.sql
    
After configuring the database, verify that the computer's PATH variable contains Java's _bin_ directory. If auto-run is enabled, attempt to run the _HMT-PN-APPLICATION-1.0-jar-with-dependencies.jar_ file by double-clicking it.

If double-clicking fails, execute the JAR file through the command line or terminal window, using the following command:

    jar -jar HMT-PN-APPLICATION-1.0-jar-with-dependencies.jar

It will run the programm, using the following default settings for the mysql database connection 

    MYSQL_ADDRESS = "jdbc:mysql://45.56.126.161:3306/"
    DATABASE_USERNAME = <username>
    DATABASE_PASSWD = <password>
    
and ContextNet gateway connection.

    GATEWAY_IP_ADDRESS = "127.0.0.1"
    GATEWAY_IP = "scp.inf.puc-rio.br"

To connect with the farm module simulation, you must also run the ContextNet Kafka Core [ContextNet](https://wiki.lac.inf.puc-rio.br/) middleware. The application attempts to connect with ContextNet at all times to exchange information with the farm module. However, it provides the ability to use other functionalities that do not rely on new tracking information.
To initiate ContextNet gateways, download the ContextNet Kafka Core from github:

    $ git clone https://gitlab.com/lac-puc/context-net-kafka-core.git

And raise the Docker image of the gateway:

    $ sudo docker-compose -f /opt/ContextNet/CKC/docker-start-gw.yml up

If you want to check if the images are running:

    $ sudo docker ps -a

### Frontend web application
In order to run the web application, you need to have installed PHP 7.4.3, HTML5, CSS3, and a web server (we recomend Apache). This web server will host the front-end hmt web application in PHP. 

If you want the data to persist, install a MySQL version 8.0.31 or higher, create the database and import the SQL file using the following command:

    $ mysql -u <username> -p hmt < hmt.sql

Replace _<username>_ with the name of your database user.

Copy the _hmt-web_ directory to the PHP server directory. Then, enter the new location of hmt-web and open the config.ini file to modify the settings values  (servername, username, password, and dbname). Make sure that the updated data reflects the actual database access information, and that <username> and <password> are the same used in the MySQL database , in the backend application settings , and in the frontend application config.ini file.

Open the terminal and navigate to the root directory of the hmt-web. Proceed to run the following command to initiate the embedded PHP server.

    $ php -S localhost:8000

  This will allow the hmt application to be accessed via http://localhost:8000.



## Demo site
A demo HMT application webpage is avaliable in http://hmtpuc.ddns.net/hmt/ to visualize the application graphical interface and functionalities.

_Note: Functionalities as Grazing track does not work in demo mode, because it needs integration with a farm module._

## Simulation
The farm module can be simulated using the [Herd identification](https://github.com/milliandrade/herd_identification) simulation in GrADyS.

## Technical Documentation
