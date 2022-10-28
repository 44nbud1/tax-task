# Exercise

A tax invoice transaction has the following elements.
- customer Id for instance `123`
- commercial invoice number that would look like `2021/04/customer1-12212` 
- timestamp - of format ```year-month-dayThour:minute:second```
- amount - of format ```dollars.cents```
- tax type which could be the following: GST, PAYROLL, COMPANY_TAX, LAND_TAX, CAPITOL_GAIN

Transactions are to be received in a file as a comma separated string of elements, one per line, e.g.:

```123, 2021/04/customer1-12212, 2021-04-29T13:15:54, 10.00, GST```

Write a command line application which will print a tax report for the tax given in input. 

The tax Report will be for a customer and a tax type and 10% of the sum of all tax invoices

The arguments of the command line will be the tax, the customer id and a filename, e.g.:

```your-app GST 123 filename.csv```

The file passed to your app will contain a sequence of tax invoices in chronological order.
Your app should print out something like in the console: 

```
For tax GST, customer 123 has declared $123.4
```
# TAX COMPUTATION
Hello, In this project have finished to create a tax computation cli in java. In order to make the project, I was using tools and env:
1. Laptop MacBook Pro 2021 M1
2. IDE Intellij 2021
3. Programming language Java

In order to make easier when develop the tak, I use several library from maven repository: 

		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.11</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.24</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>info.picocli</groupId>
			<artifactId>picocli</artifactId>
			<version>3.9.6</version>
		</dependency>
		<dependency>
			<groupId>net.moznion</groupId>
			<artifactId>random-string</artifactId>
			<version>1.1.0</version>
		</dependency>
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.0</version>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-all</artifactId>
			<version>1.10.19</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<version>5.8.1</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<version>4.6.1</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-junit-jupiter</artifactId>
			<version>4.8.1</version>
			<scope>test</scope>
		</dependency>

## Project Structure

    systems.achilles.tax.cli
    |-bootstrap
    |-dto
    |-enums
    |-exception
    |-model
    |-processor
    |-service
    |-util
    |-validation

1. bootstrap

    Bootstrap is used for running the program. The main class in this project is TaxApplicationCommand, where this class will implement picocli in order to map the request to the variable that is declared.
2. dto

    dto stands for data transfer object, this package is used for communicate between main class to service, if we need to change our port we just change in this dto.

3. enums

   enums is package which is used for storing our constant value, in this task I use it to store tax type enum.

4. exception

   An exception is an object that represents an error or an exceptional situation that has occurred. This project I need to create a new exception in order to map our error process. 

5. model

   model is used to communicate between service. 

6. processor

   This package is used to process the request which come from user and will call to the service. 

7. service

   service is used for processing business logic

8. util

   util is used to store class or method that common use in this project

9. validation

   validation is used for checking our request is acceptable or not.

## How to run this Project

1. Please open terminal go to the directory that store this project. 
2. move to bootstrap folder **cd /src/main/java/systems/achilles/tax/cli/bootstrap**
3. type **javac TaxApplicationCommand.java**
4. type **java TaxApplicationCommand arg0 arg1 arg2**
   1. arg0 = taxType
      taxType There are 5 transactions type which are (GST | PAYROLL | COMPANY_TAX | LAND_TAX | CAPITOL_GAIN) 
   2. arg1 = customerId
      Customer id user, e.g = 123
   3. arg1 = fileLocation
      file location is name of csv file, the file should be stored under **resources** folder. file location should be written with sufix **.csv**
5. if we success generate the code the cli will show you detail of tax: 


    
	      For tax CAPITOL_GAIN, customer 2751 has declared $826.78
	      For tax CAPITOL_GAIN, customer 3201 has declared $1000.81
	      For tax CAPITOL_GAIN, customer 3327 has declared $349.04
	      For tax CAPITOL_GAIN, customer 4527 has declared $1678.94
	      For tax CAPITOL_GAIN, customer 4758 has declared $634.92
	      For tax CAPITOL_GAIN, customer 6908 has declared $346.46
	      For tax CAPITOL_GAIN, customer 8143 has declared $172.57
	      For tax COMPANY_TAX, customer 3077 has declared $492.66
	      For tax COMPANY_TAX, customer 3201 has declared $1210.84
	      For tax COMPANY_TAX, customer 3327 has declared $122.56
	      For tax COMPANY_TAX, customer 5218 has declared $950.60
	      For tax COMPANY_TAX, customer 6908 has declared $835.85
	      For tax GST, customer 3077 has declared $1786.95
	      For tax GST, customer 4758 has declared $654.94
	      For tax LAND_TAX, customer 3201 has declared $1085.25
	      For tax LAND_TAX, customer 3327 has declared $303.96
	      For tax LAND_TAX, customer 5218 has declared $460.82
	      For tax LAND_TAX, customer 6908 has declared $632.53
	      For tax LAND_TAX, customer 9832 has declared $632.28
	      For tax PAYROLL, customer 3201 has declared $563.19
	      For tax PAYROLL, customer 3327 has declared $827.01
	      For tax PAYROLL, customer 6908 has declared $942.44
	      For tax PAYROLL, customer 8143 has declared $1038.43

6. If there are some exceptions occurs, the system will give you a message: 

   - Request less than 2 or more than 3
   Request invalid, request length less than 3 or more than 3, request: [, ] length: 2Tax Report CLI.
   
   - File csv not found
   [Tax_Service]Error occur: File is not found
   - etc.
