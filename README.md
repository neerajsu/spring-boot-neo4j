# Neo4j backend Tutorial

This project is a sample project created for backend of neo4j tutorial at : [YouTube neo4j tutorial](https://www.youtube.com/watch?v=_IgbB24scLI&list=LL&index=7&t=9131s)
Code for tutorial available [here](https://github.dev/fhsinchy/spring-neo4j/tree/master/src/main/java/dev/farhan/springneo4j)

This project is slightly modified version compared to the code from the tutorial.

## Deployment

This project uses Gradle and not maven. To run this project locally, do the following. This project uses Jdk 17. You'll need to setup this JDK either in IntelliJ idea and/or JAVA_HOME environment variable.

**To RUN this project**

```bash
  run.cmd
```

**To Debug this project**

```bash
  debug.cmd
```

You'll see in the terminal : Listening for transport dt_socket at address: 5005

Create a debug confirguration. Edit configurations.

Click + on top left and select. Remote JVM debug.
Rename it to "Debug project".

Debug is setup.
Then select debug option. Icon that looks like bug.
Once clicked the terminal will show output that Spring boot application has started.

Now you can put break points to debug.

## Setting up database

Create a neo4j database and run the following script

```bash
  src/main/resources/static/initDatabase.script
```

Run the statements in the script file on neo4j database to create the initial database script

To drop all the changed records. Delete all nodes and relationship using the following script to cleanup.

```bash
MATCH (n)
DETACH DELETE n
```

After removing all nodes, you can execute statements/query in initDatabase.script to reset data