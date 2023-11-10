package common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
public class MyInterceptor implements HandlerInterceptor{
	//private final String GATEWAY_URI;
	private final String ACTIVE_PROFILE;
	public MyInterceptor(
			String ACTIVE_PROFILE
			//, String GATEWAY_URI
	) {
		this.ACTIVE_PROFILE = ACTIVE_PROFILE;
		//this.GATEWAY_URI = GATEWAY_URI;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("===============================================");
        log.info("==================== BEGIN ====================");
        Enumeration eHeader = request.getHeaderNames();
    	while (eHeader.hasMoreElements()) {
	    	String key = (String)eHeader.nextElement();
	    	String value = request.getHeader(key);
	    	log.info("key : " + key + " ===> value : " + value);
	    }

		String requestUri = request.getHeader("x-forwarded-host");

		System.out.println("ACTIVE_PROFILE : " + ACTIVE_PROFILE);
		if(ACTIVE_PROFILE.equals("local")) {
			log.info("로컬에서 실행중입니다.");
			//GATEWAY_URI = "http://localhost:8080";
		}
		/*else{
			System.out.println("요청_URI : " + requestUri);
			if(("".equals(requestUri)||requestUri == null||!requestUri.equals(GATEWAY_URI))) {
				throw new BadCredentialsException("잘못된 접근입니다.");
			}
		}*/

		// 요청 본문을 읽고 출력합니다.
		/*final CachedBodyHttpServletWrapper cachingRequest = (CachedBodyHttpServletWrapper) request;
		String requestBodyStr = "";
		requestBodyStr = CommonUtils.readRequestBody(cachingRequest);
		System.out.println("requestBody : " + requestBodyStr); // 요청 본문 내용을 출력합니다.*/

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info("==================== END ======================");
        log.info("===============================================");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}
