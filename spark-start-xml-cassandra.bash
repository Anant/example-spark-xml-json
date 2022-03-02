sudo ./bin/spark-shell --packages com.datastax.spark:spark-cassandra-connector_2.12:3.0.0,com.databricks:spark-xml_2.12:0.14.0 \
--master spark://DESKTOP-F7N0K6I.localdomain:7077 \
--conf spark.cassandra.connection.host=127.0.0.1 \
--conf spark.cassandra.connection.port=9042