import java.util.Calendar;

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
	private int getDayOfWeek(String date) 
	{
		String dateParams [] = date.split("-");
		int year = Integer.parseInt(dateParams[0]);
		int month = Integer.parseInt(dateParams[1]);
		int day = Integer.parseInt(dateParams[2]);
		
		Calendar cal = Calendar.getInstance();  
        cal.set(year, month - 1, day, 0, 0, 0);
        int weeks[] = {7, 1, 2, 3, 4, 5, 6};
        
        return weeks[cal.get(Calendar.DAY_OF_WEEK) - 1];
	}
	
	/**
	 * Calculate the charge for the given record
	 * @param record
	 * @return result of charge
	 */
	private String makeCharge(String record)
	{
		// get date, time and the number of people
		String params [] = record.split(" ");
		
		// get day of week
		int dayOfWeek = getDayOfWeek(params[0]);
		
		// get from time and end time
		int fromHour = Integer.parseInt(params[1].substring(0, 2));
		int fromMin = Integer.parseInt(params[1].substring(3, 5));
		int endHour = Integer.parseInt(params[1].substring(6, 8));
		int endMin = Integer.parseInt(params[1].substring(9));
		
		// input record cannot be illegal
		if (fromHour < 9 || endHour < 9 || fromHour > 22 || endHour > 22 || fromMin > 60 || endMin > 60 || fromMin < 0 || endMin < 0)
			return "Illegal Input";
		
		// get the number of people
		int numOfPeople = Integer.parseInt(params[2]);

		// compute charge
		int t = numOfPeople / 6;
		int x = numOfPeople % 6;
		String result = params[0] + " " + params[1] + " ";
		int feePerRound = calcFee(dayOfWeek, fromHour, fromMin, endHour, endMin);
		
		// determine how many rooms to be booked
		if (t == 0 && x < 4)
			return result + "+0 -0 0";
		else if (t == 0 && x >= 4)
			feePerRound *= 1;
		else if (t == 1)
			feePerRound *= 2;
		else if (((t == 2) || (t == 3)) && (x >= 4))
			feePerRound *= (t + 1);
		else if (((t == 2) || (t == 3)) && (x < 4))
			feePerRound *= t;
		else if (t > 3)
			feePerRound *= t;
		
		// update the total income and payment and compute profit
		income += numOfPeople * 30;
		payment += feePerRound;
		int profit = (numOfPeople * 30) - feePerRound;

		// build the summary for the given record
		result = result + " +" + (numOfPeople * 30) + " -" + feePerRound;
		if (profit == 0)
			result = result + " 0";
		else if (profit > 0)
			result = result + " +" + profit;
		else
			result = result + " " + profit;
		
		return result;
	}
	
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
	private int calcFee(int dayOfWeek, int fromHour, int fromMin, int endHour, int endMin) 
	{
		final int regularDay[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 30, 30, 50, 50, 50, 50, 50, 50, 80, 80, 60, 60};
		final int weekend[] =    {0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 40, 40, 50, 50, 50, 50, 50, 50, 60, 60, 60, 60};
		
		// compute how much we have to pay for
		int fee = 0;
		for (int i = fromHour; i < endHour; i++) 
			fee += (dayOfWeek >= 6) ? weekend[i] : regularDay[i];
			
		return fee;
	}

	/**
	 * Get summary for the input record.
	 * @param input
	 * @return total income, payment and profit in String format.
	 */
	public String GenerateSummary(String input)
	{
		// split by '\n' to separate each record
		String charges[] = input.split("\n");

		// charge for every record
		String result = "[Summary]\n";
		for (int i = 0; i < charges.length; i++) 
			result += makeCharge(charges[i]) + "\n";
		
		// build summary
		result += "\nTotal Income:" + income + "\n";
		result += "Total Payment:" + payment + "\n";
		result += "Profit:" + (income - payment);
		
		return result;
	}
	
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
}