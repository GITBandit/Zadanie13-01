import java.io.PrintWriter;

public class ConverterOutput {


    public String printData(double metersConverted, double centimetersConverted, double millimetersConverted, double kilogramsConverted, double gramsConverted, double milligramsConverted, int count){


            return "<HTML><H2>Podana wartość w przeliczeniu na:</H2>" +
                    "<P>Metry: " + metersConverted  + "</P>" +
                    "<P>Centymetry: " + centimetersConverted  + "</P>" +
                    "<P>Milimetry: " + millimetersConverted  + "</P><BR>" +
                    "<P>Kilogramy: " + kilogramsConverted  + "</P>" +
                    "<P>Gramy: " + gramsConverted  + "</P>" +
                    "<P>Miligramy: " + milligramsConverted  + "</P>"
            ;
    }
    public String printToManyNumbersError(){
        return "<HTML><H3>Wprowadź tylko jedną liczbę!</H3>";
    }
}
