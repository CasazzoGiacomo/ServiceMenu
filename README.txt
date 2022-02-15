Per creare il jar eseguibile utilizzando come IDE Eclipse,
è necessario esportare il progetto (tasto destro, export) scegliendo Java > Runnable JAR file
fatto ciò selezonare la classe con il metodo main() ed inserirla nel "Launch configuration"
e, tenendo spuntata la casella "Extract required libraries into generated JAR", selezioniamo
nell' "Export destination" la cartella in cui vogliamo creare il runnable Jar.

Per eseguire il jar,
è necessario andare sulla cartella dove lo abbiamo creato, per far si che funzioni correttamente in
quest'ultima devono esserci le cartelle di output, properties e input con all'interno il
file.json.
Aprire il promt dei comandi ed esegure il comando "java -jar <nome del file.jar>".
All'interno della cartella output dovrebbe aver creato il file excel.