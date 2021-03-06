package ca.bc.gov.educ.grad.report.api.client.utils;

import ca.bc.gov.educ.grad.report.api.client.OtherProgram;
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

public class OtherProgramListDeserializer extends StdDeserializer<List<OtherProgram>> {

    protected OtherProgramListDeserializer() {
        this(null);
    }

    protected OtherProgramListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<OtherProgram> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<OtherProgram> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Iterator<JsonNode> elements = node.elements();
        for (; elements.hasNext();) {
            JsonNode nextNode = elements.next();
            String code = (String) nextNode.get("programCode").asText("");
            String description = (String) nextNode.get("programName").asText("");
            OtherProgram r = new OtherProgram();
            r.setProgramCode(code);
            r.setProgramName(description);
            result.add(r);
        }
        return result;
    }
}
