package ac.uk.shef.cc19grp10.dashboard.utils;

import java.text.Normalizer;

public class NameUtils {
    /**
     * Create a normalised, schema safe
     *
     * Replaces non-ascii characters with a close equivalent,
     * and removes non-alphanumeric characters
     */
    public static String nameToSchemaName(String applicationName) {
        //unicode normalise
        return "app_"+ Normalizer.normalize(applicationName, Normalizer.Form.NFD)
                //replace space with dash
                .replaceAll("\\p{Space}","")
                //remove non alphanumeric/dash characters
                .replaceAll("[^\\p{Alnum}]", "")
                //and lowercase it
                .toLowerCase();
    }

    /**
     * Create a normalised, url-safe client id slug.
     *
     * Replaces non-ascii characters with a close equivalent,
     * and removes non-alphanumeric characters
     */
    public static String nameToClientId(String applicationName) {
        //unicode normalise
        return "app_"+Normalizer.normalize(applicationName, Normalizer.Form.NFD)
                //replace space with dash
                .replaceAll("\\p{Space}","_")
                //remove non alphanumeric/dash characters
                .replaceAll("[^\\p{Alnum}_]", "")
                //and lowercase it
                .toLowerCase();
    }

    public static String nameToUrl(String name) {
        return "/" + nameToClientId(name);
    }
}
