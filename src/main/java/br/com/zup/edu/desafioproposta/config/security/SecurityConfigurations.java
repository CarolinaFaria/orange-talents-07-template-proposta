package br.com.zup.edu.desafioproposta.config.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;


@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    //    @Override
    //@Bean(AUTHENTICATION_MANAGER)
    //public AuthenticationManager authenticationManagerBean() throws Exception {
    //    return super.authenticationManagerBean();
    //}

    //Configuracoes de autenticacao
    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //   auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    //}

    //Configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.POST, "/propostas").hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_escopo-proposta")
                .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    //Configuracoes de recursos estaticos(js, css, imagens, etc.)
 //   @Override
 //   public void configure(WebSecurity web) throws Exception {
 //       web.ignoring().antMatchers("/**.html",  "/v2/api-docs", "/webjars/**",
 //               "/configuration/**", "/swagger-resources/**", "/css/**", "/**.ico", "/js/**" );
 //   }

}


