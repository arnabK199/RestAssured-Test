package com.ig.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
	
	public Properties prop;
	public FileInputStream fis;
	
	public BaseTest() throws IOException {
		prop = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop.load(fis);
	}

}
