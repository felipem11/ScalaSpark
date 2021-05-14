![](https://github.com/felipem11/felipem11/blob/main/logo.png?raw=true)
# ScalaSpark

Execute using Spark environment instead by Intellij

~/spark/bin/spark-submit --class com.sundogsoftware.spark.HelloWorld ~/git/ScalaSpark/out/artifacts/SparkCourse/SparkCourse.jar 

---
##Downloaded from:
- https://www.apache.org/dyn/closer.lua/spark/spark-3.1.1/spark-3.1.1-bin-hadoop2.7.tgz

Sources of files e.g -> ratings.dat 
https://files.grouplens.org/datasets/movielens/ml-1m-README.txt

***

##Download SBT (like a Maven for Scala) 
https://www.scala-sbt.org/1.x/docs/zh-cn/Installing-sbt-on-Linux.html#Ubuntu
```shell
echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add
sudo apt-get update
sudo apt-get install sbt
```
Project sbt
http://media.sundog-soft.com/SparkScala/SparkScalaCourse.zip

```sbt
name := "MovieSimilarities1MDataset"

version := "1.0"

organization := "com.sundogsoftware"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
"org.apache.spark" %% "spark-core" % "2.4.5" % "provided",
"org.apache.spark" %% "spark-sql" % "2.4.5" % "provided"
)
```
felipe@MotherTux:~/git/ScalaSpark/sbt$ sbt assembly
```shell
[info] welcome to sbt 1.5.2 (Ubuntu Java 11.0.11)
[info] loading global plugins from /home/felipe/.sbt/1.0/plugins
[info] loading settings for project sbt-build from assembly.sbt ...
[info] loading project definition from /home/felipe/git/ScalaSpark/sbt/project
[info] loading settings for project sbt from build.sbt ...
[info] set current project to MovieSimilarities1MDataset (in build file:/home/felipe/git/ScalaSpark/sbt/)
[info] compiling 1 Scala source to /home/felipe/git/ScalaSpark/sbt/target/scala-2.11/classes ...
[info] Strategy 'discard' was applied to a file (Run the task at debug level to see details)
[warn] Ignored unknown package option FixedTimestamp(Some(1262304000000))
[success] Total time: 8 s, completed May 14, 2021, 8:25:12 AM

```
---
## Using SBT and spark-submit

Check installed Spark version at 
/home/felipe/spark/RELEASE
```text
Spark 3.1.1 (git revision 1d550c4e90) built for Hadoop 2.7.4
Build flags: -B -Pmesos -Pyarn -Pkubernetes -Psparkr -Pscala-2.12 -Phadoop-2.7 -Phive -Phive-thriftserver -DzincPort=3038
```

Check which version of Scala is necessary for Spark 3.1.1 
http://spark.apache.org/downloads.html

```text
Note that, Spark 2.x is pre-built with Scala 2.11 except version 2.4.2, which is pre-built with Scala 2.12. Spark 3.0+ is pre-built with Scala 2.12.
```

Check exacly Scala version 
http://github.com/scala/scala/releases.html

- ###Scala 2.12.13
- @SethTisue SethTisue released this on Jan 13

```shell
sudo cp /home/felipe/git/ScalaSpark/sbt/target/scala-2.12/MinTemperatureDataSet-assembly-1.0.jar ../../..
/home/felipe/spark/bin/spark-submit MinTemperatureDataSet-assembly-1.0.jar
````
OR if there is more than one classes in .jar
```shell
/home/felipe/spark/bin/spark-submit --class com.sundogsoftware.spark.MinTemperaturesDataset MinTemperatureDataSet-assembly-1.0.jar
```

---
---
---