package com.capitalnumbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AssigmentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Integer input = 15;
		
		// if this is divisible by 3
		if(input % 3 == 0 && input % 5 == 0) {
			System.out.println("fizz buzz");
		}
		else if( input % 3 == 0) {
			System.out.println("fizz");
		} else if(input % 5 == 0) {
			System.out.println("buzz");
		} else {
			System.out.println(input);
		}
		
		swap(4,5);
	}
	
	public static void swap(Integer a, Integer b) {
		//System.out.println("Initial values: a: " + a + " b: +" + b);
		
		//System.out.println(reverse("Hello"));
		createList();
	}
	
	public static String reverse(String myStr) {
		String reverseString = "";
		if(myStr.length() <= 1)
			return myStr;
		
		for(int i=myStr.length()-1; i >= 0 ; i--) {
			reverseString = reverseString + myStr.charAt(i);
		}
		
		return reverseString;
	}
	
	public static void createList() {
		int[] firstList = {+4,5,-4,5,10,0,10,0,0,0};
		Set<Integer> uniqueList = new HashSet<Integer>();
		List<Integer> lastList = new ArrayList<Integer>();
		for(int i=0; i < firstList.length; i ++) {			
			uniqueList.add(firstList[i]);
			lastList.add(firstList[i]);
		}
		
		System.out.print(uniqueList);
		
		uniqueList.forEach((value) -> lastList.add(value));		
		System.out.print(lastList);
		
	}

}
