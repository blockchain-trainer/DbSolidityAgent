
package in.bt.blockinterceptor.model;

import in.bt.blockinterceptor.utils.CommonConstants;
import in.bt.blockinterceptor.utils.CommonUtils;

public class MappingField {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = CommonUtils.getTitleCase(value);
	}

	private String key;
	private String value;

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();

		buff.append(CommonConstants.MAPPING).append(CommonConstants.LEFT_BRACE).append(key)
				.append(CommonConstants.ARROW_FUNCTION).append(value).append(CommonConstants.RIGHT_BRACE).append(CommonConstants.SPACE).append(name)
				.append(CommonConstants.SEMI_COLON);
		return buff.toString();
	}

}