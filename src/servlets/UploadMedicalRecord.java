package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bo.MedicalRecordBO;
import bo.PatientBO;
import domain.Patient;

@MultipartConfig(maxFileSize = 16177215) 
public class UploadMedicalRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadMedicalRecord() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("in upload");
			String patientId = request.getParameter("patientId");
			Patient patient = new PatientBO().getPatientByPatientId(patientId);
			System.out.println("Received patient in upload -- "+patient.getName());
			Part filePart = request.getPart("document");
			System.out.println("Received file in upload -- "+filePart);
			String message = null;
			if (filePart != null) {

	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	            
				message = new MedicalRecordBO().insertRecord(filePart, patient);
				
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	        }
			
            // sets the message in request scope
            request.setAttribute("message", message);
             
            // forwards to the message page
            request.getRequestDispatcher("/uploadRecord.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
