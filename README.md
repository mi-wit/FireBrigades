# FireBrigades

FireBrigades is a simple program using k-vertex-connected graph. Given Cities (Nodes) and Roads (paths), program returns the Cities names in with will be Fire Brigades set in a way that firefighters can drive to every City under specified time, but also leaving as little FireBrigades as possible. 

## Running program 

*Program requires Java RE 1.8 installed.*
1.	Put file “in.json” in same folder as FireBrigades.jar
2.	Open CMD and go to this folder,
3.	Use java command to run program: 
`java –jar FireBrigades.jar`
4.	Program will start and it will be working for number of seconds specified inside “in.json” file as “timeout”. When time ends, the result will be saved in the same folder in file “out.json”.

## Compiling and running tests

In order to run tests, you need to have [Apache Maven](https://maven.apache.org/install.html) installed.

Note: Maven requires Java JDK, otherwise program compilation will fail.
	
  
1.	Open cmd and go to FireBrigades whole project directory.
2.	Run maven command:
`mvn package`
3.	Program will compile, and all tests will run, showing results.
