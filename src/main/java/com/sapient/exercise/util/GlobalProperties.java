package com.sapient.exercise.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
public class GlobalProperties {

    @Value("${openDbUri}")
    private String openDbUri;

    @Value("${amount}")
    private int amount;
    
    @Value("${categoryList}")
    private String categoryList;

	public String getOpenDbUri() {
		return openDbUri;
	}

	public void setOpenDbUri(String openDbUri) {
		this.openDbUri = openDbUri;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(String categoryList) {
		this.categoryList = categoryList;
	}

}