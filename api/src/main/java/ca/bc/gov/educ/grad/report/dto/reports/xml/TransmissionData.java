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
	@JacksonXmlProperty(localName = "DocumentID")
	private String DocumentID;
	@JacksonXmlProperty(localName = "CreatedDateTime")
	private Date CreatedDateTime;
	@JacksonXmlProperty(localName = "DocumentTypeCode")
	private String DocumentTypeCode;
	@JacksonXmlProperty(localName = "TransmissionType")
	private String TransmissionType;
	@JacksonXmlProperty(localName = "Source")
	private Source Source;
	@JacksonXmlProperty(localName = "Destination")
	private Destination Destination;
	@JacksonXmlProperty(localName = "DocumentProcessCode")
	private String DocumentProcessCode;
	@JacksonXmlProperty(localName = "DocumentOfficialCode")
	private String DocumentOfficialCode;
	@JacksonXmlProperty(localName = "DocumentCompleteCode")
	private String DocumentCompleteCode;
}