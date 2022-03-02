import org.apache.spark.sql.SparkSession
import com.databricks.spark.xml._

import org.apache.spark.sql.functions._
import com.datastax.spark.connector._
import org.apache.spark.sql.cassandra._
import com.datastax.spark.connector.cql._

import com.databricks.spark.xml.functions.from_xml
import com.databricks.spark.xml.schema_of_xml
import spark.implicits._

val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("rowTag", "book").xml("/home/stefan/Spark/book.xml")

df.createCassandraTable("demo", "books01", partitionKeyColumns = Some(Seq("author")), clusteringKeyColumns = Some(Seq("price")))
df.write.cassandraFormat("books", "demo").mode("append").save()