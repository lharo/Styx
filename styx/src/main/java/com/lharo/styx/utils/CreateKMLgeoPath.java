package com.lharo.styx.utils;

import java.io.*;

public class CreateKMLgeoPath {

    public void writeFile(String datapath, String name,double OrigenLAT, double OrigenLON, double DestinoLAT,double DestinoLON){

        File testexists = new File(datapath+"/"+name+".kml");
        Writer fwriter;

        String salida = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\"\n" +
                "  xmlns:gx=\"http://www.google.com/kml/ext/2.2\">\n" +
                "  \n" +
                "  <gx:Tour>\n" +
                "    <name>Bounce tour example</name>\n" +
                "    \n" +
                "    <gx:Playlist>\n" +
                "      \n" +
                "      <gx:FlyTo>\n" +
                "        <gx:duration>4.0</gx:duration>\n" +
                "        <!-- bounce is the default flyToMode -->\n" +
                "        <LookAt>\n" +
                "          <longitude>"+OrigenLAT+"</longitude>\n" +
                "          <latitude>"+OrigenLON+"</latitude>\n" +
                "          <altitude>0</altitude>\n" +
                "          <heading>-27.92338743600616</heading>\n" +
                "          <tilt>71.60007457352123</tilt>\n" +
                "          <range>22570.5468007044</range>\n" +
                "          <altitudeMode>relativeToGround</altitudeMode>\n" +
                "        </LookAt>\n" +
                "      </gx:FlyTo>\n" +
                "<gx:Wait>\n" +
                "    <gx:duration>10.0</gx:duration>   <!-- wait time in seconds -->\n" +
                "</gx:Wait>"+
                "      \n" +
                "      <gx:FlyTo>\n" +
                "        <gx:duration>4.0</gx:duration>\n" +
                "        <LookAt>\n" +
                "          <longitude>"+DestinoLAT+"</longitude>\n" +
                "          <latitude>"+DestinoLON+"</latitude>\n" +
                "          <altitude>0</altitude>\n" +
                "          <heading>-71.95152128859215</heading>\n" +
                "          <tilt>68.69127809807833</tilt>\n" +
                "          <range>14448.99871318129</range>\n" +
                "          <altitudeMode>relativeToGround</altitudeMode>\n" +
                "        </LookAt>\n" +
                "      </gx:FlyTo>\n" +
                "</gx:Playlist>"+
                "  </gx:Tour>\n" +
                "</kml>";


        if(!testexists.exists()){
            try {
                fwriter = new FileWriter(datapath+"/"+name+".kml");
                fwriter.write(salida);
                fwriter.flush();
                fwriter.close();
            }catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }


    public void writeHotelMap(String datapath, String name,double OrigenLAT, double OrigenLON, String msg) {

        File testexists = new File(datapath + "/" + name + ".kml");
        Writer fwriter;

        String salida = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\"\n" +
                "  xmlns:gx=\"http://www.google.com/kml/ext/2.2\">\n" +
                "  \n" +
                "  <gx:Tour>\n" +
                "    <name>Bounce tour example</name>\n" +
                "    \n" +
                "    <gx:Playlist>\n" +
                "      \n" +
                "      <gx:FlyTo>\n" +
                "        <gx:duration>4.0</gx:duration>\n" +
                "        <!-- bounce is the default flyToMode -->\n" +
                "        <LookAt>\n" +
                "          <longitude>" + OrigenLAT + "</longitude>\n" +
                "          <latitude>" + OrigenLON + "</latitude>\n" +
                "          <altitude>0</altitude>\n" +
                "          <heading>-27.92338743600616</heading>\n" +
                "          <tilt>71.60007457352123</tilt>\n" +
                "          <range>22570.5468007044</range>\n" +
                "          <altitudeMode>relativeToGround</altitudeMode>\n" +
                "        </LookAt>\n" +
                "      </gx:FlyTo>\n" +
                "<gx:AnimatedUpdate>\n" +
                "          <Update>\n" +
                "            <targetHref/>\n" +
                "            <Change>\n" +
                "              <Placemark targetId=\"hotelInfo\">\n" +
                "                <gx:balloonVisibility>1</gx:balloonVisibility>\n" +
                "              </Placemark>\n" +
                "            </Change>\n" +
                "          </Update>\n" +
                "        </gx:AnimatedUpdate>" +
                "<gx:Wait>\n" +
                "    <gx:duration>10.0</gx:duration>   <!-- wait time in seconds -->\n" +
                "</gx:Wait>" +
                "<Placemark id=\"hotelInfo\">\n" +
                "      <name>Datos del hotel</name>\n" +
                "      <description>\n" + msg +
                "      </description>\n" +
                "      <Point>\n" +
                "        <gx:altitudeMode>relativeToGround</gx:altitudeMode>\n" +
                "        <coordinates>"+OrigenLAT+","+OrigenLON+"</coordinates>\n" +
                "      </Point>\n" +
                "    </Placemark>" +
                "</gx:Playlist>"+
                "  </gx:Tour>\n" +
                "</kml>";


        if (!testexists.exists()) {
            try {
                fwriter = new FileWriter(datapath + "/" + name + ".kml");
                fwriter.write(salida);
                fwriter.flush();
                fwriter.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

}
