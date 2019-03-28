import java.io.FileNotFoundException
import java.util

import com.lyw.spark.entity.LoginUserEntity
import com.lyw.spark.scala.service.LoginAction
import org.apache.spark.sql.SparkSession

class LoginActionImpl extends Serializable with LoginAction {

//  case class LoginUserEntity();

  val HDFS_ADDRESS = "hdfs://localhost:9000"

   def packageEntity(line: String): LoginUserEntity ={
      val userEntity = new LoginUserEntity()
      val rdd = line.split("\\s+").foreach(word => {
        println(word)
        userEntity.setUserName("lyw")
      })
      return userEntity
  }

   def document(file_path: String): Array[LoginUserEntity] ={

    try{
      val spark = SparkSession.builder().appName("SSO_LOG")
                        .master("local[2]")
          .getOrCreate();

      val logData = spark.sparkContext.textFile(file_path).cache()
//      val df = logData.toDF()
//      val tt = df.select("*").show(5)
      val loginUserEntity=logData.filter( line => line.contains("LoginMsg")).
  map( target_line => packageEntity(target_line)).collect()

      return loginUserEntity

    } catch {
      case ex : FileNotFoundException =>{
        println(ex.getMessage)
      }

      case e :Exception =>{
        e.printStackTrace()
        println("系统异常:"+e.getMessage)
      }
    }

    return null
  }

  //获取hdfs文件
  def hdfs(address : String):String = {
    val stringBuilder = new StringBuilder();

    val spark = SparkSession.builder()
      .appName("hdfs app")
      .master("local[2]")
      .config("spark.driver.host", "localhost")
      .getOrCreate()

    val rdd = spark.sparkContext.textFile(address)
//    rdd.foreach(lines => stringBuilder.append(lines))
    val listrow = rdd.filter(line => line.contains("hadoop")).flatMap( line => {
      val list : List[LoginUserEntity] = List()
      val spli = line.split("\\s").foreach( x =>{
        val user = new LoginUserEntity()
        user.setAppId(x)
        user +: list
      })
      list
    })
    return stringBuilder.toString()
  }
}

object LoginActionImpl{
  def main(args: Array[String]): Unit = {
    val loginaction = new LoginActionImpl()
//    val list = loginaction.document("D:\\export\\sso.txt")
//    val list = loginaction.document("C:\\Users\\liangyuwu\\Downloads\\catalina.txt")
//    loginaction.packageEntity("tesfsdf\\sfsdfasd")
    val list = loginaction.hdfs("hdfs://192.168.1.13:9000/user/wangxiaowu/test.txt")
    println("测试数据："+list)
  }
}