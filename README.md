# Simple Banking System

A simple banking system created with Java. This simple system features basic functions including deposit, withdraw ,and transfer and client profile management. Users interact with the system on the terminal.

## Getting Started

### Requirements

- Java 11+
- Gson==2.8.8

### Installing

* **If you are using VScode as your IDE**

Simply `git clone` this project either through command line or through desktop app, and you are good to go.

Any setting required is included in the folder `.vscode`, but the `Gson` library need to be downloaded manually.

* **If you are using other IDE**

After `git clone` the project, you need to do extra work to set up the environment for development. Details please refer to related documentations.

### Executing program

* Run `ATM.java` with IDE or building tools and you can start to experience the services

## Project Files Description

* `ATM.java`: Main program

* `OptionMenu.java`: Where the main program `ATM.java` runs on

* `Account.java`: A class stores a client's account number, pin number , name, balance and transaction logic

* `Profile.java`: A class stores a client's profile including phone number, address, email information

* `DataPool.java`: A generic class to store data in a hash map with account number as key and an object as value

* `ReadWriteToFile.java`: A support class to read clients data from and write to a `json` file as a database

## Data Files

Every time the system is started, these json files are extracted and converted to corresponding data pool. The data pool will be converted and written to the original json files after the user exits the applicaiton.

* `account.txt`: Where stores clients' account number as key and the information in `class Account` as value.

* `profile.txt`: Where stores clients' account number as key and the information in `class Profile` as value.

## Features

* User can open an account and make deposit, withdraw and transfer money to another existing account.

* Clients can edit their phone number, address and email address as needed.

* All data is written to json files for clients later login.

## Help and Resources

The tricky part of this project is to convert generic classes to json file through Gson. When using `ReadWriteToFile` class, it results in error for not clearly specifying the type of the parameter in the generic class. To avoid this runtime error, the data type of the `DataPool` parameter is required to specify at compile time and thus it's stated in the `OptionMenu.java` when initiating the class.

If you still have problems about deserialising nested, generic classes with Gson, the detailed explanation can be found [here](https://stackoverflow.com/questions/14503881/strange-behavior-when-deserializing-nested-generic-classes-with-gson).

