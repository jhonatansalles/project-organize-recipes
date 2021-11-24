package br.com.vibbra.organizerecipes.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthSSORequest {

    private String login;

    @JsonProperty("app_token")
    private String appToken;
}