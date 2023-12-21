package com.douglas.develop.radio.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.douglas.develop.radio.domain.PerfilTipo;
import com.douglas.develop.radio.service.UsuarioService;

import lombok.AllArgsConstructor;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UsuarioService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		final String ADMIN = PerfilTipo.ADMIN.getDesc();
		final String MEDICO = PerfilTipo.MEDICO.getDesc();
		final String PACIENTE = PerfilTipo.PACIENTE.getDesc();

        http.authorizeRequests(requests -> requests
                .antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
                .antMatchers("/", "/home", "/expired").permitAll()
                .antMatchers("/u/novo/cadastro", "/u/cadastro/realizado", "/u/cadastro/paciente/salvar").permitAll()
                .antMatchers("/u/confirmacao/cadastro").permitAll()
                .antMatchers("/u/p/**").permitAll()

                // acessos privados admin
                .antMatchers("/u/editar/senha", "/u/confirmar/senha").hasAnyAuthority(PACIENTE, MEDICO)
                .antMatchers("/u/**").hasAuthority(ADMIN)

                // acessos privados medicos
                .antMatchers("/medicos/especialidade/titulo/*").hasAnyAuthority(PACIENTE, MEDICO)
                .antMatchers("/medicos/dados", "/medicos/salvar", "/medicos/editar").hasAnyAuthority(MEDICO, ADMIN)
                .antMatchers("/medicos/**").hasAuthority(MEDICO)

                // acessos privados pacientes
                .antMatchers("/pacientes/**").hasAuthority(PACIENTE)

                // acessos privados especialidades
                .antMatchers("/especialidades/datatables/server/medico/*").hasAnyAuthority(MEDICO, ADMIN)
                .antMatchers("/especialidades/titulo").hasAnyAuthority(MEDICO, ADMIN, PACIENTE)
                .antMatchers("/especialidades/**").hasAuthority(ADMIN)

                // acessos privados albuns
                .antMatchers("/albuns/datatables/server/medico/*").hasAnyAuthority(MEDICO, ADMIN)
                .antMatchers("/albuns/titulo").hasAnyAuthority(MEDICO, ADMIN, PACIENTE)
                .antMatchers("/albuns/**").hasAuthority(ADMIN)

                .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login-error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/acesso-negado"))
                .rememberMe(withDefaults());

        http.sessionManagement(management -> management
                .maximumSessions(1)
                .expiredUrl("/expired")
                .maxSessionsPreventsLogin(false) // mudar para true para somente um dispositivo
                .sessionRegistry(sessionRegistry()));

        //comentar este bloco para impedir logim em dois dispositivos
        http.sessionManagement(management -> management
                .sessionFixation()
                .newSession()
                .sessionAuthenticationStrategy(sessionAuthtrategy()));
		//--------------------------------------------------------------
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//comentar este bloco para impedir logim em dois dispositivos
	@Bean
	public SessionAuthenticationStrategy sessionAuthtrategy() {
		return new RegisterSessionAuthenticationStrategy(sessionRegistry());
	}
	//-----------------------------------------------------------
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
	@Bean
	public ServletListenerRegistrationBean<?> servletListenerRegistrationBean() {
		return new ServletListenerRegistrationBean<>( new HttpSessionEventPublisher() );
	}

}
