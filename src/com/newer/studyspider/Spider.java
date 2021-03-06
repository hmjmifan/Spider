package com.newer.studyspider;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * HTTP/HTML 爬虫
 */
public class Spider implements Runnable {

	private String url;
	private List<Film> films;
	
	
	public Spider(String url,List<Film> films) {
		this.url = url;
		this.films=films;

	}

	Document doc;

	@Override
	public void run() {
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
			doc = Jsoup.connect(url).timeout(10000).get();

			

				// grid_view 类别中的 item，
				Elements items = doc.select(".grid_view .item");
				// 遍历

				for (int i = 0; i < items.size(); i++) {
					Film film = new Film();
					// 取到一部影片
					Element item = items.get(i);
					id = item.select("em").first().text();				// 排名
					poster = item.select("img").first().attr("src");	// 海报
					title = item.select(".info .title").first().text();// 影片名
					
					
					// 获取影片地址进入获取其内部信息
					String path = item.select(".hd a").first().attr("href");
					try {
						Document docin = Jsoup.connect(path).get();
						Elements ddc = docin.select("#info");

						dy = ddc.select(".attrs a[rel$=v:directedBy]").text();
						zy = ddc.select(" .actor").text();
						lx = ddc.select("  span[property$=v:genre]").text();
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
					film.setId(id);
					film.setPoster(poster);
					film.setTitle(title);
					film.setInfo(info);
					film.setNum(num);
					film.setZhaiyao(zhaiyao);
					films.add(film);
					
					
					System.out.println(Thread.currentThread().getName() + " 抓取 " + id);
//					System.out.println(new Film(id, poster, title, info, watched, num, zhaiyao, dy, zy, lx, time).toString());
				}

		} catch (IOException e) {
			
		}
	}

}
