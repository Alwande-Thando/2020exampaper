package za.co.prog6112.q2;

/** English words and their Dutch, French and Italian translations. */
public class WordTranslations {
    private final String[] englishWords = {"sky", "run", "study", "music", "dog"};
    // Each row corresponds to an English word; columns are Dutch, French, Italian.
    private final String[][] translations = {
        {"hemel", "ciel", "cielo"},
        {"rennen", "courir", "correre"},
        {"studie", "etude", "studia"},
        {"muziek", "musique", "musica"},
        {"hond", "chien", "cane"}
    };
    private final String[] languages = {"Dutch", "French", "Italian"};

    public String[] getEnglishWords() { return englishWords.clone(); }

    public String getTranslation(String englishWord, int languageIndex) {
        if (languageIndex < 0 || languageIndex >= languages.length) {
            throw new IllegalArgumentException("Invalid language index.");
        }
        for (int i = 0; i < englishWords.length; i++) {
            if (englishWords[i].equalsIgnoreCase(englishWord)) {
                return translations[i][languageIndex];
            }
        }
        throw new IllegalArgumentException("English word not found: " + englishWord);
    }

    public String createReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("%-12s %-12s %-12s %-12s%n",
                "English", "Dutch", "French", "Italian"));
        for (int i = 0; i < englishWords.length; i++) {
            report.append(String.format("%-12s %-12s %-12s %-12s%n",
                    englishWords[i], translations[i][0], translations[i][1], translations[i][2]));
        }
        return report.toString();
    }

    public static void main(String[] args) {
        System.out.println(new WordTranslations().createReport());
    }
}
