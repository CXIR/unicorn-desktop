timeout 1
FOR %%f IN (plugin\temporaryJar\*) DO DEL /F %%f
java -jar out/artifacts/Partage_ta_caisse_jar/Partage_ta_caisse.jar
