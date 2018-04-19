package com.yuxin.wx.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * app工具类
 * @author admin
 *
 */
public class AppUtils {
	public static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	public static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	public static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
//	public static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
	public static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";// 定义所有w标签
	
	/**
	 * 去除html标签
	 * @param htmlStr
	 * @return
	 */
	public static String cancelHtmlLabel(String htmlStr) {

		Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
		Matcher m_w = p_w.matcher(htmlStr);
		htmlStr = m_w.replaceAll(""); // 过滤script标签

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

//		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
//		Matcher m_space = p_space.matcher(htmlStr);
//		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签

//		htmlStr = htmlStr.replaceAll(" ", ""); // 过滤
		return htmlStr;
	}
	
	 public static String timeChange(String time){
			String[] array = time.split(":");
			Integer i = 0;
			i = new Integer(array[0]).intValue() * 3600 + new Integer(array[1]).intValue() * 60 +new Integer(array[2]).intValue();
			return i.toString();
		}
}
