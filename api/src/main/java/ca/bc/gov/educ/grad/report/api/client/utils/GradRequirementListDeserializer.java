package ca.bc.gov.educ.grad.report.api.client.utils;

import ca.bc.gov.educ.grad.report.api.client.GradRequirement;
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

public class GradRequirementListDeserializer extends StdDeserializer<List<GradRequirement>> {

    protected GradRequirementListDeserializer() {
        this(null);
    }

    protected GradRequirementListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<GradRequirement> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<GradRequirement> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Iterator<JsonNode> elements = node.elements();
        for (; elements.hasNext();) {
            JsonNode nextNode = elements.next();
            String code = (String) nextNode.get("code").asText();
            String description = (String) nextNode.get("description").asText();
            GradRequirement r = new GradRequirement();
            r.setCode(code);
            r.setDescription(description);
            result.add(r);
        }
        return result;
    }
}
