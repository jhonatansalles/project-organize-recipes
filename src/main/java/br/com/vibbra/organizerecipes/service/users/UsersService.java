package br.com.vibbra.organizerecipes.service.users;

import br.com.vibbra.organizerecipes.base.BaseService;
import br.com.vibbra.organizerecipes.exception.NotFoundException;
import br.com.vibbra.organizerecipes.exception.ValidationException;
import br.com.vibbra.organizerecipes.model.entity.users.UserRole;
import br.com.vibbra.organizerecipes.model.entity.users.Users;
import br.com.vibbra.organizerecipes.model.enums.RoleEnum;
import br.com.vibbra.organizerecipes.model.response.UserResponse;
import br.com.vibbra.organizerecipes.repository.users.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UsersService extends BaseService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsersRepository usersRepository;

    public UserResponse getUser(Long id) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        return UserResponse.builder()
                .user(optionalUser.orElseThrow(() -> new NotFoundException("Users not found with -> ID: " + id)))
                .build();
    }

    public Boolean existsUserByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    public Users createUser(Users user) {

        this.validateRequiredFields(this.returnMapRequiredFields(user));

        if (BooleanUtils.isTrue(usersRepository.existsByEmail(user.getEmail()))) {
            throw new ValidationException("There is already a user with -> EMAIL: " + user.getEmail());
        }

        user.setRoles(Arrays.asList(UserRole.builder().role(RoleEnum.ROLE_USER).user(user).build()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserLastchange("API_CREATE_USER");

        Users savedUser = usersRepository.save(user);

        return Users.builder()
                .id(savedUser.getId())
                .build();
    }

    public void updateUser(Long id, Users user) {

        this.validateRequiredFields(this.returnMapRequiredFields(user));

        Users userBD = usersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with -> ID: " + id));

        user.setId(id);
        user.setVersion(userBD.getVersion());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userBD.getRoles());
        user.setUserLastchange("API_UPDATE_USER");

        usersRepository.save(user);
    }

    private Map<String, Optional<?>> returnMapRequiredFields(Users object) {
        return Map.of(
                "Name", Optional.ofNullable(object).map(Users::getName),
                "Email", Optional.ofNullable(object).map(Users::getEmail),
                "Password", Optional.ofNullable(object).map(Users::getPassword),
                "Cnpj", Optional.ofNullable(object).map(Users::getCnpj),
                "Company Name", Optional.ofNullable(object).map(Users::getCompanyName),
                "Phone Number", Optional.ofNullable(object).map(Users::getPhoneNumber)
        );
    }
}
