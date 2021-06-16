package com.sapient.opendb.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenDbResponse {
	public int response_code;
    public List<Result> results;
}
