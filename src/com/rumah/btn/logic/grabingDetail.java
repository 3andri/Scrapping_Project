package com.rumah.btn.logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.hibernate.internal.build.AllowSysOut;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import com.rumah.btn.OrmConnection;
import com.rumah.btn.model.BTN_rumah;

public class grabingDetail {

	private static String localpath = "img";

	public static void main(String[] args) {
		getDetail();

	}

	public static void getDetail() {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		// while (true) {

		try {

//				List<BTN_rumah> btn_rumahs = OrmConnection.getRumahBtn().queryBuilder().limit((long) 1000).where()
//						.isNull("BTN_KODE_ASET").query();
			List<BTN_rumah> btn_rumahs = OrmConnection.getRumahBtn().queryBuilder().where().isNull("BTN_KODE_ASET")
					.query();
			for (final BTN_rumah btn_rumah : btn_rumahs) {
				Runnable Operation = new Runnable() {
					public void run() {
						name(btn_rumah);
					}
				};
				pool.execute(Operation);
			}
//				if (btn_rumahs.size() < 999) {
//					break;
//				}
			// break;
		} catch (Exception e) {
			e.printStackTrace();
			// break;

		}

		// }
		pool.shutdown();

	}

	public static void name(BTN_rumah btn_rumah) {

		try {
			System.out.println(btn_rumah.getBtn_url());
			Document doc = Jsoup.connect(btn_rumah.getBtn_url()).get();
			System.out.println(doc.select("section#quick-summary.clearfix dl dd").get(3).text());
			btn_rumah.setBtn_kode_aset(doc.select("section#quick-summary.clearfix dl dd").get(0).text());
			if (doc.select("section#quick-summary.clearfix dl dd span.tag").size() > 1) {
				System.out.println(doc.select("section#quick-summary.clearfix dl dd span.tag").get(0));
				btn_rumah.setBtn_harga_awal(new BigInteger(doc.select("section#quick-summary.clearfix dl dd span.tag")
						.get(0).text().replaceAll("[A-Za-z .]", "")));
				btn_rumah.setBtn_harga(new BigInteger(doc.select("section#quick-summary.clearfix dl dd span.tag").get(1)
						.text().replaceAll("[A-Za-z .]", "")));
			} else {
				btn_rumah.setBtn_harga(new BigInteger(
						doc.select("section#quick-summary.clearfix dl dd").get(1).text().replaceAll("[A-Za-z .]", "")));
			}
			btn_rumah.setBtn_lokasi(doc.select("section#quick-summary.clearfix dl dd").get(2).text());
			if (doc.select("section#quick-summary.clearfix dl dd").size() == 6) {
				btn_rumah.setBtn_lebar_jalan_depan(
						Integer.parseInt(doc.select("section#quick-summary.clearfix dl dd").get(3).text()));
				btn_rumah.setBtn_luas_tanah(Integer.parseInt(
						doc.select("section#quick-summary.clearfix dl dd").get(4).text().replaceAll("[.]", "")));
				btn_rumah.setBtn_luas_bangunan(Integer.parseInt(
						doc.select("section#quick-summary.clearfix dl dd").get(5).text().replaceAll("[.]", "")));
			} else {
				btn_rumah.setBtn_dokumen(doc.select("section#quick-summary.clearfix dl dd").get(3).text());
				btn_rumah.setBtn_lebar_jalan_depan(
						Integer.parseInt(doc.select("section#quick-summary.clearfix dl dd").get(4).text()));
				btn_rumah.setBtn_luas_tanah(Integer.parseInt(
						doc.select("section#quick-summary.clearfix dl dd").get(5).text().replaceAll("[.]", "")));
				btn_rumah.setBtn_luas_bangunan(Integer.parseInt(
						doc.select("section#quick-summary.clearfix dl dd").get(6).text().replaceAll("[.]", "")));
			}
			btn_rumah.setBtn_rencana_lokasi_penjualan(doc.select("section#quick-lelang.clearfix dl dd").get(0).text());

			try {
				btn_rumah.setBtn_keterangan_penjualan(doc.select("section#quick-lelang.clearfix dl dd").get(1).text());
				btn_rumah.setBtn_nomor_telp(doc.select("section#quick-lelang.clearfix dl dd").get(2).text());
			} catch (Exception e) {
				e.printStackTrace();
			}
			btn_rumah.setBtn_gambar_local(localpath + "\\" + btn_rumah.getBtn_kode_aset() + ".jpg");
			//getDownloadImage(btn_rumah.getBtn_gambar_url(), btn_rumah.getBtn_gambar_local());

			System.out.println(btn_rumah.toString());
			OrmConnection.getRumahBtn().update(btn_rumah);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getDownloadImage(String url, String image) {
		Document doc = null;
		try {
			// Open a URL Stream
			System.out.println(url   );
			Response resultImageResponse = Jsoup.connect(url)
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0")
					.ignoreContentType(true).execute();

			// output here
			FileOutputStream out = (new FileOutputStream(new java.io.File(image)));
			out.write(resultImageResponse.bodyAsBytes()); // resultImageResponse.body() is where the image's contents
															// are.
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
