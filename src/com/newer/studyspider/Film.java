package com.newer.studyspider;

public class Film implements Comparable<Film>{
	String id, poster, title, info, watched, num, zhaiyao;
	String dy;
	String zy;
	String lx;
	String time;
	
	public Film() {
		
	}
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getZhaiyao() {
		return zhaiyao;
	}

	public void setZhaiyao(String zhaiyao) {
		this.zhaiyao = zhaiyao;
	}

	public String getDy() {
		return dy;
	}

	public void setDy(String dy) {
		this.dy = dy;
	}

	@Override
	public String toString() {
		return "排名：" + id  +
				"\n电影名：" + title + 
				"\n海报：" + poster+ 
				"\n导演：" + dy + "\n主演：" + zy + "\n类型：" + lx + "\n片长："
				+ time + "\n评分： " + watched+ "\n评价次数 ：" + num + "\n摘要 ：" + zhaiyao +  "\n********************************************";
	}
	@Override
	public int compareTo(Film o) {
			Integer.parseInt(id);
		
		return Integer.parseInt(id)-Integer.parseInt(o.id);
	}

	

	// 字段

}
