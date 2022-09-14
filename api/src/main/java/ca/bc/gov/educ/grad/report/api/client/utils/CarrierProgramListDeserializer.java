package ca.bc.gov.educ.grad.report.api.client.utils;

import ca.bc.gov.educ.grad.report.api.client.CareerProgram;
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

public class CarrierProgramListDeserializer extends StdDeserializer<List<CareerProgram>> {

    protected CarrierProgramListDeserializer() {
        this(null);
    }

    protected CarrierProgramListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<CareerProgram> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<CareerProgram> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Iterator<JsonNode> elements = node.elements();
        for (; elements.hasNext();) {
            JsonNode nextNode = elements.next();
            String programCode = nextNode.get("careerProgramCode").asText("");
            String programName = nextNode.get("careerProgramName").asText("");

            CareerProgram r = new CareerProgram();
            r.setCareerProgramCode(programCode);
            r.setCareerProgramName(programName);

            result.add(r);

        }
        return result;
    }
}
