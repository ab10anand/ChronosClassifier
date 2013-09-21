package com.chronos.classifier;

import java.util.List;
import java.util.Map;

public interface Classifier {

	public List<String> classify(Map<String,Map<String,Integer>> appsToFeature, List<String> apps);
}
