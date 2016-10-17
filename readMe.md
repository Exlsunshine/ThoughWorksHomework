## Thought Works Homework.

|Name|Guang.Yang|
| -----:|------:|
| Education |Beijing University of Technology, Master|
|Email|exlsunshine@outlook.com|
|Mobile| 13671045367 |
|Apply|Software Development Engineer|


## 0. How to run the program
- Before running the following command, <font color=red>**PLEASE MAKE SURE**</font> that the Java environment is well installed.
	1. `cd xxx`, where `xxx` is the directory which contains the source code `FeeManager.java`.
	2. Compile the source code by running this command: `javac FeeManager.java`
	3. Run the program by this command: `java FeeManager `
	4. The program itself contains a unit test, after running the program, you will see the output coresponding to the input.


* **Unit test Input**: 
	1. Input can be composed by multiple lines;
	2. Each line should be composed like YYYY-MM-DD HH:mm~HH:mm [0-9];
	3. There should be a '\n' between each line.
	4. **Sample input** should look like this:

#
	2016-06-02 20:00~22:00 7  
	2016-06-03 09:00~12:00 14  
	2016-06-04 14:00~17:00 22  
	2016-06-05 19:00~22:00 3  
	2016-06-06 12:00~15:00 15  
	2016-06-07 15:00~17:00 12  
	2016-06-08 10:00~13:00 19  
	2016-06-09 16:00~18:00 16  
	2016-06-10 20:00~22:00 5  
	2016-06-11 13:00~15:00 11

<br>

* **Unit test Output**: 
	1. Output is composed by one or more lines.
	2. Each line is composed in the following format: YYYY-MM-DD HH:mm~HH:mm +[0-9]  -[0-9] [0-9]
	3. Finally, the total income, outcome and net income will be given at the end of the output.
	4. **Sample output** should look like this:
	
#
	2016-06-02 20:00~22:00 +210 -240 -30  
	2016-06-03 09:00~12:00 +420 -180 +240  
	2016-06-04 14:00~17:00 +660 -600 +60    
	2016-06-05 19:00~22:00 +0 -0 0  
	2016-06-06 12:00~15:00 +450 -300 +150  
	2016-06-07 15:00~17:00 +360 -200 +160  
	2016-06-08 10:00~13:00 +570 -330 +240  
	2016-06-09 16:00~18:00 +480 -300 +180  
	2016-06-10 20:00~22:00 +150 -120 +30  
	2016-06-11 13:00~15:00 +330 -200 +130  
	
	Total Income: 3630  
	Total Payment: 2470  
	Profit: 1160  

## 1. API Design
| Function name | Description   | Input | Output  |
|----------- |:-------------|-----:|--:|
| getDayOfWeek| get the day of the week | String date | int |
|makeCharge      | Calculate the charge for the given record      |  String record |String|
| calcFee | Calculate the fee from fromHour to endHour.      |    int dayOfWeek, <br>int fromHour, <br>int fromMin, <br>int endHour, int endMin | int |
|GenerateSummary|Get summary for the input record|String input| String |

<br>
<br>
<br>
<br>
<br>
## 2. Implementation of the design


```
/**
 * Home works for Thought works.<br>
 * 2016.10.14
 * @author Guang.Yang
 */
public class FeeManager 
{
	/**
	 * Total income
	 */
	private int income = 0;
	
	/**
	 * Total payment
	 */
	private int payment = 0;

	/**
	 * get the day of the week.
	 * @param date
	 * @return day of the week
	 */
	private int getDayOfWeek(String date);
	
	/**
	 * Calculate the charge for the given record
	 * @param record
	 * @return result of charge
	 */
	private String makeCharge(String record);
	
	/**
	 * 	Calculate the fee from fromHour to endHour.
	 * @param dayOfWeek
	 * @param fromHour
	 * @param fromMin
	 * @param endHour
	 * @param endMin
	 * @param numOfPeople
	 * @return
	 */
	private int calcFee(int dayOfWeek, int fromHour, int fromMin, int endHour, int endMin) ;

	/**
	 * Get summary for the input record.
	 * @param input
	 * @return total income, payment and profit in String format.
	 */
	public String GenerateSummary(String input);
}
```

## 3. Unit test code
```
public static void main(String[] args)
{
	FeeManager fManager = new FeeManager();
	String input = "2016-06-02 20:00~22:00 7"
			+ "\n2016-06-03 09:00~12:00 14"
			+ "\n2016-06-04 14:00~17:00 22"
			+ "\n2016-06-05 19:00~22:00 3"
			+ "\n2016-06-06 12:00~15:00 15"
			+ "\n2016-06-07 15:00~17:00 12"
			+ "\n2016-06-08 10:00~13:00 19"
			+ "\n2016-06-09 16:00~18:00 16"
			+ "\n2016-06-10 20:00~22:00 5"
			+ "\n2016-06-11 13:00~15:00 11";
	
	System.out.println(fManager.GenerateSummary(input));
}
```

## 4. Source code
Please check it at [here](./FeeManager.java).<br>
PS: <font color=red>**PLEASE MAKE SUR**E</font> that the `FeeManager.java` is located in the same directory with this `readMe.pdf` file, otherwise the above link will not work.