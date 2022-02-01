package ca.bc.gov.educ.grad.report.api.client.utils;

import ca.bc.gov.educ.grad.report.api.client.AssessmentResult;
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

            String name = (String) nextNode.get("assessmentName").asText("");
            String code = (String) nextNode.get("assessmentCode").asText("");
            String requirementMet = (String) nextNode.get("gradReqMet").asText("");
            String specialCase = (String) nextNode.get("specialCase").asText("");
            String sessionDate = (String) nextNode.get("sessionDate").asText("");
            String exceededWrites = (String) nextNode.get("exceededWriteFlag").asText("");
            Double proficiencyScore = (Double) nextNode.get("proficiencyScore").asDouble(0.0);

            AssessmentResult r = new AssessmentResult();

            r.setAssessmentName(name);
            r.setAssessmentCode(code);
            r.setSessionDate(sessionDate);
            r.setProficiencyScore(proficiencyScore);
            r.setGradReqMet(requirementMet);
            r.setSpecialCase(specialCase);
            r.setExceededWriteFlag(exceededWrites);

            result.add(r);
        }
        return result;
    }
}
