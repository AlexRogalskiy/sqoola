package com.sensiblemetrics.api.sqoola.common.serializer;

@JsonTest
@RunWith(SpringRunner.class)
public class UserJsonSerializerTest {
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    public void testSerialization() throws JsonProcessingException {
        User user = new User(Color.ALICEBLUE);
        String json = objectMapper.writeValueAsString(user);
  
        assertEquals("{\"favoriteColor\":\"#f0f8ff\"}", json);
    }
}
