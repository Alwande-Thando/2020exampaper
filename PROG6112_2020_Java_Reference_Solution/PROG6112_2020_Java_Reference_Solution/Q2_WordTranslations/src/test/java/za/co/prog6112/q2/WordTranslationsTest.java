package za.co.prog6112.q2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WordTranslationsTest {
    private final WordTranslations words = new WordTranslations();

    @Test void skyInDutchIsHemel() {
        assertEquals("hemel", words.getTranslation("sky", 0));
    }
    @Test void runInFrenchIsCourir() {
        assertEquals("courir", words.getTranslation("run", 1));
    }
    @Test void dogInItalianIsCane() {
        assertEquals("cane", words.getTranslation("dog", 2));
    }
}
