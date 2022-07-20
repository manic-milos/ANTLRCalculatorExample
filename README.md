# Basic calculator in AntLR

## 1) Build instructions (on win)
**build.bat** batch file builds antlr grammar file in **./antlrcalc folder**. Then it builds the **App** and **TRunner** classes. 

**note*: **antlr4** command in **build.bat** is a batch file made on windows per instructions in https://tomassetti.me/antlr-mega-tutorial/ 

## 2) Running the calculator (on win)
- App class contains main method, that has a loop, in which the user inputs an expression, and gets the result of the expression on standard output. 
- Since it is an infinite loop, Ctrl+c is the fastest way to exit. 
- If there is an error, the program should write the exception message on stdout, and then continue with another input. 
- If there is any problem with the input, it should not return a result.

## 3) Testing 
- TRunner class runs the companion automated tests.
