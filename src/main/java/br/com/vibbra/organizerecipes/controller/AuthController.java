package br.com.vibbra.organizerecipes.controller;

import br.com.vibbra.organizerecipes.base.BaseController;
import br.com.vibbra.organizerecipes.model.request.AuthRequest;
import br.com.vibbra.organizerecipes.model.request.AuthSSORequest;
import br.com.vibbra.organizerecipes.model.response.AuthJwtResponse;
import br.com.vibbra.organizerecipes.service.auth.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {

    @Autowired
    AuthService authService;

    @ApiOperation(value="Perform user authentication")
    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthJwtResponse> auth(@RequestBody AuthRequest login) {
        return new ResponseEntity<>(authService.login(login), HttpStatus.OK);
    }

    @ApiOperation(value="Perform user authentication SSO")
    @PostMapping(path = "/sso", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthJwtResponse> authSSO(@RequestBody AuthSSORequest loginSSO) {
        // TODO: Implementar futuro
        //return new ResponseEntity<>(authService.login(loginSSO), HttpStatus.OK);
        return null;
    }
}