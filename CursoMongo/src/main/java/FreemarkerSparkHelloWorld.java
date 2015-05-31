import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by pablo on 30/05/15.
 */
public class FreemarkerSparkHelloWorld {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(FreemarkerHelloWorld.class, "/");
        get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");

                Map<String, Object> helloMap = new HashMap<String, Object>();
                helloMap.put("name", "Pablo");

                helloTemplate.process(helloMap, writer);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
            return writer;
        });
    }
}
