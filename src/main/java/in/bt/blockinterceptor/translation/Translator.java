package in.bt.blockinterceptor.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import in.bt.blockinterceptor.model.Contract;
import in.bt.blockinterceptor.model.Function;
import in.bt.blockinterceptor.model.MappingField;
import in.bt.blockinterceptor.model.Solidity;
import in.bt.blockinterceptor.model.StructField;
import in.bt.blockinterceptor.utils.CommonConstants;
import in.bt.blockinterceptor.utils.CommonUtils;

/**
 * Blockchain Trainer (www.blockchaintrainer.in)
 * 
 * This is start blockchain spring boot class it adds four blocks after setting
 * the mining difficulty level
 */

@Component
public class Translator {

	public Solidity translateSingletable(String tableName, Map<String, String> columns) {
		Solidity solidity = new Solidity();
		solidity.setFileName(tableName + CommonConstants.EXTENSION_SOL);
		Contract c = new Contract();
		c.setName(CommonUtils.getVariableName(tableName));
		solidity.getContracts().add(c);
		addStructField(c, tableName, columns);
		addMappingField(c, tableName, columns);
		addFunction(c, tableName, columns);
		return solidity;
	}

	private void addFunction(Contract c, String tableName, Map<String, String> columns) {
		List<Function> functions = new ArrayList<>();
		Function functionAdd = createGetFunction(tableName, columns);
		Function functionUpdate = createAddUpdateFunction(tableName, columns);

		functions.add(functionAdd);
		functions.add(functionUpdate);
		c.setFunctions(functions);

	}

	private Function createAddUpdateFunction(String tableName, Map<String, String> columns) {
		Function function = new Function();
		function.setModifier(CommonConstants.PUBLIC);
		function.setName(CommonUtils.getVariableName(tableName));
		List<String> parameters = new ArrayList<>();
		function.setFuncType(CommonConstants.ADD_UPDATE);
		function.setBody(CommonUtils.createAddUpdateBody(CommonUtils.getVariableName(tableName), columns));
		for (String columnName : columns.keySet()) {
			String type = columns.get(columnName);
			String solidityType = CommonUtils.getSolidityType(type);
			if (CommonConstants.SOLIDITY_STRING.equals(solidityType)) {
				solidityType = CommonConstants.SOLIDITY_STRING + CommonConstants.SPACE + CommonConstants.MEMORY;
			} 
			parameters.add(solidityType + CommonConstants.SPACE + CommonUtils.getVariableName(columnName));

		}
		function.setParameters(parameters);
		return function;
	}

	private Function createGetFunction(String tableName, Map<String, String> columns) {
		Function function = new Function();
		function.setModifier(CommonConstants.PUBLIC);
		function.setName(CommonUtils.getVariableName(tableName));
		List<String> parameters = new ArrayList<>();
		List<String> returnTypes = new ArrayList<>();
		function.setFuncType(CommonConstants.GET);
		function.setView(true);
		function.setBody(CommonUtils.createGetBody(CommonUtils.getVariableName(tableName), columns));
		for (String columnName : columns.keySet()) {
			String type = columns.get(columnName);
			String solidityType = CommonUtils.getSolidityType(type);
			
			if (columnName.startsWith(CommonConstants.UNDERSCORE)) {
				parameters.add(solidityType + CommonConstants.SPACE + CommonUtils.getVariableName(columnName));
			} else {
				returnTypes.add(solidityType);
			}

		}
		function.setParameters(parameters);
		function.setReturnTypes(returnTypes);
		return function;
	}

	private void addMappingField(Contract c, String tableName, Map<String, String> columns) {
		MappingField mapping = new MappingField();
		mapping.setName(CommonUtils.getVariableName(tableName) + CommonConstants.CHAR_S);
		for (String columnName : columns.keySet()) {
			if (columnName.startsWith(CommonConstants.UNDERSCORE)) {
				String type = columns.get(columnName);
				String solidityType = CommonUtils.getSolidityType(type);
				mapping.setKey(solidityType);
				break;
			}
		}
		mapping.setValue(CommonUtils.getVariableName(tableName));
		c.getMappings().add(mapping);
	}

	private void addStructField(Contract c, String tableName, Map<String, String> columns) {
		c.setName(CommonUtils.getVariableName(tableName));
		StructField struct = new StructField();
		struct.setName(CommonUtils.getVariableName(tableName));

		for (String columnName : columns.keySet()) {
			String type = columns.get(columnName);
			String solidityType = CommonUtils.getSolidityType(type);
			struct.getFields().add(solidityType + CommonConstants.SPACE + CommonUtils.getVariableName(columnName));
		}
		c.getStructs().add(struct);
	}

}
