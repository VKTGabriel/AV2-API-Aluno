package br.dev.vktgabriel.av2.API_Aluno.api.controllers;

import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestTokenDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("token")
@RequiredArgsConstructor
@Tag(name = "Security")
public class SecuriryController {

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> token(@RequestBody RequestTokenDTO dados) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", dados.username());
        map.add("password", dados.password());
        map.add("client_id", dados.clientID());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForEntity("http://keycloak:8080/realms/AV2/protocol/openid-connect/token", request, String.class);
    }
}
