**Design approach** <br> <br>
glob is I <br>
prok is V <br>
pish is X <br>
tegj is L <br>
glob glob Silver is 34 <br>
Credits glob prok Gold is 57800 <br>
Credits pish pish Iron is 3910 <br>
Credits how much is pish tegj glob glob ? <br>
how many Credits is glob prok Silver? <br>
how many Credits is glob prok Gold ? <br>
how many Credits is glob prok Iron ? <br>
how much wood could a woodchuck chuck if a woodchuck could chuck wood? <br>

After analyzing the input carefully, I divided them to 4 categories <br>
Case 1 - Input lines which define words equivalent to Roman symbols <br>
Case 2 - Input lines which define units <br>
Case 3 - Questions with known Roman words and units <br>
Case 4 - Questions with unknown strings <br>

I used word "is" for splitting input line. <br>

In case 1 - I stored words equivalent to Roman symbols and respective Roman symbols in a Map. <br>
In case 2 - I determined the unit value based and Roman words and value given. Stored them in a Map. <br>
In case 3 - I used results of case 1 and case 2 to determine the output value. <br>
In case 4 - I returned default output. <br>

**How to run** <br> <br>
Once you check-out the project, you can use either eclipse or IntelliJ to run it. <br><br>
In case of no IDE, use following commands from command line <br> 
1. From command prompt to go location of InterGalacticTransaction.java file <br>
2. run javac InterGalacticTransaction.java <br>
3. run java InterGalacticTransaction
