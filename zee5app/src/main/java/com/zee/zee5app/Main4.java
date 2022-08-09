package com.zee.zee5app;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import com.zee.zee5app.dto.User;

public class Main4 {
	
	public static void main(String[] args) {

	List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,8,1,2);
	
	
	// forEach method using lambda expression
	
	//list.forEach(e->System.out.println(e));
	
	Collections.sort(list);
	
	//list.forEach(e->System.out.print(e));
	
	HashSet hashSet = new HashSet();
	
	User user1 = new User("rajnish","shonkhia","@gmail.com",LocalDate.now(),LocalDate.now(),true);
	User user2 = new User("rajnish","shonkhia","@gmail.com",LocalDate.now(),LocalDate.now(),true);
	User user3 = new User("rajnish","shonkhia","@gmail.com",LocalDate.now(),LocalDate.now(),true);
	
	hashSet.add(user1);
	hashSet.add(user2);
	hashSet.add(user3);
	
	System.out.println(hashSet);
	
	TreeSet<User> set = new TreeSet<>((e1,e2)->e2.getUserId().compareTo(e1.getUserId()));
	
	set.add(user1);
	set.add(user2);
	set.add(user3);
	
	set.forEach(e->System.out.println(e));
	
	}
}
