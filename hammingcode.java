/* Converting binary input to its appropriate hamming code in Java language.*/
/* Includes extra feature to analyze & fix errors in hamming code. M. Kloiber */
/* October 13th 2016  */

import java.util.Scanner;
import java.util.Arrays;

class Hamming {
    public String toString(int n){
	return "" + n;
    }
    public static void main(String args[]){
	System.out.println("Enter the number of bits for the Hamming code");
	Scanner scan = new Scanner(System.in);
	int a = scan.nextInt();
	int[] newArray = new int[a];

	/* This function reads in a string and converts it to integers  */
	System.out.println("Enter a list of 0's and 1's below: ");
	Scanner s = new Scanner(System.in);
	String x = s.nextLine();
	for(int i = 0; i < newArray.length; i++){
	    newArray[i] = Integer.parseInt(x.substring(i,i+1));
	}

	System.out.println("You have inputted ");
	for(int i = 0; i < newArray.length; i++){
	    System.out.print(newArray[i]);
	}
	System.out.println();

	int[] hammingArray = new int[21];
	hammingArray[0] = 2;
	/*A 2 is used here so the later computations for the parity bits can 
	  continue normally without attempting to add an undefined value. */
	/*A 2 was chosen specifically because it wouldn't change the
        evennness or oddness of a sum in the computation of the parity bits.*/ 
	hammingArray[1] = 2;
	hammingArray[2] = newArray[0];
	hammingArray[3] = 2;
	hammingArray[4] = newArray[1];
	hammingArray[5] = newArray[2];
	hammingArray[6] = newArray[3];
	hammingArray[7] = 2;
	hammingArray[8] = newArray[4];
	hammingArray[9] = newArray[5];
	hammingArray[10] = newArray[6];
	hammingArray[11] = newArray[7];
	hammingArray[12] = newArray[8];
	hammingArray[13] = newArray[9];
	hammingArray[14] = newArray[10];
	hammingArray[15] = 2;
	hammingArray[16] = newArray[11];
	hammingArray[17] = newArray[12];
	hammingArray[18] = newArray[13];
	hammingArray[19] = newArray[14];
	hammingArray[20] = newArray[15];

	/*These next few computations generate the parity bits for the code. */
	int sum1 = 0;
	for(int i = 0; i < hammingArray.length; i++){
	    if(i==0 || i % 2 == 0){
		sum1 += hammingArray[i];
	    }
	}
	int p1 = 0;
	if(sum1 % 2 == 0){
	    p1 = 0;
	}
	else{
	    p1 = 1;
	}

	int sum2 = 0;
	for(int i = 0; i < hammingArray.length; i++){
	    if(i==1 || i % 4 == 1){
		for(int j = i; j < i + 2; j++){
		    sum2 += hammingArray[j];
		}
	    }
	}
	int p2;
	if(sum2 % 2 == 0){
	    p2 = 0;
	}
	else {
	    p2 = 1;
	}

	int sum4 = 0;
	for(int i = 0; i < hammingArray.length; i++){
	    if(i == 3 || i == 11){
		for(int j = i; j < i+4; j++){
		    sum4 += hammingArray[j];
		}
	    }
	    if(i == 19){
		for(int k = i; k < i+2; k++){
		    sum4 += hammingArray[k];
		}
	    }
	}
	int p4;
	if(sum4 % 2 == 0){
	    p4 = 0;
	}
	else {
	    p4 = 1;
	}
	int sum8 = 0;
	for(int i = 0; i < hammingArray.length; i++){
	    if(i == 7){
		for(int j = i; j < i+8; j++){
		    sum8 += hammingArray[j];
		}
	    }
	}
	int p8;
	if(sum8 % 2 == 0){
	    p8 = 0;
	}
	else {
	    p8 = 1;
	}
	int sum16 = 0;
	for(int i = 0; i < hammingArray.length; i++){
	    if(i == 15){
		for(int j = i; j < i+6; j++){
		    sum16 += hammingArray[j];
		}
	    }
	}
	int p16;
	if(sum16 % 2 == 0){
	    p16 = 0;
	}
	else {
	    p16 = 1;
	}
	hammingArray[0] = p1;
	hammingArray[1] = p2;
	hammingArray[3] = p4;
	hammingArray[7] = p8;
	hammingArray[15] = p16;

	/* This output is just for the purposes of checking my work to make sure this program correctly translates the binary into its hamming code.  */
	System.out.println("The Hamming code in binary is ");
	for(int i = 0; i < hammingArray.length; i++){
	    System.out.print(hammingArray[i]);
	}
	System.out.println();

	/* This converts the array of integers to a single int variable */
	int sums = 0;
	for(int i = 0; i < hammingArray.length; i++){
	    if(hammingArray[i] == 1){
		sums += Math.pow(2, (20-i));
	    }
	}
	System.out.println("The code in decimal is " + sums);

	/* These lines of code implement the extra feature. */
	/* The purpose of the extra feature is to examine the hamming code for
	   errors and to fix any errors if they exist. */
	System.out.println("Enter hamming code you want to check for errors");
	int[] errorArray = new int[21];
	Scanner m = new Scanner(System.in);
	String z = s.nextLine();
	for(int i = 0; i < errorArray.length; i++){
	    errorArray[i] = Integer.parseInt(z.substring(i,i+1));
	}

	sum1 = 0;
	for(int i = 0; i < errorArray.length; i++){
	    if(i==0 || i % 2 == 0){
		sum1 += errorArray[i];
	    }
	}
	p1 = 0;
	if(sum1 % 2 == 0){
	    p1 = 0;
	}
	else{
	    p1 = 1;
	}
	sum2 = 0;
	for(int i = 0; i < errorArray.length; i++){
	    if(i==1 || i % 4 == 1){
		for(int j = i; j < i + 2; j++){
		    sum2 += errorArray[j];
		}
	    }
	}
	p2 = 0;
	if(sum2 % 2 == 0){
	    p2 = 0;
	}
	else {
	    p2 = 1;
	}
	sum4 = 0;
	for(int i = 0; i < errorArray.length; i++){
	    if(i == 3 || i == 11 || i==19){
		if(i+4 < errorArray.length){
		    for(int j = i; j < i+4; j++){
			sum4 += errorArray[j];
		    }
		}
		else{
		    for(int j = i; j < errorArray.length; j++){
			sum4 += errorArray[j];
		    }
		}
	    }
	}
	p4 = 0;
	if(sum4 % 2 == 0){
	    p4 = 0;
	}
	else {
	    p4 = 1;
	}
	sum8 = 0;
	for(int i = 0; i < errorArray.length; i++){
	    if(i == 7){
		for(int j = i; j < i+8; j++){
		    sum8 += errorArray[j];
		}
	    }
	}
	p8 = 0;
	if(sum8 % 2 == 0){
	    p8 = 0;
	}
	else {
	    p8 = 1;
	}
	sum16 = 0;
	for(int i = 0; i < errorArray.length; i++){
	    if(i == 15){
		for(int j = i; j < errorArray.length; j++){
		    sum16 += errorArray[j];
		}
	    }
	}
	p16 = 0;
	if(sum16 % 2 == 0){
	    p16 = 0;
	}
	else {
	    p16 = 1;
	}

	/* This part checks to see if any of the parity bits are incorrect. */
	int error = 0;
	if(p1 == 1){
	    error += 1;
	}
	if(p2 == 1){
	    error += 2;
	}
	if(p4 == 1){
	    error += 4;
	}
	if(p8 == 1){
	    error += 8;
	}
	if(p16 == 1){
	    error += 16;
	}
	if(p1 == 1 || p2 == 1 || p4 == 1 || p8 == 1 || p16 == 1){
	    System.out.print("The bit containing the error ");
	    System.out.print("(assuming one-bit error)");
	    System.out.println(" is bit # " + error);

	    if(errorArray[error-1] == 0){
		errorArray[error-1] = 1;
	    }
	    else if(errorArray[error-1] == 1){
		errorArray[error-1] = 0;
	    }
	    
	    System.out.print("The correct hamming code is " );
	    for(int i = 0; i < errorArray.length; i++){
		System.out.print(errorArray[i]);
	    }
	    System.out.println();
	    /* This converts the array of integers to a single int variable */
	    sums = 0;
	    for(int i = 0; i < errorArray.length; i++){
		if(errorArray[i] == 1){
		    sums += Math.pow(2, (20-i));
		}
	    }
	    System.out.println("The correct code in decimal is " + sums);
	}
	else if(p1==0 && p2==0 && p4==0 && p8==0 && p16==0){
	    System.out.println("The code is correct - there are no errors");
	}
    }
}
