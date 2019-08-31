# DbSolidityAgent

This is a very simple (almost trivial) database to smart contract convertor project - built on spring boot. It is a part of a larger project Im working on. It hooks up to a db (mysql for now) and loads all the table descriptions. It converts the table columns to solidity structs. It adds a mapping with the primary key to struct map. It adds two CRUD functions - addUpdate<Struct>() and get<Struct>().
  
 <b> Build:</b>
  
  $mvn clean install.
  
  <b>Run (from inside target directory):</b>
  
  $java -jar dbagent-ri-V01-0.0.1-SNAPSHOT.jar jdbc:mysql://localhost:3306/<schema> <user> <password> <schema>
  
  The solidity files are created in user.home (look for your home directory in your operating system - you should see a directory "solidity", the contract files are in there. 
  
  <b>This is pre-alpha bug prone and doesnt support all DB data types yet.</b>
