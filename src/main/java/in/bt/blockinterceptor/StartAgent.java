package in.bt.blockinterceptor;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import in.bt.blockinterceptor.db.DBOperations;
import in.bt.blockinterceptor.model.Solidity;
import in.bt.blockinterceptor.translation.Translator;
import in.bt.blockinterceptor.utils.CommonUtils;

/**
 * Blockchain Trainer (www.blockchaintrainer.in)
 * 
 * This is start blockchain spring boot class it adds four blocks after setting
 * the mining difficulty level
 */

@SpringBootApplication
public class StartAgent implements CommandLineRunner {
	@Autowired
	private Translator translator;
	@Autowired
	private DBOperations dBOperations;

	public static void main(String[] args) {
		new SpringApplicationBuilder(StartAgent.class).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Connection connection = dBOperations.connectMySql(args[0], args[1], args[2]);
		List<String> tables = dBOperations.getTables(args[3], connection);
		for (String table : tables) {
			Map<String, String> columns = dBOperations.getColumns(connection,args[3], table);
			Solidity solidity = translator.translateSingletable(table, columns);
			CommonUtils.writeFile(solidity);
		}
		

	}
}
