package br.com.vibbra.organizerecipes;

import br.com.vibbra.organizerecipes.model.entity.users.UserRole;
import br.com.vibbra.organizerecipes.model.entity.users.Users;
import br.com.vibbra.organizerecipes.model.enums.RoleEnum;
import br.com.vibbra.organizerecipes.service.users.UsersService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class StartApplication {

	@Autowired
	UsersService usersService;

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
        return args -> {
			Users user = Users.builder()
                    .name("Test Users 1")
                    .email("user1@test.com")
                    .password("123456@")
                    .cnpj("75.126.536/0001-57")
                    .companyName("Company Test 1")
                    .phoneNumber("+5531912345678")
                    .build();

			user.setRoles(Arrays.asList(UserRole.builder()
					.role(RoleEnum.ROLE_USER)
					.user(user)
					.build()));

			if (BooleanUtils.isTrue(!usersService.existsUserByEmail(user.getEmail())))
            	usersService.createUser(user);
        };
	}
}
