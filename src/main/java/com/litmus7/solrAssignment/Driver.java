package com.litmus7.solrAssignment;

import java.util.List;

import com.litmus7.solrAssignment.dto.IndexSkuDto;
import com.litmus7.solrAssignment.dto.SkuDto;

/**
 *This is the driver class. All functionalities are called
 *from this class.
 *
 */
public class Driver 
{
	//input json data file name
	private static final String FILENAME="sku_data.json";
	
    public static void main( String[] args )
    {
       
       
       DataParser cleaner=new DataParser();
       
       //Reading data into list of sku from given json file.
       List<SkuDto> skuList=cleaner.parseJsonFile(FILENAME);
       
       
       SkuValidater validater=new SkuValidater();
       
      
       
       
       //Validates skuList based on time cleans to get list to be indexed.
       List<IndexSkuDto> validSkuList= validater.validateSkuList(skuList);
       
       DataIndexer indexer=new DataIndexer();
       
       //Index valid sku list into solr.
       indexer.indexData("assignment_core", validSkuList);
       
    }
}
