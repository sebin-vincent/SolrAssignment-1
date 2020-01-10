package com.litmus7.solrAssignment.dto;

public class IndexSkuDto {

	private String sku_id;
	private String display_name;
	private String start_date;
	private String end_date;
	private String avg_star_rating;
	private String total_review_count;
	private String[] metaSearchKeywords;
	private String promo_start_date;
	private String promo_end_date;
	private String promo_type;
	private String[] product_attribute;
	private String[] upcid;

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getAvg_star_rating() {
		return avg_star_rating;
	}

	public void setAvg_star_rating(String avg_star_rating) {
		this.avg_star_rating = avg_star_rating;
	}

	public String getTotal_review_count() {
		return total_review_count;
	}

	public void setTotal_review_count(String total_review_count) {
		this.total_review_count = total_review_count;
	}

	public String[] getMeta_search_keywords() {
		return metaSearchKeywords;
	}

	public void setMeta_search_keywords(String[] strings) {
		this.metaSearchKeywords = strings;
	}

	public String getPromo_start_date() {
		return promo_start_date;
	}

	public void setPromo_start_date(String promo_start_date) {
		this.promo_start_date = promo_start_date;
	}

	public String getPromo_end_date() {
		return promo_end_date;
	}

	public void setPromo_end_date(String promo_end_date) {
		this.promo_end_date = promo_end_date;
	}

	public String getPromo_type() {
		return promo_type;
	}

	public void setPromo_type(String promo_type) {
		this.promo_type = promo_type;
	}

	public String[] getProduct_attribute() {
		return product_attribute;
	}

	public void setProduct_attribute(String[] strings) {
		this.product_attribute = strings;
	}

	public String[] getUpcid() {
		return upcid;
	}

	public void setUpcid(String[] strings) {
		this.upcid = strings;
	}

}
