package com.fps.ribosome;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

import static com.fps.ribosome.Ribosome.CodonsAndDNAStringStruct;
import static org.junit.Assert.assertEquals;

/**
 * Created by fperezsorrosal on 7/10/16.
 */
@RunWith(Parameterized.class)
public class RibosomeTest {

    private String inputFile;
    private String expectedProteins;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "dnastring1.txt", "MVHLTLI\nMRAMALRII" },
                { "dnastring2.txt", "MHWARYPAFVPFSEG\n" +
                                    "MVIAVSCVKLLSAHNSTQHTSRKHKV\n" +
                                    "MSELTHINCVALTARFPVGKPVVPAALMNRPTRGERRFAYWALFRFLAH\n" +
                                    "M\n" +
                                    "M\n" +
                                    "MRLSKRIFT\n" +
                                    "MKF\n" +
                                    "MSKLGLTVTNA\n" +
                                    "MIPRDPRSPAPDLSAINQPAGRAERRSGPATLSASIQSINCCREARVSSSPVNSLRNVVAIATGIVVSRSSFGMASFSSGSQRSRRVT\n" +
                                    "MLCKKAVSSFGPPIVVRSKLAAVLSLMVMAALHNSLTVMPSVRCFSVTGEYSTKSF\n" +
                                    "MRRPSCSCPASIRDNTAPHSRTLKVLIIGKRSSGRKLSRILPLLRSSSM\n" +
                                    "MPQKRE\n" +
                                    "MLNTHTLPFSILLKHLSGLLSHERIHI\n" +
                                    "MYLEK\n" +
                                    "MAHYVNHHPNQVFWGRGAVKH\n" +
                                    "MRRYRARPIRHSGCATVGKGDRCGPLRYYASRP\n" +
                                    "MAYMDSCQFNMVDLDLCQFNMAYMDSCQFNMVDLDPSQFNMADLAPCQFNMADLALCQLGRGLLGTVPSLRRGLGPVPSPPY\n" +
                                    "MVPIMAAILAICQDQYIGNIQYGPMPIWLLARFNTMYWPYAI\n" +
                                    "MGFPIDVDSPSQWAVPYTIYGAS\n" +
                                    "MVSIYGLSY\n" +
                                    "MGGPIDVYGASPIDVNYGKWPAWLNAH\n" +
                                    "MGWLIAHSYPFSRPLLTSMTVNGPLGSTSISINSNLASTLLLEGRQGTLAVLPLTSMAVNGPRWLPSTSPLTSMGRGNDANGRSIDVNGR\n" +
                                    "MGGLYKQCSFREPPFCLGTSEQA\n" +
                                    "MPEPAKSAPAPKKGSKKAVTKAQKKGGKKRKRSRKESYSIYVYKVLKQVHPDTGISSKAMGIMNSFVNDIFERIAGEASRLAHYNKRSTITSREIQTAVRLLLPGELAKHAVSEGTKAITKYTSAKDPPVATMVSKGEELFTGVVPILVELDGDVNGHKFSVSGEGEGDATYGKLTLKFICTTGKLPVPWPTLVTTLTYGVQCFSRYPDHMKQHDFFKSAMPEGYVQERTIFFKDDGNYKTRAEVKFEGDTLVNRIELKGIDFKEDGNILGHKLEYNYNSHNVYIMADKQKNGIKVNFKIRHNIEDGSVQLADHYQQNTPIGDGPVLLPDNHYLSTQSALSKDPNEKRDHMVLLEFVTAAGITLGMDELYK\n" +
                                    "MIRYIDEFGQTTTRMQ\n" +
                                    "MLYL\n" +
                                    "MLLLYL\n" +
                                    "MFQVQGEVWEVF" }
        });
    }

    public RibosomeTest(String inputFile, String expectedProteins) {

        this.inputFile = inputFile;
        this.expectedProteins = expectedProteins;

    }

    @Test
    public void test() throws IOException {

        Ribosome ribosome = new Ribosome();
        CodonsAndDNAStringStruct inputStruct = ribosome.parseInputFile(Paths.get(inputFile));
        assertEquals(expectedProteins, ribosome.translateToProteins(inputStruct.dnaString, inputStruct.codonToProteinMap));

    }

}