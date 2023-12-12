package study.ji_xiao_yuan.common;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Persolute
 * @version 1.0
 * @description 返回视频流
 * @email 1538520381@qq.com
 * @date 2023/12/12 14:33
 */
@Component
public class NonStaticResourceHttpRequestHandler extends ResourceHttpRequestHandler {
    public final static String ATTR_FILE = "NON-STATIC-FILE";

    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {
        final Path path = (Path) request.getAttribute(ATTR_FILE);
        return new FileSystemResource(path);
    }
}
