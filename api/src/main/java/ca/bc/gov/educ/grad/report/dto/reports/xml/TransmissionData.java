package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransmissionData {
	@JacksonXmlProperty(localName = "DocumentID")
	private String DocumentID = UUID.randomUUID().toString();
	@JacksonXmlProperty(localName = "CreatedDateTime")
	private Date CreatedDateTime = new Date();
	@JacksonXmlProperty(localName = "DocumentTypeCode")
	private String DocumentTypeCode = "StudentRequest";
	@JacksonXmlProperty(localName = "TransmissionType")
	private String TransmissionType = "Original";
	@JacksonXmlProperty(localName = "Source")
	private Source Source = new Source();
	@JacksonXmlProperty(localName = "Destination")
	private Destination Destination = new Destination();
	@JacksonXmlProperty(localName = "DocumentProcessCode")
	private String DocumentProcessCode = "Production";
	@JacksonXmlProperty(localName = "DocumentOfficialCode")
	private String DocumentOfficialCode = "Official";
	@JacksonXmlProperty(localName = "DocumentCompleteCode")
	private String DocumentCompleteCode = "Complete";
	@JacksonXmlProperty(localName = "RequestTrackingID")
	private String RequestTrackingID;
}