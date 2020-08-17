@etl
Feature: File processing. Process Single csv file

  @SeverityLevel.NORMAL
  @tmsLink=DATAFORM-1963
  @etl
  @AC1
  Scenario: 01.01.01 Single CSV file processing. Valid CSV with header row
    Given CSV file with '' name is uploaded to S3 bucket
    And information about uploaded file is passed to application
    When processing of uploaded file has started
    #Then output is generated in avro format and result is uploаded to target bucket

  @SeverityLevel.NORMAL
  @tmsLink=DATAFORM-1963
  @etl
  @AC1
  Scenario: 01.01.02 Single CSV file processing. Valid CSV without header row
    Given CSV file with '' name is uploaded to S3 bucket
    And information about uploaded file is passed to application
    When processing of uploaded file has started
    Then output is generated in avro format and result is uploаded to target bucket
