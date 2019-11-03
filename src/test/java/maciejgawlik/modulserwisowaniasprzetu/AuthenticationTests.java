package maciejgawlik.modulserwisowaniasprzetu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.comment.DeviceCommentDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:sql/authentication-tests.sql")
public class AuthenticationTests {

    private static RequestBuilder GET_REQUEST;
    private static RequestBuilder POST_REQUEST;
    private static RequestBuilder PUT_REQUEST;
    private static RequestBuilder DELETE_REQUEST;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void init() throws JsonProcessingException {
        GET_REQUEST = get("/device/all");

        POST_REQUEST = post("/device/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new DeviceCommentDto(2L, "Comment content", 1L)));

        PUT_REQUEST = put("/device/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new DeviceCommentDto(1L, "Comment content", 1L)));

        DELETE_REQUEST = delete("/device/comment/1");
    }

    @Test
    @WithAnonymousUser
    public void anonymousUserCannotUseAnyMethod() throws Exception {
        //when
        Map<String, Integer> responseStatusPerMethod = createMapOfResponseStatusPerMethod();

        //then
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseStatusPerMethod.get("GET"));
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseStatusPerMethod.get("POST"));
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseStatusPerMethod.get("PUT"));
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseStatusPerMethod.get("DELETE"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void loggedUserCanUseOnlyGetAndPost() throws Exception {
        //when
        Map<String, Integer> responseStatusPerMethod = createMapOfResponseStatusPerMethod();

        //then
        assertEquals(HttpStatus.OK.value(), responseStatusPerMethod.get("GET"));
        assertEquals(HttpStatus.CREATED.value(), responseStatusPerMethod.get("POST"));
        assertEquals(HttpStatus.FORBIDDEN.value(), responseStatusPerMethod.get("PUT"));
        assertEquals(HttpStatus.FORBIDDEN.value(), responseStatusPerMethod.get("DELETE"));
    }

    @Test
    @WithMockUser(roles = {"USER","ADMIN"})
    public void adminCanUseAllMethods() throws Exception {
        //when
        Map<String, Integer> responseStatusPerMethod = createMapOfResponseStatusPerMethod();

        //then
        assertEquals(HttpStatus.OK.value(), responseStatusPerMethod.get("GET"));
        assertEquals(HttpStatus.CREATED.value(), responseStatusPerMethod.get("POST"));
        assertEquals(HttpStatus.CREATED.value(), responseStatusPerMethod.get("PUT"));
        assertEquals(HttpStatus.OK.value(), responseStatusPerMethod.get("DELETE"));
    }

    private Map<String, Integer> createMapOfResponseStatusPerMethod() throws Exception {
        return new HashMap<String, Integer>() {{
            put("GET", getResponseStatus(GET_REQUEST));
            put("POST", getResponseStatus(POST_REQUEST));
            put("PUT", getResponseStatus(PUT_REQUEST));
            put("DELETE", getResponseStatus(DELETE_REQUEST));
        }};
    }

    private int getResponseStatus(RequestBuilder request) throws Exception {
        return mockMvc.perform(request)
                .andReturn()
                .getResponse()
                .getStatus();
    }

}