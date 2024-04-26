# Fastfood Ordering and Management System (FOMS)
This is our group assignment for SC2002 (Object Oriented Programming).

# Setup Instructions
## Project Folder Structure
> Folder structure of our project
### Top Level Directory Layout
````
.
├── build                   # Compiled jar file
├── docs                    # Javadocs generated as html
├── src                     # Source files (all the codes)
└── README.md
````

### Source Files
````
.
├── ...
├── src                    # Source files (all the codes)
│   ├── controller         # Controller classes
│   ├── enums              # Enums classes
│   ├── helper             # Helper classes
│   ├── main               # Main Driver file (FOMS App)
│   ├── model              # Model classes
│   ├── repository         # Repository classes
│   └── view               # View classes
└── ...
````

## Scripts
> How to run our project
1. In your command line change directory into src
````
C:\Users\Jacky\Documents\NTU\Y1S2\SC2002 Object Oriented Programming\Assignment\foms
````

2. Compile the java files using command line
````
javac -d bin src/controller/*java src/enums/*java src/helper/*java src/model/*java src/repository/*java src/view/*java src/main/*java
````

3.  Run the java file using command line
````
java './src/main/FOMSApp.java'
````

# Java Docs
Create javadocs - make sure you are at foms directory
````
javadoc -d ./docs/ ./src/controller/*java ./src/enums/*java ./src/helper/*java ./src/main/*java ./src/model/enums/*java ./src/repository/*java ./src/view/*java -encoding ISO-8859-1
````
Launch the index.html under ./javadoc/index.html

# Contributors
- @Jeakai
- @lyh0805
- @CHAN1203
- @YapHS0514
- @smtio2402
