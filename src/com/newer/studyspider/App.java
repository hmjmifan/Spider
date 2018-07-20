package com.newer.studyspider;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;


public class App {
	public static void main(String[] args) {
		
		List<Film> films = Collections.synchronizedList(new LinkedList<>());
		//线程池
		ExecutorService pool = Executors.newCachedThreadPool();
		
		for(int i=0;i<10;i++) {
			String url = String.format( "https://movie.douban.com/top250?start=%d", 25*i);
			pool.execute(new Spider(url,films));
		}
		pool.shutdown();
		
		
		while(true) {
			if(pool.isTerminated()) {
				WriteData(films);
				break;
			}else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}

	private static void WriteData(List<Film> films) {
		System.out.println(films.size());
		Collections.sort(films);
		for(Film f:films) {
			System.out.println(f);
		}
		String gson = new Gson().toJson(films);
		
		try(FileWriter write = new FileWriter("D:/250.json")){
			write.write(gson);
			System.out.println("写over");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ExecutorService pool2 = Executors.newFixedThreadPool(4);
		for(Film f2:films) {
			pool2.execute(new imgdownload(f2));
		}
		pool2.shutdown();
		
	}
}
