package com.sensiblemetrics.api.sqoola.common.serializer;

@JsonTest
@RunWith(SpringRunner.class)
public class UserJsonDeserializerTest {
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    public void testDeserialize() throws IOException {
        String json = "{\"favoriteColor\":\"#f0f8ff\"}"
        User user = objectMapper.readValue(json, User.class);
  
        assertEquals(Color.ALICEBLUE, user.getFavoriteColor());
    }
}
