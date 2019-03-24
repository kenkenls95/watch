package application.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

//    @Autowired
//    private ConnectionFactoryLocator connectionFactoryLocator;

//    @Autowired
//    private UsersConnectionRepository usersConnectionRepository;
//
//    @Autowired
//    private FacebookConnectionSignup facebookConnectionSignup;

//
//    @Bean
//    public ProviderSignInController providerSignInController() {
//        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
//                .setConnectionSignUp(facebookConnectionSignup);
//
//        return new ProviderSignInController(
//                connectionFactoryLocator,
//                usersConnectionRepository,
//                new FacebookSignInAdapter());
//    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        logger.info("-----configure(HttpSecurity http)");
//
//        http
//                .cors().and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/user/**").hasAnyRole("USER")
//                .antMatchers("/admin").hasAnyRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
//                .permitAll()
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //TODO: for production, must be reconfigured in order to disable only in specific cases. This line was added because without it, HTTP POST requests did not work.
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        logger.info("-----configure(WebSecurity web)");
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/static/**")
                .antMatchers("/publics/**")
                .antMatchers("/img/**")
                .antMatchers("/uploaded/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("-----configureGlobal(AuthenticationManagerBuilder auth)");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
