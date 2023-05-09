# Programming background and algorithms (30min)
## We want to unit test the TripService.getTripsByUser method. How can you make the code testable? Do you have any refactoring suggestions ?

Refacto has made directly in file [TripService](imported-models/src/main/java/net/courtanet/tripservice/TripService.java)

I have split the method into 2 methods: 
* First to check if User has friends, we could test parameters and return with this method
* Second to check main method, if User is logged and if we could retrieve Trips


## Suppose that our TripService uses the API google-api-client and you have several versions of this library in the classpath. How would you solve this dependency conflict ?

We can specify which version of the client we want use in [pom.xml](imported-models/pom.xml) as junit version
```xml
    <dependency>
        <groupId>com.google.api-client</groupId>
        <artifactId>google-api-client</artifactId>
        <version>2.2.0</version>
    </dependency>
```
In this case, we define we are using google-api-client in version 2.2.0

# SQL and data processing (30 min)
## We have been given the task to query data coming from a network of sensors that was modeled in a MySQL database

### Write an SQL query that returns all the measurements for a given sensors on a given day (sort the results on the measurement’s timestamp)
```sql
    SELECT
        measurements.m_date AS date,
        measurements.m_parameter AS parameter,
        measurements.m_value AS value
    WHERE measurements.ref_sensor_id = 1
        AND measurements.m_date = '2021-12-01'
    ORDER BY measurements.m_timestamp ASC
```

### How would you speed-up the execution of the previous query ?
- Create indices for each table to optimize the search on sensors (name, region, etc...) and on fields m_timestamp / m_date
- split field m_timestamp to keep only time information

## How would you model the previous sensor network in an NoSQL database of your choice?
- Create only one *table* with all information for a measurements (I use Elasticsearch in the next example)
```h
PUT /test
{
  "settings": {
    "number_of_shards": 1
  },
  "mappings": {
    "properties": {
      "network_name": { "type": "text" },
      "network_description": { "type": "text" },
      "network_region": { "type": "text" },
      "network_no_of_sensors": { "type": "double" },

      "sensors_id": { "type": "double" },
      "sensors_location": { "type": "text" },
      "characteristics": { "type": "text" },

      "measurements.date": {"type": "date"},
      "measurements.parameter": {"type": "text"},
      "measurements.m_value": {"type": "text"}
    }
  }
}
```

## How do you relaunch a failed Airflow batch. Which variables allow you to control the behaviour?
It is possible to use:
* In the _context_, we have some informations already usable as *run_id*, *dag_run*, *data_interval_start*, *data_interval_end*, etc... to relaunch a previsous dag
* *params* could be use to pass specific arguments with the UI or CLI if we need some informations who are no present in the _context_

## A production Apache Spark computation is throwing a java.io.NotSerializableException. What is the probable cause and how can you fix it?
* We try to cast an object and it is not working, maybe a null value
* We have two solutions to fix it: 
    * Add a check to test if all variables as correctly defined and/or not null
    * Try/Catch the Exception and print/log an error for this specific case

## Explain the following Terraform snippets:
A new dataset called finance dataset with specific role/permissions are created.
We load in this dataset the structure (and data) from the file _events_pii.json_ with a protection for deletion

## Given the previously defined events_pii table what can you say about the following BigQuery query
The request is not optimizing to run on previous dataset, the request is ordered by _event_id_ but the dataset is partitionned by _creation_datetime_
We request all fields from the dataset, it is possible to return only necessary fields

## How would you test a Dataflow data pipeline that loads data from Google Cloud Storage to a BigQuery table?
I dont have use Dataflow, so just one suggestion:
* create static dataset for input in your Google Cloud Storage and static dataset in BigQuery table for output to check if the output stay the same after an update in the flow


