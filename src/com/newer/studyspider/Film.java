package com.newer.studyspider;

public class Film {
	String id, poster, title, info, watched, num, zhaiyao;
	String dy;
	String zy;
	String lx;
	String time;

	public Film(String id, String poster, String title, String info, String watched, String num, String zhaiyao,
			String dy, String zy, String lx, String time) {
		super();
		this.id = id;
		this.poster = poster;
		this.title = title;
		this.info = info;
		this.watched = watched;
		this.num = num;
		this.zhaiyao = zhaiyao;
		this.dy=dy;
		this.zy=zy;
		this.lx=lx;
		this.time=time;
	}

	@Override
	public String toString() {
		return "排名：" + id  +
				"\n电影名：" + title + 
				"\n海报：" + poster+ 
				"\n导演：" + dy + "\n主演：" + zy + "\n类型：" + lx + "\n片长："
				+ time + "\n评分： " + watched+ "\n评价次数 ：" + num + "\n摘要 ：" + zhaiyao +  "\n********************************************";
	}

	

	// 字段

}
