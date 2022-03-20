package com.brasatech.shelldemo;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.Availability;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
public class ShellDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShellDemoApplication.class, args);
	}

}

@ShellComponent
class LoginService {

	private final AtomicReference<String> user = new AtomicReference<>();

	void login(String username, String password) {
		this.user.set(username);
	}

	void logout() {
		this.user.set(null);
	}

	boolean isLoggedIn() {
		return this.user.get() != null;
	}

	String loggedInUser(){
		return this.user.get();
	}
}
@ShellComponent
record  LoginCommands(LoginService service){

	@ShellMethod("login")
	public void login(String username, String password){
		this.service.login(username,password);
	}


	@ShellMethod("logout")
	@ShellMethodAvailability("logoutAvailability")
	public void logout(){
		this.service.logout();
	}

	public Availability logoutAvailability(){
		return this.service.isLoggedIn() ? Availability.available() : Availability.unavailable("you must be logged in");
	}


}

@ShellComponent
record LoginPromptProvider(LoginService loginService) implements PromptProvider {

	@Override
	public AttributedString getPrompt() {
		return this.loginService.isLoggedIn()?
				new AttributedString(loginService.loggedInUser() + ">", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN)):
				new AttributedString("shell>", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
	}

}