package CSCI5308.GroupFormationTool.Courses;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import CSCI5308.GroupFormationTool.AccessControl.User;

public class StudentCSVParser implements IStudentCSVParser
{
	private Logger logger = LoggerFactory.getLogger(StudentCSVParser.class);
	private MultipartFile uploadedFile;
	private List<User> studentList = new ArrayList<>(); 

	public StudentCSVParser(MultipartFile file) 
	{
		this.uploadedFile = file;

	}
	
	@Override
	public List<User> parseCSVFile(List<String> failureResults) 
	{
		try
		{
			Reader reader = new InputStreamReader(uploadedFile.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).build();
			List<String[]> records = csvReader.readAll();
			Iterator<String[]> iterator = records.iterator();
			User user;
			while (iterator.hasNext())
			{
				String[] record = iterator.next();
				
				String bannerID = record[0];
				String firstName = record[1];
				String lastName = record[2];
				String email = record[3];
				
				user = new User();
				user.setBannerID(bannerID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				studentList.add(user);
			}
		
		}
		catch (IOException ioException)
		{
			failureResults.add("Failure reading uploaded file: " + ioException.getMessage());
			logger.info("Error Message : {}",ioException.getMessage());
		}
		catch (Exception exception)
		{
			failureResults.add("Failure parsing CSV file: " + exception.getMessage());
			logger.info("Error Message : {}",exception.getMessage());
		}

		return studentList;

	}

}
