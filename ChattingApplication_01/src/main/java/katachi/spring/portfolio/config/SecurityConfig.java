package katachi.spring.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration 
public class SecurityConfig{
	
	 @Autowired
	 @Lazy
	 private UserDetailsService userDetailsService;
	
	@Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .requestMatchers(mvc.pattern("/")).permitAll()
                .requestMatchers(mvc.pattern("/signup/signup")).permitAll()
                .requestMatchers(mvc.pattern("/signup/*")).permitAll()
                .requestMatchers(mvc.pattern("/signup/signupConfirmation")).permitAll()
                .requestMatchers(mvc.pattern("/signup/signupCompletion")).permitAll()
                .requestMatchers(mvc.pattern("/message/postMessage/rest")).permitAll()
                .anyRequest().authenticated()
//        		.anyRequest().permitAll()
        );

     // 変更点 ここから
        http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("userName")
                .passwordParameter("password")
                .defaultSuccessUrl("/menu/overallMenu", true)
                .permitAll()
        );
        // ここまで
        
        http.headers(headers -> headers
                .frameOptions(FrameOptionsConfig::disable)
        );

        // CSRF 対策を無効に設定 (一時的)
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers(PathRequest.toH2Console())
                .disable()
        );

        return http.build();
    }
	
	// 変更点 ここから
//    @Bean
//    InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password("user"))
//                .roles("GENERAL")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
    // ここまで
	
/*	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        PasswordEncoder encoder = passwordEncoder();
	     // ユーザーデータで認証
	        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
*/
}
