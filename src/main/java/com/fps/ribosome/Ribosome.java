package com.fps.ribosome;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by fperezsorrosal on 7/10/16.
 */
public class Ribosome {

    private static final String START_CODON = "ATG";
    private static final Set<String> END_CODONS = new HashSet<>(Arrays.asList("TAA", "TAG", "TGA"));

    public static void main(String[] args) throws IOException {

        final Ribosome ribosome = new Ribosome();
        CodonsAndDNAStringStruct inputStruct  = ribosome.parseInputFile(Paths.get(args[0]));
        System.out.println("DNA String to decipher: " + inputStruct.dnaString);
        System.out.println(ribosome.translateToProteins(inputStruct.dnaString, inputStruct.codonToProteinMap));

    }

    CodonsAndDNAStringStruct parseInputFile(Path pathToFile) throws IOException {

        Map<String, String> codonToProteinMap = new HashMap<>();
        Optional<String> dnaString = Optional.empty();

        try (Scanner scanner = new Scanner(pathToFile)) {
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                String[] tokens = scanner.next().split(" ");
                if(tokens.length != 3) {
                    if (tokens.length == 2 && "DNA".equals(tokens[1]) && scanner.hasNext()) {
                        dnaString = dnaString.of(scanner.next());
                        break;
                    }
                    continue;
                }
                codonToProteinMap.put(tokens[0], tokens[1]);
            }
        }

        if (!dnaString.isPresent()) {
            throw new IllegalStateException("Can't parse input file. DNA string not found");
        }

        return new CodonsAndDNAStringStruct(codonToProteinMap, dnaString.get());

    }

    String translateToProteins(String dna, Map<String, String> codonToProteinMap) {

        String result = "";

        int idx = dna.indexOf(START_CODON, 0);
        while (idx < dna.length() && idx != -1) {
            String dnaChunk = dna.substring(idx, idx + 3);
            if (END_CODONS.contains(dnaChunk)) {
                idx = dna.indexOf(START_CODON, idx);
                result += "\n";
            } else {
                result += codonToProteinMap.get(dnaChunk);
                idx += 3;
            }
        }

        return result.trim();

    }

    static class CodonsAndDNAStringStruct {

        Map<String, String> codonToProteinMap = new HashMap<>();
        String dnaString;

        public CodonsAndDNAStringStruct(Map<String, String> codonToProteinMap, String dnaString) {

            this.codonToProteinMap = codonToProteinMap;
            this.dnaString = dnaString;

        }

    }

}
