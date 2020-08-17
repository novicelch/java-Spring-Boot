package cn.lch.java_spring_boot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(filterName = "requestParamaFilter",urlPatterns = "/**")
public class RequestParamaFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamaFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("====== Init RequestParama Filter ======");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("====== Do RequestParama Filter ======");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {
            @Override
            public String getParameter(String name) {
                String value = httpRequest.getParameter(name);
                if (StringUtils.isNotBlank(value)) {
                    return value.replaceAll("TMD", "***");
                }
                return super.getParameter(name);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values = httpRequest.getParameterValues(name);
                if (values != null && values.length > 0) {
                    for (int i = 0; i < values.length; i ++) {
                        values[i] = values[i].replaceAll("TMD", "***");
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };
        filterChain.doFilter(wrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
