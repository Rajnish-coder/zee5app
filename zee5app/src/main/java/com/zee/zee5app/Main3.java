package com.zee.zee5app;

import java.util.Scanner;

import com.zee.zee5app.dto.Movie;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Main3 {
	
	static {
		//System.out.println("hello from static");
	}
	
	// initialization block is not executed because no object created
	{
		//System.out.println("hello from initialization");
	}
	
	static int div(int a,int b)
	{
		Scanner scanner = new Scanner(System.in);
		int c=0;
		try {
			c = a/b;
			return c;
		} catch (ArithmeticException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			int count=1;
			while(count<=3 && b==0)
			{
				System.out.println("enter value of b");
				b = scanner.nextInt();
				count++;
			}
		}
		finally {
			System.out.println("some closure");
		}
		return c;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("hello from main");
		int a=0,b=0,c=0;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("enter value of a");
		a = scanner.nextInt();
		System.out.println("enter value of b");
		b = scanner.nextInt();
		int result = div(a,b);
		System.out.println(result);

	}

}
