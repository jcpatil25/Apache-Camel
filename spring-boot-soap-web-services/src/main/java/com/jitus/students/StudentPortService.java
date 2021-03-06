package com.jitus.students;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.6
 * 2021-11-18T21:32:33.847+05:30
 * Generated source version: 3.3.6
 *
 */
@WebServiceClient(name = "StudentPortService",
                  wsdlLocation = "file:/C:/Users/jitendra.sonawane/git/apache-camel-cxf-example/Apache-Camel/apache-camel-client/src/main/resources/wsdl/students.wsdl",
                  targetNamespace = "http://jitus.com/students")
public class StudentPortService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://jitus.com/students", "StudentPortService");
    public final static QName StudentPortSoap11 = new QName("http://jitus.com/students", "StudentPortSoap11");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/jitendra.sonawane/git/apache-camel-cxf-example/Apache-Camel/apache-camel-client/src/main/resources/wsdl/students.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(StudentPortService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/jitendra.sonawane/git/apache-camel-cxf-example/Apache-Camel/apache-camel-client/src/main/resources/wsdl/students.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public StudentPortService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public StudentPortService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public StudentPortService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public StudentPortService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public StudentPortService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public StudentPortService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns StudentPort
     */
    @WebEndpoint(name = "StudentPortSoap11")
    public StudentPort getStudentPortSoap11() {
        return super.getPort(StudentPortSoap11, StudentPort.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns StudentPort
     */
    @WebEndpoint(name = "StudentPortSoap11")
    public StudentPort getStudentPortSoap11(WebServiceFeature... features) {
        return super.getPort(StudentPortSoap11, StudentPort.class, features);
    }

}
