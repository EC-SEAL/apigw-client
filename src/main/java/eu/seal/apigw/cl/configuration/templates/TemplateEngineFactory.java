package eu.seal.apigw.cl.configuration.templates;

import java.util.HashMap;
import java.util.Map;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Created by teclab on 13/12/18.
 */
public class TemplateEngineFactory
{
    private static Map<String, TemplateEngine> templateEngines = new HashMap<String, TemplateEngine>();

    private static TemplateEngine initializeTemplateEngine(String templateMode, String prefix,
            String sufix, boolean cacheable, Long timeToLive)
    {
        TemplateResolver templateResolver = new ClassLoaderTemplateResolver();//new FileTemplateResolver();
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(templateMode);
        templateResolver.setPrefix(prefix);
        templateResolver.setSuffix(sufix);
        templateResolver.setCacheable(cacheable);
        templateResolver.setCacheTTLMs(timeToLive);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(new LayoutDialect());

        templateEngine.setMessageResolver(new OneFileMessageResolver());

        return templateEngine;
    }

    public static TemplateEngine getTemplateEngine(String templateMode, String prefix, String sufix)
    {
        return getTemplateEngine(templateMode, prefix, sufix, false, 3600000L);
    }

    public static TemplateEngine getTemplateEngine(String templateMode, String prefix, String sufix,
            boolean cacheable, Long timeToLive)
    {
        if (templateEngines == null || !templateEngines.containsKey(templateMode))
        {
            TemplateEngine templateEngine = initializeTemplateEngine(templateMode, prefix, sufix,
                    false, 3600000L);
            templateEngines.put(templateMode, templateEngine);
        }

        return templateEngines.get(templateMode);
    }
}
