package ca.bc.gov.educ.grad.utils;

import ca.bc.gov.educ.grad.dto.impl.NonGradReasonImpl;
import ca.bc.gov.educ.grad.model.graduation.NonGradReason;
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

public class NonGradReasonListDeserializer extends StdDeserializer<List<NonGradReason>> {

    protected NonGradReasonListDeserializer() {
        this(null);
    }

    protected NonGradReasonListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<NonGradReason> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<NonGradReason> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Iterator<JsonNode> elements = node.elements();
        for (; elements.hasNext();) {
            JsonNode nextNode = elements.next();
            String code = (String) nextNode.get("code").asText();
            String description = (String) nextNode.get("description").asText();
            NonGradReasonImpl r = new NonGradReasonImpl();
            r.setCode(code);
            r.setDescription(description);
            result.add(r);
        }
        return result;
    }
}
