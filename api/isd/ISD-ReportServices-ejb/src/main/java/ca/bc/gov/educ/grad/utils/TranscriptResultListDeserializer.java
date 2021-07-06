package ca.bc.gov.educ.grad.utils;

import ca.bc.gov.educ.isd.transcript.TranscriptResult;
import ca.bc.gov.educ.isd.transcript.impl.CourseImpl;
import ca.bc.gov.educ.isd.transcript.impl.MarkImpl;
import ca.bc.gov.educ.isd.transcript.impl.TranscriptResultImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TranscriptResultListDeserializer extends StdDeserializer<List<TranscriptResult>> {

    protected TranscriptResultListDeserializer() {
        this(null);
    }

    protected TranscriptResultListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<TranscriptResult> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<TranscriptResult> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Iterator<JsonNode> elements = node.elements();
        for (; elements.hasNext();) {
            JsonNode nextNode = elements.next();

            JsonNode courseNode = nextNode.get("course");
            CourseImpl course = new CourseImpl();
            course.setCode(courseNode.get("code").asText());
            course.setCredits(courseNode.get("credits").asText());
            course.setName(courseNode.get("name").asText());
            course.setLevel(courseNode.get("level").asText());
            course.setSessionDate(courseNode.get("sessionDate").asText());
            course.setType(courseNode.get("type").asText());
            course.setRelatedCourse(courseNode.get("relatedCourse").asText());
            course.setRelatedLevel(courseNode.get("relatedLevel").asText());

            JsonNode markNode = nextNode.get("mark");
            MarkImpl mark = new MarkImpl();
            mark.setSchoolPercent(markNode.get("schoolPercent").asText());
            mark.setExamPercent(markNode.get("examPercent").asText());
            mark.setFinalPercent(markNode.get("finalPercent").asText());
            mark.setFinalLetterGrade(markNode.get("finalLetterGrade").asText());
            mark.setInterimPercent(markNode.get("interimPercent").asText());
            mark.setInterimLetterGrade(markNode.get("interimLetterGrade").asText());

            String requirement = (String) nextNode.get("requirement").asText();
            String requirementName = (String) nextNode.get("requirementName").asText();
            String equivalency = (String) nextNode.get("equivalency").asText();
            String usedForGrad = (String) nextNode.get("usedForGrad").asText();

            TranscriptResultImpl r = new TranscriptResultImpl();
            r.setCourse(course);
            r.setMark(mark);
            r.setEquivalencyChallenge(equivalency);
            r.setRequirementMet(requirement);
            r.setRequirementMetName(requirementName);
            r.setUsedForGrad(usedForGrad);

            result.add(r);
        }
        return result;
    }
}
