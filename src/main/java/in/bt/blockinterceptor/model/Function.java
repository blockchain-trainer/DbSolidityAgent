package in.bt.blockinterceptor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.bt.blockinterceptor.utils.CommonConstants;
import in.bt.blockinterceptor.utils.CommonUtils;

public class Function {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = CommonUtils.getTitleCase(name);
	}

	public List<String> getReturnTypes() {
		return returnTypes;
	}

	public void setReturnTypes(List<String> returnTypes) {
		this.returnTypes = returnTypes;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public boolean isPayable() {
		return isPayable;
	}

	public void setPayable(boolean isPayable) {
		this.isPayable = isPayable;
	}

	boolean isPure;

	public boolean isPure() {
		return isPure;
	}

	public void setPure(boolean isPure) {
		this.isPure = isPure;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	private String name;
	private List<String> returnTypes = new ArrayList<String>();
	private String modifier;
	private boolean isPayable;
	private List<String> parameters = new ArrayList<>();
	private String funcType;
	private boolean view;
	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public String getFuncType() {
		return funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}

	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append(CommonConstants.FUNCTION).append(CommonConstants.SPACE);
		buff.append(funcType).append(name).append(CommonConstants.LEFT_BRACE);
		int pos = 0;
		for (String parameter : parameters) {
			pos++;
			if (pos > 1) {
				buff.append(CommonConstants.COMMA);
			}
			buff.append(parameter);
			
		}
		buff.append(CommonConstants.RIGHT_BRACE).append(CommonConstants.SPACE);
		buff.append(modifier).append(CommonConstants.SPACE);
		if (isPayable) {
			buff.append(CommonConstants.PAYABLE).append(CommonConstants.SPACE);
		}
		if (isPure) {
			buff.append(CommonConstants.PURE).append(CommonConstants.SPACE);
		}
		if (view) {
			buff.append(CommonConstants.VIEW).append(CommonConstants.SPACE);
		}
		
		if (returnTypes != null && returnTypes.size() > 0) {
			buff.append(CommonConstants.RETURNS).append(CommonConstants.LEFT_BRACE);
			pos = 0;
			for (String parameter : returnTypes) {
				pos++;
				if (pos > 1) {
					buff.append(CommonConstants.COMMA);
				}
				if (CommonConstants.SOLIDITY_STRING.equals(parameter)) {
					buff.append(parameter).append(CommonConstants.SPACE).append(CommonConstants.MEMORY);
				} else {
					buff.append(parameter);
				}

			}
			buff.append(CommonConstants.RIGHT_BRACE);
		}
		
		buff.append(CommonConstants.LEFT_CURLY_BRACE);
		buff.append(body);
		buff.append(CommonConstants.RIGHT_CURLY_BRACE);
		return buff.toString();
	}
}
