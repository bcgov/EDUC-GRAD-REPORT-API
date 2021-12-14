package ca.bc.gov.educ.grad.report.utils;

import ca.bc.gov.educ.grad.report.dto.impl.OptionalProgramImpl;
import ca.bc.gov.educ.grad.report.model.graduation.OptionalProgram;
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

public class OptionalProgramListDeserializer extends StdDeserializer<List<OptionalProgram>> {

    protected OptionalProgramListDeserializer() {
        this(null);
    }

    protected OptionalProgramListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<OptionalProgram> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<OptionalProgram> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Iterator<JsonNode> elements = node.elements();
        for (; elements.hasNext();) {
            JsonNode nextNode = elements.next();
            String optionalProgramCode = (String) nextNode.get("optionalProgramCode").asText();
            String optionalProgramName = (String) nextNode.get("optionalProgramName").asText();
            String programCompletionDate = (String) nextNode.get("programCompletionDate").asText();
            String hasRequirementMet = (String) nextNode.get("hasRequirementMet").asText();

            OptionalProgramImpl r = new OptionalProgramImpl();
            r.setOptionalProgramCode(optionalProgramCode);
            r.setOptionalProgramName(optionalProgramName);
            r.setProgramCompletionDate(programCompletionDate);
            r.setHasRequirementMet(hasRequirementMet);
            result.add(r);
        }
        return result;
    }
}
