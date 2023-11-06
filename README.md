there are a couple of structural changes:
To run the project, you will use the commands: gradlew build, and gradlew run, these commands work on Windows, but I am not sure about mac. 
We no longer need a libs folder to contain JavaFX and the json library, since gradle will handle dependencies
I have not set up the vscode classpath yet, so there will be no autocomplete, and no intellisense, which means that you will have to use gradlew build to see if there are any errors in the code. PLEASE USE gradlew build REGULARLY TO ENSURE THAT YOUR CODE COMPILES!
Resources and files that the code uses should preferably be stored in the src/resources folder, things like images, recipe lists, etc.
