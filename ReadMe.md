### PROJECT TITLE 
TODO APPLICATION API TESTING USING REST ASSURED 

### PROJECT DESCRIPTION

The project is based on API testing of TODO PRACTICE APP that consist of following test cases:-

- Authentication Controller: 1 Valid & 3 Invalid Test Cases
- User Controller: 2 Valid & 6 Invalid Test Cases
- Create Task: 1 Valid & 3 Invalid Test Cases
- Edit Task: 1 test case 
- Delete Task: 1 test case 


### USED TOOLS AND FRAMEWORKS
1.Rest-Assured

2.Maven repository

3.TestNG


##### Prerequisites

Different ways to run this project.

```
1.Command Prompt
2.Eclipse
3.Batch File
```

### GETTING STARTED 

##### To run all the test cases

**Option A: Import your project into Eclipse (recommended) **

In Eclipse, go to the File menu and choose:


   - File -> Import... -> Existing Maven Projects into Workspace
  
   - Select the directory containing this file.
  
   - Click Finish.
 

You can now browse the project in Eclipse.

   - Now, right click on the project and go to the Run As
   
   - Click maven clean and then maven install

**Option B: Import your project into Command Prompt **

In command prompt, go to the path where the project is located

   - enter maven clean
   
   - enter maven install
   
**Option C: Open the .bat file **
 
###NOTE

**A**:-To Run the project through user credentials, then follow the following steps:-.

```
	Note that username and password for accessing token must be todolist and password respectively.
```

**Steps**


```
	Step 1.Go to the src/test/resources source folder
	Step 2.Open the API_Data.xlsx
	Step 3.Change the credentials accordingly
	Step 4.Save it.
```



### src/test/java DESCRIPTION

**com.todo.tests**- Define all the  tests and assertions.

**com.todo.utils**- Define the actions required to run the test cases.


### src/test/resources DESCRIPTION

**config.properties**- It provides the baseUri , endpoints and excel path.

**API_Data.xlsx**- Excel while where user can provide the request body


### src/main/resources DESCRIPTION

**log4j.properties**- log4j file to run logs.



### REPORTS FOLDER

Generate the extent reports for all the test cases

### LOG FOLDER

Stores all the logs

### RunProject Batch File

Use this to run the project through .bat file


##### TESTER NAME

Samiksha Gupta

