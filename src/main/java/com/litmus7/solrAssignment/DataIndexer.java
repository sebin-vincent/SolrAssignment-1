package com.litmus7.solrAssignment;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;

import com.litmus7.solrAssignment.dto.IndexSkuDto;

public class DataIndexer {

	/**
	 * @param coreName
	 * @param validSkuList
	 * 
	 *                     index input validated skuList into solr
	 * 
	 */
	public void indexData(String coreName, List<IndexSkuDto> validSkuList) {

		String urlString = "http://localhost:8983/solr/" + coreName;

		HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(urlString).build();
		httpSolrClient.setParser(new XMLResponseParser());

		SolrInputDocument doc1;

		for (IndexSkuDto sku : validSkuList) {

			doc1 = new SolrInputDocument();

			doc1.setField("sku_id", sku.getSku_id());
			doc1.setField("display_name", sku.getDisplay_name());
			doc1.setField("start_date", sku.getStart_date());
			doc1.setField("end_date", sku.getEnd_date());
			doc1.setField("avg_star_rating", sku.getAvg_star_rating());
			doc1.setField("total_review_count", Integer.parseInt(sku.getTotal_review_count()));
			
			
			
			for (String metaKeyword : sku.getMeta_search_keywords()) {
				//Here the multivalued indexing is not working
				doc1.setField("meta_search_keywords", metaKeyword);
			}
			doc1.setField("promo_start_date", sku.getPromo_start_date());
			doc1.setField("promo_end_date", sku.getPromo_end_date());
			doc1.setField("promo_type", sku.getPromo_type());

			for (String attribute : sku.getProduct_attribute()) {
				doc1.setField("product_attribute", attribute);
			}

			for (String upcid : sku.getUpcid()) {
				//Here the multivalued indexing is not working
				doc1.setField("upcid", upcid);
			}

			try {
				httpSolrClient.add(doc1);
			} catch (SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			httpSolrClient.commit(true, true);
			System.out.println("Indexing successfull");
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
