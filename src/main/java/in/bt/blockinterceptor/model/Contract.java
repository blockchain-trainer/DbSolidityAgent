package in.bt.blockinterceptor.model;

import java.util.ArrayList;
import java.util.List;

import in.bt.blockinterceptor.utils.CommonConstants;
import in.bt.blockinterceptor.utils.CommonUtils;

public class Contract {
	
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = CommonUtils.getTitleCase(name);;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	private List<Field> fields = new ArrayList<>();
	private List<Function> functions = new ArrayList<>();
	private List<MappingField> mappings = new ArrayList<>();
	public List<MappingField> getMappings() {
		return mappings;
	}

	public void setMappings(List<MappingField> mappings) {
		this.mappings = mappings;
	}

	public List<StructField> getStructs() {
		return structs;
	}

	public void setStructs(List<StructField> structs) {
		this.structs = structs;
	}

	private List<StructField> structs = new ArrayList<>();
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append(CommonConstants.CONTRACT).append(CommonConstants.SPACE).append(name).append(CommonConstants.LEFT_CURLY_BRACE);
		for (Field field : fields) {
			buff.append(field.toString());
		}
		for (Function function : functions) {
			buff.append(function.toString());
		}
		
		for (MappingField mapping : mappings) {
			buff.append(mapping.toString());
		}
		
		for (StructField struct : structs) {
			buff.append(struct.toString());
		}
		buff.append(CommonConstants.RIGHT_CURLY_BRACE);
		return buff.toString();
	}

}
