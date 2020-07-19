Prerequisite :   

     -	Docker must be installed on your machine (Windows 10 pro or Ubuntu)
     
Description:

      The objective is to import the financial transactions made on bank accounts. Supported files: CSV and XML. In the case of an xml file, it must be validated against the records.xsd schema. The application also offers the possibility of importing asynchronously for large files.
After cloning the project, you must run the start.sh script. A Postgres database will be installed. For a first start, it will take between 7 to 10 minutes. The application will be available on port 8080. To get the list of endpoints via swagger, just go here: http://localhost:8080/api/swagger-ui.html


-	GET /workflowexecution allows to retrieve the list of imports and their status
-	POST /async-records allows to import asynchronously 
-	POST /records allows you to import synchronously.
Technical documentation
The application is designed on the Spring boot 2, and java 8 framework. If the execution is done locally, an H2 memory base is started.
1)  Error management
The application manages 2 types of exceptions:
  -	BusinessExecption. allows you to report the business errors triggered:
      -	REFERENCE_TRANSACTION_ALREADY_EXIST
      - RECORD_FORMAT_FILE_NAME_INCORRECT
      - RECORD_CSV_FILE_TOKEN_INCORRECT
      - RECORD_END_BALANCE_NOT_CONSISTENT
      -	TECHNICAL_ERROR
      - RECORD_XML_BAD_FORMAT_NOT_VALID
      - RECORD_XML_PARSING_ERROR
      -	RECORD_EXTENSION_FILE_NAME_INCORRECT
-	TechnicalException. It reports various technical errors that occurred during execution
All business errors are handled by a Spring @ControllerAdvice component.

2)  Database
The application is started on a Postgres database. It uses Liquibase for the traceability, management and maintenance of changes at the schema level

3)  Asynchronous execution
The asynchronous import here is handled by the Spring ThreadPoolTaskExecutor.
