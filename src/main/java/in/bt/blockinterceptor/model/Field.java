package in.bt.blockinterceptor.model;

import in.bt.blockinterceptor.utils.CommonConstants;

/**
 * Blockchain Trainer (www.blockchaintrainer.in)
 * 
 * This is start blockchain spring boot class it adds four blocks after setting
 * the mining difficulty level
 */

public class Field {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getInitValue() {
		return initValue;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

	private String type = CommonConstants.EMPTY_STRING;;
	private String modifier = CommonConstants.EMPTY_STRING;
	private String initValue = CommonConstants.EMPTY_STRING;

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append(type).append(CommonConstants.EMPTY_STRING).append(modifier).append(CommonConstants.EMPTY_STRING)
				.append(name).append(CommonConstants.SEMI_COLON);
		return buff.toString();
	}

}
