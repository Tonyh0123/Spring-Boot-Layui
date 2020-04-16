package com.tangtang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import com.tangtang.TextExtract;

/**
 * TextExtractor功能测试类.
 */

public class UseDemo {

	public static void main(String[] args) throws IOException {

		/*
		 * 测试网站：
		 * 百度博客空间             http://hi.baidu.com/liyanhong/
		 * 新浪娱乐音乐新闻与信息	http://ent.sina.com.cn/music/roll.html
		 * 腾讯娱乐新闻与信息		http://ent.qq.com/m_news/mnews.htm
		 * 搜狐音乐新闻				http://music.sohu.com/news.shtml
		 * 哈尔滨工业大学校内信息网 http://today.hit.edu.cn/
		 * 哈尔滨工业大学校内新闻网 http://news.hit.edu.cn/
		 */


		/* 注意：本处只为展示抽取效果，不处理网页编码问题，getHTML只能接收GBK编码的网页，否则会出现乱码 */
		String content = getHTML("https://news.sina.com.cn/c/2020-04-13/doc-iircuyvh7574830.shtml");

		// http://ent.sina.com.cn/y/2010-04-18/08332932833.shtml
		// http://ent.qq.com/a/20100416/000208.htm
		// http://ent.sina.com.cn/y/2010-04-18/15432932937.shtml
		// http://ent.qq.com/a/20100417/000119.htm
		// http://news.hit.edu.cn/articles/2010/04-12/04093006.htm


		/*
		 * 当待抽取的网页正文中遇到成块的新闻标题未剔除时，只要增大此阈值即可。
		 * 相反，当需要抽取的正文内容长度较短，比如只有一句话的新闻，则减小此阈值即可。
		 * 阈值增大，准确率提升，召回率下降；值变小，噪声会大，但可以保证抽到只有一句话的正文
		 */
		//TextExtract.setThreshold(76); // 默认值86

		System.out.println("got html");
		System.out.println(TextExtract.parse(content));
	}


	public static String getHTML(String strURL) throws IOException {
		URL url = new URL(strURL);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		String s = "";
		StringBuilder sb = new StringBuilder("");
		while ((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}
		return sb.toString();
	}
}
