package com.example.icaro.aplicacaologinmenu;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by icaro on 28/03/2017.
 */

public class CalculatorWS {

    public CalculatorWS()
    {

    }
    public int add(int i, int j) throws IOException, XmlPullParserException
    {
        SoapObject soap = new SoapObject("http://calculator.me.org/", "add");
        soap.addProperty("i", i);
        soap.addProperty("j", j);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        HttpTransportSE httpTrans = new HttpTransportSE("http://192.168.43.146:8080/CalculatorApp/CalculatorWSService?wsdl");

        httpTrans.call("add", envelope);

        Object resultado = envelope.getResponse();

        return Integer.parseInt(resultado.toString());
    }

}
