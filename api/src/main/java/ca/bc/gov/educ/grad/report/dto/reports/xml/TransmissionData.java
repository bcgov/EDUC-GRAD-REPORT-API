package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransmissionData {
	private String DocumentID;
	private Date CreatedDateTime;
	private String DocumentTypeCode;
	private String TransmissionType;
	@JacksonXmlProperty(localName = "Source")
	private Source Source;
	@JacksonXmlProperty(localName = "Destination")
	private Destination Destination;
	private String DocumentProcessCode;
	private String DocumentOfficialCode;
	private String DocumentCompleteCode;
}