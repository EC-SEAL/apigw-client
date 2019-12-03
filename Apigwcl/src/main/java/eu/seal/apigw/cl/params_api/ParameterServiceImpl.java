/**
Copyright © 2019  UAegean. All rights reserved.
This file is part of SEAL API Gateway Client (SEAL Apigwcl).
SEAL Apigwcl is free software: you can redistribute it and/or modify it under the terms of EUPL 1.2.
THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT ANY WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT, 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
See README file for the full disclaimer information and LICENSE file for full license information in the project root.
*/

package eu.seal.apigw.cl.params_api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author UAegean
 */
@Service
public class ParameterServiceImpl implements ParameterService{

    private final Logger log = LoggerFactory.getLogger(ParameterServiceImpl.class);

    private final Map<String, String> properties;

    public ParameterServiceImpl() {
        properties = getConfigProperties();
    }

    @Override
    public String getParam(String paramName) {
        if (StringUtils.isEmpty(System.getenv(paramName))) {
            return properties.get(paramName);
        }
        return System.getenv(paramName);
    }

    private Map<String, String> getConfigProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        HashMap<String, String> map = new HashMap();
        try {
            input = new FileInputStream("/webappConfig/config.properties");
            prop.load(input);
            prop.forEach((key, value) -> {
                map.put((String) key, (String) value);
            });
        } catch (IOException ex) {
        	//log.info("Properties file not found in /webappConfig/config.properties", ex);
             ;
            //log.info("Properties file not found in /webappConfig/config.properties");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

}
