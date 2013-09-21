package com.chronos.classifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SimpleClassifier implements Classifier {
	// Given a list of feature. Gets all apps with the features and classifies them
	private int TOP_N = 20;
	@Override
	public List<String> classify(
			Map<String, Map<String, Integer>> appsToFeature, List<String> currFeatures) {
		Map<String, Map<String,Integer>> featureToApps = featureToApps(appsToFeature);
		Map<String, Integer> appScore = new HashMap<String,Integer>();
		for(String featureValue:currFeatures){
			List<String> appList = getTopAppsforFeature(featureValue, featureToApps,TOP_N);
			//Add score to overall list
			int score = TOP_N;
			for(String app: appList){
				if(appScore.containsKey(app)){
					appScore.put(app,appScore.get(app)+score);
				}else{
					appScore.put(app,score);
				}
				score--;
			}
		}
		return getTopN(appScore, TOP_N);
	}
	
	private List<String> getTopAppsforFeature(String featureValue, Map<String, Map<String,Integer>> featureToApps, int topN){
		Map<String,Integer> appValues = featureToApps.get(featureValue);
		return getTopN(appValues, topN);
	}
	
	private List<String> getTopN(Map<String,Integer> values, int topN){
		List<Entry<String,Integer>> appEntries = new ArrayList(values.entrySet());
		Collections.sort(appEntries, new Comparator<Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		List<String> valList = new ArrayList<String>();
		int count = 0;
		for(Entry<String, Integer> e: appEntries){
			if(count < topN){
				valList.add(e.getKey());
				count++;
			}else{
				break;
			}
		}
		return valList;
	}
	
	
	private Map<String, Map<String,Integer>> featureToApps(Map<String, Map<String,Integer>> appsToFeature) {
		Map<String, Map<String,Integer>> featureToApps = new HashMap<String, Map<String,Integer>>();
		for (String appName: appsToFeature.keySet()){
			Map<String,Integer> featureValues = appsToFeature.get(appName);
			for(String featureName: featureValues.keySet()){
				Map<String,Integer> appScore = featureToApps.get(featureName);
				if(appScore == null){
					appScore = new HashMap<String,Integer>();
					featureToApps.put(featureName, appScore);
				}
				appScore.put(appName, featureValues.get(featureName));
			}
		}
		return featureToApps;
		
	}
}
