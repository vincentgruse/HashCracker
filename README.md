This is a JavaFX application to find (brute force) the input string used to generate a given MD5 hash.

The UI provides the user with the following options:
- Provide the hash string to be cracked
- Select the dictionary file to be used

The dictionary file should contain one input string per line in the file. (Example password lists here: <https://github.com/danielmiessler/SecLists/tree/master/Passwords>)

If found, the application will display the matching string. Otherwise, it will display a failure message.

Directions for Building and Running from the command line:
1. Use "java -version" to ensure JDK v.21.0.2
2. Use "gradle -version" to ensure Gradle v.8.5
3. unzip and open the target folder
4. "gradlew build" to build the application
5. "gradlew run" to run the application