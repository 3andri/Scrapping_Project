package com.andri.btn;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

public class Run_Test_solr {
	public static void main(String[] args) {
		String urlString = "http://localhost:8983/solr/datarumah";
		HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
		solr.setParser(new XMLResponseParser());
	}

}
