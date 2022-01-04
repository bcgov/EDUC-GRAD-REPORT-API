package ca.bc.gov.educ.grad.report.api.client.utils;

import ca.bc.gov.educ.grad.report.api.client.AchievementCourse;
import ca.bc.gov.educ.grad.report.api.client.GradRequirement;
import ca.bc.gov.educ.grad.report.api.client.NonGradReason;
import ca.bc.gov.educ.grad.report.api.client.OptionalProgram;
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

            OptionalProgram r = new OptionalProgram();
            r.setOptionalProgramCode(optionalProgramCode);
            r.setOptionalProgramName(optionalProgramName);
            r.setProgramCompletionDate(programCompletionDate);
            r.setHasRequirementMet(hasRequirementMet);

            List<GradRequirement> gradRequirements = new ArrayList();
            JsonNode gradRequirementNode = nextNode.get("requirementMet");
            Iterator<JsonNode> gradRequirementNodeElements = gradRequirementNode.elements();
            for (; gradRequirementNodeElements.hasNext();) {
                JsonNode gradRequirementNextNode = gradRequirementNodeElements.next();
                String gradRequirementCode = gradRequirementNextNode.get("code").asText("");
                String gradRequirementDescription = gradRequirementNextNode.get("description").asText("");
                GradRequirement gradRequirement = new GradRequirement();
                gradRequirement.setCode(gradRequirementCode);
                gradRequirement.setDescription(gradRequirementDescription);

                List<AchievementCourse> courseDetails = new ArrayList<>();
                JsonNode courseDetailsNode = gradRequirementNextNode.get("courseDetails");
                Iterator<JsonNode> courseDetailsNodeElements = courseDetailsNode.elements();
                for (; courseDetailsNodeElements.hasNext();) {
                    JsonNode courseDetailsNextNode = courseDetailsNodeElements.next();
                    String courseCode = courseDetailsNextNode.get("courseCode").asText("");
                    String courseLevel = courseDetailsNextNode.get("courseLevel").asText("");
                    String sessionDate = courseDetailsNextNode.get("sessionDate").asText("");
                    AchievementCourse achievementCourse = new AchievementCourse(courseCode, courseLevel, sessionDate);

                    courseDetails.add(achievementCourse);

                }
                gradRequirement.setCourseDetails(courseDetails);

                gradRequirements.add(gradRequirement);
            }
            r.setRequirementMet(gradRequirements);

            List<NonGradReason> nonGradReasons = new ArrayList();
            JsonNode nonGradReasonsNode = nextNode.get("nonGradReasons");
            Iterator<JsonNode> nonGradReasonsNodeElements = nonGradReasonsNode.elements();
            for (; nonGradReasonsNodeElements.hasNext();) {
                JsonNode nonGradReasonsNextNode = nonGradReasonsNodeElements.next();
                String nonGradReasonCode = nonGradReasonsNextNode.get("code").asText("");
                String nonGradReasonDescription = nonGradReasonsNextNode.get("description").asText("");
                NonGradReason nonGradReason = new NonGradReason();
                nonGradReason.setCode(nonGradReasonCode);
                nonGradReason.setDescription(nonGradReasonDescription);
                nonGradReasons.add(nonGradReason);
            }
            r.setNonGradReasons(nonGradReasons);

            result.add(r);
        }
        return result;
    }
}
