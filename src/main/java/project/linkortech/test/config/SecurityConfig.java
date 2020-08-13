package project.linkortech.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by ycj on 2016/11/28.
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * http.authorizeRequests()
         .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", true)
         .and().logout().logoutUrl("/logout")
         .and().sessionManagement().maximumSessions(1).expiredUrl("/expired")
         .and()
         .and().exceptionHandling().accessDeniedPage("/accessDenied");

         loginProcessingUrl - 设置表单的提交地址-交给security处理

         http.authorizeRequests().antMatchers("/assets/", "/").permitAll()
         .anyRequest().authenticated()
         .and().formLogin().usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login").loginPage("/login")
         .and().logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/login")
         .logoutSuccessHandler(logoutSuccessHandler)
         .invalidateHttpSession(true).addLogoutHandler(logoutHandler).deleteCookies(new String[] { "cookie名字" })
         .and().rememberMe();
         */
//        http
//                .authorizeRequests()
//                .antMatchers("/css/**", "/index").permitAll()
//                .antMatchers("/user/**").hasRole("USER")
//                .and()
//                .formLogin().loginPage("/login").failureUrl("/login-error");
//        http
//                .antMatcher()
//                .authorizeRequests()
//                    .antMatchers("/main").permitAll()
//                    .antMatchers("/secur/**").authenticated()
//                    .anyRequest().authenticated()
//                    .and()
//                .httpBasic();
        http.csrf().disable();
//        http
//            .authorizeRequests()
//                .anyRequest().authenticated()
//                .and();
//                .csrf().disable()
//                .formLogin()
//                    .loginPage("/user/toLogin")
//                    .and()
//            .addFilter(filter());
        super.configure(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/rest/**","/user/toLogin");
        web.ignoring().antMatchers("/**");
        super.configure(web);
    }

//    @Bean
//    public UsernamePwdAuthenFilter filter() throws Exception {
//        UsernamePwdAuthenFilter filter = new UsernamePwdAuthenFilter();
//        filter.setAuthenticationManager(authenticationManager());
////        filter.setMapper(mapper);
//        return filter;
//    }

//    @Autowired
//    private UserMapper userMapper;

//    @Bean
//    public MyUserDetailsService myUserDetailsService(){
//        return new MyUserDetailsService(userMapper);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(detailsService).passwordEncoder(new BCryptPasswordEncoder());
        super.configure(auth);
    }

    /**
     *  业务上 手动赋予user 权限
     *  PreAuthorize("hasAuthority('" + Role.SALER+ "')")
     */
//    public static void authStore(User u){
//        /* 每次请求这里都要执行 每次都是新的 TODO */
//        /* security 作用于当前线程 */
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        for(String p:u.getRole().getPrivileges()) {
//            authorities.add(new SimpleGrantedAuthority(p));
//        }
//        Authentication req = new UsernamePasswordAuthenticationToken(u.getId(), u.getPwd(),authorities);
//        SecurityContextHolder.getContext().setAuthentication(req);
//    }

}
