Instructions to run the code -

1. Go to the scripts folder and open a new terminal inside the folder.
2. Log into mysql and run the three script files in the folder after creating a new database named 'restaurant'. Use commands

create database restaurant;
use restaurant;
source Restaurant_create.sql;
source Restaurant_alter.sql;
source Restaurant_data.sql;

3. Open the src folder in VS Code and make sure the connector present in the lib folder is connected to the project.
4. Go to DAO_Factory.java and change the username and password accordingly on line 16, 17.
5. Go to Interface.java and run it.
