import spark.implicits._
import org.apache.spark.sql.SparkSession
import com.databricks.spark.xml._

import org.apache.spark.sql.functions._
import com.datastax.spark.connector._
import org.apache.spark.sql.cassandra._
import com.datastax.spark.connector.cql._


val path = "/home/stefan/Spark/jsonexamples/Data-Person.json"
val peopleDF = spark.read.json(path)

peopleDF.printSchema()

val spark = SparkSession.builder().getOrCreate()

peopleDF.createCassandraTable("demo", "people", partitionKeyColumns = Some(Seq("phone")), clusteringKeyColumns = Some(Seq("name")))
peopleDF.write.cassandraFormat("people", "demo").mode("append").save()
