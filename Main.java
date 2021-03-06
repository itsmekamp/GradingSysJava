package io.itsmekamp.gradingsys;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static DecimalFormat df = new DecimalFormat("##.00");
	
	public static double avg(List<Integer> gradeList) {
	    long sum = 0;
	    for (Integer grade : gradeList) {
	        sum += grade;
	    }
	    return gradeList.isEmpty()? 0: 1.0*sum/gradeList.size();
	}
	
	public static String letterScore(List<Integer> gradeList) {
		double aveg = avg(gradeList);
		if (aveg >= 90) {
			return "A"; 
		} else if (aveg >= 80 && aveg <= 89) {
			return "B";
		} else if (aveg >= 70 && aveg <= 79) {
			return "C";
		} else {
			return "F";
		}
	}
	
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static String ordinal(int num) {
		String suffix = "";
		String numS = Integer.toString(num);
		char[] numA = numS.toCharArray();
		if (num < 20) {
			if (num == 1) {
				suffix = "st";
			} else if (num == 2) {
				suffix = "nd";
			} else if (num == 3) {
				suffix = "rd";
			} else {
				suffix = "th";
			}
		} else {
			char tens = numA[numA.length-2];
			char unit = numA[numA.length-1];
			if (tens == '1') {
				suffix = "th";
			} else {
				if (unit == '1') {
					suffix = "st";
				} else if (unit == '2') {
					suffix = "nd";
				} else if (unit == '3') {
					suffix = "rd";
				} else {
					suffix = "th";
				}
			}
		}
		return (numS + suffix);
	}
	public static void main(String[] args) {
		List<Integer> grades = new ArrayList<Integer>();
		Scanner input = new Scanner(System.in);
		int count = 1;
		System.out.println("Welcome to GradingSys");
		boolean running = true;
		while (running) {
			System.out.println("Enter your " + ordinal(count)  + " grade.");
			String inp = input.next();
			switch(inp) {
			case "#view":
				System.out.println(grades);
			case "":
				System.out.println("Invalid Grade. ErrorCode: EmptyInput");
			case "#avg":
				System.out.println("Your average grade is " + df.format(avg(grades)));
				running = false;
				break;
			case "#letter":
				System.out.println("Your letter score is " + letterScore(grades));
				running = false;
				break;
			case "#x":
				running = false;
				break;
			default:
				if (isInteger(inp) == true) {
					int inpInt = Integer.parseInt(inp);
					if  (inpInt > 100) {
						System.out.println("Grade larger than 100.");
					} else if (inpInt <= 100) {
						grades.add(inpInt);
						count += 1;
					}
				} else {
					System.out.println("Invalid Grade. ErrorCode: NotInteger");
				}
			}
		}
		input.close();
	}

}
