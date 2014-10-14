package calculator;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
	public static void main(String [] args) {
		  Scanner keyboard = new Scanner(System.in);
		  System.out.println("Enter the expression seperated by spaces");
		  String expression = keyboard.nextLine();
		  Stack<String> operators = new Stack<String>();
		  String output = "";
		  //String expression1 = "( 3 + 4 ) * 5";
	      String []exps = expression.split(" ");
	      for(int i = 0; i < exps.length; ++i){
	    	  if (exps[i].matches("[0-9]+"))
	    		  output += exps[i] + " ";
	    	  else if (exps[i].matches("[\\+\\-\\*/%\\^]")/*|| exps[i].equals("sqrt")*/){
	    		  char o1 = exps[i].charAt(0);
	    		  if(!(operators.empty())){
	    			  //char o2 = operators.peek().charAt(0);
	    			  while(!operators.empty() && opPrecedence(operators.peek().charAt(0)) >= opPrecedence(o1) && !(operators.peek().equals("("))){
	    				  output += operators.pop() + " ";
	    			  }
	    		  }
	    		  operators.push(exps[i]);
	    	  }
	    	  else if(exps[i].equals("(")){
	    		  operators.push(exps[i]);
	    	  }
	    	  else if(exps[i].equals(")")){
	    		  while (!operators.empty() && !(operators.peek().equals("("))){
	    		  //while (!operators.empty())
	    			  output += operators.pop() + " ";
	    		  }
	    	  }	      

	   }  
	      while (!operators.empty() ){
	    	  if((opPrecedence(operators.peek().charAt(0)) == 3))
	    		  operators.pop();
	    	  else
	    		  output += operators.pop() + " ";
	      }
	      System.out.println("Expression in RPN: " + output);
	      Stack<Double> values = new Stack<Double>();
	      String []exps2 = output.split(" ");
	      for(String s: exps2){
	    	  if (s.matches("[0-9]+")){
	    		  double d = Double.parseDouble(s);
	    		  values.push(d);
	    	  }
	    	  else if(s.matches("[\\+\\-\\*/%\\^]")){
	    		  double first = values.pop();
	    		  double second = values.pop();
	    		  double answer = 0;
	    		  	if(s.equals("+"))
	    		  		answer = second + first;
	    		  	else if(s.equals("-"))
	    		  		answer = second - first;
	    		  	else if(s.equals("*"))
	    		  		answer = second*first;
	    		  	else if(s.equals("/"))
	    		  		answer = second/first;
	    		  	else if(s.equals("^"))
	    		  		answer = Math.pow(second, first);
	    		  	//case 'sqrt'
	    		  		//answer = Math.sqrt
	    		  
	    		  values.push(answer);
	    	  }	    	  	
	      }
	      if(values.size() == 1)
    		  System.out.println("The answer is " + values.pop());
	      
	}
	public static int opPrecedence(char op){
		switch (op) {
    		case '+':
    		case '-':
    			return 0;
    		case '*':
    		case '/':
    			return 1;
    		case '^':
    			return 2;
    		case '(':
    		case ')':
    			return 3;
    		default:
    			throw new IllegalArgumentException("Operator unknown: " + op);
		}
	}
}

