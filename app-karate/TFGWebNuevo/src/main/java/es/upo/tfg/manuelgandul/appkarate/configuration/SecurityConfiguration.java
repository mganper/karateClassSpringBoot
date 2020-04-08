package es.upo.tfg.manuelgandul.appkarate.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Esta clase sirve para configurar Spring Secutiry.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("empleadoService")
    private UserDetailsService empleadoService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder oauth) throws Exception{
        oauth.userDetailsService(empleadoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/css/**", "/images/**", "/js/**", "/Roboto/**", "/scss/**", "/vendors/**",
                    "/api/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login")
            .loginProcessingUrl("/loginCheck").usernameParameter("username").passwordParameter("password")
            .defaultSuccessUrl("/loginSuccess",true).permitAll()
            .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();
    }
}
