package com.example.demo.domain.model.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(staticName = "of")
@Getter
public class ResponseAdvancedForChatGPT extends GenericResponseForChatGPT{

    @Override
    String testes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testes'");
    }
    
}
