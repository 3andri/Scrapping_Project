


package com.rumah.btn.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.rumah.btn.OrmConnection;
import com.rumah.btn.model.BTN_rumah;

import javafx.beans.binding.When;

public class grabingBTN {
	static int lastlist;
	static int now=0;
	static public String url_Base = "https://rumahmurahbtn.co.id/btn/type/1";
	static boolean loop=true;
	public static void main(String[] args) {
		getList();

	}

	public static void getList() {

		// System.setProperty("sun.net.spi.nameservice.nameservers", "127.0.0.1");
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		Map<String, String> cookies=new HashMap<String, String>();
		cookies.put("_ga", "GA1.3.7349724.1628051990");
		cookies.put("_gid", "GA1.3.255195605.1634172761");
		cookies.put("remember_me", "visited");
		cookies.put("rmbtnses", "a5aec24c836c3bb0f662fba9bc5cad870644b0030");
		cookies.put("_gat_gtag_UA_117545583_1", "1");
		try {

			getLastList();
			while (loop) {
				String url_home=url_Base+"/"+now;
				Runnable Operation = new Runnable() {
					public void run() {
						try {
							Document doc = Jsoup.connect(url_home).cookies(cookies).get();
							doc.select("div.property-image a").forEach(rumah -> {
								try {
									System.out.println(rumah.attr("href"));
									BTN_rumah btn_rumah = new BTN_rumah();
									btn_rumah.setBtn_url(rumah.attr("href"));
									btn_rumah.setBtn_gambar_url(rumah.child(0).attr("src"));
									if(!btn_rumah.getBtn_url().contains("#")) {
										OrmConnection.getRumahBtn().create(btn_rumah);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							});
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				pool.execute(Operation);
				now=now +9;
				if(now>lastlist) {
					loop=false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}

	private static void getLastList() throws Exception {
		Document doc = Jsoup.connect(url_Base).get();
		String Last = doc.select("section#properties div.center ul.pagination li a").last().attr("href");
		Last = Last.replaceAll(".*/", "");
		System.out.println("Last :" + Last);
		lastlist = Integer.parseInt(Last);
		System.out.println("Last :" + lastlist);
	}

}
