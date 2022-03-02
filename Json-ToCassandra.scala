import spark.implicits._

val path = "/home/stefan/Spark/jsonexamples/Data-Person.json"
val peopleDF = spark.read.json(path)

peopleDF.printSchema()

val spark = SparkSession.builder().getOrCreate()

peopleDF.createCassandraTable("demo", "people", partitionKeyColumns = Some(Seq("phone")), clusteringKeyColumns = Some(Seq("name")))
peopleDF.write.cassandraFormat("people", "demo").mode("append").save()