package com.battleship.application.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This is generic utility class provides application level common functions.
 * 
 * @author pmalsh
 *
 */
@Component
public class GenericUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	/**
	 * Method that return <code>List<code> from comma separated values in string.
	 * 
	 * @param <code>String<code> commaSeparatedValues
	 * @return <code>List<String><code>
	 */
	public List<String> convertToList(String commaSeparatedValues) {

		List<String> listOfValues = new ArrayList<>();
		String[] arrayValues = commaSeparatedValues.split(",");

		for (String value : arrayValues) {
			listOfValues.add(value.trim());
		}
		return listOfValues;
	}

	/**
	 * Method that return <code>List<code> from comma separated values in string.
	 * 
	 * @param <code>String<code> commaSeparatedValues
	 * @return <code>List<String><code>
	 */
	public List<int[][]> convertToListOfArrayIntegers(String commaSeparatedValues) {

		logger.info("Inside GenericUtil.convertToListOfArrayIntegers");
		List<int[][]> listOfValues = new ArrayList<>();
		String[] arrayValues = commaSeparatedValues.split(",");

		for (String value : arrayValues) {
			listOfValues.add(new int[][]{{Integer.parseInt(String.valueOf(value.charAt(0)))},{ Integer.parseInt(String.valueOf(value.charAt(1)))}});
		}
		return listOfValues;
	}
	
	/**
	 * Method that return <code>List<code> from comma separated values in string.
	 * 
	 * @param <code>String<code> commaSeparatedValues
	 * @return <code>List<String><code>
	 */
	public int[][] getCoordinateArray(String value) {
		return new int[][]{{Integer.parseInt(String.valueOf(value.charAt(0)))},{ Integer.parseInt(String.valueOf(value.charAt(1)))}};
	}
	
}
