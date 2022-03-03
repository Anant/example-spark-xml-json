# example-spark-xml-json
Download the latest spark release here: https://spark.apache.org/downloads.html

1. Start Spark
`./sbin/start-master.sh` and `./sbin/start-slave.sh`
2. Start a Spark Application with the `spark-start-xml-cassandra.bash` script
3. Use the `Json-ToCassandra.scala` script to load a json file into a cassandra table
4. Use the `Xml-ToCassandra.scala` script to load a xml file into a cassandra table
5. Use the `Xml-InsideCassandra.scala` script to parse xml file inside a cassandra table and load it into a new one
