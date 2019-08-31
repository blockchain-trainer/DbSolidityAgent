package in.bt.blockinterceptor.model;

import java.util.ArrayList;
import java.util.List;

import in.bt.blockinterceptor.utils.CommonConstants;

public class Solidity {
	
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	List<Contract> contracts = new ArrayList<>();

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	@Override	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append(CommonConstants.PRAGMA);
		for (Contract contract : contracts) {
			buff.append(contract.toString());
		}
		return buff.toString();
	}

}
