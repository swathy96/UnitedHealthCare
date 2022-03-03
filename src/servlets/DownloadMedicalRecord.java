package servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.MedicalRecordBO;
import domain.MedicalRecord;

@MultipartConfig(maxFileSize = 16177215) 
public class DownloadMedicalRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DownloadMedicalRecord() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("in download servlet");
			
			int recordId = Integer.parseInt(request.getParameter("recordId"));
			MedicalRecord medicalRecord = new MedicalRecordBO().getMedicalRecordById(recordId);
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=document"+medicalRecord.getUploadDate().getTime()+".txt");
				
			System.out.println("record length "+medicalRecord.getDocument().length);
			
			
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(medicalRecord.getDocument(), 0, medicalRecord.getDocument().length);
			response.getOutputStream().close();
				
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
