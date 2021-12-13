package ca.bc.gov.educ.grad.report.utils;

import ca.bc.gov.educ.grad.report.dto.impl.AssessmentResultImpl;
import ca.bc.gov.educ.grad.report.model.assessment.AssessmentResult;
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

public class AssessmentResultListDeserializer extends StdDeserializer<List<AssessmentResult>> {

    protected AssessmentResultListDeserializer() {
        this(null);
    }

    protected AssessmentResultListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<AssessmentResult> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<AssessmentResult> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Iterator<JsonNode> elements = node.elements();
        for (; elements.hasNext();) {
            JsonNode nextNode = elements.next();

            String name = (String) nextNode.get("name").asText();
            String code = (String) nextNode.get("code").asText();
            String requirementMet = (String) nextNode.get("requirementMet").asText();
            String specialCase = (String) nextNode.get("specialCase").asText();
            String sessionDate = (String) nextNode.get("sessionDate").asText();
            String exceededWrites = (String) nextNode.get("exceededWrites").asText();
            Integer proficiencyScore = (Integer) nextNode.get("proficiencyScore").asInt();

            AssessmentResultImpl r = new AssessmentResultImpl();

            r.setAssessmentName(name);
            r.setAssessmentCode(code);
            r.setAssessmentSession(sessionDate);
            r.setAssessmentProficiencyScore(proficiencyScore);
            r.setRequirementMet(requirementMet);
            r.setSpecialCase(specialCase);
            r.setExceededWrites(exceededWrites);

            result.add(r);
        }
        return result;
    }
}
