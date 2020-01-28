package eu.seal.apigw.cl.configuration.templates;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.thymeleaf.Arguments;
import org.thymeleaf.messageresolver.AbstractMessageResolver;
import org.thymeleaf.messageresolver.MessageResolution;

/**
 * Created by teclab on 13/12/18.
 */
public class OneFileMessageResolver extends AbstractMessageResolver
{
    private static Logger log = Logger.getLogger(OneFileMessageResolver.class);

    private Map<String, Properties> configurationRegistry;

    public OneFileMessageResolver()
    {
        super();

        configurationRegistry = new HashMap<>();
    }

    @Override
    public MessageResolution resolveMessage(Arguments arguments, String key,
            Object[] messageParameters)
    {
        Locale locale = arguments.getContext().getLocale();
        String language = locale.getLanguage();

        if (!configurationRegistry.containsKey(language))
        {
            initPropertiesFileForLanguage(language);
        }

        Properties configuration = configurationRegistry.get(locale.getLanguage());

        String value = configuration.getProperty(key);
        if (value == null)
        {
            value = "[[" + key + "]]";
        }
        if (messageParameters != null && messageParameters.length > 0)
        {
            return new MessageResolution(MessageFormat.format(value, messageParameters));
        }
        else
        {
            return new MessageResolution(value);
        }
    }

    private void initPropertiesFileForLanguage(String language)
    {
        try
        {
            Properties diskConfiguration = new Properties();
            diskConfiguration.load(this.getClass().getResourceAsStream(
                    MessageFormat.format("i18n/i18n_{0}.properties", language)));
            configurationRegistry.put(language, diskConfiguration);
        }
        catch (Exception e)
        {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

}
