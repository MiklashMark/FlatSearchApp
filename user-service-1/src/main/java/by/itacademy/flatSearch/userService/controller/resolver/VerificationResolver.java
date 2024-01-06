package by.itacademy.flatSearch.userService.controller.resolver;


import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class VerificationResolver implements HandlerMethodArgumentResolver {

    private static final  String CODE_PARAM = "code";
    private static final  String MAIL_PARAM = "mail";
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
       return parameter.getDeclaringClass().equals(VerificationDTO.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest req,
                                  WebDataBinderFactory binderFactory) {
        String codeRaw = req.getParameter(CODE_PARAM);
        String code = null;
        if (codeRaw != null && !codeRaw.isBlank()) {
            code = codeRaw;
        }

        String mailRaw = req.getParameter(MAIL_PARAM);
        String mail = null;
        if (mailRaw != null && !mailRaw.isBlank()) {
            mail = mailRaw;
        }

        return new VerificationDTO(code, mail);
    }
}