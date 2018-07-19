package com.newer.studyspider;

public class App {
	public static void main(String[] args) {
		//新建爬虫
		Spider spider = new Spider("https://movie.douban.com/top250"); 
		//启动爬虫
		try {
			spider.start();
		} catch (NullException e) {
			e.printStackTrace();
			
		}
	}
}
