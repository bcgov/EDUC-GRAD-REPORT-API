package ca.bc.gov.educ.grad.report.api.client.utils;

import ca.bc.gov.educ.grad.report.api.client.AssessmentResult;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

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

            String name = (String) nullSafeString(nextNode.get("assessmentName")).asText("");
            String code = (String) nullSafeString(nextNode.get("assessmentCode")).asText("");
            String requirementMet = (String) nullSafeString(nextNode.get("gradReqMet")).asText("");
            String specialCase = (String) nullSafeString(nextNode.get("specialCase")).asText("");
            String sessionDate = (String) nullSafeString(nextNode.get("sessionDate")).asText("");
            String exceededWrites = (String) nullSafeString(nextNode.get("exceededWriteFlag")).asText("");
            String proficiencyScore = (String) nullSafeString(nextNode.get("proficiencyScore")).asText("");
            Boolean projected = (Boolean) nullSafeBoolean(nextNode.get("projected")).asBoolean(false);

            AssessmentResult r = new AssessmentResult();

            r.setAssessmentName(name);
            r.setAssessmentCode(code);
            r.setSessionDate(sessionDate);
            r.setProficiencyScore(proficiencyScore);
            r.setGradReqMet(requirementMet);
            r.setSpecialCase(specialCase);
            r.setExceededWriteFlag(exceededWrites);
            r.setProjected(projected);

            result.add(r);
        }
        return result;
    }

    private JsonNode nullSafeString(final JsonNode s) {
        return s == null ? new TextNode("") : s;
    }

    private JsonNode nullSafeInteger(final JsonNode s) {
        return s == null ? new IntNode(0) : s;
    }

    private JsonNode nullSafeBoolean(final JsonNode s) {
        return s == null ? BooleanNode.FALSE : s;
    }
}
