#Creare il jar eseguibile
Nella cartella del progetto aprire il prompt dei comandi e digitare "mvn package assembly:single" questo dovrebbe creare il nostro jar eseguibile
all'interno della cartella target.

#Eseguire il jar
E' necessario aprire la cartella target, dove e' presente il runnable jar,
Aprire il promt dei comandi ed esegure il comando "java -jar <nome del file.jar>", nel nostro caso "java -jar Service_Menu-0.1.jar".
All'esecuzione del comando in console avremo un messaggio che ci avvisa di aver creato una nuova cartella output nella directory principale (Service_Menu) 
all'interno della nuova cartella avremo il nostro file excel.