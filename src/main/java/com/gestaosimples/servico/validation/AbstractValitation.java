package com.gestaosimples.servico.validation;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import com.gestaosimples.servico.services.AbstractRepository;

public abstract class AbstractValitation extends AbstractRepository {

    @Autowired
    protected HttpServletRequest request;

    @SuppressWarnings("unchecked")
    protected Long recuperarIdURL(String campoIdURL) {
        try {
            Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            return Long.parseLong(map.get(campoIdURL));
        } catch (Exception e) {
            return null;
        }
    }
}
