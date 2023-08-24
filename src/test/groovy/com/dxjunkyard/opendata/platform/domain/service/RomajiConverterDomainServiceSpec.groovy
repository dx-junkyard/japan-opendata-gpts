package com.dxjunkyard.opendata.platform.domain.service

import com.atilika.kuromoji.ipadic.Tokenizer
import com.ibm.icu.text.Transliterator
import spock.lang.Specification

class RomajiConverterDomainServiceSpec extends Specification {

    private final Tokenizer tokenizer = new Tokenizer()

    private final Transliterator katakanaToHiraganaTranslator = Transliterator.getInstance("Katakana-Hiragana")

    private final Transliterator hiraganaToRomajiTranslator = Transliterator.getInstance("Hiragana-Latin")

    def "convertToRomaji"() {
        given:
        def target = new RomajiConverterDomainService(tokenizer, katakanaToHiraganaTranslator, hiraganaToRomajiTranslator)
        when:
        def result = target.convert("教員検定合格者 (大正5-14年度) : 日本帝国統計年鑑 47 (昭和3年) 表218")
        then:
        result == "kyouin kentei goukaku sha   ( taishou 5 - 14 nendo )   :   nippon teikoku toukei nenkan   47   ( shouwa 3 nen )   hyou 218"
    }
}
