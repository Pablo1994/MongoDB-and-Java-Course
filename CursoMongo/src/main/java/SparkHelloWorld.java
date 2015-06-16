import static spark.Spark.*;

/**
 * Created by pablo on 30/05/15.
 */
public class SparkHelloWorld {
    public static void main(String [] args){
        get("/", (req,res) -> "Hello World");
    }
}
