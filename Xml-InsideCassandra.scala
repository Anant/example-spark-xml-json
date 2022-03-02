
import org.apache.spark.sql.SparkSession
import com.databricks.spark.xml._

import org.apache.spark.sql.functions._
import com.datastax.spark.connector._
import org.apache.spark.sql.cassandra._
import com.datastax.spark.connector.cql._

import com.databricks.spark.xml.functions.from_xml
import com.databricks.spark.xml.schema_of_xml
import spark.implicits._

val df = spark.read.cassandraFormat("books", "demo").load()
val payloadSchema = schema_of_xml(df.select("description").as[String])
val parsed = df.withColumn("parsed", from_xml($"description", payloadSchema))

parsed.printSchema()

parsed.select("parsed.title","parsed.description","parsed.price").filter($"price" > 8).show()

parsed.select("parsed.title","parsed.description","parsed.price").createCassandraTable("demo", "transform", partitionKeyColumns = Some(Seq("title")), clusteringKeyColumns = Some(Seq("price")))
parsed.select("parsed.title","parsed.description","parsed.price").write.cassandraFormat("transform", "demo").mode("append").save()
