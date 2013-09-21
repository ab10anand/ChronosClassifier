package com.chronos.classifier;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith( JUnit4.class )
public class ClassifierTest extends TestCase  {
	
   SimpleClassifier classifier;
	Map<String,Map<String,Integer>> appsToFeatures; 
	
	@Before
	public void initialize(){
		classifier = new SimpleClassifier();
		appsToFeatures = new HashMap<String,Map<String,Integer>>();
		HashMap<String, Integer> featureScore = new HashMap<String, Integer>();
		featureScore.put("3G-ON", 4);
		featureScore.put("Wifi-ON",5);
		featureScore.put("Camera-ON", 9);
		appsToFeatures.put("Instagram", featureScore);
		featureScore = new HashMap<String, Integer>();
		featureScore.put("3G-OFF", 2);
		featureScore.put("Wifi-OFF",4);
		featureScore.put("Brightness-30",5);
		appsToFeatures.put("kindle", featureScore);
		featureScore = new HashMap<String, Integer>();
		featureScore.put("HEADSET-in",4);
		featureScore.put("Wifi-ON",3);
		featureScore.put("3G-ON",3);
		appsToFeatures.put("savvn", featureScore);
	}
	
	@After
	public void cleanup(){
		appsToFeatures.clear();
	}
	
	@Test
	public void testClassify(){
		List<String> featureList = Arrays.asList("Wifi-ON","HEADSET-in");
		assertEquals("savvn", classifier.classify(appsToFeatures, featureList).get(0));
		featureList = Arrays.asList("Wifi-OFF", "3G-OFF");
		assertEquals("kindle", classifier.classify(appsToFeatures, featureList).get(0));
		featureList = Arrays.asList("Wifi-ON", "Camera-ON");
		assertEquals("Instagram", classifier.classify(appsToFeatures, featureList).get(0));
		
	}
}
