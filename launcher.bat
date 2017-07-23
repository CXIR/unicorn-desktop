timeout 1
FOR %%f IN (plugin\temporaryJar\*) DO DEL /F %%f
java -cp "lib/*;target/classes" model.Main
