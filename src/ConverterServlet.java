import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/converter")
public class ConverterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Converter converter = new Converter();

        //meters module

        double meters;
        double centimeters;
        double millimeters;


        try {
            meters = Double.valueOf(request.getParameter("meters"));
        } catch (NumberFormatException e){
            meters = 0;
        }

        try {
            centimeters = Double.valueOf(request.getParameter("centimeters"));
        } catch (NumberFormatException e){
            centimeters = 0;
        }

        try {
            millimeters = Double.valueOf(request.getParameter("millimeters"));
        } catch (NumberFormatException e){
            millimeters = 0;
        }

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        int count = 0;
        double metersConverted = meters;
        double millimetersConverted = 0;
        double centimetersConverted = 0;


        if (meters != 0){
            metersConverted = meters;
            millimetersConverted = converter.metersToMilimeters(meters);
            centimetersConverted = converter.metersToCentimeters(meters);
            count++;
        }
        if (centimeters != 0){
            centimetersConverted = centimeters;
            millimetersConverted = converter.centimetersToMilimeters(centimeters);
            metersConverted = converter.centimetersToMeters(centimeters);
            count++;
        }
        if (millimeters != 0){
            millimetersConverted = millimeters;
            centimetersConverted = converter.millimetersToCentimeters(millimeters);
            metersConverted = converter.millimetersToMeters(millimeters);
            count++;
        }


        // kilograms module

        double kilograms;
        double grams;
        double milligrams;


        try {
            kilograms = Double.valueOf(request.getParameter("kilograms"));
        } catch (NumberFormatException e){
            kilograms = 0;
        }

        try {
            grams = Double.valueOf(request.getParameter("grams"));
        } catch (NumberFormatException e){
            grams = 0;
        }

        try {
            milligrams = Double.valueOf(request.getParameter("milligrams"));
        } catch (NumberFormatException e){
            milligrams = 0;
        }


        double kilogramsConverted = meters;
        double gramsConverted = 0;
        double milligramsConverted = 0;


        if (kilograms != 0){
            kilogramsConverted = kilograms;
            gramsConverted = converter.kilogramsToGrams(kilograms);
            milligramsConverted = converter.kilogramsToMilligrams(kilograms);
            count++;
        }
        if (grams != 0){
            gramsConverted = grams;
            milligramsConverted = converter.gramsToMilligrams(grams);
            kilogramsConverted = converter.gramsToKilograms(grams);
            count++;
        }
        if (milligrams != 0){
            milligramsConverted = milligrams;
            gramsConverted = converter.milligramsToGrams(milligrams);
            kilogramsConverted = converter.milligramsToKilograms(milligrams);
            count++;
        }

        ConverterOutput converterOutput = new ConverterOutput();

        if(count < 2){
            writer.println(converterOutput.printData(metersConverted,centimetersConverted,millimetersConverted,kilogramsConverted,gramsConverted,milligramsConverted,count));


        } else {
            writer.println(converterOutput.printToManyNumbersError());
        }


    }
}
