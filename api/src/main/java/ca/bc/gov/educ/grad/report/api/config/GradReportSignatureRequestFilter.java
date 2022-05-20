package ca.bc.gov.educ.grad.report.api.config;

import ca.bc.gov.educ.grad.report.api.util.JwtTokenUtil;
import ca.bc.gov.educ.grad.report.dao.SignatureImageRepository;
import ca.bc.gov.educ.grad.report.entity.GradReportSignatureImageEntity;
import ca.bc.gov.educ.grad.report.utils.EducGradSignatureImageApiConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class GradReportSignatureRequestFilter extends OncePerRequestFilter {

    JwtTokenUtil jwtTokenUtil;

    @Autowired
    GradReportSignatureAuthenticationService authenticationService;

    @Autowired
    SignatureImageRepository signatureImageRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String contentPath = httpServletRequest.getRequestURI();
        if(!StringUtils.contains(contentPath, EducGradSignatureImageApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String accessToken = httpServletRequest.getParameter("access_token");
        if (isEmpty(accessToken)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String signatureCode = StringUtils.substringAfter(contentPath, EducGradSignatureImageApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING + "/");
        GradReportSignatureImageEntity signatureImage = signatureImageRepository.findBySignatureCode(signatureCode);
        if(signatureImage == null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String tokenKey = StringUtils.substring(accessToken, 0, 256);
        jwtTokenUtil = new JwtTokenUtil(tokenKey);
        accessToken = StringUtils.replace(accessToken, tokenKey, "");
        String username = jwtTokenUtil.getUsernameFromToken(accessToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.authenticationService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(accessToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
