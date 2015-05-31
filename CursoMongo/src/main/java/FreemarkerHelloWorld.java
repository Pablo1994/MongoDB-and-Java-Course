import freemarker.template.Configuration;

/**
 * Created by pablo on 30/05/15.
 */
public class FreemarkerHelloWorld {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(FreemarkerHelloWorld.class, "/");
    }
}
