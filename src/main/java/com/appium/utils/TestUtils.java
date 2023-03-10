/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Appium Mobile Automation - Android & iOS + Frameworks + CICD (https://www.udemy.com/course/the-complete-appium-course-for-ios-and-android/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package com.appium.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.appium.constants.FrameworkConstants;
import io.qameta.allure.internal.shadowed.jackson.databind.exc.InvalidFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static com.appium.constants.FrameworkConstants.ADDARTICLES_SHEET_NAME;

public class TestUtils {

	public static Workbook book;

	public static Sheet sheet;

	static public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
		HashMap<String, String> stringMap = new HashMap<String, String>();
		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Build Document
		Document document = builder.parse(file);

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		// Here comes the root node
		Element root = document.getDocumentElement();

		// Get all elements
		NodeList nList = document.getElementsByTagName("string");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				// Store each element key value in map
				// stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
				stringMap.put(eElement.getAttribute("name"), eElement.getAttribute("value"));
			}
		}
		return stringMap;
	}

	public static String dateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static Object[][] getTestData(String sheetName){
		FileInputStream file = null;
		try {
				file = new FileInputStream(FrameworkConstants.TESTDATA_SHEET_PATH+ADDARTICLES_SHEET_NAME);

		} catch (FileNotFoundException e) {
			Assert.fail("Fichier introuvable");
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			Assert.fail("Format Invalide");
			e.printStackTrace();
		} catch (IOException e) {
			Assert.fail("Format Invalide");
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for(int i=0; i<sheet.getLastRowNum(); i++){
			for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++){
//                if (data[i][k].equals(1))
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}

		return data;

	}

	/*
	 * public static void log(String txt) { BaseTest base = new BaseTest(); String
	 * msg = Thread.currentThread().getId() + ":" + base.getPlatform() + ":" +
	 * base.getDeviceName() + ":" +
	 * Thread.currentThread().getStackTrace()[2].getClassName() + ":" + txt;
	 * 
	 * System.out.println(msg);
	 * 
	 * String strFile = "logs" + File.separator + base.getPlatform() + "_" +
	 * base.getDeviceName() + File.separator + base.getDateTime();
	 * 
	 * File logFile = new File(strFile);
	 * 
	 * if (!logFile.exists()) { logFile.mkdirs(); }
	 * 
	 * FileWriter fileWriter = null; try { fileWriter = new FileWriter(logFile +
	 * File.separator + "log.txt", true); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } PrintWriter printWriter =
	 * new PrintWriter(fileWriter); printWriter.println(msg); printWriter.close(); }
	 */
	public static Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}

}
