package br.com.vibbra.organizerecipes.service.auth;

import br.com.vibbra.organizerecipes.base.BaseService;
import br.com.vibbra.organizerecipes.model.entity.users.Users;
import br.com.vibbra.organizerecipes.model.request.AuthRequest;
import br.com.vibbra.organizerecipes.model.response.AuthJwtResponse;
import br.com.vibbra.organizerecipes.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService extends BaseService {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	ModelMapper modelMapper;

	public AuthJwtResponse login(AuthRequest login) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword())
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);

		return AuthJwtResponse.builder()
				.token(jwt)
				.user(modelMapper.map(authentication.getPrincipal(), Users.class))
				.build();
	}
}