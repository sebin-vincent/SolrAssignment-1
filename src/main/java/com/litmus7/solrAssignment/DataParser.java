package com.litmus7.solrAssignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.litmus7.solrAssignment.dto.SkuDto;

public class DataParser {
	
	/**
	 * @param FileName
	 * 
	 * Parse json file and return unvalidated-skuList
	 * 
	 * @return
	 */
	public List<SkuDto> parseJsonFile(String FileName) {
		
		JSONParser jsonParser = new JSONParser();
		JSONArray skuArray=null;
		
		try (FileReader reader = new FileReader(FileName))
        {
            Object obj = jsonParser.parse(reader);
 
            JSONObject jsonObject = (JSONObject) obj;
            
            skuArray=(JSONArray) jsonObject.get("items");
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return parseSkuObject(skuArray);
		
		
	}
	
	
	
	/**
	 * @param skuArray
	 * 
	 * Convert Json(Jackson) Object to java object.
	 * 
	 * @return
	 */
	private List<SkuDto> parseSkuObject(JSONArray skuArray){
		SkuDto skuObject;
		List<SkuDto> skuList=new ArrayList<SkuDto>();
		JSONObject jsonSkuObject;
		
		int length=skuArray.size();
		
		for(int i=0;i<length;i++) {
			jsonSkuObject=(JSONObject) skuArray.get(i);
			
			skuObject=new SkuDto();
			
			skuObject.setSku_id((String) jsonSkuObject.get("sku_id"));
			skuObject.setDisplay_name((String) jsonSkuObject.get("display_name"));
			skuObject.setStart_date((String) jsonSkuObject.get("start_date"));
			skuObject.setEnd_date((String) jsonSkuObject.get("end_date"));
			skuObject.setAvg_star_rating(""+(Object) jsonSkuObject.get("avg_star_rating"));
			skuObject.setTotal_review_count(""+(Object) jsonSkuObject.get("total_review_count"));
			skuObject.setMeta_search_keywords((String) jsonSkuObject.get("meta_search_keywords"));
			skuObject.setPromo_start_date((String) jsonSkuObject.get("promo_start_date"));
			skuObject.setPromo_end_date((String) jsonSkuObject.get("promo_end_date"));
			skuObject.setProduct_attribute((String) jsonSkuObject.get("product_attribute"));
			skuObject.setPromo_type((String) jsonSkuObject.get("promo_type"));
			skuObject.setUpcid((String) jsonSkuObject.get("upcid"));
			skuList.add(skuObject);	
		}
		return skuList;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
