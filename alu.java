/* Implementation of 1-bit ALU functions in Java */
/* Maria E Kloiber 11/4 */

public class alu{

    /* This method outlines the system of logic gates that output the sum and the carry-out in the ALU*/
    /* The input values of this function are all the input values that go into the 1-bit ALU and have values of either 0 or 1 */
    /* This returns a value from 0 to 3 to represent the values of both the sum and the carry-out combined*/
    public static int myalu(int F0, int F1, int INVA, int ENA, int ENB, int carryin, int a, int b){
	
	return (BLogic.bOr(BLogic.bAnd(BLogic.bAnd(BLogic.bAnd(ENB, b), BLogic.bXor(INVA, BLogic.bAnd(a, ENA))), BLogic.bAnd(BLogic.bNot(F0), BLogic.bNot(F1))), BLogic.bAnd(BLogic.bOr(BLogic.bXor(INVA, BLogic.bAnd(a, ENA)), BLogic.bAnd(ENB, b)), BLogic.bAnd(BLogic.bNot(F0), F1)), BLogic.bAnd(BLogic.bNot(BLogic.bAnd(b, ENB)), BLogic.bAnd(F0, BLogic.bNot(F1))), BLogic.bAnd(BLogic.bAnd(F0, F1), BLogic.bXor(carryin, BLogic.bXor(BLogic.bAnd(b, ENB), BLogic.bXor(BLogic.bAnd(a, ENA), INVA))))) <<1) + BLogic.bOr(BLogic.bAnd(BLogic.bAnd(F0, F1), BLogic.bXor(BLogic.bAnd(ENA, a), INVA), BLogic.bAnd(ENB, b)), BLogic.bAnd(BLogic.bAnd(F0, F1), carryin, BLogic.bXor(BLogic.bAnd(b, ENB), BLogic.bXor(BLogic.bAnd(a, ENA), INVA))));
    }

    /* This method prints out one instance of the ALU function, showing one possible combination of input values and their corresponding output  */
    /* The input values of this function are the same as the above method for the ALU */
    /* This returns both the set of input values and their output as computed by the ALU method written above */
    public static void print_call(int F0, int F1, int INVA, int ENA, int ENB, int carryin, int a, int b){
	System.out.print("alu("+F0+", "+F1+", "+INVA+", "+ENA+", "+ENB+", "+carryin+", "+a+", "+b+") --> ");
	System.out.println(myalu(F0, F1, INVA, ENA, ENB, carryin, a, b));
    }

    /* This prints a series of possible combinations of input values for the ALU method */
    public static void print_section(int F0, int F1, int INVA, int ENA, int ENB){
	System.out.println("F0="+F0+", F1="+F1+", INVA="+INVA+", ENA="+ENA+", ENB="+ENB);
	print_call(F0, F1, INVA, ENA, ENB, 0, 0, 0);
	print_call(F0, F1, INVA, ENA, ENB, 0, 0, 1);
	print_call(F0, F1, INVA, ENA, ENB, 0, 1, 0);
	print_call(F0, F1, INVA, ENA, ENB, 0, 1, 1);
	print_call(F0, F1, INVA, ENA, ENB, 1, 0, 0);
	print_call(F0, F1, INVA, ENA, ENB, 1, 0, 1);
	print_call(F0, F1, INVA, ENA, ENB, 1, 1, 0);
	print_call(F0, F1, INVA, ENA, ENB, 1, 1, 1);
    }

    /* In main, the series of for loops run through various possible combinations of input values  */
    /* The for loops utilize the print_section method to output all 256 possible combinations of input values for the ALU, and their corresponding outputs  */
    public static void main(String[] args){
	for(int F0 = 0; F0 < 2; F0++){
	    for(int F1 = 0; F1 < 2; F1++){
		for(int INVA = 0; INVA < 2; INVA ++){
		    for(int ENA = 0; ENA < 2; ENA++){
			for(int ENB = 0; ENB < 2; ENB++){
			    print_section(F0, F1, INVA, ENA, ENB);
			}
		    }
		}
	    }
	}
    }
}
