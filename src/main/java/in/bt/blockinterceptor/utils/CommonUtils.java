package in.bt.blockinterceptor.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.text.WordUtils;

import in.bt.blockinterceptor.model.Solidity;

/**
 * Block Interceptor Agent (www.blockinterceptor.in)
 * 
 * This is Utils class that holds the hash logic We can plugin any hash logic
 * which works on the Block object parameter
 */

public class CommonUtils {

	public static void writeFile(Solidity solidity) throws IOException {
		String userHome = System.getProperty("user.home");
		Path path = Paths.get(userHome + "/solidity/" + solidity.getFileName());
		Files.write(path, solidity.toString().getBytes());
	}

	public static String getSolidityType(String type) {
		if (type.startsWith(CommonConstants.VARCHAR)) {
			return CommonConstants.SOLIDITY_STRING;
		} else if (type.startsWith(CommonConstants.INT) || type.startsWith(CommonConstants.BIGINT)
				|| type.startsWith(CommonConstants.DATETIME)) {
			return CommonConstants.SOLIDITY_UINT;
		}
		return CommonConstants.EMPTY_STRING;
	}

	public static String getVariableName(String name) {
		if (isSolidityKeyword(name)) {
			return name + CommonConstants.TYPE;
		}
		return name;
	}

	private static boolean isSolidityKeyword(String name) {
		return CommonConstants.SOLIDITY_TYPES.contains(name);

	}

	public static String getTitleCase(String input) {
		final char[] delimiters = { ' ', '_' };
		input = WordUtils.capitalizeFully(input, delimiters);
		return input;
	}

	public static String createGetBody(String name, Map<String, String> parameters) {
		String structVal = CommonUtils.getVariableName(name) + CommonConstants.CHAR_S
				+ CommonConstants.LEFT_SQUARE_BRACKET + CommonConstants.PLACEHOLDER
				+ CommonConstants.RIGHT_SQUARE_BRACKET;
		StringBuffer buff = new StringBuffer();
		StringBuffer buffReturn = new StringBuffer();
		int pos = 1;
		buffReturn.append(CommonConstants.RETURN).append(CommonConstants.LEFT_BRACE);
		for (String columnName : parameters.keySet()) {
			if (columnName.startsWith(CommonConstants.UNDERSCORE)) {
				structVal = structVal.replace(CommonConstants.PLACEHOLDER, columnName);
			}
		}
		for (String columnName : parameters.keySet()) {
			pos++;
			String type = parameters.get(columnName);

			String solidityType = CommonUtils.getSolidityType(type);
			if (pos != 2 &&  pos != parameters.size()+1) {
				buffReturn.append(CommonConstants.COMMA);
			}
			
			if (columnName.startsWith(CommonConstants.UNDERSCORE)) {
				continue;
			} else {
				buffReturn.append(CommonConstants.SPACE).append(CommonConstants.RETURNS).append(pos);
				if (CommonConstants.SOLIDITY_STRING.equals(solidityType)) {
					buff.append(solidityType).append(CommonConstants.SPACE).append(CommonConstants.MEMORY);
				} else {
					buff.append(solidityType);
				}
				buff.append(CommonConstants.SPACE).append(CommonConstants.RETURNS).append(pos)
						.append(CommonConstants.EQUALS).append(structVal).append(CommonConstants.DOT)
						.append(CommonUtils.getVariableName(columnName)).append(CommonConstants.SEMI_COLON);
			}

		}
		buffReturn.append(CommonConstants.RIGHT_BRACE).append(CommonConstants.SEMI_COLON);
		buff.append(buffReturn);
		return buff.toString();
	}

	public static String createAddUpdateBody(String name, Map<String, String> parameters) {
		String structVal = CommonUtils.getVariableName(name) + CommonConstants.CHAR_S
				+ CommonConstants.LEFT_SQUARE_BRACKET + CommonConstants.PLACEHOLDER
				+ CommonConstants.RIGHT_SQUARE_BRACKET;
		for (String columnName : parameters.keySet()) {
			if (columnName.startsWith(CommonConstants.UNDERSCORE)) {
				structVal = structVal.replace(CommonConstants.PLACEHOLDER, columnName);
			}
		}
		StringBuffer buff = new StringBuffer();
		for (String columnName : parameters.keySet()) {
			buff.append(structVal).append(CommonConstants.DOT).append(CommonUtils.getVariableName(columnName))
					.append(CommonConstants.SPACE).append(CommonConstants.EQUALS)
					.append(CommonUtils.getVariableName(columnName)).append(CommonConstants.SEMI_COLON);
		}

		return buff.toString();
	}

}
