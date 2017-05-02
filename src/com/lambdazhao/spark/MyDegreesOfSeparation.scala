package com.lambdazhao.spark
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._;

object MyDegreesOfSeparation {
  type BFSData=(Array[Int],Int,String)
  type BFSNode=(Int,BFSData)
  
  def convertToBFS(line:String)={
    val datas=line.split("\\s+");
    println(datas.mkString(","))
  }
  //:RDD[BFSNode]
  def createStartingRdd(sc:SparkContext)={
    val graph=sc.textFile("../SparkScala/Marvel-graph.txt");
    graph.foreach(convertToBFS)
  }
  def main(args:Array[String]){
    //(heroID, (connections.toArray, distance, color))
    val sc=new SparkContext("local[*]","the test name");
    createStartingRdd(sc);
    
  }
}