package com.download.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlparser.tags.LinkTag;

public class Url {

	/**
	 * http://repo.maven.apache.org/maven2/
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HttpClient http = new DefaultHttpClient();
			// http.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new
			// HttpHost("172.17.18.84",8080));

			HttpGet hg = new HttpGet("http://repo.maven.apache.org/maven2/");
			HttpResponse hr = http.execute(hg);
			HttpEntity he = hr.getEntity();// 鍝堝搱
			if (he != null) {
				String charset = EntityUtils.getContentCharSet(he);
				InputStream is = he.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "GBK"));
				String line = null;
				// IOUtils.copy(is,new FileOutputStream("E:/Baidu.html"));
				while ((line = br.readLine()) != null) {
					List<LinkTag> link = Attrbuite.getText(line, LinkTag.class);
					for (LinkTag l : link) {
						System.out.println(l.getStringText());
					}
				}
				is.close();
			}
			http.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}