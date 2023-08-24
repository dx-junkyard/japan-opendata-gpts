package com.dxjunkyard.opendata.platform.domain.service;

import com.atilika.kuromoji.ipadic.Tokenizer;
import com.ibm.icu.text.Transliterator;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RomajiConverterDomainService {

    private final Tokenizer tokenizer;

    private final Transliterator katakanaToHiraganaTranslator;

    private final Transliterator hiraganaToRomajiTranslator;

    @NonNull
    public String convert(final String text) {
        return tokenizer.tokenize(text).stream()
            .map(token -> Objects.nonNull(token.getReading()) ? token.getReading() : token.getSurface())
            .map(katakanaToHiraganaTranslator::transliterate)
            .map(hiraganaToRomajiTranslator::transliterate)
            .collect(Collectors.joining(" "));
    }

}
