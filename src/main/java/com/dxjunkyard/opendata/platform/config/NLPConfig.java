package com.dxjunkyard.opendata.platform.config;

import com.atilika.kuromoji.ipadic.Tokenizer;
import com.ibm.icu.text.Transliterator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NLPConfig {

    @Bean
    Tokenizer tokenizer() {
        return new Tokenizer();
    }

    @Bean
    Transliterator katakanaToHiraganaTranslator() {
        return Transliterator.getInstance("Katakana-Hiragana");
    }

    @Bean
    Transliterator hiraganaToRomajiTranslator() {
        return Transliterator.getInstance("Hiragana-Latin");
    }
}
