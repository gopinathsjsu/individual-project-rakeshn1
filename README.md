# Stock inventory

## Problem Statement

A simple application to maintain an inventory of stock and process the orders based on given conidtions.

## Instructions to run the application:
The system needs to have Java 8 installed.

## Instructions to run the JAR file:
- Navigate to the home folder of the application( /individual-project-rakeshn1/inventory/)
- Run the following command: java -jar out/artifacts/inventory_jar/inventory.jar "Items dataset File Path" "Card dataset File Path" "Order Information File Path" 
- For Example: java -jar out/artifacts/inventory_jar/inventory.jar "Dataset File Path" "Card Information File Path" "Order Information File Path"
- Output files will be created in the home path of the application(/individual-project-rakeshn1/inventory/).

## Design Patterns:
The following design patterns were used
1) Builder
2) Chain of Responsibility
3) Singleton

### Builder Design Pattern
Builder is a creational design pattern that lets us construct complex objects step by step. It is used to build complex objects. The builder pattern is used to build and store data in item storage. This involved several sub-tasks like reading data from file, creating objects of item class, and building item inventory.
It consists of:
- Builder
  - ItemStorageBuilder
  - CardStorageBuilder
  - OrderStorageBuilder
  
These internally uses csvReaders to read aand store data. Since building this storage is a step that involves several processes, a builder pattern can be used to efficiently do the task. The same procedure is used to build card storage and the order from the given file.

### Chain of Responsibility
It is a behavioral design pattern that lets us pass requests along a chain of handlers. This design pattern is used to validate if the given order can be processed to give an output file. The implementation involves a main handler with abstract methods and three validation handlers implementing these abstract methods. Each handler validates the order against given conditions and decides whether to give it for further processing or reject it with a suitable error message. 
Validation handlers include code for validating stock availability, stock existence and category limit validation.

### Singleton Design Pattern
Since there is only one common ItemStorage for all orders, it’s better to make the ItemStorage a singleton to make sure that only one instance of it is accessed in all the places. Similarly, CardStorage which is used to store card information is also made singleton.

## Class Diagram
![image](https://github.com/gopinathsjsu/individual-project-rakeshn1/blob/main/images/class.png)

## Test cases
### Test case 1
#### Card intermdeiate output
![image](https://github.com/gopinathsjsu/individual-project-rakeshn1/blob/main/images/inter1.png)

#### Final output
![image](https://github.com/gopinathsjsu/individual-project-rakeshn1/blob/main/images/output1.png)

### Test case 2
#### Final output
![image](https://github.com/gopinathsjsu/individual-project-rakeshn1/blob/main/images/output2.png)

### Test case 3
#### Card intermdeiate output
![image](https://github.com/gopinathsjsu/individual-project-rakeshn1/blob/main/images/inter3.png)

#### Final output
![image](https://github.com/gopinathsjsu/individual-project-rakeshn1/blob/main/images/output3.png)
