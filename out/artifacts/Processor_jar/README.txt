Instruction pour obtenir la documentation au format HTML :

- dans la console, se déplacer dans le dossier du projet dont on veut la documentation
- taper : dir /s /B *.java > sources.txt (Cette commande va chercher tous les fichiers qui se terminent par ".java" (donc nos fichiers sources) dans le dossier courant les sous-dossiers, et envoie tout les résultats trouvés dans le fichier sources.txt.)
- taper : javac -processorpath D:\MesProjets\GitHubProjects\unicorn-desktop\out\artifacts\Processor_jar\Processor_jar.jar -processor processor.HTMLProcessor @sources.txt




D:\MesProjets\GitHubProjects\unicorn-desktop\out\artifacts\Processor_jar\Processor_jar.jar