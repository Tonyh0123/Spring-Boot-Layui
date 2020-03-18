package com.tangtang.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.tangtang.manager.dao")
public class ManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

<<<<<<< HEAD
	public void trr(){
		System.out.println("nishishabi");
	}
	//tyrtyrtyrtyrtyrtyrtyrtyr
=======
	public void ssss(){
		System.out.println("nishidashabi");
	}
	
	public void nishidashabi(){
		System.out.println("nishidashabi");
	}
	//I just try.
>>>>>>> mymymy
}
