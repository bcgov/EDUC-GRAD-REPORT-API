package ca.bc.gov.educ.grad.report.api.client.utils;

import ca.bc.gov.educ.grad.report.api.client.AchievementCourse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AchievementCourseListDeserializer extends StdDeserializer<List<AchievementCourse>> {

    protected AchievementCourseListDeserializer() {
        this(null);
    }

    protected AchievementCourseListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<AchievementCourse> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<AchievementCourse> result = new ArrayList<>();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Iterator<JsonNode> elements = node.elements();
        for (; elements.hasNext();) {
            JsonNode nextNode = elements.next();
            String courseName = (String) nullSafeString(nextNode.get("courseName")).asText("");
            String courseCode = (String) nullSafeString(nextNode.get("courseCode")).asText("");
            String courseLevel = (String) nullSafeString(nextNode.get("courseLevel")).asText("");
            String sessionDate = (String) nullSafeString(nextNode.get("sessionDate")).asText("");
            String gradReqMet = (String) nullSafeString(nextNode.get("gradReqMet")).asText("");
            String completedCoursePercentage = (String) nullSafeString(nextNode.get("completedCoursePercentage")).asText("");
            String completedCourseLetterGrade = (String) nullSafeString(nextNode.get("completedCourseLetterGrade")).asText("");
            String interimPercent = (String) nullSafeString(nextNode.get("interimPercent")).asText("");
            String equivOrChallenge = (String) nullSafeString(nextNode.get("equivOrChallenge")).asText("");
            Integer creditsUsedForGrad = (Integer) nullSafeInteger(nextNode.get("creditsUsedForGrad")).asInt(0);
            AchievementCourse r = new AchievementCourse(
                    courseName,
                    courseCode,
                    courseLevel,
                    sessionDate,
                    gradReqMet,
                    completedCoursePercentage,
                    completedCourseLetterGrade,
                    interimPercent,
                    equivOrChallenge,
                    creditsUsedForGrad
            );
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
}
