package com.dxjunkyard.opendata.platform.presentation.controller;

import com.dxjunkyard.opendata.platform.config.AiPluginConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * マニフェストファイル用のコントローラー
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/.well-known")
public class AiPluginController {

    private final AiPluginConfig aiPluginConfig;

    /**
     * ai-plugin.jsonを返却する
     *
     * @return Mono<AiPluginConfig>
     */
    @GetMapping("/ai-plugin.json")
    public Mono<AiPluginConfig> getAiPluginConfig() {
        return Mono.just(aiPluginConfig);
    }
}
