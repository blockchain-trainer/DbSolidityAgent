package in.bt.blockinterceptor.model;

import java.util.ArrayList;
import java.util.List;

import in.bt.blockinterceptor.utils.CommonConstants;
import in.bt.blockinterceptor.utils.CommonUtils;

public class StructField {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {		
		this.name = CommonUtils.getTitleCase(name);
	}

	private List<String> fields = new ArrayList<>();

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append(CommonConstants.STRUCT).append(CommonConstants.SPACE).append(name).append(CommonConstants.LEFT_CURLY_BRACE);
		for (String field : fields) {
			buff.append(field).append(CommonConstants.SEMI_COLON);
		}
		buff.append(CommonConstants.RIGHT_CURLY_BRACE);
		return buff.toString();
	}

}
