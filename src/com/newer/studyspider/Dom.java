package com.newer.studyspider;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Dom {
	List<Film> films;
	public Dom(List<Film> films){
		this.films=films;
	}
	
	public void xlh() {
		Document doc;
		try {
			//新建一个doc 首先需要新进一个doc工厂，然后再用工厂内的doc模型，生成doc
			doc = DocumentBuilderFactory.newInstance().
					newDocumentBuilder().newDocument();
			//从doc中新建一个根元素结点 标签为Films
			Element root = doc.createElement("Films");	
			//遍历列表中的数据
			for(int i = 0 ;i<films.size();i++) {
				//新建一个电影标签 加上属性 属性名为id 从列表中获取对应index的元素再获取它的ID
				Element film = doc.createElement("Film");
				film.setAttribute("ID",films.get(i).getId());
				//将film结点设为root结点的子节点
				root.appendChild(film);
				
				//新建标题标签 新建文本标签，并将标题加到电影标签上 文本标签加到标题上
				Element title = doc.createElement("title");
				Text text = doc.createTextNode(films.get(i).getTitle());
				film.appendChild(title);
				title.appendChild(text);
				
				//导演
				Element director = doc.createElement("director");
				film.appendChild(director);
				director.appendChild(doc.createTextNode(films.get(i).getDy()));
				
				//评价人数
				Element num = doc .createElement("num");
				film.appendChild(num);
				num.appendChild(doc.createTextNode(films.get(i).getNum()));
				
				//摘要信息
				Element zhaiyao = doc.createElement("zhaiyao");
				film.appendChild(zhaiyao);
				zhaiyao.appendChild(doc.createTextNode(films.get(i).getZhaiyao()));
				
				
				
				
			}
			//将根结点设为doc的子节点
			doc.appendChild(root);
			
			//序列化
			Transformer trans = TransformerFactory.newInstance()
					.newTransformer();
			
			trans.transform(new DOMSource(doc),
					new StreamResult(new File("films.xml")));
			
			
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		
		
		
	}


}
