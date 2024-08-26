package com.nrw.non_revenue_water.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrw.non_revenue_water.repository.AccountRepository;
import com.nrw.non_revenue_water.service.IJWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component // It Spring to detect our custom beans automatically.Instantiate them and
           // inject any specified dependencies into them.
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final IJWTService service;
    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var path = request.getRequestURI(); // HttpServletRequest.getRequestURI() method. This method returns the
                                            // request URI of the original request, that is, the invoked URL without
                                            // parameters. Ex- If the request is
                                            // http://localhost:1200/nrw/api/v1/register
                                            // then the getRequestURI will give this /nrw/api/v1/register

        var passedPaths = List.of("login", "adminlogin", "register", "adminsignup", "create", "swagger", "api-doc",
                "/nrw/api/v1/accounts/\\d+/image");

        for (var passedPath : passedPaths) {
            if (path.contains(passedPath) || path.matches(passedPath)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String token = request.getHeader("Authorization");// There are many auto generated headers which are associated
                                                          // with an API one of the Header is Authorization Refer to the
                                                          // postman Header tab then go to hidden to know more

        try {
            if (token == null) {
                throw new RuntimeException("Token should not be empty");
            }

            String accountNumber = service.validateToken(token.substring(7));// substring(7) becoz for ex if the
                                                                             // authorization(key) value is
                                                                             // Bearer
                                                                             // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWtlc2giLCJpYXQiOjE3MDMzOTc1MjQsImV4cCI6MTcwMzM5NzgyNH0.ytT-fG8Ub84qQ1NnVtMxKt7yU9U44xh3jF-piqt3ouw
                                                                             // Here the token starts from index
                                                                             // 7(remove the Bearer string which is the
                                                                             // prefix of Authorization token which is
                                                                             // placed automatically by Spring) so i
                                                                             // have taken substring(7)
                                                                             // refer to the postman Header tab which is
                                                                             // present in between Authorization and
                                                                             // body tab

            if (accountNumber == null || accountNumber.isEmpty() || accountNumber.isBlank())
                throw new RuntimeException("Token not found");

            var account = accountRepository.findByAccountNumber(Long.parseLong(accountNumber)).orElseThrow();
            var auth = new UsernamePasswordAuthenticationToken(account.getCredential().getAccountEmail(),
                    account.getCredential().getAccountPassword(), null);

            SecurityContextHolder.getContext().setAuthentication(auth);
            request.setAttribute("accountNumber", accountNumber);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            var problemDetails = ProblemDetail.forStatus(400);
            problemDetails.setTitle("Token issue");
            problemDetails.setDetail(e.getMessage());

            response.setContentType("application/json");
            response.setStatus(400);
            response.getWriter().println(objectMapper.writeValueAsString(problemDetails));
        }

    }

}
