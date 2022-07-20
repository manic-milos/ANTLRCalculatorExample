cd antlrcalc
call antlr4 Calc.g4 -visitor -no-listener -package antlrcalc
cd ..
javac App.java
javac TRunner.java