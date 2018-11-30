import java.io.FileNotFoundException
import java.util

import com.lyw.spark.entity.LoginUserEntity
import com.lyw.spark.scala.service.LoginAction
import org.apache.spark.sql.SparkSession

class LoginActionImpl {

//  case class LoginUserEntity();

   def packageEntity(line: String): LoginUserEntity ={
      val userEntity = new LoginUserEntity()
      val rdd = line.split("\\s+").foreach(word => {
        println(word)
//        userEntity.setUserName("lyw")
      })
      return userEntity
  }

   def document(file_path: String): Array[LoginUserEntity] ={

    try{
      val spark = SparkSession.builder().appName("SSO_LOG")
                        .master("local[2]")
          .getOrCreate();
      import spark.implicits._

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
        println("系统异常:"+e.getMessage)
      }
    }

    return null
  }
}

object LoginActionImpl{
  def main(args: Array[String]): Unit = {
    val loginaction = new LoginActionImpl()
//    val list = loginaction.document("D:\\export\\sso.txt")
    val list = loginaction.document("C:\\Users\\liangyuwu\\Downloads\\catalina.out")
//    loginaction.packageEntity("tesfsdf\\sfsdfasd")
    println(list)
  }
}