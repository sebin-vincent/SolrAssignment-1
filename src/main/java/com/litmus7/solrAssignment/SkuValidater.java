package com.litmus7.solrAssignment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.litmus7.solrAssignment.dto.IndexSkuDto;
import com.litmus7.solrAssignment.dto.SkuDto;

public class SkuValidater {
	
	
	/**
	 * JSON_DF-input json date format
	 */
	private final DateFormat JSON_DF = new SimpleDateFormat("dd-MM-yy hh:mm:ss.SSSSSSSSS aa");
	
	
	/**
	 * output date format
	 */
	private final DateFormat OUTPUT_DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	private final String PROMO_DATEFORMAT="dd-MM-yy";
	
	private final DateFormat PROMO_DF=new SimpleDateFormat(PROMO_DATEFORMAT);
	
	String productAttribute;

	
	
	/**
	 * @param skuList
	 * 
	 * Removes invalid sku entry and cleans data
	 * 
	 * @return valid list of sku
	 */
	public List<IndexSkuDto> validateSkuList(List<SkuDto> skuList) {
		List<IndexSkuDto> indexSkuList = new ArrayList<IndexSkuDto>();
		IndexSkuDto indexSkuDto;
		String endDate;
		String promoEndDate;
		String[] checkers=null;
		Date date=null ;
		Date promoDate=null;
		Date today=new Date();

		for(SkuDto skuDto: skuList) {
			endDate=skuDto.getEnd_date();
			
			promoEndDate=skuDto.getPromo_end_date();
			try {
				date=JSON_DF.parse(endDate);
			} catch (ParseException e) {
				System.out.println("invdalid date format in endDate in skuId:"+skuDto.getSku_id());
				continue;
			}
			
			if(date.before(today)) {
				System.out.println("product with Skuid:"+skuDto.getSku_id()+" is out of date. Removing product");
				continue;
			}else {
				
				indexSkuDto=new IndexSkuDto();
				if(!promoEndDate.equals("")) {
					try {
						promoDate=PROMO_DF.parse(promoEndDate);
					} catch (ParseException e1) {
						System.out.println("invdalid date format in PromoendDate in skuId:"+skuDto.getSku_id());
						continue;
					}
					if(promoDate.after(today)) {
						try {
							indexSkuDto.setPromo_start_date(convertToIndexDate(skuDto.getPromo_start_date(),PROMO_DATEFORMAT));
							indexSkuDto.setPromo_end_date(convertToIndexDate(skuDto.getPromo_end_date(),PROMO_DATEFORMAT));
							indexSkuDto.setPromo_type(skuDto.getPromo_type());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							System.out.println("invalid promodate format for skuID:"+skuDto.getSku_id());
							continue;
						}
					}
					
				}
				indexSkuDto.setSku_id(skuDto.getSku_id());
				indexSkuDto.setDisplay_name(skuDto.getDisplay_name());
				try {
					indexSkuDto.setStart_date(convertToIndexDate(skuDto.getEnd_date()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("invalid date format in start date in skuId:"+skuDto.getSku_id());
					continue;
				}
				indexSkuDto.setEnd_date(OUTPUT_DF.format(date));
				indexSkuDto.setAvg_star_rating(skuDto.getAvg_star_rating());
				indexSkuDto.setTotal_review_count(skuDto.getTotal_review_count());
				indexSkuDto.setPromo_type(skuDto.getPromo_type());
				checkers=skuDto.getMeta_search_keywords().split(",");
				indexSkuDto.setMeta_search_keywords(checkers);
				indexSkuDto.setProduct_attribute((skuDto.getProduct_attribute().replace("|", "")).split(", "));
				indexSkuDto.setUpcid((skuDto.getUpcid().replace("|", ",")).split(","));
				
				indexSkuList.add(indexSkuDto);
				
			}
		}
		
		

		return indexSkuList;

	}
	
	private String convertToIndexDate(String inputdate) throws ParseException {
		String outputDate=null;
		
		outputDate=OUTPUT_DF.format(JSON_DF.parse(inputdate));
		
		return outputDate;
		
	}
	
	private String convertToIndexDate(String inputdate,String inputFormat) throws ParseException {
		
		DateFormat dateFormat=new SimpleDateFormat(inputdate);
		
		String outputDate=null;
		
		outputDate=OUTPUT_DF.format(dateFormat.parse(inputdate));
		
		return outputDate;
		
	}
	

}
