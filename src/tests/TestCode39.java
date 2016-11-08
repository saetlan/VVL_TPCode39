/**
 * Created by arnoul on 03/11/16.
 */
import junit.framework.*;
import model.Code39;

public class TestCode39 extends TestCase{
    public void test_code39_encoder_authorizedCharacters() throws Exception {
        assertEquals("**", Code39.encoder(""));
        assertEquals("*ABCDEFGHIJKLMNOPQRSTUVWXYZ*", Code39.encoder("abcdefghijklmnopqrstuvwxyz"));
        assertEquals("*ABCDEFGHIJKLMNOPQRSTUVWXYZ*", Code39.encoder("*ABCDEFGHIJKLMNOPQRSTUVWXYZ*"));
        assertEquals("*0123456789*", Code39.encoder("0123456789"));
        assertEquals("*A0B1C2D3E4F5G6H7I8J9*", Code39.encoder("a0b1c2d3e4f5g6h7i8j9"));
    }

    public void test_code39_encoder_unauthorizedCharacters() throws Exception {
        assertEquals("**", Code39.encoder("âêûôîŷ"));
        assertEquals("**", Code39.encoder("éèàù"));
        assertEquals("**", Code39.encoder("äëüïöÿ"));
        assertEquals("**", Code39.encoder("ßæœð"));
        assertEquals("**", Code39.encoder("ç"));
        assertEquals("**", Code39.encoder("ÆÂÊÞŸÛÎŒÔÖĿÏÜÐËÄ"));
    }

    public void test_code39_encoder_authorizedSymbol() throws Exception {
        assertEquals("* *", Code39.encoder(" "));
        assertEquals("*-*", Code39.encoder("-"));
        assertEquals("*$*", Code39.encoder("$"));
        assertEquals("*%*", Code39.encoder("%"));
        assertEquals("*.*", Code39.encoder("."));
        assertEquals("*/*", Code39.encoder("/"));
        assertEquals("*+*", Code39.encoder("+"));
        assertEquals("* -$%./+*", Code39.encoder(" -$%./+"));
    }

    public void test_code39_encoder_unauthorizedSymbol() throws Exception {
        assertEquals("**", Code39.encoder("£€¥"));
        assertEquals("**", Code39.encoder("=*^÷×<>"));
        assertEquals("**", Code39.encoder("`\"\\'"));
        assertEquals("**", Code39.encoder("{}[]()|_"));
        assertEquals("**", Code39.encoder("~&ø§¡¿°"));
        assertEquals("**", Code39.encoder("þŀ«»©®¬¢…"));
        assertEquals("**", Code39.encoder("↕↙↓↘←↔→↗↑↖"));
        assertEquals("**", Code39.encoder("⇕⇙⇓⇘⇐⇔⇒⇖⇑⇗"));
    }

    public void test_code39_encoder_combination() throws Exception {
        assertEquals("*ABC012$%./+*", Code39.encoder("abc012ùçà$%./+"));
        assertEquals("*JAMBON FRAIS 42$*", Code39.encoder("jambon frais 42$"));
        assertEquals("*JAMBON FRAIS 42$*", Code39.encoder("JAMBON FRAIS 42$"));
        assertEquals("*JAMBON FRAIS 42*", Code39.encoder("JAMBON FRAIS 42€"));
        assertEquals("*SANDWITCH+JAMBON12+13-REDUCTION*", Code39.encoder("***sandwitch+jambon=12+13-reduction***"));
    }

    public void test_modulo43_encoderExtended_authorizedCharacters() throws Exception {
        assertEquals("*0*", Code39.encoderExtended(""));
        assertEquals("*ABCDEFGHIJKLMNOPQRSTUVWXYZQ*", Code39.encoderExtended("abcdefghijklmnopqrstuvwxyz"));
        assertEquals("*ABCDEFGHIJKLMNOPQRSTUVWXYZQ*", Code39.encoderExtended("*ABCDEFGHIJKLMNOPQRSTUVWXYZ*"));
        assertEquals("*01234567892*", Code39.encoderExtended("0123456789"));
        assertEquals("*A0B1C2D3E4F5G6H7I8J9I*", Code39.encoderExtended("a0b1c2d3e4f5g6h7i8j9"));
    }

    public void test_modulo43_encoderExtended_unauthorizedCharacters() throws Exception {
        assertEquals("*0*", Code39.encoderExtended("âêûôîŷ"));
        assertEquals("*0*", Code39.encoderExtended("éèàù"));
        assertEquals("*0*", Code39.encoderExtended("äëüïöÿ"));
        assertEquals("*0*", Code39.encoderExtended("ßæœð"));
        assertEquals("*0*", Code39.encoderExtended("ç"));
        assertEquals("*0*", Code39.encoderExtended("ÆÂÊÞŸÛÎŒÔÖĿÏÜÐËÄ"));
    }

    public void test_modulo43_encoderExtended_authorizedSymbol() throws Exception {
        assertEquals("*  *", Code39.encoderExtended(" "));
        assertEquals("*--*", Code39.encoderExtended("-"));
        assertEquals("*$$*", Code39.encoderExtended("$"));
        assertEquals("*%%*", Code39.encoderExtended("%"));
        assertEquals("*..*", Code39.encoderExtended("."));
        assertEquals("*//*", Code39.encoderExtended("/"));
        assertEquals("*++*", Code39.encoderExtended("+"));
        assertEquals("* -$%./+F*", Code39.encoderExtended(" -$%./+"));
    }

    public void test_modulo43_encoderExtended_unauthorizedSymbol() throws Exception {
        assertEquals("*0*", Code39.encoderExtended("£€¥"));
        assertEquals("*0*", Code39.encoderExtended("=*^÷×<>"));
        assertEquals("*0*", Code39.encoderExtended("`\"\\'"));
        assertEquals("*0*", Code39.encoderExtended("{}[]()|_"));
        assertEquals("*0*", Code39.encoderExtended("~&ø§¡¿°"));
        assertEquals("*0*", Code39.encoderExtended("þŀ«»©®¬¢…"));
        assertEquals("*0*", Code39.encoderExtended("↕↙↓↘←↔→↗↑↖"));
        assertEquals("*0*", Code39.encoderExtended("⇕⇙⇓⇘⇐⇔⇒⇖⇑⇗"));
    }

    public void test_modulo43_encoderExtended_combination() throws Exception {
        assertEquals("*ABC012$%./+K*", Code39.encoderExtended("abc012ùçà$%./+"));
        assertEquals("*JAMBON FRAIS 42$R*", Code39.encoderExtended("jambon frais 42$"));
        assertEquals("*JAMBON FRAIS 42$R*", Code39.encoderExtended("JAMBON FRAIS 42$"));
        assertEquals("*JAMBON FRAIS 42V*", Code39.encoderExtended("JAMBON FRAIS 42€"));
        assertEquals("*SANDWITCH+JAMBON12+13-REDUCTION4*", Code39.encoderExtended("***sandwitch+jambon=12+13-reduction***"));
    }
}