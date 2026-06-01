import java.util.Scanner;
import java.io.File;

public class LabSix{
	public static void main(String[] args){
		File studentGrades = new File("studentGrades.txt");

		String [] studentName = new String [amountStudents(studentGrades)];
		int [] studentGrade = new int [amountStudents(studentGrades)];

		loadData("studentGrades.txt",studentName,studentGrade);

		System.out.println("--------Student-Names--------");
		for(int i=0;i<studentName.length;i++){// Print the entire list of students
			System.out.println("Student "+i+": "+studentName[i]);
		}
		System.out.println("-----------------------------");
		System.out.println();

		System.out.println("The Highest Grade is: "+findHighest(studentGrade,studentName));// Print the student with the Highest Grade
		System.out.println();

		int recievedGrade = 80;

		System.out.println("Students Who got above an "+recievedGrade+": ");// Print the students with a grade above 80
		String [] aboveStudents= findAbove(recievedGrade,studentGrade,studentName);
		for(int i=0;i<aboveStudents.length;i++){
			System.out.println(aboveStudents[i]);
		}
		System.out.println();

		System.out.println("Student Who got below an "+recievedGrade+": ");// Print the students with a grade below 80
		String [] belowStudents= findBelow(recievedGrade,studentGrade,studentName);
		for(int i=0;i<belowStudents.length;i++){
			System.out.println(belowStudents[i]);
		}
		System.out.println();

		System.out.println("Average Grade: "+calcAverage(studentGrade));// Print the average grade of all the students

	}

	public static void loadData(String fileName,String [] studentName,int [] studentGrade){// loadData - receives the file name and 2 empty arrays and populates the student and GPA arrays
		try {
			 Scanner fileReader = new Scanner(new File(fileName));
			for (int i = 0; i < amountStudents(new File(fileName)); i++) {
				String line = fileReader.nextLine();
				String loadGrade= "";
				String loadName = "";
				boolean isIndent=false;
				for(int j=0; j<line.length();j++){
					char iChar=line.charAt(j);
					if(iChar==('\t')){
						isIndent=true;
					}
					if(!isIndent){
						if(Character.isLetter(iChar)){
							loadName += iChar;
						}
					}
					if(isIndent){
						if(Character.isDigit(iChar)){
							loadGrade +=iChar;
						}
					}
				}
				studentName[i]=loadName;
				studentGrade[i]=Integer.parseInt(loadGrade);;
			}
		} 
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public static String findHighest(int[]gradeArr,String[]studentName){// findHighest - Finds the highest grade amongst the students and returns their name
		int highestGrade = Integer.MIN_VALUE;
		int index = 0;
		for(int i = 0; i < gradeArr.length;i++){
			if(highestGrade < gradeArr[i]){
				highestGrade = gradeArr[i];
				index=i;
			}
		}
		return studentName[index];
	}

	public static String[] findAbove(int recievedGrade,int[]studentGrade, String[]studentName){/* findAbove - Finds all of the students who have above the received grade and returns an array of their names*/
		int amountStudents =0;
		int index=0;
		for(int i=0;i<studentGrade.length;i++){
			if(studentGrade[i]>recievedGrade){
				amountStudents++;
			}
		}
		String[] aboveStudents = new String[amountStudents];
		for(int i=0;i<studentGrade.length;i++){
			if(studentGrade[i]>recievedGrade){
				aboveStudents[index]=studentName[i];
				index++;
			}
		}
		return	aboveStudents;
	}

	public static String[] findBelow(int recievedGrade,int[]studentGrade, String[]studentName){// findBelow - Same as findAbove but for below the received grade
		int amountStudents =0;
		int index=0;
			for(int i=0;i<studentGrade.length;i++){
				if(studentGrade[i]<recievedGrade){
					amountStudents++;
				}
			}
			String[] belowStudents = new String[amountStudents];
			for(int i=0;i<studentGrade.length;i++){
				if(studentGrade[i]<recievedGrade){
					belowStudents[index]=studentName[i];
					index++;
				}
			}
			return	belowStudents;
	}


	public static double calcAverage(int[]gradeArr){// calcAverage - Calculates and returns the average grade of the received array
		double averageGrade = 0.0;
		for(int i = 0; i < gradeArr.length;i++){
			averageGrade+=gradeArr[i];
			}
			return (averageGrade/gradeArr.length);
	}

	public static int amountStudents(File fileName){// finds how many students there are by reading how many lines are in the file
		int amountLines = 0;
		try{
			Scanner fileReader = new Scanner(fileName);
			while(fileReader.hasNextLine()){
				amountLines++;
				fileReader.nextLine();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return amountLines;
	}

}