package eu.seal.apigw.cl.configuration.templates;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

public class HTMLTemplate
{
    protected String name;
    protected Map<String, Object> properties;
    private final Locale locale;

    public HTMLTemplate(String name)
    {
        this(name, new Locale("ca"));
    }

    public HTMLTemplate(String name, Locale locale)
    {
        this.name = name;
        this.properties = new HashMap<>();
        this.locale = locale;
    }

    /*
     * public HTMLTemplate(String name, Locale locale, String application) { this.name = name;
     * this.properties = new HashMap<>(); this.locale = locale; }
     */

    public void put(String key, Object value)
    {
        this.properties.put(key, value);
    }

    public byte[] process()
    {
        TemplateEngine templateEngine = TemplateEngineFactory.getTemplateEngine("HTML5",
                "templates/", ".html");

        IContext context = new Context(locale);
        context.getVariables().putAll(properties);

        return templateEngine.process(name, context).getBytes();
    }
}
