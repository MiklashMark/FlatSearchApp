package by.itacademy.jd2.MkJD210323.flatSearch.user_service.controller.web.resolver;


import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.dto.VerificationDTO;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserVerificationResolver implements HandlerMethodArgumentResolver {

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