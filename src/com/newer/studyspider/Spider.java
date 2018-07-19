package com.newer.studyspider;

import java.io.File;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * HTTP/HTML 爬虫
 */
public class Spider {

	String url;

	public Spider(String url) {
		this.url = url;

	}

	Document doc;

	public void start() throws NullException {
		
		String id;
		String poster;
		String title;
		String info;
		String watched;
		String num;
		String zhaiyao;
		
		String dy = null;
		String zy = null;
		String lx = null;
		String time = null;
		try {
			// 发送HTTP GET请求 ，获得文档
			doc= Jsoup.connect(url).timeout(10000).get();
			

			Elements page = doc.select(".paginator  a");

			 for (int j = 1; j <= page.size(); j++) {

			// grid_view 类别中的 item，
			Elements items = doc.select(".grid_view .item");
			// 遍历

			for (int i = 0; i < items.size(); i++) {
				// 取到一部影片
				Element item = items.get(i);
				// 排名
				// 在某一部内部找
				// 得到某部影片中的第一个<em>标签中的文本
				id = item.select("em").first().text();
				// 海报
				// 得到某部影片中第一个<img>标签 中的属性：src
				poster = item.select("img").first().attr("src");

				// 影片名
				title = item.select(".info .title").first().text();

				// 获取影片地址进入获取其内部信息
				 String path = item.select(".hd a").first().attr("href");
				 try {
				 Document docin = Jsoup.connect(path).get();
				 Elements ddc = docin.select("#info");
				
				 dy = ddc.select(".attrs a[rel$=v:directedBy]").text();
				 
				 zy = ddc.select(" .actor").text();
				 
				 lx=ddc.select("  span[property$=v:genre]").text();
				 
				 time = ddc.select(" span[property$=v:runtime]").text();
				 } catch (Exception e) {
				 e.printStackTrace();
				 }

				//

				info = item.select("p").first().text();
				// 评分
				watched = item.select(".info .bd .star .rating_num").first().text();
				// 评价次数
				num = item.select(".info .bd .star span").get(3).text();
				char[] num1 = num.toCharArray();
				num = new String(num1, 0, num1.length - 3);

				// 摘要
				try {
					zhaiyao = item.select(".info .bd  .quote .inq").first().text();
				} catch (Exception e) {
					zhaiyao = null;
				}
				
				Film film = new Film(id,poster,title,info,watched,num,zhaiyao,dy,zy,lx,time) ;
				System.out.println(film.toString());
			}
			 if (j == 10) {
			 System.out.println("over");
			 break;
			 }
			 String in = doc.select(".paginator .next a").first().attr("href");
			 String net = url + in;
			 doc = Jsoup.connect(net).get();
			
			 }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
