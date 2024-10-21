package com.example.demo.domain.model.responses;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(staticName = "of")
@Getter
public class ResponseSimpleForChatGPT extends GenericResponseForChatGPT {
    
}
