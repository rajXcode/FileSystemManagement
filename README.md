# FileManagementSystem
Project to handle the changes on the file system.
Task:	
1. Create	a	database	design	to	able	to	store	given	file	system	as	above.	
2. Create	a	text	file	with	above	file	system	structure	and	read	and	insert	into	database.	Inserting	above	
structure	into	database	manually	will	not	be	accepted.	
3. Research	for	possible	solutions	and	explain	that	why	your	solution	is	better	fit	than	other	possible	
solutions.	
4. Create	a	web	interface	for	the	user	so	they	can	able	to	search	any	file	or	folder	within	database.	
Web	search	interface	should	only	include	an	input	box	and	search	button.	When	a	user	click	search	
button,	result	should	be listed	as	below.	For	example	if	the	user enter	‘image’	into	the	input	box	and	
the	click	search,	the	code	able	to	list	the	results	as	below.
C:\Documents\Images
C:\Documents\Images\Image1.jpg
C:\Documents\Images\Image1.jpg
C:\Documents\Images\Image3.jpg
5. The	code	should	be	written	with	OO	PHP	using	design	patterns. Unit	test	is	preferred	but	not	
required.	

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
 - IntelliJ IDEA 2019+
 - JDK 1.8
 - MySQL

Clone or download the repository on your local environment and import the project in IntelliJ IDEA and build the project.

## Running the tests from IntelliJ IDEA
Right click on the FileSystemManagementApplicationTest.java file and click on Run 'FileSystemManagementApplicationTest' and see if all the test cases pass successfully.

## Running test from command line
You can run the following command to run all your test cases:

```
mvn clean test
```

Or you can run a particular test as below

```
mvn clean test -Dtest=com.technologi.techtest1.FileSystemManagementApplicationTest
```

```
mvn clean test -Dtest=com.technologi.techtest1.FileSystemManagementApplicationTest#testGetter
```

## License
This project is licensed under the GNU GENERAL PUBLIC LICENSE-Version 3 - see the [LICENSE.md](LICENSE.md) file for details

## Authors
* **Rajender Singh Chauhan**
