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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

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
        double metersConverted;
        double millimetersConverted;
        double centimetersConverted;

        double[] convertedMeasurements = sendMeasurmentToConverted(meters, centimeters, millimeters);

        metersConverted = convertedMeasurements[0];
        centimetersConverted = convertedMeasurements[1];
        millimetersConverted = convertedMeasurements[2];
        count += (int)convertedMeasurements[3];


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


        double kilogramsConverted;
        double gramsConverted;
        double milligramsConverted;

        double[] weightsConverted = sendWeightsToConverter(kilograms, grams, milligrams);

        kilogramsConverted = weightsConverted[0];
        gramsConverted = weightsConverted[1];
        milligramsConverted = weightsConverted[2];

        count += (int)weightsConverted[3];


        // output

        ConverterOutput converterOutput = new ConverterOutput();

        if(count < 2){
            writer.println(converterOutput.printData(metersConverted,centimetersConverted,millimetersConverted,kilogramsConverted,gramsConverted,milligramsConverted,count));


        } else {
            writer.println(converterOutput.printToManyNumbersError());
        }


    }


    private double[] sendMeasurmentToConverted(double meters, double centimeters, double millimeters){
        double[] result = new double[4];
        int count = 0;
        Converter converter = new Converter();




        if (meters != 0){
            result[0] = meters;
            result[2] = converter.metersToMilimeters(meters);
            result[1] = converter.metersToCentimeters(meters);
            count++;
        }
        if (centimeters != 0){
            result[1] = centimeters;
            result[2] = converter.centimetersToMilimeters(centimeters);
            result[0] = converter.centimetersToMeters(centimeters);
            count++;
        }
        if (millimeters != 0){
            result[2] = millimeters;
            result[1] = converter.millimetersToCentimeters(millimeters);
            result[0] = converter.millimetersToMeters(millimeters);
            count++;
        }

        result[3] = count;

        return result;

    }

    private double[] sendWeightsToConverter(double kilograms, double grams, double milligrams){
        double[] result = new double[4];
        int count = 0;
        Converter converter = new Converter();

        if (kilograms != 0){
            result[0] = kilograms;
            result[1] = converter.kilogramsToGrams(kilograms);
            result[2] = converter.kilogramsToMilligrams(kilograms);
            count++;
        }
        if (grams != 0){
            result[1] = grams;
            result[2] = converter.gramsToMilligrams(grams);
            result[0] = converter.gramsToKilograms(grams);
            count++;
        }
        if (milligrams != 0){
            result[2] = milligrams;
            result[1] = converter.milligramsToGrams(milligrams);
            result[0] = converter.milligramsToKilograms(milligrams);
            count++;
        }

        result[3] = count;

        return result;
    }

}
